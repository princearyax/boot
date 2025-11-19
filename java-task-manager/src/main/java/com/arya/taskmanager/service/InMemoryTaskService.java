package com.arya.taskmanager.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import com.arya.taskmanager.model.Task;
import com.arya.taskmanager.model.TaskStatus;

/**
 * An in-memory implementation of the TaskService.
 * This class demonstrates:
 * 1. Implementing an interface.
 * 2. Using Collections (Map). We use ConcurrentHashMap for thread-safety.
 * 3. Using Java 8 Streams (filter, map, collect).
 * 4. Using Java 8 Optional.
 * 5. Using AtomicLong for a thread-safe ID generator.
 */
public class InMemoryTaskService implements TaskService{
    // Using a Map for efficient O(1) average-time lookups by ID.
    private final ConcurrentHashMap<Long, Task> taskStore = new ConcurrentHashMap<>();
    // Using AtomicLong for a thread-safe way to generate unique IDs.
    private final AtomicLong idGenerator = new AtomicLong(1); 

    @Override
    public Task createTask(String title, String description, LocalDate dueDate){
        long newId = idGenerator.getAndIncrement();
        Task newTask = new Task(newId, title, description, dueDate);
        taskStore.put(newId, newTask);
        return newTask;
    }
    
    @Override
    public Optional<Task> getTaskById(long id) {
        // .ofNullable() creates an Optional that is empty if taskStore.get(id) is null,
        // or contains the task if it's not null.
        return Optional.ofNullable(taskStore.get(id));
    }

    @Override
    public List<Task> getAllTasks() {
        // We get the collection of values (all tasks) from the map,
        // turn it into a stream, and collect it into a List.
        return taskStore.values().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskStore.values().stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateTaskStatus(long id, TaskStatus newStatus) {
        Task task = taskStore.get(id);
        if(task!=null){
            task.setStatus(newStatus);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean deleteTask(long id) {
        return taskStore.remove(id) != null;
    }
}
