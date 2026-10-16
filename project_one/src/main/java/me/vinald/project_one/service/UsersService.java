package me.vinald.project_one.service;

import me.vinald.project_one.model.Users;
import me.vinald.project_one.repository.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
	private final UsersRepository usersRepository;

	public  UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public Users register(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return usersRepository.save(user);
	}
}
