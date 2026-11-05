package me.vinald.project_four_jpa.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * One request shape for every resource type. Only the field matching {@code type} is used:
 * {@code length} for VIDEO, {@code content} for TEXT, {@code fileType} for FILE.
 */
public record ResourceRequest(
		@NotNull ResourceType type,
		@NotBlank @Size(max = 200) String name,
		String size,
		String url,
		@Min(0) Integer length,
		String content,
		String fileType
) {
}
