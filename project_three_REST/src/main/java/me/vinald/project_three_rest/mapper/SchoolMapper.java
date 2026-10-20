package me.vinald.project_three_rest.mapper;

import me.vinald.project_three_rest.dto.SchoolDTO;
import me.vinald.project_three_rest.dto.SchoolResponseDTO;
import me.vinald.project_three_rest.model.School;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
	public School toSchool(SchoolDTO schoolDto) {
		School school = new School();
		school.setName(schoolDto.name());
		return school;
	}

	public SchoolResponseDTO toSchoolResponseDto(School school) {
		return new SchoolResponseDTO(school.getName());
	}
}
