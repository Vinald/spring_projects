package me.vinald.project_three_rest.home;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String home() {
		return "Welcome to the Home Page!";
	}
}
