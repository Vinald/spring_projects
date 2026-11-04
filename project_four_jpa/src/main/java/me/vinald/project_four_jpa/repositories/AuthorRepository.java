package me.vinald.project_four_jpa.repositories;

import me.vinald.project_four_jpa.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
