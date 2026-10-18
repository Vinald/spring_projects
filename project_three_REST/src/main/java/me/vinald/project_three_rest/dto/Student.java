package me.vinald.project_three_rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private Integer studentId;
	private  String firstName;
	private String lastName;
	private  String email;
	private Integer age;
	private String gender;
}
