package me.vinald.project_three_rest.student;

import me.vinald.project_three_rest.school.School;
import me.vinald.project_three_rest.school.SchoolRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
	private final SchoolRepository schoolRepository;

	public StudentMapper(SchoolRepository schoolRepository) {
		this.schoolRepository = schoolRepository;
	}

	public Student toStudent(StudentDTO studentDto) {
		School school = schoolRepository.findById(studentDto.schoolId())
				.orElseThrow(() -> new IllegalArgumentException("School not found with id: " + studentDto.schoolId()));
		Student student = new Student();
		student.setFirstName(studentDto.firstName());
		student.setLastName(studentDto.lastName());
		student.setEmail(studentDto.email());
		student.setAge(studentDto.age());
		student.setGender(studentDto.gender());
		student.setSchool(school);
		return student;
	}

	public StudentResponseDTO toStudentResponseDTO(Student student) {
		return new StudentResponseDTO(
				student.getFirstName(),
				student.getLastName(),
				student.getEmail(),
				student.getAge(),
				student.getGender(),
				student.getSchool().getId()
		);
	}
}
