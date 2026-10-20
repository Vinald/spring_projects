package me.vinald.project_three_rest.controller;

import me.vinald.project_three_rest.dto.StudentDto;
import me.vinald.project_three_rest.dto.StudentResponseDTO;
import me.vinald.project_three_rest.model.School;
import me.vinald.project_three_rest.model.Student;
import me.vinald.project_three_rest.repository.SchoolRepository;
import me.vinald.project_three_rest.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
	private final StudentRepository studentRepository;
	private final SchoolRepository schoolRepository;

	public StudentController(StudentRepository studentRepository, SchoolRepository schoolRepository) {
		this.studentRepository = studentRepository;
		this.schoolRepository = schoolRepository;
	}

	@GetMapping("/students")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Student> getAllStudents(){
		return studentRepository.findAll();
	}

	@GetMapping("/students/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Student getStudentById(@PathVariable Integer id){

		return studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
	}

	@PostMapping("/students")
	@ResponseStatus(HttpStatus.CREATED)
	public StudentResponseDTO createStudent(@RequestBody StudentDto student){
		var newStudent = toStudent(student);
		studentRepository.save(newStudent);
		return toStudentResponseDTO(newStudent);
	}

	@PutMapping("/students/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String updateStudent(@PathVariable Integer id, @RequestBody Student student){
		Student existingStudent = studentRepository.findById(id).orElse(null);
		if(existingStudent != null){
			existingStudent.setFirstName(student.getFirstName());
			existingStudent.setLastName(student.getLastName());
			existingStudent.setEmail(student.getEmail());
			existingStudent.setAge(student.getAge());
			existingStudent.setGender(student.getGender());
			studentRepository.save(existingStudent);
			return "Student updated successfully!";
		}
		return "Student not found!";
	}

	@DeleteMapping("/students/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String deleteStudent(@PathVariable Integer id){
		Student existingStudent = studentRepository.findById(id).orElse(null);
		if(existingStudent != null){
			studentRepository.delete(existingStudent);
			return "Student deleted successfully!";
		}
		return "Student not found!";
	}

	@GetMapping("/students/search")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Student> searchStudents(@RequestParam String firstName){
		return studentRepository.findAllByFirstNameContainingIgnoreCase(firstName);
	}

	private Student toStudent(StudentDto studentDto) {
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

	private StudentResponseDTO toStudentResponseDTO(Student student) {
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
