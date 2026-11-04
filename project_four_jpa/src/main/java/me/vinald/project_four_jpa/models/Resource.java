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
public class Resource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer resourceId;
	private String name;
	private String size;
	private String url;

	@OneToOne
	@JoinColumn(name = "lecture_id")
	private Lecture lecture;
}
