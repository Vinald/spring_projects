package me.vinald.project_three_rest.dto;

public record StudentResponseDTO(
	String firstName,
	String lastName,
	String email,
	Integer age,
	String gender,
	Integer schoolId
) {
}
