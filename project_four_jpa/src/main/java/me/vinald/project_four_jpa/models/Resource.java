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

	@OneToOne(mappedBy = "resource")
	private Lecture lecture;

	private LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
	private String createdBy;
	private String lastModifiedBy;

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		lastModifiedAt = createdAt;
	}

	@PreUpdate
	protected void onUpdate() {
		lastModifiedAt = LocalDateTime.now();
	}
}
