package me.vinald.project_four_jpa.exceptions;

public class ConflictException extends RuntimeException {
	public ConflictException(String message) {
		super(message);
	}
}
