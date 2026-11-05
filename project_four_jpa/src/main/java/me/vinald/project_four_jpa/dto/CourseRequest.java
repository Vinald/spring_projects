package me.vinald.project_four_jpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CourseRequest(
		@NotBlank @Size(max = 200) String title,
		@Size(max = 2000) String description,
		List<Integer> authorIds
) {
}
