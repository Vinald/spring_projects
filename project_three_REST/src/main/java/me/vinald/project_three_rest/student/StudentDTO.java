package me.vinald.project_three_rest.student;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record StudentDTO(
		@NotEmpty(message = "First name cannot be empty")
		String firstName,
		@NotEmpty(message = "Last name cannot be empty")
		String lastName,
		@Email(message = "Email should be valid")
		@NotEmpty(message = "Email cannot be empty")
		String email,
		Integer age,
		String gender,
		Integer schoolId
) {

}
