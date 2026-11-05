package me.vinald.project_four_jpa.controller;

import me.vinald.project_four_jpa.dto.AuthorRequest;
import me.vinald.project_four_jpa.dto.AuthorResponse;
import me.vinald.project_four_jpa.services.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
	private final AuthorService authorService;

	@GetMapping
	public List<AuthorResponse> findAll() {
		return authorService.findAll();
	}

	@GetMapping("/{id}")
	public AuthorResponse findById(@PathVariable Integer id) {
		return authorService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AuthorResponse create(@Valid @RequestBody AuthorRequest request) {
		return authorService.create(request);
	}

	@PutMapping("/{id}")
	public AuthorResponse update(@PathVariable Integer id, @Valid @RequestBody AuthorRequest request) {
		return authorService.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		authorService.delete(id);
	}
}
