package me.vinald.project_three_rest.student;

import me.vinald.project_three_rest.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
	public Student toStudent(StudentDTO studentDto) {
		Student student = new Student();
		student.setFirstName(studentDto.firstName());
		student.setLastName(studentDto.lastName());
		student.setEmail(studentDto.email());
		student.setAge(studentDto.age());
		student.setGender(studentDto.gender());

		var school = new School();
		school.setId(studentDto.schoolId());
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
