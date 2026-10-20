package me.vinald.project_three_rest.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.vinald.project_three_rest.school.School;
import me.vinald.project_three_rest.studentprofile.StudentProfile;

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
	@JsonManagedReference
	private StudentProfile studentprofile;

	@ManyToOne
	@JoinColumn(name = "school_id", referencedColumnName = "id")
	@JsonBackReference
	private School school;
}
