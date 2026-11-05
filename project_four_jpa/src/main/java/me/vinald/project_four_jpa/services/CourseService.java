package me.vinald.project_four_jpa.services;

import me.vinald.project_four_jpa.dto.CourseRequest;
import me.vinald.project_four_jpa.dto.CourseResponse;
import me.vinald.project_four_jpa.exceptions.ConflictException;
import me.vinald.project_four_jpa.exceptions.NotFoundException;
import me.vinald.project_four_jpa.mappers.CourseMapper;
import me.vinald.project_four_jpa.models.Author;
import me.vinald.project_four_jpa.models.Course;
import me.vinald.project_four_jpa.repositories.AuthorRepository;
import me.vinald.project_four_jpa.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {
	private final CourseRepository courseRepository;
	private final AuthorRepository authorRepository;
	private final AuthorService authorService;

	@Transactional(readOnly = true)
	public List<CourseResponse> findAll() {
		return courseRepository.findAll().stream().map(CourseMapper::toResponse).toList();
	}

	@Transactional(readOnly = true)
	public CourseResponse findById(Integer id) {
		return CourseMapper.toResponse(getCourse(id));
	}

	public CourseResponse create(CourseRequest request) {
		Course course = new Course();
		course.setTitle(request.title());
		course.setDescription(request.description());
		course.setAuthors(resolveAuthors(request.authorIds()));
		return CourseMapper.toResponse(courseRepository.save(course));
	}

	public CourseResponse update(Integer id, CourseRequest request) {
		Course course = getCourse(id);
		course.setTitle(request.title());
		course.setDescription(request.description());
		if (request.authorIds() != null) {
			course.getAuthors().clear();
			course.getAuthors().addAll(resolveAuthors(request.authorIds()));
		}
		return CourseMapper.toResponse(course);
	}

	public void delete(Integer id) {
		Course course = getCourse(id);
		if (course.getSections() != null && !course.getSections().isEmpty()) {
			throw new ConflictException("Course " + id + " still has sections; delete them first");
		}
		courseRepository.delete(course);
	}

	public CourseResponse addAuthor(Integer courseId, Integer authorId) {
		Course course = getCourse(courseId);
		Author author = authorService.getAuthor(authorId);
		boolean alreadyLinked = course.getAuthors().stream().anyMatch(a -> a.getId().equals(authorId));
		if (!alreadyLinked) {
			course.getAuthors().add(author);
		}
		return CourseMapper.toResponse(course);
	}

	public CourseResponse removeAuthor(Integer courseId, Integer authorId) {
		Course course = getCourse(courseId);
		if (!course.getAuthors().removeIf(a -> a.getId().equals(authorId))) {
			throw new NotFoundException("Author on course " + courseId, authorId);
		}
		return CourseMapper.toResponse(course);
	}

	Course getCourse(Integer id) {
		return courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course", id));
	}

	private List<Author> resolveAuthors(List<Integer> authorIds) {
		if (authorIds == null || authorIds.isEmpty()) {
			return new ArrayList<>();
		}
		Set<Integer> wanted = new HashSet<>(authorIds);
		List<Author> found = authorRepository.findAllById(wanted);
		if (found.size() != wanted.size()) {
			found.forEach(a -> wanted.remove(a.getId()));
			throw new NotFoundException("Author", wanted);
		}
		return new ArrayList<>(found);
	}
}
