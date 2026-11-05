package me.vinald.project_four_jpa.repositories;

import me.vinald.project_four_jpa.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Integer> {
	List<Section> findByCourseIdOrderBySectionOrderAsc(Integer courseId);
}
