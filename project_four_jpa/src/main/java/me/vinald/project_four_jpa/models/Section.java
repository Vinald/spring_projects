package me.vinald.project_four_jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sectionId;
	private String name;
	private Integer sectionOrder;

	@ManyToOne
	@JoinTable(name = "course_section",
			joinColumns = @JoinColumn(name = "section_id"),
			inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	private Course course;

	@OneToMany(mappedBy = "section")
	private List<Lecture> lectures;
}
