package me.vinald.project_four_jpa.controller;

import me.vinald.project_four_jpa.dto.LectureRequest;
import me.vinald.project_four_jpa.dto.LectureResponse;
import me.vinald.project_four_jpa.services.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lectures")
@RequiredArgsConstructor
public class LectureController {
	private final LectureService lectureService;

	@GetMapping("/{id}")
	public LectureResponse findById(@PathVariable Integer id) {
		return lectureService.findById(id);
	}

	@PutMapping("/{id}")
	public LectureResponse update(@PathVariable Integer id, @Valid @RequestBody LectureRequest request) {
		return lectureService.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		lectureService.delete(id);
	}

	@PutMapping("/{id}/resource/{resourceId}")
	public LectureResponse attachResource(@PathVariable Integer id, @PathVariable Integer resourceId) {
		return lectureService.attachResource(id, resourceId);
	}

	@DeleteMapping("/{id}/resource")
	public LectureResponse detachResource(@PathVariable Integer id) {
		return lectureService.detachResource(id);
	}
}
