package me.vinald.project_four_jpa.mappers;

import me.vinald.project_four_jpa.dto.CourseResponse;
import me.vinald.project_four_jpa.dto.SectionSummary;
import me.vinald.project_four_jpa.models.Course;

import java.util.Comparator;
import java.util.List;

public final class CourseMapper {
	private CourseMapper() {
	}

	public static CourseResponse toResponse(Course course) {
		return new CourseResponse(
				course.getId(),
				course.getTitle(),
				course.getDescription(),
				MapperUtils.nullSafe(course.getAuthors()).stream().map(AuthorMapper::toSummary).toList(),
				sections(course),
				course.getCreatedAt(),
				course.getLastModifiedAt()
		);
	}

	private static List<SectionSummary> sections(Course course) {
		return MapperUtils.nullSafe(course.getSections()).stream()
				.sorted(Comparator.comparing(s -> s.getSectionOrder(), Comparator.nullsLast(Comparator.naturalOrder())))
				.map(SectionMapper::toSummary)
				.toList();
	}
}
