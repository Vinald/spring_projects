package me.vinald.project_four_jpa.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class Course extends BaseEntity {
	private String title;
	private String description;

	@ManyToMany
	@JoinTable(
			name = "course_author",
			joinColumns = @JoinColumn(name = "course_id"),
			inverseJoinColumns = @JoinColumn(name = "author_id")
	)
	private List<Author> authors;

	@OneToMany(mappedBy = "course")
	private List<Section> sections;
}
