package me.vinald.project_three_rest.school;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
	private final SchoolRepository schoolRepository;
	private final SchoolMapper schoolMapper;

	public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
		this.schoolRepository = schoolRepository;
		this.schoolMapper = schoolMapper;
	}

	public SchoolResponseDTO createSchool(SchoolDTO schoolDto) {
		var school = schoolMapper.toSchool(schoolDto);
		schoolRepository.save(school);
		return schoolMapper.toSchoolResponseDto(school);
	}

	public List<SchoolResponseDTO> getAllSchools() {
		return schoolRepository.findAll()
				.stream()
				.map(schoolMapper::toSchoolResponseDto)
				.toList();
	}

	public SchoolResponseDTO getSchoolById(Integer id) {
		var school = schoolRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("School not found with id: " + id));
		return schoolMapper.toSchoolResponseDto(school);
	}

	public  SchoolResponseDTO updateSchoolById(Integer id, SchoolDTO schoolDto) {
		var existingSchool = schoolRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("School not found with id: " + id));
		existingSchool.setName(schoolDto.name());
		schoolRepository.save(existingSchool);
		return schoolMapper.toSchoolResponseDto(existingSchool);
	}

	public  SchoolResponseDTO deleteSchoolById(Integer id) {
		var existingSchool = schoolRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("School not found with id: " + id));
		schoolRepository.delete(existingSchool);
		return schoolMapper.toSchoolResponseDto(existingSchool);
	}
}
