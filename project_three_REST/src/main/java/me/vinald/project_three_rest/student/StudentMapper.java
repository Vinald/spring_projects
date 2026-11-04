package me.vinald.project_three_rest.student;

import me.vinald.project_three_rest.school.School;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
	public Student toStudent(StudentDTO studentDto) {
		if (studentDto == null) {
			throw new NullPointerException("StudentDTO cannot be null");
		}
		Student student = new Student();
		student.setFirstName(studentDto.firstName());
		student.setLastName(studentDto.lastName());
		student.setEmail(studentDto.email());
		student.setAge(studentDto.age());
		student.setGender(studentDto.gender());

		if (studentDto.schoolId() != null) {
			student.setSchool(new School(studentDto.schoolId()));
		}
		return student;
	}

	public StudentResponseDTO toStudentResponseDTO(Student student) {
		if (student == null) {
			throw new NullPointerException("Student cannot be null");
		}
		return new StudentResponseDTO(
				student.getFirstName(),
				student.getLastName(),
				student.getEmail(),
				student.getAge(),
				student.getGender(),
				student.getSchool() != null ? student.getSchool().getId() : null
		);
	}
}
