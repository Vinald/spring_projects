package me.vinald.project_one.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.vinald.project_one.model.UserPrincipal;
import me.vinald.project_one.model.Users;
import me.vinald.project_one.repository.UsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Issues one of our own JWTs once a social login (Google/GitHub) succeeds,
 * so the rest of the API keeps using the same Bearer-token authentication
 * as the username/password login flow.
 */
@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
	private final UsersRepository usersRepository;
	private final JwtService jwtService;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public OAuth2LoginSuccessHandler(UsersRepository usersRepository, JwtService jwtService) {
		this.usersRepository = usersRepository;
		this.jwtService = jwtService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										 Authentication authentication) throws IOException {
		String registrationId = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

		String username = "google".equals(registrationId)
				? oAuth2User.getAttribute("email")
				: oAuth2User.getAttribute("login"); // github

		Users user = usersRepository.findByUsername(username)
				.orElseGet(() -> {
					Users newUser = new Users();
					newUser.setUsername(username);
					newUser.setPassword(null);
					return usersRepository.save(newUser);
				});

		String token = jwtService.generateToken(new UserPrincipal(user));

		response.setContentType("application/json");
		response.getWriter().write(objectMapper.writeValueAsString(Map.of("token", token)));
	}
}
