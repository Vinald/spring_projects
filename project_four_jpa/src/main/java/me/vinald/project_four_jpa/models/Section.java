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
public class Section extends BaseEntity{
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
