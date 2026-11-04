package me.vinald.project_three_rest.student;

import me.vinald.project_three_rest.school.SchoolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private StudentService studentService;
	@MockitoBean
	private SchoolRepository schoolRepository;

	private static final String VALID_STUDENT = """
			{"firstName":"John","lastName":"Doe","email":"john@gmail.com","age":20,"gender":"Male","schoolId":1}
			""";
	private static final StudentResponseDTO JOHN =
			new StudentResponseDTO("John", "Doe", "john@gmail.com", 20, "Male", 1);

	// POST /students

	@Test
	void createStudent_shouldReturn201_whenValid() throws Exception {
		when(studentService.createStudent(any())).thenReturn(JOHN);

		mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(VALID_STUDENT))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName").value("John"))
				.andExpect(jsonPath("$.schoolId").value(1));
	}

	@Test
	void createStudent_shouldReturn400WithFieldErrors_whenInvalid() throws Exception {
		String invalid = """
				{"firstName":"","lastName":"","email":"not-an-email","schoolId":1}
				""";

		mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(invalid))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.firstName").value("First name cannot be empty"))
				.andExpect(jsonPath("$.lastName").value("Last name cannot be empty"))
				.andExpect(jsonPath("$.email").value("Email should be valid"));

		verifyNoInteractions(studentService);
	}

	@Test
	void createStudent_shouldReturn400_whenEmailMissing() throws Exception {
		String invalid = """
				{"firstName":"John","lastName":"Doe","schoolId":1}
				""";

		mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(invalid))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.email").value("Email cannot be empty"));
	}

	@Test
	void createStudent_shouldReturn400_whenSchoolIdMissing() throws Exception {
		String invalid = """
				{"firstName":"John","lastName":"Doe","email":"john@gmail.com"}
				""";

		mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(invalid))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.schoolId").value("School id is required"));

		verifyNoInteractions(studentService);
	}

	@Test
	void createStudent_shouldReturn400_whenAgeNegative() throws Exception {
		String invalid = """
				{"firstName":"John","lastName":"Doe","email":"john@gmail.com","age":-1,"schoolId":1}
				""";

		mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(invalid))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.age").value("Age must be a positive number or zero"));

		verifyNoInteractions(studentService);
	}

	@Test
	void createStudent_shouldReturn404_whenSchoolNotFound() throws Exception {
		when(studentService.createStudent(any()))
				.thenThrow(new IllegalArgumentException("School not found with id: 1"));

		mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(VALID_STUDENT))
				.andExpect(status().isNotFound())
				.andExpect(content().string("School not found with id: 1"));
	}

	// GET /students

	@Test
	void getAllStudents_shouldReturn200WithList() throws Exception {
		when(studentService.getAllStudents()).thenReturn(List.of(JOHN));

		mockMvc.perform(get("/students"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].email").value("john@gmail.com"));
	}

	@Test
	void getAllStudents_shouldReturn200WithEmptyList() throws Exception {
		when(studentService.getAllStudents()).thenReturn(List.of());

		mockMvc.perform(get("/students"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	// GET /students/{id}

	@Test
	void getStudentById_shouldReturn200_whenFound() throws Exception {
		when(studentService.getStudentById(1)).thenReturn(JOHN);

		mockMvc.perform(get("/students/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("John"));
	}

	@Test
	void getStudentById_shouldReturn404_whenNotFound() throws Exception {
		when(studentService.getStudentById(99))
				.thenThrow(new IllegalArgumentException("Student not found with id: 99"));

		mockMvc.perform(get("/students/99"))
				.andExpect(status().isNotFound())
				.andExpect(content().string("Student not found with id: 99"));
	}

	// PUT /students/{id}

	@Test
	void updateStudent_shouldReturn200_whenValid() throws Exception {
		when(studentService.updateStudent(eq(1), any())).thenReturn(JOHN);

		mockMvc.perform(put("/students/1").contentType(MediaType.APPLICATION_JSON).content(VALID_STUDENT))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("John"));
	}

	@Test
	void updateStudent_shouldReturn400_whenInvalid() throws Exception {
		String invalid = """
				{"firstName":"","lastName":"Doe","email":"john@gmail.com","schoolId":1}
				""";

		mockMvc.perform(put("/students/1").contentType(MediaType.APPLICATION_JSON).content(invalid))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.firstName").value("First name cannot be empty"));

		verifyNoInteractions(studentService);
	}

	@Test
	void updateStudent_shouldReturn404_whenNotFound() throws Exception {
		when(studentService.updateStudent(eq(99), any()))
				.thenThrow(new IllegalArgumentException("Student not found with id: 99"));

		mockMvc.perform(put("/students/99").contentType(MediaType.APPLICATION_JSON).content(VALID_STUDENT))
				.andExpect(status().isNotFound());
	}

	// DELETE /students/{id}

	@Test
	void deleteStudent_shouldReturn204_whenFound() throws Exception {
		when(studentService.deleteStudent(1)).thenReturn(JOHN);

		mockMvc.perform(delete("/students/1"))
				.andExpect(status().isNoContent());

		verify(studentService).deleteStudent(1);
	}

	@Test
	void deleteStudent_shouldReturn404_whenNotFound() throws Exception {
		when(studentService.deleteStudent(99))
				.thenThrow(new IllegalArgumentException("Student not found with id: 99"));

		mockMvc.perform(delete("/students/99"))
				.andExpect(status().isNotFound());
	}

	// GET /students/search

	@Test
	void searchStudents_shouldReturn200WithMatches() throws Exception {
		when(studentService.searchStudents("jo")).thenReturn(List.of(JOHN));

		mockMvc.perform(get("/students/search").param("firstName", "jo"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].firstName").value("John"));
	}

	@Test
	void searchStudents_shouldReturn200WithEmptyList_whenNoMatches() throws Exception {
		when(studentService.searchStudents("zz")).thenReturn(List.of());

		mockMvc.perform(get("/students/search").param("firstName", "zz"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	void searchStudents_shouldReturn400_whenFirstNameParamMissing() throws Exception {
		mockMvc.perform(get("/students/search"))
				.andExpect(status().isBadRequest());

		verifyNoInteractions(studentService);
	}
}
