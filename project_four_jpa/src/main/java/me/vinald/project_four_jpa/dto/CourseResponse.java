package me.vinald.project_four_jpa.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CourseResponse(
		Integer id,
		String title,
		String description,
		List<AuthorSummary> authors,
		List<SectionSummary> sections,
		LocalDateTime createdAt,
		LocalDateTime lastModifiedAt
) {
}
