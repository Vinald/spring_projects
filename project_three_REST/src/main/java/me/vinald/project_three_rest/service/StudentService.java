package me.vinald.project_three_rest.service;

import me.vinald.project_three_rest.dto.StudentDTO;
import me.vinald.project_three_rest.dto.StudentResponseDTO;
import me.vinald.project_three_rest.mapper.StudentMapper;
import me.vinald.project_three_rest.repository.SchoolRepository;
import me.vinald.project_three_rest.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

	public List<StudentResponseDTO> getAllStudents() {
		var students = studentRepository.findAll();
		return students.stream()
				.map(studentMapper::toStudentResponseDTO)
				.toList();
	}

	public StudentResponseDTO getStudentById(Integer id) {
		var student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
		return studentMapper.toStudentResponseDTO(student);
	}

	public StudentResponseDTO updateStudent(Integer id, StudentDTO studentDTO) {
		var existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
		var updatedStudent = studentMapper.toStudent(studentDTO);
		existingStudent.setFirstName(updatedStudent.getFirstName());
		existingStudent.setLastName(updatedStudent.getLastName());
		existingStudent.setEmail(updatedStudent.getEmail());
		existingStudent.setAge(updatedStudent.getAge());
		existingStudent.setGender(updatedStudent.getGender());
		existingStudent.setSchool(updatedStudent.getSchool());
		studentRepository.save(existingStudent);
		return studentMapper.toStudentResponseDTO(existingStudent);
	}

	public StudentResponseDTO deleteStudent(Integer id) {
		var existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
		studentRepository.delete(existingStudent);
		return studentMapper.toStudentResponseDTO(existingStudent);
	}

	public List<StudentResponseDTO> searchStudents(String firstName) {
		var students = studentRepository.findAllByFirstNameContainingIgnoreCase(firstName);
		if (students.isEmpty()) {
			throw new IllegalArgumentException("No students found with first name containing: " + firstName);
		}
		return students.stream()
				.map(studentMapper::toStudentResponseDTO)
				.toList();
	}

}
