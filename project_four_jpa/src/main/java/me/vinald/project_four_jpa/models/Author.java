package me.vinald.project_four_jpa.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Author extends BaseEntity {
	private String firstName;
	private String lastName;
	@Column(unique = true, nullable = false)
	private String email;
	private Integer age;

	@ManyToMany(mappedBy = "authors")
	private List<Course> courses;
}
