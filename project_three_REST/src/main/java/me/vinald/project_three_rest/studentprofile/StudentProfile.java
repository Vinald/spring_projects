package me.vinald.project_three_rest.studentprofile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.vinald.project_three_rest.student.Student;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String bio;

	@OneToOne
	@JoinColumn(name = "student_id", referencedColumnName = "studentId")
	@JsonBackReference
	private Student student;
}
