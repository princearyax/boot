package com.arya.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//dto: defines only what to accept from users
//declare an immutable data class, private final fields where Java automatically generates fields, constructor, getters, equals(), hashCode(), and toString()
public record EmployeeRequestDto(
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 50 ,message = "size must be between 3 and 50")
    String name,

    @NotBlank(message = "Email must not be empty")
    @Email
    String email,

    @NotBlank(message = "Department is mandatory")
    String departrment
) {}
