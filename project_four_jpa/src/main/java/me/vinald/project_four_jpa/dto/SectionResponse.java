package me.vinald.project_four_jpa.dto;

import java.time.LocalDateTime;
import java.util.List;

public record SectionResponse(
		Integer id,
		String name,
		Integer sectionOrder,
		Integer courseId,
		List<LectureSummary> lectures,
		LocalDateTime createdAt,
		LocalDateTime lastModifiedAt
) {
}
