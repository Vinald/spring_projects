package me.vinald.project_one.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@GetMapping("/students")
	public String getStudents() {
		return "List of students";
	}

	@PostMapping("/students")
	public String addStudent(HttpServletRequest request) {
		return "Student added csrf token: " + request.getSession().getAttribute("csrfToken");
	}
}
