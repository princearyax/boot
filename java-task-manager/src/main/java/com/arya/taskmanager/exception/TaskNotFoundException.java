package com.arya.taskmanager.exception;

/**
 * This is a custom UNCHECKED exception. //optional to handle
 * It extends RuntimeException, so we are not forced
 * to catch it everywhere, only where it makes sense.
 * This is for "exceptional" but "recoverable" business errors.
 */
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(long id) {
        super("Task not found with ID: " + id);
    }
}