package me.vinald.project_four_jpa.mappers;

import me.vinald.project_four_jpa.dto.AuthorRequest;
import me.vinald.project_four_jpa.dto.AuthorResponse;
import me.vinald.project_four_jpa.dto.AuthorSummary;
import me.vinald.project_four_jpa.models.Author;

public final class AuthorMapper {
	private AuthorMapper() {
	}

	public static AuthorResponse toResponse(Author author) {
		return new AuthorResponse(
				author.getId(),
				author.getFirstName(),
				author.getLastName(),
				author.getEmail(),
				author.getAge(),
				author.getCreatedAt(),
				author.getLastModifiedAt()
		);
	}

	public static AuthorSummary toSummary(Author author) {
		return new AuthorSummary(author.getId(), author.getFirstName(), author.getLastName(), author.getEmail());
	}

	public static void apply(AuthorRequest request, Author author) {
		author.setFirstName(request.firstName());
		author.setLastName(request.lastName());
		author.setEmail(request.email());
		author.setAge(request.age());
	}
}
