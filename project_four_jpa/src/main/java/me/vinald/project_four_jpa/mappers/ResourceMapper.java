package me.vinald.project_four_jpa.mappers;

import me.vinald.project_four_jpa.dto.ResourceRequest;
import me.vinald.project_four_jpa.dto.ResourceResponse;
import me.vinald.project_four_jpa.dto.ResourceType;
import me.vinald.project_four_jpa.models.File;
import me.vinald.project_four_jpa.models.Resource;
import me.vinald.project_four_jpa.models.Text;
import me.vinald.project_four_jpa.models.Video;

public final class ResourceMapper {
	private ResourceMapper() {
	}

	public static ResourceType typeOf(Resource resource) {
		return switch (resource) {
			case Video v -> ResourceType.VIDEO;
			case Text t -> ResourceType.TEXT;
			case File f -> ResourceType.FILE;
			default -> throw new IllegalStateException("Unknown resource subtype: " + resource.getClass());
		};
	}

	public static Resource newEntity(ResourceType type) {
		return switch (type) {
			case VIDEO -> new Video();
			case TEXT -> new Text();
			case FILE -> new File();
		};
	}

	public static ResourceResponse toResponse(Resource resource) {
		return new ResourceResponse(
				resource.getId(),
				typeOf(resource),
				resource.getName(),
				resource.getSize(),
				resource.getUrl(),
				resource.getLecture() != null ? resource.getLecture().getId() : null,
				resource instanceof Video v ? v.getLength() : null,
				resource instanceof Text t ? t.getContent() : null,
				resource instanceof File f ? f.getType() : null,
				resource.getCreatedAt(),
				resource.getLastModifiedAt()
		);
	}

	public static void apply(ResourceRequest request, Resource resource) {
		resource.setName(request.name());
		resource.setSize(request.size());
		resource.setUrl(request.url());
		switch (resource) {
			case Video v -> v.setLength(request.length());
			case Text t -> t.setContent(request.content());
			case File f -> f.setType(request.fileType());
			default -> {
			}
		}
	}
}
