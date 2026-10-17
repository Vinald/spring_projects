package me.vinald.project_one.controller;

import jakarta.validation.Valid;
import me.vinald.project_one.dto.UsersDTO;
import me.vinald.project_one.model.Users;
import me.vinald.project_one.security.JwtService;
import me.vinald.project_one.service.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UsersService usersService;

	public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UsersService usersService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.usersService = usersService;
	}

	@PostMapping("/register")
	public Users registerUser(@RequestBody Users user) {
		return usersService.register(user);
	}

	@PostMapping("/login")
	public Map<String, String> login(@Valid @RequestBody UsersDTO loginRequest) {
		UsernamePasswordAuthenticationToken authRequest =
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

		UserDetails userDetails = (UserDetails) authenticationManager.authenticate(authRequest).getPrincipal();

		String token = jwtService.generateToken(userDetails);
		return Map.of("token", token);
	}
}
