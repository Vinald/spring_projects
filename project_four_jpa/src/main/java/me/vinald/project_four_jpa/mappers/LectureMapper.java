package me.vinald.project_four_jpa.mappers;

import me.vinald.project_four_jpa.dto.LectureResponse;
import me.vinald.project_four_jpa.models.Lecture;

public final class LectureMapper {
	private LectureMapper() {
	}

	public static LectureResponse toResponse(Lecture lecture) {
		return new LectureResponse(
				lecture.getId(),
				lecture.getName(),
				lecture.getSection() != null ? lecture.getSection().getId() : null,
				lecture.getResource() != null ? ResourceMapper.toResponse(lecture.getResource()) : null,
				lecture.getCreatedAt(),
				lecture.getLastModifiedAt()
		);
	}
}
