package me.vinald.project_four_jpa.dto;

import java.time.LocalDateTime;

public record LectureResponse(
		Integer id,
		String name,
		Integer sectionId,
		ResourceResponse resource,
		LocalDateTime createdAt,
		LocalDateTime lastModifiedAt
) {
}
