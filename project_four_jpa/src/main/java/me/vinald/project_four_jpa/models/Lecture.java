package me.vinald.project_four_jpa.models;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class Lecture extends BaseEntity{
	private String name;

	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section section;

	@OneToOne
	@JoinColumn(name = "resource_id")
	private Resource resource;
}
