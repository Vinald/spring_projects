package me.vinald.project_four_jpa.repositories;

import me.vinald.project_four_jpa.models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
	List<Lecture> findBySectionId(Integer sectionId);
}
