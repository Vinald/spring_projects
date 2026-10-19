package me.vinald.project_three_rest.controller;

import me.vinald.project_three_rest.model.School;
import me.vinald.project_three_rest.repository.SchoolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchoolController {
	private final SchoolRepository schoolRepository;

	public  SchoolController(SchoolRepository schoolRepository) {
		this.schoolRepository = schoolRepository;
	}

	@PostMapping("/schools")
	@ResponseStatus(HttpStatus.CREATED)
	public String createSchool(@RequestBody School school) {
		schoolRepository.save(school);
		return "School created successfully!";
	}

	@GetMapping("/schools")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<School> getAllSchools() {
		return schoolRepository.findAll();
	}

	@GetMapping("/schools/{id}")
	@ResponseStatus(HttpStatus.OK)
	public School getSchoolById(@PathVariable Integer id) {
		return schoolRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("School not found with id: " + id));
	}

	@PutMapping("/schools/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String updateSchool(@PathVariable Integer id, @RequestBody School school) {
		School existingSchool = schoolRepository.findById(id).orElse(null);
		if (existingSchool != null) {
			existingSchool.setName(school.getName());
			schoolRepository.save(existingSchool);
			return "School updated successfully!";
		} else {
			return "School not found!";
		}
	}

	@DeleteMapping("/schools/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteSchool(@PathVariable Integer id) {
		School existingSchool = schoolRepository.findById(id).orElse(null);
		if (existingSchool != null) {
			schoolRepository.delete(existingSchool);
			return "School deleted successfully!";
		} else {
			return "School not found!";
		}
	}
}
