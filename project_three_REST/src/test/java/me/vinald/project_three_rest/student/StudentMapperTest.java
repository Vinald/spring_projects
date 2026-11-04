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
		StudentDTO studentDto = new StudentDTO(
				"John",
				"Doe",
				"john@gmail.com",
				20,
				"Male",
				1);

		Student student = studentMapper.toStudent(studentDto);

		assertEquals(studentDto.firstName(), student.getFirstName());
		assertEquals(studentDto.lastName(), student.getLastName());
		assertEquals(studentDto.email(), student.getEmail());
		assertEquals(studentDto.age(), student.getAge());
		assertEquals(studentDto.gender(), student.getGender());
		assertNotNull(student.getSchool());
		assertEquals(studentDto.schoolId(), student.getSchool().getId());
	}

	@Test
	public void shouldMapStudentToStudentResponseDto() {

	}
}
