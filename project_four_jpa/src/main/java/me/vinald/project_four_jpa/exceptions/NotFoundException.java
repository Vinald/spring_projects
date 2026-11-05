package me.vinald.project_four_jpa.exceptions;

public class NotFoundException extends RuntimeException {
	public NotFoundException(String entity, Object id) {
		super(entity + " with id " + id + " not found");
	}
}
