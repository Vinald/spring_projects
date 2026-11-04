package me.vinald.project_three_rest.school;

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

@WebMvcTest(SchoolController.class)
class SchoolControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private SchoolService schoolService;

	private static final String VALID_SCHOOL = """
			{"name":"Greenwood High"}
			""";
	private static final SchoolResponseDTO GREENWOOD = new SchoolResponseDTO("Greenwood High");

	// POST /schools

	@Test
	void createSchool_shouldReturn201_whenValid() throws Exception {
		when(schoolService.createSchool(any())).thenReturn(GREENWOOD);

		mockMvc.perform(post("/schools").contentType(MediaType.APPLICATION_JSON).content(VALID_SCHOOL))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value("Greenwood High"));
	}

	@Test
	void createSchool_shouldReturn400_whenNameBlank() throws Exception {
		mockMvc.perform(post("/schools").contentType(MediaType.APPLICATION_JSON).content("""
						{"name":"   "}
						"""))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.name").value("School name cannot be blank"));

		verifyNoInteractions(schoolService);
	}

	@Test
	void createSchool_shouldReturn400_whenNameMissing() throws Exception {
		mockMvc.perform(post("/schools").contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.name").value("School name cannot be blank"));

		verifyNoInteractions(schoolService);
	}

	// GET /schools

	@Test
	void getAllSchools_shouldReturn200WithList() throws Exception {
		when(schoolService.getAllSchools()).thenReturn(List.of(GREENWOOD, new SchoolResponseDTO("Riverside")));

		mockMvc.perform(get("/schools"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[1].name").value("Riverside"));
	}

	// GET /schools/{id}

	@Test
	void getSchoolById_shouldReturn200_whenFound() throws Exception {
		when(schoolService.getSchoolById(1)).thenReturn(GREENWOOD);

		mockMvc.perform(get("/schools/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Greenwood High"));
	}

	@Test
	void getSchoolById_shouldReturn404_whenNotFound() throws Exception {
		when(schoolService.getSchoolById(99))
				.thenThrow(new IllegalArgumentException("School not found with id: 99"));

		mockMvc.perform(get("/schools/99"))
				.andExpect(status().isNotFound())
				.andExpect(content().string("School not found with id: 99"));
	}

	// PUT /schools/{id}

	@Test
	void updateSchool_shouldReturn200_whenValid() throws Exception {
		when(schoolService.updateSchoolById(eq(1), any())).thenReturn(GREENWOOD);

		mockMvc.perform(put("/schools/1").contentType(MediaType.APPLICATION_JSON).content(VALID_SCHOOL))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Greenwood High"));
	}

	@Test
	void updateSchool_shouldReturn400_whenNameBlank() throws Exception {
		mockMvc.perform(put("/schools/1").contentType(MediaType.APPLICATION_JSON).content("""
						{"name":""}
						"""))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.name").value("School name cannot be blank"));

		verifyNoInteractions(schoolService);
	}

	@Test
	void updateSchool_shouldReturn404_whenNotFound() throws Exception {
		when(schoolService.updateSchoolById(eq(99), any()))
				.thenThrow(new IllegalArgumentException("School not found with id: 99"));

		mockMvc.perform(put("/schools/99").contentType(MediaType.APPLICATION_JSON).content(VALID_SCHOOL))
				.andExpect(status().isNotFound());
	}

	// DELETE /schools/{id}

	@Test
	void deleteSchool_shouldReturn204_whenFound() throws Exception {
		when(schoolService.deleteSchoolById(1)).thenReturn(GREENWOOD);

		mockMvc.perform(delete("/schools/1"))
				.andExpect(status().isNoContent());

		verify(schoolService).deleteSchoolById(1);
	}

	@Test
	void deleteSchool_shouldReturn404_whenNotFound() throws Exception {
		when(schoolService.deleteSchoolById(99))
				.thenThrow(new IllegalArgumentException("School not found with id: 99"));

		mockMvc.perform(delete("/schools/99"))
				.andExpect(status().isNotFound());
	}
}
