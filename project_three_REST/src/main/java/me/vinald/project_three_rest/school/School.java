package me.vinald.project_three_rest.school;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.vinald.project_three_rest.student.Student;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Student> students;
}
