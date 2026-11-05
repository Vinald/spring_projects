package me.vinald.project_four_jpa.mappers;

import me.vinald.project_four_jpa.dto.LectureSummary;
import me.vinald.project_four_jpa.dto.SectionRequest;
import me.vinald.project_four_jpa.dto.SectionResponse;
import me.vinald.project_four_jpa.dto.SectionSummary;
import me.vinald.project_four_jpa.models.Section;

public final class SectionMapper {
	private SectionMapper() {
	}

	public static SectionResponse toResponse(Section section) {
		return new SectionResponse(
				section.getId(),
				section.getName(),
				section.getSectionOrder(),
				section.getCourse() != null ? section.getCourse().getId() : null,
				MapperUtils.nullSafe(section.getLectures()).stream()
						.map(l -> new LectureSummary(l.getId(), l.getName()))
						.toList(),
				section.getCreatedAt(),
				section.getLastModifiedAt()
		);
	}

	public static SectionSummary toSummary(Section section) {
		return new SectionSummary(section.getId(), section.getName(), section.getSectionOrder());
	}

	public static void apply(SectionRequest request, Section section) {
		section.setName(request.name());
		section.setSectionOrder(request.sectionOrder());
	}
}
