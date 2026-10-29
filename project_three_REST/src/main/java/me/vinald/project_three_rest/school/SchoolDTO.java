package me.vinald.project_three_rest.school;

import jakarta.validation.constraints.NotBlank;

public record SchoolDTO(
		@NotBlank(message = "School name cannot be blank")
		String name
) {
}
