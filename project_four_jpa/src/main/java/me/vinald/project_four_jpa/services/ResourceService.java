package me.vinald.project_four_jpa.services;

import me.vinald.project_four_jpa.dto.ResourceRequest;
import me.vinald.project_four_jpa.dto.ResourceResponse;
import me.vinald.project_four_jpa.exceptions.BadRequestException;
import me.vinald.project_four_jpa.exceptions.NotFoundException;
import me.vinald.project_four_jpa.mappers.ResourceMapper;
import me.vinald.project_four_jpa.models.Resource;
import me.vinald.project_four_jpa.repositories.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ResourceService {
	private final ResourceRepository resourceRepository;

	@Transactional(readOnly = true)
	public List<ResourceResponse> findAll() {
		return resourceRepository.findAll().stream().map(ResourceMapper::toResponse).toList();
	}

	@Transactional(readOnly = true)
	public ResourceResponse findById(Integer id) {
		return ResourceMapper.toResponse(getResource(id));
	}

	public ResourceResponse create(ResourceRequest request) {
		Resource resource = ResourceMapper.newEntity(request.type());
		ResourceMapper.apply(request, resource);
		return ResourceMapper.toResponse(resourceRepository.save(resource));
	}

	public ResourceResponse update(Integer id, ResourceRequest request) {
		Resource resource = getResource(id);
		if (ResourceMapper.typeOf(resource) != request.type()) {
			throw new BadRequestException("Cannot change resource type from " + ResourceMapper.typeOf(resource)
					+ " to " + request.type() + "; delete and recreate it instead");
		}
		ResourceMapper.apply(request, resource);
		return ResourceMapper.toResponse(resource);
	}

	public void delete(Integer id) {
		Resource resource = getResource(id);
		// Lecture owns the lecture -> resource foreign key, so clear it before deleting.
		if (resource.getLecture() != null) {
			resource.getLecture().setResource(null);
		}
		resourceRepository.delete(resource);
	}

	Resource getResource(Integer id) {
		return resourceRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource", id));
	}
}
