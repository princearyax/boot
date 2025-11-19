package com.arya.taskmanager.model;
/**
 * Represents the possible statuses of a task.
 * Using an enum is much safer and clearer than using Strings or ints.
 */
public enum TaskStatus{
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    ARCHIVED
}