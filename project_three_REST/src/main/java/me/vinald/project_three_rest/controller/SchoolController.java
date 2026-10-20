package me.vinald.project_three_rest.controller;

import me.vinald.project_three_rest.dto.SchoolDTO;
import me.vinald.project_three_rest.dto.SchoolResponseDTO;
import me.vinald.project_three_rest.model.School;
import me.vinald.project_three_rest.repository.SchoolRepository;
import me.vinald.project_three_rest.service.SchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {
	private final SchoolService schoolService;

	public  SchoolController(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

	@PostMapping("/schools")
	@ResponseStatus(HttpStatus.CREATED)
	public SchoolResponseDTO createSchool(@RequestBody SchoolDTO schoolDto) {
		return schoolService.createSchool(schoolDto);
	}

	@GetMapping("/schools")
	@ResponseStatus(HttpStatus.OK)
	public List<SchoolResponseDTO> getAllSchools() {
		return schoolService.getAllSchools();
	}

	@GetMapping("/schools/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SchoolResponseDTO getSchoolById(@PathVariable Integer id) {
		return schoolService.getSchoolById(id);
	}

	@PutMapping("/schools/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SchoolResponseDTO updateSchool(@PathVariable Integer id, @RequestBody SchoolDTO school) {
		return schoolService.updateSchoolById(id, school);
	}

	@DeleteMapping("/schools/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public SchoolResponseDTO deleteSchool(@PathVariable Integer id) {
		return this.schoolService.deleteSchoolById(id);
	}
}
