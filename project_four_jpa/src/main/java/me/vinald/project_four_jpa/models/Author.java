package me.vinald.project_four_jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer authorId;
	private String firstName;
	private String lastName;
	@Column(unique = true, nullable = false)
	private String email;
	private Integer age;
	@Column(updatable = false, nullable = false)
	private LocalDateTime createdAt;
	@Column(insertable = false)
	private LocalDateTime lastModified;
}
