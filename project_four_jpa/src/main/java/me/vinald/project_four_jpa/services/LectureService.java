package me.vinald.project_four_jpa.services;

import me.vinald.project_four_jpa.dto.LectureRequest;
import me.vinald.project_four_jpa.dto.LectureResponse;
import me.vinald.project_four_jpa.exceptions.ConflictException;
import me.vinald.project_four_jpa.exceptions.NotFoundException;
import me.vinald.project_four_jpa.mappers.LectureMapper;
import me.vinald.project_four_jpa.models.Lecture;
import me.vinald.project_four_jpa.models.Resource;
import me.vinald.project_four_jpa.models.Section;
import me.vinald.project_four_jpa.repositories.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureService {
	private final LectureRepository lectureRepository;
	private final SectionService sectionService;
	private final ResourceService resourceService;

	@Transactional(readOnly = true)
	public List<LectureResponse> findBySection(Integer sectionId) {
		sectionService.getSection(sectionId);
		return lectureRepository.findBySectionId(sectionId).stream().map(LectureMapper::toResponse).toList();
	}

	@Transactional(readOnly = true)
	public LectureResponse findById(Integer id) {
		return LectureMapper.toResponse(getLecture(id));
	}

	public LectureResponse create(Integer sectionId, LectureRequest request) {
		Section section = sectionService.getSection(sectionId);
		Lecture lecture = new Lecture();
		lecture.setName(request.name());
		lecture.setSection(section);
		return LectureMapper.toResponse(lectureRepository.save(lecture));
	}

	public LectureResponse update(Integer id, LectureRequest request) {
		Lecture lecture = getLecture(id);
		lecture.setName(request.name());
		return LectureMapper.toResponse(lecture);
	}

	public void delete(Integer id) {
		Lecture lecture = getLecture(id);
		if (lecture.getResource() != null) {
			lecture.getResource().setLecture(null);
		}
		lectureRepository.delete(lecture);
	}

	public LectureResponse attachResource(Integer lectureId, Integer resourceId) {
		Lecture lecture = getLecture(lectureId);
		Resource resource = resourceService.getResource(resourceId);
		if (resource.getLecture() != null && !resource.getLecture().getId().equals(lectureId)) {
			throw new ConflictException("Resource " + resourceId + " is already attached to lecture "
					+ resource.getLecture().getId());
		}
		if (lecture.getResource() != null) {
			lecture.getResource().setLecture(null);
		}
		lecture.setResource(resource);
		resource.setLecture(lecture);
		return LectureMapper.toResponse(lecture);
	}

	public LectureResponse detachResource(Integer lectureId) {
		Lecture lecture = getLecture(lectureId);
		if (lecture.getResource() == null) {
			throw new NotFoundException("Resource on lecture", lectureId);
		}
		lecture.getResource().setLecture(null);
		lecture.setResource(null);
		return LectureMapper.toResponse(lecture);
	}

	Lecture getLecture(Integer id) {
		return lectureRepository.findById(id).orElseThrow(() -> new NotFoundException("Lecture", id));
	}
}
