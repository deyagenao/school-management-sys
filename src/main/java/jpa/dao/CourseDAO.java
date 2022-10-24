package jpa.dao;

import java.util.List;

import jpa.entitymodels.Course;

/**
 * The 'CourseDAO' interface creates the structure and declares the method to be implemented in the CourseService class.
 * @author deyaniragenao
 *
 */
public interface CourseDAO {

	List<Course> getAllCourses();
}
