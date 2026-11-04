package me.vinald.project_three_rest.student;

import me.vinald.project_three_rest.school.School;
import me.vinald.project_three_rest.school.SchoolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
	@Mock
	private StudentRepository studentRepository;
	@Mock
	private SchoolRepository schoolRepository;
	@Mock
	private StudentMapper studentMapper;

	private StudentService studentService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		studentService = new StudentService(studentRepository, schoolRepository, studentMapper);
	}

	private static StudentDTO studentDto(Integer schoolId) {
		return new StudentDTO("John", "Doe", "john@gmail.com", 20, "Male", schoolId);
	}

	private static Student student(String firstName, Integer schoolId) {
		return Student.builder()
				.firstName(firstName)
				.lastName("Doe")
				.email(firstName.toLowerCase() + "@gmail.com")
				.age(20)
				.gender("Male")
				.school(new School(schoolId))
				.build();
	}

	// createStudent

	@Test
	void createStudent_shouldSaveAndReturnStudent_whenSchoolExists() {
		when(schoolRepository.existsById(1)).thenReturn(true);

		StudentResponseDTO response = studentService.createStudent(studentDto(1));

		ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
		verify(studentRepository).save(captor.capture());
		Student saved = captor.getValue();
		assertAll(
				() -> assertEquals("John", saved.getFirstName()),
				() -> assertEquals(1, saved.getSchool().getId()),
				() -> assertEquals(new StudentResponseDTO("John", "Doe", "john@gmail.com", 20, "Male", 1), response)
		);
	}

	@Test
	void createStudent_shouldThrow_whenSchoolDoesNotExist() {
		when(schoolRepository.existsById(99)).thenReturn(false);

		var ex = assertThrows(IllegalArgumentException.class, () -> studentService.createStudent(studentDto(99)));

		assertEquals("School not found with id: 99", ex.getMessage());
		verify(studentRepository, never()).save(any());
	}

	@Test
	void createStudent_shouldThrow_whenSchoolIdIsNull() {
		assertThrows(IllegalArgumentException.class, () -> studentService.createStudent(studentDto(null)));

		verifyNoInteractions(schoolRepository);
		verify(studentRepository, never()).save(any());
	}

	// getAllStudents

	@Test
	void getAllStudents_shouldReturnMappedStudents() {
		when(studentRepository.findAll()).thenReturn(List.of(
				student("John", 1),
				student("Jane", 2)
		));

		List<StudentResponseDTO> response = studentService.getAllStudents();

		assertEquals(2, response.size());
		assertEquals("John", response.get(0).firstName());
		assertEquals(2, response.get(1).schoolId());
	}

	@Test
	void getAllStudents_shouldReturnEmptyList_whenNoStudents() {
		when(studentRepository.findAll()).thenReturn(List.of());

		assertTrue(studentService.getAllStudents().isEmpty());
	}

	// getStudentById

	@Test
	void getStudentById_shouldReturnStudent_whenFound() {
		when(studentRepository.findById(1))
				.thenReturn(Optional.of(student("John", 1)));

		StudentResponseDTO response = studentService.getStudentById(1);

		assertEquals("John", response.firstName());
	}

	@Test
	void getStudentById_shouldThrow_whenNotFound() {
		when(studentRepository.findById(99)).thenReturn(Optional.empty());

		var ex = assertThrows(IllegalArgumentException.class, () -> studentService.getStudentById(99));

		assertEquals("Student not found with id: 99", ex.getMessage());
	}

	// updateStudent

	@Test
	void updateStudent_shouldUpdateAllFields_whenStudentAndSchoolExist() {
		Student existing = student("Old", 1);
		when(studentRepository.findById(1)).thenReturn(Optional.of(existing));
		when(schoolRepository.existsById(2)).thenReturn(true);

		StudentResponseDTO response = studentService.updateStudent(1,
				new StudentDTO("New", "Name", "new@gmail.com", 25, "Female", 2));

		verify(studentRepository).save(existing);
		assertAll(
				() -> assertEquals("New", existing.getFirstName()),
				() -> assertEquals("Name", existing.getLastName()),
				() -> assertEquals("new@gmail.com", existing.getEmail()),
				() -> assertEquals(25, existing.getAge()),
				() -> assertEquals("Female", existing.getGender()),
				() -> assertEquals(2, existing.getSchool().getId()),
				() -> assertEquals(new StudentResponseDTO("New", "Name", "new@gmail.com", 25, "Female", 2), response)
		);
	}

	@Test
	void updateStudent_shouldThrow_whenStudentNotFound() {
		when(studentRepository.findById(99)).thenReturn(Optional.empty());

		var ex = assertThrows(IllegalArgumentException.class, () -> studentService.updateStudent(99, studentDto(1)));

		assertEquals("Student not found with id: 99", ex.getMessage());
		verify(studentRepository, never()).save(any());
	}

	@Test
	void updateStudent_shouldThrow_andLeaveStudentUnchanged_whenSchoolDoesNotExist() {
		Student existing = student("Old", 1);
		when(studentRepository.findById(1)).thenReturn(Optional.of(existing));
		when(schoolRepository.existsById(99)).thenReturn(false);

		assertThrows(IllegalArgumentException.class, () -> studentService.updateStudent(1, studentDto(99)));

		assertEquals("Old", existing.getFirstName());
		verify(studentRepository, never()).save(any());
	}

	// deleteStudent

	@Test
	void deleteStudent_shouldDeleteAndReturnStudent_whenFound() {
		Student existing = student("John", 1);
		when(studentRepository.findById(1)).thenReturn(Optional.of(existing));

		StudentResponseDTO response = studentService.deleteStudent(1);

		verify(studentRepository).delete(existing);
		assertEquals("John", response.firstName());
	}

	@Test
	void deleteStudent_shouldThrow_whenNotFound() {
		when(studentRepository.findById(99)).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> studentService.deleteStudent(99));

		verify(studentRepository, never()).delete(any());
	}

	// searchStudents

	@Test
	void searchStudents_shouldReturnMatches() {
		when(studentRepository.findAllByFirstNameContainingIgnoreCase("jo"))
				.thenReturn(List.of(student("John", 1), student("Joan", 1)));

		List<StudentResponseDTO> response = studentService.searchStudents("jo");

		assertEquals(List.of("John", "Joan"), response.stream().map(StudentResponseDTO::firstName).toList());
	}

	@Test
	void searchStudents_shouldReturnEmptyList_whenNoMatches() {
		when(studentRepository.findAllByFirstNameContainingIgnoreCase("zz")).thenReturn(List.of());

		assertTrue(studentService.searchStudents("zz").isEmpty());
	}
}
