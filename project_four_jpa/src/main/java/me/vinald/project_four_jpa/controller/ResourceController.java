package me.vinald.project_four_jpa.controller;

import me.vinald.project_four_jpa.dto.ResourceRequest;
import me.vinald.project_four_jpa.dto.ResourceResponse;
import me.vinald.project_four_jpa.services.ResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {
	private final ResourceService resourceService;

	@GetMapping
	public List<ResourceResponse> findAll() {
		return resourceService.findAll();
	}

	@GetMapping("/{id}")
	public ResourceResponse findById(@PathVariable Integer id) {
		return resourceService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResourceResponse create(@Valid @RequestBody ResourceRequest request) {
		return resourceService.create(request);
	}

	@PutMapping("/{id}")
	public ResourceResponse update(@PathVariable Integer id, @Valid @RequestBody ResourceRequest request) {
		return resourceService.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		resourceService.delete(id);
	}
}
