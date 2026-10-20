package me.vinald.project_three_rest.service;

import me.vinald.project_three_rest.dto.StudentDTO;
import me.vinald.project_three_rest.dto.StudentResponseDTO;
import me.vinald.project_three_rest.mapper.StudentMapper;
import me.vinald.project_three_rest.repository.SchoolRepository;
import me.vinald.project_three_rest.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	private final StudentMapper studentMapper;

	public  StudentService(SchoolRepository schoolRepository, StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
		this.studentMapper = new StudentMapper(schoolRepository);
	}

	public StudentResponseDTO createStudent(StudentDTO studentDTO) {
		var newStudent = studentMapper.toStudent(studentDTO);
		studentRepository.save(newStudent);
		return studentMapper.toStudentResponseDTO(newStudent);
	}

}
