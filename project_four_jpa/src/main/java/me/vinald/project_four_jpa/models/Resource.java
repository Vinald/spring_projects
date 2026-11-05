package me.vinald.project_four_jpa.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "resource_type", discriminatorType = DiscriminatorType.STRING)
public class Resource{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String size;
	private String url;

	@OneToOne
	@JoinColumn(name = "lecture_id")
	private Lecture lecture;

	private LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
	private String createdBy;
	private String lastModifiedBy;
}
