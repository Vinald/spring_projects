package me.vinald.project_three_rest.controller;

import me.vinald.project_three_rest.dto.SchoolDTO;
import me.vinald.project_three_rest.dto.SchoolResponseDTO;
import me.vinald.project_three_rest.model.School;
import me.vinald.project_three_rest.repository.SchoolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {
	private final SchoolRepository schoolRepository;

	public  SchoolController(SchoolRepository schoolRepository) {
		this.schoolRepository = schoolRepository;
	}

	@PostMapping("/schools")
	@ResponseStatus(HttpStatus.CREATED)
	public SchoolResponseDTO createSchool(@RequestBody SchoolDTO schoolDto) {
		School school = toSchool(schoolDto);
		schoolRepository.save(school);
		return toSchoolResponseDto(school);
	}

	@GetMapping("/schools")
	@ResponseStatus(HttpStatus.OK)
	public List<SchoolResponseDTO> getAllSchools() {
		return schoolRepository.findAll()
				.stream()
				.map(this::toSchoolResponseDto)
				.toList();
	}

	@GetMapping("/schools/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SchoolResponseDTO getSchoolById(@PathVariable Integer id) {
		return toSchoolResponseDto(schoolRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("School not found with id: " + id)));
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

	private School toSchool(SchoolDTO schoolDto) {
		School school = new School();
		school.setName(schoolDto.name());
		return school;
	}

	private SchoolResponseDTO toSchoolResponseDto(School school) {
		return new SchoolResponseDTO(school.getName());
	}
}
