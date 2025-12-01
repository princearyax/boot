package com.arya.taskmanager;

import java.util.Scanner;

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
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
