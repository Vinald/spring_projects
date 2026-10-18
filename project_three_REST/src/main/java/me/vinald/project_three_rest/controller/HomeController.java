package me.vinald.project_three_rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String home() {
		return "Welcome to the Home Page!";
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public String create(@RequestBody String message) {
		return "Resource created successfully: " + message;
	}

	// http://localhost:8080/hello/Vinald
	@GetMapping("/hello/{name}")
	@ResponseStatus(HttpStatus.OK)
	public String pathVar(
			@PathVariable String name
	) {
		return "Hello, World! " + name;
	}

	// http://localhost:8080/hello?name=Vinald
	@GetMapping("/hello")
	@ResponseStatus(HttpStatus.OK)
	public String queryParam(
			@RequestParam String name
	) {
		return "Hello, World! " + name;
	}
}
