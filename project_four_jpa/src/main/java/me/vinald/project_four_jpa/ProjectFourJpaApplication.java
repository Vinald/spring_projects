package me.vinald.project_four_jpa;

import me.vinald.project_four_jpa.models.Author;
import me.vinald.project_four_jpa.repositories.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectFourJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectFourJpaApplication.class, args);
	}

//	@Bean
	public CommandLineRunner commandLineRunner(
			AuthorRepository authorRepository
	) {
		return args -> {
			var author = Author.builder()
					.firstName("John1")
					.lastName("Doe")
					.email("john1.doe@example.com")
					.age(26)
					.createdAt(java.time.LocalDateTime.now())
					.build();
			authorRepository.save(author);
		};
	}

}
