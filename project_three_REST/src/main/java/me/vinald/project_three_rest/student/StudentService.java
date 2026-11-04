package me.vinald.project_three_rest.student;

import me.vinald.project_three_rest.school.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	private final SchoolRepository schoolRepository;
	private final StudentMapper studentMapper;

	public  StudentService(StudentRepository studentRepository, SchoolRepository schoolRepository, StudentMapper studentMapper) {
		this.studentRepository = studentRepository;
		this.schoolRepository = schoolRepository;
		this.studentMapper = studentMapper;
	}

	private void ensureSchoolExists(Integer schoolId) {
		if (schoolId == null || !schoolRepository.existsById(schoolId)) {
			throw new IllegalArgumentException("School not found with id: " + schoolId);
		}
	}

	public StudentResponseDTO createStudent(StudentDTO studentDTO) {
		ensureSchoolExists(studentDTO.schoolId());
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
		ensureSchoolExists(studentDTO.schoolId());
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
		return studentRepository.findAllByFirstNameContainingIgnoreCase(firstName)
				.stream()
				.map(studentMapper::toStudentResponseDTO)
				.toList();
	}

}
