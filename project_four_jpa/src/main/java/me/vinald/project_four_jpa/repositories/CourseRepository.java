package me.vinald.project_four_jpa.repositories;

import me.vinald.project_four_jpa.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
