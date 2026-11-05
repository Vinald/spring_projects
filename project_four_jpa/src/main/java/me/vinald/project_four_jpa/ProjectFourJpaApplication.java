package me.vinald.project_four_jpa;

import com.github.javafaker.Faker;
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

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthorRepository authorRepository
	) {
		return args -> {
			for (int i = 0; i < 50; i++) {
				Faker faker = new Faker();
				var author = Author.builder()
						.firstName(faker.name().firstName())
						.lastName(faker.name().lastName())
						.email(faker.internet().emailAddress())
						.age(faker.number().numberBetween(20, 50))
						.createdBy("System")
						.createdAt(java.time.LocalDateTime.now())
						.build();
				authorRepository.save(author);
			}
		};
	}

}
