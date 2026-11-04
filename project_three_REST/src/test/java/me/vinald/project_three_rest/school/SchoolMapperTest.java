package me.vinald.project_three_rest.school;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolMapperTest {
	private final SchoolMapper schoolMapper = new SchoolMapper();

	@Test
	void shouldMapSchoolDtoToSchool() {
		School school = schoolMapper.toSchool(new SchoolDTO("Greenwood High"));

		assertAll(
				() -> assertEquals("Greenwood High", school.getName()),
				() -> assertNull(school.getId())
		);
	}

	@Test
	void shouldMapSchoolToSchoolResponseDto() {
		School school = new School(1);
		school.setName("Greenwood High");

		SchoolResponseDTO response = schoolMapper.toSchoolResponseDto(school);

		assertEquals("Greenwood High", response.name());
	}
}
