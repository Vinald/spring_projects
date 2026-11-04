package me.vinald.project_three_rest.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchoolServiceTest {
	@Mock
	private SchoolRepository schoolRepository;

	private SchoolService schoolService;

	@BeforeEach
	void setUp() {
		schoolService = new SchoolService(schoolRepository, new SchoolMapper());
	}

	private static School school(Integer id, String name) {
		School school = new School(id);
		school.setName(name);
		return school;
	}

	// createSchool

	@Test
	void createSchool_shouldSaveAndReturnSchool() {
		SchoolResponseDTO response = schoolService.createSchool(new SchoolDTO("Greenwood High"));

		ArgumentCaptor<School> captor = ArgumentCaptor.forClass(School.class);
		verify(schoolRepository).save(captor.capture());
		assertEquals("Greenwood High", captor.getValue().getName());
		assertEquals(new SchoolResponseDTO("Greenwood High"), response);
	}

	// getAllSchools

	@Test
	void getAllSchools_shouldReturnMappedSchools() {
		when(schoolRepository.findAll()).thenReturn(List.of(school(1, "A"), school(2, "B")));

		List<SchoolResponseDTO> response = schoolService.getAllSchools();

		assertEquals(List.of(new SchoolResponseDTO("A"), new SchoolResponseDTO("B")), response);
	}

	@Test
	void getAllSchools_shouldReturnEmptyList_whenNoSchools() {
		when(schoolRepository.findAll()).thenReturn(List.of());

		assertTrue(schoolService.getAllSchools().isEmpty());
	}

	// getSchoolById

	@Test
	void getSchoolById_shouldReturnSchool_whenFound() {
		when(schoolRepository.findById(1)).thenReturn(Optional.of(school(1, "A")));

		assertEquals(new SchoolResponseDTO("A"), schoolService.getSchoolById(1));
	}

	@Test
	void getSchoolById_shouldThrow_whenNotFound() {
		when(schoolRepository.findById(99)).thenReturn(Optional.empty());

		var ex = assertThrows(IllegalArgumentException.class, () -> schoolService.getSchoolById(99));

		assertEquals("School not found with id: 99", ex.getMessage());
	}

	// updateSchoolById

	@Test
	void updateSchoolById_shouldUpdateName_whenFound() {
		School existing = school(1, "Old");
		when(schoolRepository.findById(1)).thenReturn(Optional.of(existing));

		SchoolResponseDTO response = schoolService.updateSchoolById(1, new SchoolDTO("New"));

		verify(schoolRepository).save(existing);
		assertEquals("New", existing.getName());
		assertEquals(new SchoolResponseDTO("New"), response);
	}

	@Test
	void updateSchoolById_shouldThrow_whenNotFound() {
		when(schoolRepository.findById(99)).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> schoolService.updateSchoolById(99, new SchoolDTO("New")));

		verify(schoolRepository, never()).save(any());
	}

	// deleteSchoolById

	@Test
	void deleteSchoolById_shouldDeleteAndReturnSchool_whenFound() {
		School existing = school(1, "A");
		when(schoolRepository.findById(1)).thenReturn(Optional.of(existing));

		SchoolResponseDTO response = schoolService.deleteSchoolById(1);

		verify(schoolRepository).delete(existing);
		assertEquals(new SchoolResponseDTO("A"), response);
	}

	@Test
	void deleteSchoolById_shouldThrow_whenNotFound() {
		when(schoolRepository.findById(99)).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> schoolService.deleteSchoolById(99));

		verify(schoolRepository, never()).delete(any());
	}
}
