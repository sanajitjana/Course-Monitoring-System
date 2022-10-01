package com.cms.dao;

import java.util.List;

import com.cms.bean.Course;
import com.cms.exceptions.CourseException;

public interface CourseDao {

	// checking course name unique or not
	public boolean isNameUnique(String name) throws CourseException;

	// create course
	public String createCourse(Course course) throws CourseException;

	// before update checking course name available or not
	public boolean isCourseNameAvailable(String name) throws CourseException;

	// update course by name
	public String upadteCourseByName(String old_name, Course course) throws CourseException;

	// view all courses details
	public List<Course> viewAllCourseDetails() throws CourseException;

	// delete course by name
	public String courseDeleteByName() throws CourseException;

}
