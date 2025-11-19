package com.arya.taskmanager.model;

import java.time.LocalDate;

/**
 * Represents a single Task. This is our core "model" or "domain" object.
 * It is a POJO (Plain Old Java Object).
 * Its fields are private (Encapsulation).
 */
public class Task {

    private long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate dueDate;

    // A constructor for creating a new task
    public Task(long id, String title, String description, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = TaskStatus.PENDING; // Default status
    }

    // Getters and Setters provide controlled access to the private fields.
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        // A nice string representation for printing
        return "Task #" + id +
                " [" + status + "]" +
                " - " + title +
                " (Due: " + dueDate + ")\n" +
                "  Desc: " + description;
    }
}