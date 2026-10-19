package me.vinald.project_three_rest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;
	private  String firstName;
	private String lastName;
	@Column(unique = true)
	private  String email;
	private Integer age;
	private String gender;

	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	private StudentProfile studentprofile;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "school_id", referencedColumnName = "id")
	private School school;
}
