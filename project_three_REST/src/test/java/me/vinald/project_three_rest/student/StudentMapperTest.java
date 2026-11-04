package me.vinald.project_three_rest.student;

import me.vinald.project_three_rest.school.School;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {
	private final StudentMapper studentMapper = new StudentMapper();

	@Test
	void shouldMapStudentDtoToStudent() {
		StudentDTO studentDto = new StudentDTO(
				"John",
				"Doe",
				"john@gmail.com",
				20,
				"Male",
				1);

		Student student = studentMapper.toStudent(studentDto);

		assertAll(
				() -> assertEquals(studentDto.firstName(), student.getFirstName()),
				() -> assertEquals(studentDto.lastName(), student.getLastName()),
				() -> assertEquals(studentDto.email(), student.getEmail()),
				() -> assertEquals(studentDto.age(), student.getAge()),
				() -> assertEquals(studentDto.gender(), student.getGender()),
				() -> assertNotNull(student.getSchool()),
				() -> assertEquals(studentDto.schoolId(), student.getSchool().getId())
		);
	}

	@Test
	void shouldNotAttachSchoolWhenSchoolIdIsNull() {
		StudentDTO studentDto = new StudentDTO(
				"John",
				"Doe",
				"john@gmail.com",
				20,
				"Male",
				null);

		Student student = studentMapper.toStudent(studentDto);

		assertNull(student.getSchool());
	}

	@Test
	void shouldMapStudentToStudentResponseDto() {
		Student student = Student.builder()
				.firstName("John")
				.lastName("Doe")
				.email("john@gmail.com")
				.age(20)
				.gender("Male")
				.school(new School(1))
				.build();

		StudentResponseDTO studentResponseDTO = studentMapper.toStudentResponseDTO(student);

		assertAll(
				() -> assertEquals(student.getFirstName(), studentResponseDTO.firstName()),
				() -> assertEquals(student.getLastName(), studentResponseDTO.lastName()),
				() -> assertEquals(student.getEmail(), studentResponseDTO.email()),
				() -> assertEquals(student.getAge(), studentResponseDTO.age()),
				() -> assertEquals(student.getGender(), studentResponseDTO.gender()),
				() -> assertEquals(student.getSchool().getId(), studentResponseDTO.schoolId())
		);
	}

	@Test
	void shouldMapStudentWithoutSchoolToResponseDto() {
		Student student = Student.builder()
				.firstName("John")
				.lastName("Doe")
				.email("john@gmail.com")
				.build();

		StudentResponseDTO studentResponseDTO = studentMapper.toStudentResponseDTO(student);

		assertNull(studentResponseDTO.schoolId());
	}
}
