package me.vinald.project_four_jpa.models;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
public class Resource extends BaseEntity{
	private String name;
	private String size;
	private String url;

	@OneToOne
	@JoinColumn(name = "lecture_id")
	private Lecture lecture;
}
