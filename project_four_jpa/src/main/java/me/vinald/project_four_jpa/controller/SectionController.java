package me.vinald.project_four_jpa.controller;

import me.vinald.project_four_jpa.dto.LectureRequest;
import me.vinald.project_four_jpa.dto.LectureResponse;
import me.vinald.project_four_jpa.dto.SectionRequest;
import me.vinald.project_four_jpa.dto.SectionResponse;
import me.vinald.project_four_jpa.services.LectureService;
import me.vinald.project_four_jpa.services.SectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
public class SectionController {
	private final SectionService sectionService;
	private final LectureService lectureService;

	@GetMapping("/{id}")
	public SectionResponse findById(@PathVariable Integer id) {
		return sectionService.findById(id);
	}

	@PutMapping("/{id}")
	public SectionResponse update(@PathVariable Integer id, @Valid @RequestBody SectionRequest request) {
		return sectionService.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		sectionService.delete(id);
	}

	@GetMapping("/{id}/lectures")
	public List<LectureResponse> findLectures(@PathVariable Integer id) {
		return lectureService.findBySection(id);
	}

	@PostMapping("/{id}/lectures")
	@ResponseStatus(HttpStatus.CREATED)
	public LectureResponse createLecture(@PathVariable Integer id, @Valid @RequestBody LectureRequest request) {
		return lectureService.create(id, request);
	}
}
