package com.masai.dao;

import java.util.List;

import com.masai.bean.Student;
import com.masai.bean.StudentDTO;
import com.masai.exceptions.CourseException;
import com.masai.exceptions.StudentException;

public interface StudentDao {
	
	//4:20 pm
	
	
	public String registerStudent(String name,int marks,String email,String passwrod);

	public String registerStudent2(Student student);
	
	public Student getStudentByRoll(int roll)throws StudentException;

	
	public Student loginStudent(String username, String password)throws StudentException;
	
	
	public List<Student> getAllStudentDetails()throws StudentException;
	
	
	public String registerStudentInsideACourse(int cid, int roll)throws StudentException,CourseException ;
	
	public List<StudentDTO> getAllStudentsByCname(String cname)throws CourseException;
	
	
	
}
