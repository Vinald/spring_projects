package me.vinald.project_four_jpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LectureRequest(
		@NotBlank @Size(max = 200) String name
) {
}
