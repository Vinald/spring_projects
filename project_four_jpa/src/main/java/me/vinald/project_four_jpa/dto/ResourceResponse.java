package me.vinald.project_four_jpa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResourceResponse(
		Integer id,
		ResourceType type,
		String name,
		String size,
		String url,
		Integer lectureId,
		Integer length,
		String content,
		String fileType,
		LocalDateTime createdAt,
		LocalDateTime lastModifiedAt
) {
}
