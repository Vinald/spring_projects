package me.vinald.project_three_rest.student;


public record StudentDTO(
	String firstName,
	String lastName,
	String email,
	Integer age,
	String gender,
	Integer schoolId
) {

}
