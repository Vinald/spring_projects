package me.vinald.project_three_rest.student;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record StudentDTO(
		@NotEmpty(message = "First name cannot be empty")
		String firstName,
		@NotEmpty(message = "Last name cannot be empty")
		String lastName,
		@Email(message = "Email should be valid")
		@NotEmpty(message = "Email cannot be empty")
		String email,
		@PositiveOrZero(message = "Age must be a positive number or zero")
		Integer age,
		String gender,
		@NotNull(message = "School id is required")
		Integer schoolId
) {

}
