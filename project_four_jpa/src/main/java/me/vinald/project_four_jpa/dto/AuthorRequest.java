package me.vinald.project_four_jpa.dto;

import jakarta.validation.constraints.*;

public record AuthorRequest(
		@NotBlank @Size(max = 100) String firstName,
		@NotBlank @Size(max = 100) String lastName,
		@NotBlank @Email String email,
		@Min(0) @Max(150) Integer age
) {
}
