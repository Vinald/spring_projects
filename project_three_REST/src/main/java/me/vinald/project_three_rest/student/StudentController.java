package me.vinald.project_three_rest.student;

import jakarta.validation.Valid;
import me.vinald.project_three_rest.school.SchoolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
	private final StudentService studentService;
	public StudentController(StudentService studentService, SchoolRepository schoolRepository) {
		this.studentService = studentService;
	}

	@PostMapping("/students")
	@ResponseStatus(HttpStatus.CREATED)
	public StudentResponseDTO createStudent(@Valid @RequestBody StudentDTO student){
		return studentService.createStudent(student);
	}

	@GetMapping("/students")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<StudentResponseDTO> getAllStudents(){
		return studentService.getAllStudents();
	}

	@GetMapping("/students/{id}")
	@ResponseStatus(HttpStatus.OK)
	public StudentResponseDTO getStudentById(@PathVariable Integer id){
		return studentService.getStudentById(id);
	}

	@PutMapping("/students/{id}")
	@ResponseStatus(HttpStatus.OK)
	public StudentResponseDTO updateStudent(@PathVariable Integer id, @RequestBody StudentDTO student){
		return studentService.updateStudent(id, student);
	}

	@DeleteMapping("/students/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public StudentResponseDTO deleteStudent(@PathVariable Integer id){
		return studentService.deleteStudent(id);
	}

	@GetMapping("/students/search")
	@ResponseStatus(HttpStatus.OK)
	public List<StudentResponseDTO> searchStudents(@RequestParam String firstName){
		return studentService.searchStudents(firstName);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
}
