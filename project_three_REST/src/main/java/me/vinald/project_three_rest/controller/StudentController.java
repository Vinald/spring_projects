package me.vinald.project_three_rest.controller;

import me.vinald.project_three_rest.dto.StudentDTO;
import me.vinald.project_three_rest.dto.StudentResponseDTO;
import me.vinald.project_three_rest.model.Student;
import me.vinald.project_three_rest.repository.SchoolRepository;
import me.vinald.project_three_rest.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
	private final SchoolRepository schoolRepository;
	private final StudentService studentService;
	public StudentController(StudentService studentService, SchoolRepository schoolRepository) {
		this.schoolRepository = schoolRepository;
		this.studentService = studentService;
	}

	@PostMapping("/students")
	@ResponseStatus(HttpStatus.CREATED)
	public StudentResponseDTO createStudent(@RequestBody StudentDTO student){
		return studentService.createStudent(student);
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
}
