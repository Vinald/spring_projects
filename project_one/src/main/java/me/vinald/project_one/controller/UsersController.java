package me.vinald.project_one.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import me.vinald.project_one.model.Users;
import me.vinald.project_one.service.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
	private final UsersService usersService;

	public  UsersController(UsersService usersService) {
		this.usersService = usersService;
	}

	@PostMapping("/register")
	public  Users registerUser(@RequestBody Users user) {
		return usersService.register(user);
	}
}
