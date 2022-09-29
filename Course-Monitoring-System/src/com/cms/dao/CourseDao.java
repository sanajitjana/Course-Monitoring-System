package com.cms.dao;

import java.util.List;

import com.cms.bean.Course;
import com.cms.exceptions.CourseException;

public interface CourseDao {
	
	//create course
	public String createCourse(Course course)throws CourseException;
	
	
	//update course by name
	public String upadteCourseByName(String old_name, Course course)throws CourseException;
	
	
	//view all courses details
	public List<Course> viewAllCourseDetails()throws CourseException;
	
	
	//delete course by name
	public String courseDeleteByName()throws CourseException;

}
