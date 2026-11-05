package me.vinald.project_four_jpa.services;

import me.vinald.project_four_jpa.dto.AuthorRequest;
import me.vinald.project_four_jpa.dto.AuthorResponse;
import me.vinald.project_four_jpa.exceptions.ConflictException;
import me.vinald.project_four_jpa.exceptions.NotFoundException;
import me.vinald.project_four_jpa.mappers.AuthorMapper;
import me.vinald.project_four_jpa.models.Author;
import me.vinald.project_four_jpa.models.Course;
import me.vinald.project_four_jpa.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorService {
	private final AuthorRepository authorRepository;

	@Transactional(readOnly = true)
	public List<AuthorResponse> findAll() {
		return authorRepository.findAll().stream().map(AuthorMapper::toResponse).toList();
	}

	@Transactional(readOnly = true)
	public AuthorResponse findById(Integer id) {
		return AuthorMapper.toResponse(getAuthor(id));
	}

	public AuthorResponse create(AuthorRequest request) {
		if (authorRepository.existsByEmail(request.email())) {
			throw new ConflictException("An author with email " + request.email() + " already exists");
		}
		Author author = new Author();
		AuthorMapper.apply(request, author);
		return AuthorMapper.toResponse(authorRepository.save(author));
	}

	public AuthorResponse update(Integer id, AuthorRequest request) {
		Author author = getAuthor(id);
		if (authorRepository.existsByEmailAndIdNot(request.email(), id)) {
			throw new ConflictException("An author with email " + request.email() + " already exists");
		}
		AuthorMapper.apply(request, author);
		return AuthorMapper.toResponse(author);
	}

	public void delete(Integer id) {
		Author author = getAuthor(id);
		// Course owns the course_author join table, so unlink from that side first.
		if (author.getCourses() != null) {
			for (Course course : author.getCourses()) {
				course.getAuthors().removeIf(a -> a.getId().equals(id));
			}
		}
		authorRepository.delete(author);
	}

	Author getAuthor(Integer id) {
		return authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author", id));
	}
}
