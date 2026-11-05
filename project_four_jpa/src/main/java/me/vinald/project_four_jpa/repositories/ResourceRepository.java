package me.vinald.project_four_jpa.repositories;

import me.vinald.project_four_jpa.models.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}
