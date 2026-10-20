package me.vinald.project_three_rest.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	List<Student> findAllByFirstNameContainingIgnoreCase(@RequestParam String firstName);
}
