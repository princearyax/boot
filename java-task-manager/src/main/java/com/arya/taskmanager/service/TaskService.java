package com.arya.taskmanager.service;

import com.arya.taskmanager.model.Task;
import com.arya.taskmanager.model.TaskStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * This is the Service Interface. It defines the "contract" for
 * all task-related business logic.
 *
 * We code against this interface, not the implementation.
 * This allows us to easily swap out the implementation later
 * (e.g., from in-memory to a database) without changing
 * the rest of our application.
 */
public interface TaskService {

    /**
     * Creates a new task and returns the created task (with its new ID).
     */
    Task createTask(String title, String description, LocalDate dueDate);

    /**
     * Retrieves a task by its ID.
     * Uses Optional<Task> to handle the case where a task is not found.
     * This is a core Java 8+ feature.
     */
    Optional<Task> getTaskById(long id);

    /**
     * Retrieves all tasks.
     * Uses Java Collections (List).
     */
    List<Task> getAllTasks();

    /**
     * Retrieves all tasks that match a specific status.
     * Demonstrates filtering logic.
     */
    List<Task> getTasksByStatus(TaskStatus status);

    /**
     * Updates the status of an existing task.
     * Returns 'true' if the update was successful, 'false' if not found.
     */
    boolean updateTaskStatus(long id, TaskStatus newStatus);

    /**
     * Deletes a task by its ID.
     * Returns 'true' if the delete was successful, 'false' if not found.
     */
    boolean deleteTask(long id);
}