package me.vinald.project_four_jpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Resources {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer resourceId;
	private String name;
	private String size;
	private String url;
}
