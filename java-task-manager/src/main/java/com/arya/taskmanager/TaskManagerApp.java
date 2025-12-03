package com.arya.taskmanager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.arya.taskmanager.exception.TaskNotFoundException;
import com.arya.taskmanager.model.Task;
import com.arya.taskmanager.model.TaskStatus;
import com.arya.taskmanager.service.InMemoryTaskService;
import com.arya.taskmanager.service.TaskService;

/**
 * This is the main entry point for our command-line application.
 * It demonstrates:
 * 1. Composition: It "has-a" TaskService. It is not a TaskService.
 * 2. Manual Dependency Injection: We create the service (the "dependency")
 * and "inject" it into the app's constructor.
 * 3. try-with-resources: The Scanner is created in a try block,
 * so it is automatically closed.
 * 4. Exception Handling: We catch our custom TaskNotFoundException
 * and other parse exceptions.
 */
public class TaskManagerApp 
{

    // The App "has-a" dependency on the TaskService contract (interface)
    private final TaskService taskService;
    private final Scanner scanner;
    
    // We "inject" the dependency via the constructor
    public TaskManagerApp(TaskService taskService){
        this.taskService = taskService;
        this.scanner = new Scanner(System.in);
    }

    public static void main( String[] args )
    {
        // 1. Create the concrete implementation (our dependency)
        TaskService service = new InMemoryTaskService();

        // 2. Inject it into our application
        TaskManagerApp app = new TaskManagerApp(service);

        app.run();
    }

    /**
     * The main application loop.
     */
    public void run(){
        // This is a "try-with-resources" block.
        // The Scanner will be automatically closed when the loop exits.
        try (Scanner appScanner = this.scanner) {
            boolean isRunning = true;
            while (isRunning) {
                printMenu();
                String command = scanner.nextLine().trim();

                try {
                    switch (command) {
                        case "1":
                            handleCreateTask();
                            break;
                        case "2":
                            handleViewTask();
                            break;
                        case "3":
                            handleViewAllTasks();
                            break;
                        case "4":
                            handleUpdateTaskStatus();
                            break;
                        case "5":
                            handleDeleteTask();
                            break;
                        case "6":
                            handleViewByStatus();
                            break;
                        case "7":
                            handleArchiveTask();
                            break;
                        case "8":
                            isRunning = false;
                            System.out.println("Exiting application. bye!");
                            break;
                        default:
                            System.out.println("Invalid command. Please try again.");
                            // break;
                    }
                } catch (TaskNotFoundException e) {
                    // Handle our custom exception
                    System.err.println("ERROR: " + e.getMessage());
                }catch (DateTimeParseException e) {
                    System.err.println("ERROR: Invalid date format. Use YYYY-MM-DD.");
                } catch (IllegalArgumentException e) {
                    System.err.println("ERROR: Invalid status. Use PENDING, IN_PROGRESS, or COMPLETED.");
                } catch (Exception e) {
                    // Catch any other unexpected errors
                    System.err.println("An unexpected error occurred: " + e.getMessage());
                }
                
            }
        }
        
    }

    private void printMenu() {
        System.out.println("\n--- Java Task Manager ---");
        System.out.println("1. Create New Task");
        System.out.println("2. View Task by ID");
        System.out.println("3. View All Tasks");
        System.out.println("4. Update Task Status");
        System.out.println("5. Delete Task");
        System.out.println("6. View Tasks by Status");
        System.out.println("7. Archive Task");
        System.out.println("8. Exit");
        System.out.print("Enter command: ");
    }

    private void handleCreateTask(){
        System.out.print("Enter the title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter Description: ");
        String desc = scanner.nextLine().trim();
        System.out.print("Enter Due Date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine().trim());
        
        Task newTask = taskService.createTask(title, desc, dueDate);
        System.out.println("SUCCESS: Created Task #" + newTask.getId());
        System.out.println(newTask);
    }

    private void handleViewTask(){
        System.out.print("Enter the id: ");
        Long id = Long.parseLong(scanner.nextLine().trim());

        // We use the Optional returned by the service
        Task task = taskService.getTaskById(id)
                        .orElseThrow(() -> new TaskNotFoundException(id)); //throws if empty
        System.out.println("Found Task:");
        System.out.println(task);
    }

    private void handleViewAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("All Tasks:");
            tasks.forEach(System.out::println); // Java 8 method reference
        }
    }

    private void handleUpdateTaskStatus(){
        System.out.print("Enter the Task id to update: ");
        Long id = Long.parseLong(scanner.nextLine().trim());
        System.out.print("Enter New Status (PENDING, IN_PROGRESS, COMPLETED): ");
        TaskStatus status = TaskStatus.valueOf(scanner.nextLine().trim().toUpperCase());

        // We use the standard boolean method from the interface
        boolean updated = taskService.updateTaskStatus(id, status);

        if(updated){
            // Fetch the updated task to show the user the result
            Task updatedTask = taskService.getTaskById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
            System.out.println("SUCCESS: Updated Task #" + updatedTask.getId());
            System.out.println(updatedTask);
        }else{
            // If the boolean was false, it means the ID wasn't found
            throw new TaskNotFoundException(id);
        }
    }

    private void handleDeleteTask(){
        System.out.print("Enter Task ID to delete: ");
        long id = Long.parseLong(scanner.nextLine().trim());

        if (taskService.deleteTask(id)) {
            System.out.println("SUCCESS: Deleted Task #" + id);
        } else {
            // We can throw here too
            throw new TaskNotFoundException(id);
        }
    }

    private void handleViewByStatus(){
        System.out.print("Enter Status (PENDING, IN_PROGRESS, COMPLETED, ARCHIVED): ");
        TaskStatus status = TaskStatus.valueOf(scanner.nextLine().trim().toUpperCase());

        List<Task> tasks = taskService.getTasksByStatus(status);
        if (tasks.isEmpty()) {
            System.out.println("No tasks found with status " + status);
        } else {
            System.out.println("Tasks with status " + status + ":");
            tasks.forEach(System.out::println);
        }
    }

    private void handleArchiveTask() {
    System.out.print("Enter Task ID to archive: ");
    long id = Long.parseLong(scanner.nextLine().trim());

    // 1. Use the Interface method. It returns true/false.
    boolean isUpdated = taskService.updateTaskStatus(id, TaskStatus.ARCHIVED);

    if (isUpdated) {
        // 2. If true, fetch the task to show the user the result
        Task archivedTask = taskService.getTaskById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        
        System.out.println("SUCCESS: Archived Task #" + archivedTask.getId());
        System.out.println(archivedTask);
    } else {
        // 3. If false, the ID didn't exist. Throw exception to be caught by the main loop.
        throw new TaskNotFoundException(id);
    }
}
}
