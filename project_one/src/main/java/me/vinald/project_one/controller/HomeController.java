package me.vinald.project_one.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@GetMapping("/")
	public String home(HttpServletRequest request) {
		return "Welcome to the Home Page! Session ID: " + request.getSession().getId();
	}
}
