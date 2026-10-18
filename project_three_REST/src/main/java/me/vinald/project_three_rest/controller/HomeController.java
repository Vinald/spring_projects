package me.vinald.project_three_rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String home() {
		return "Welcome to the Home Page!";
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public String create() {
		return "Resource created successfully!";
	}
}
