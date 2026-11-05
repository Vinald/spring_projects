package me.vinald.project_four_jpa.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
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
