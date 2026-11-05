package me.vinald.project_four_jpa.controller;

import me.vinald.project_four_jpa.dto.CourseRequest;
import me.vinald.project_four_jpa.dto.CourseResponse;
import me.vinald.project_four_jpa.dto.SectionRequest;
import me.vinald.project_four_jpa.dto.SectionResponse;
import me.vinald.project_four_jpa.services.CourseService;
import me.vinald.project_four_jpa.services.SectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
	private final CourseService courseService;
	private final SectionService sectionService;

	@GetMapping
	public List<CourseResponse> findAll() {
		return courseService.findAll();
	}

	@GetMapping("/{id}")
	public CourseResponse findById(@PathVariable Integer id) {
		return courseService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CourseResponse create(@Valid @RequestBody CourseRequest request) {
		return courseService.create(request);
	}

	@PutMapping("/{id}")
	public CourseResponse update(@PathVariable Integer id, @Valid @RequestBody CourseRequest request) {
		return courseService.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		courseService.delete(id);
	}

	@PostMapping("/{id}/authors/{authorId}")
	public CourseResponse addAuthor(@PathVariable Integer id, @PathVariable Integer authorId) {
		return courseService.addAuthor(id, authorId);
	}

	@DeleteMapping("/{id}/authors/{authorId}")
	public CourseResponse removeAuthor(@PathVariable Integer id, @PathVariable Integer authorId) {
		return courseService.removeAuthor(id, authorId);
	}

	@GetMapping("/{id}/sections")
	public List<SectionResponse> findSections(@PathVariable Integer id) {
		return sectionService.findByCourse(id);
	}

	@PostMapping("/{id}/sections")
	@ResponseStatus(HttpStatus.CREATED)
	public SectionResponse createSection(@PathVariable Integer id, @Valid @RequestBody SectionRequest request) {
		return sectionService.create(id, request);
	}
}
