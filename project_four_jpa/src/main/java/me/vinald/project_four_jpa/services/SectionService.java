package me.vinald.project_four_jpa.services;

import me.vinald.project_four_jpa.dto.SectionRequest;
import me.vinald.project_four_jpa.dto.SectionResponse;
import me.vinald.project_four_jpa.exceptions.ConflictException;
import me.vinald.project_four_jpa.exceptions.NotFoundException;
import me.vinald.project_four_jpa.mappers.SectionMapper;
import me.vinald.project_four_jpa.models.Course;
import me.vinald.project_four_jpa.models.Section;
import me.vinald.project_four_jpa.repositories.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SectionService {
	private final SectionRepository sectionRepository;
	private final CourseService courseService;

	@Transactional(readOnly = true)
	public List<SectionResponse> findByCourse(Integer courseId) {
		courseService.getCourse(courseId);
		return sectionRepository.findByCourseIdOrderBySectionOrderAsc(courseId).stream()
				.map(SectionMapper::toResponse)
				.toList();
	}

	@Transactional(readOnly = true)
	public SectionResponse findById(Integer id) {
		return SectionMapper.toResponse(getSection(id));
	}

	public SectionResponse create(Integer courseId, SectionRequest request) {
		Course course = courseService.getCourse(courseId);
		Section section = new Section();
		SectionMapper.apply(request, section);
		section.setCourse(course);
		return SectionMapper.toResponse(sectionRepository.save(section));
	}

	public SectionResponse update(Integer id, SectionRequest request) {
		Section section = getSection(id);
		SectionMapper.apply(request, section);
		return SectionMapper.toResponse(section);
	}

	public void delete(Integer id) {
		Section section = getSection(id);
		if (section.getLectures() != null && !section.getLectures().isEmpty()) {
			throw new ConflictException("Section " + id + " still has lectures; delete them first");
		}
		sectionRepository.delete(section);
	}

	Section getSection(Integer id) {
		return sectionRepository.findById(id).orElseThrow(() -> new NotFoundException("Section", id));
	}
}
