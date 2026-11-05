package me.vinald.project_four_jpa.dto;

import java.time.LocalDateTime;

public record AuthorResponse(
		Integer id,
		String firstName,
		String lastName,
		String email,
		Integer age,
		LocalDateTime createdAt,
		LocalDateTime lastModifiedAt
) {
}
