package me.vinald.project_four_jpa.config;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.vinald.project_four_jpa.models.*;
import me.vinald.project_four_jpa.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Fills an empty database with fake authors, courses, sections, lectures and resources.
 * Runs only when {@code app.seed.enabled=true} and the author table is empty, so restarts don't duplicate data.
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.seed.enabled", havingValue = "true")
public class DataSeeder implements CommandLineRunner {
	private static final int AUTHORS = 10;
	private static final int COURSES = 5;

	private final AuthorRepository authorRepository;
	private final CourseRepository courseRepository;
	private final SectionRepository sectionRepository;
	private final LectureRepository lectureRepository;
	private final ResourceRepository resourceRepository;

	private final Faker faker = new Faker();

	@Override
	@Transactional
	public void run(String... args) {
		if (authorRepository.count() > 0) {
			log.info("Database already has data; skipping seed");
			return;
		}

		List<Author> authors = authorRepository.saveAll(createAuthors());
		int sections = 0;
		int lectures = 0;

		for (int c = 0; c < COURSES; c++) {
			Course course = courseRepository.save(Course.builder()
					.title(faker.educator().course())
					.description(faker.lorem().paragraph())
					.authors(pickAuthors(authors))
					.build());

			int sectionCount = faker.number().numberBetween(3, 6);
			for (int s = 1; s <= sectionCount; s++) {
				Section section = sectionRepository.save(Section.builder()
						.name(faker.lorem().words(3).stream().map(this::capitalize).reduce((a, b) -> a + " " + b).orElse("Section"))
						.sectionOrder(s)
						.course(course)
						.build());
				sections++;

				int lectureCount = faker.number().numberBetween(2, 5);
				for (int l = 0; l < lectureCount; l++) {
					Resource resource = resourceRepository.save(createResource());
					lectureRepository.save(Lecture.builder()
							.name(capitalize(faker.lorem().sentence(4)))
							.section(section)
							.resource(resource)
							.build());
					lectures++;
				}
			}
		}

		log.info("Seeded {} authors, {} courses, {} sections, {} lectures (each with a resource)",
				authors.size(), COURSES, sections, lectures);
	}

	private List<Author> createAuthors() {
		List<Author> authors = new ArrayList<>();
		for (int i = 1; i <= AUTHORS; i++) {
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			authors.add(Author.builder()
					.firstName(firstName)
					.lastName(lastName)
					// index keeps emails unique even if faker repeats a name
					.email((firstName + "." + lastName + i + "@example.com").toLowerCase().replaceAll("[^a-z0-9.@]", ""))
					.age(faker.number().numberBetween(25, 70))
					.build());
		}
		return authors;
	}

	private List<Author> pickAuthors(List<Author> authors) {
		List<Author> shuffled = new ArrayList<>(authors);
		Collections.shuffle(shuffled);
		return new ArrayList<>(shuffled.subList(0, faker.number().numberBetween(1, 4)));
	}

	private Resource createResource() {
		String slug = faker.lorem().word();
		String size = faker.number().numberBetween(1, 500) + "MB";
		return switch (faker.number().numberBetween(0, 3)) {
			case 0 -> Video.builder()
					.name(slug + ".mp4").size(size).url("https://cdn.example.com/videos/" + slug + ".mp4")
					.length(faker.number().numberBetween(60, 3600))
					.build();
			case 1 -> Text.builder()
					.name(slug + ".md").size(size).url("https://cdn.example.com/texts/" + slug + ".md")
					.content(faker.lorem().paragraph(3))
					.build();
			default -> File.builder()
					.name(slug + ".pdf").size(size).url("https://cdn.example.com/files/" + slug + ".pdf")
					.type("pdf")
					.build();
		};
	}

	private String capitalize(String s) {
		return s.isEmpty() ? s : Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}
}
