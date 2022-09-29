package com.masai.usecases;

import java.util.List;

import com.masai.bean.Student;
import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;
import com.masai.exceptions.StudentException;

public class GetAllStudentUseCase {
	
	
	public static void main(String[] args) {
		
		StudentDao dao = new StudentDaoImpl();
		
		try {
		List<Student> students= dao.getAllStudentDetails();
		
		students.forEach(s ->{
			
			System.out.println("Student Name :"+s.getName());
			System.out.println("Student email: "+s.getEmail());
			
			System.out.println("=======================");
		});
		
		
		
		
		}catch(StudentException se) {
			System.out.println(se.getMessage());
		}
		
		
		
		
		
	}

}
