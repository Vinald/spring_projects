package me.vinald.project_three_rest.student;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
	private StudentMapper studentMapper;

	@BeforeEach
	void setUp() {
		studentMapper = new StudentMapper();
	}


	@Test
	public void shouldMapStudentDtoToStudent() {
		StudentDTO studentDto = new StudentDTO("John", "Doe",  "john@gmail.com", 20, "Male", 1);

	}

	@Test
	public void shouldMapStudentToStudentResponseDto() {

	}
}
