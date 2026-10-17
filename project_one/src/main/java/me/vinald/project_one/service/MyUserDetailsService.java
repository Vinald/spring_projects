package me.vinald.project_one.service;

import me.vinald.project_one.model.UserPrincipal;
import me.vinald.project_one.model.Users;
import me.vinald.project_one.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
	private final UsersRepository usersRepository;

	public MyUserDetailsService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = usersRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

		return new UserPrincipal(user);
	}
}
