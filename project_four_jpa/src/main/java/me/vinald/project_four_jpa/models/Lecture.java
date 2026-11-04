package me.vinald.project_four_jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Lecture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer lectureId;
	private String name;

	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section section;

	@OneToOne
	@JoinColumn(name = "resource_id")
	private Resource resource;
}
