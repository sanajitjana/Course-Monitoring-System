package com.masai.usecases;

import java.rmi.StubNotFoundException;
import java.util.Scanner;

import com.masai.bean.Student;
import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;

public class RegisterStudentUseCase2 {
	
	
	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter Student Name:");
		String sname= sc.next();
		
		System.out.println("Enter Student marks:");
		int marks= sc.nextInt();
		
		System.out.println("Enter Student Email:");
		String email= sc.next();
		
		System.out.println("Enter Student password:");
		String password= sc.next();
		
		
		StudentDao dao=new StudentDaoImpl();
		
		
		
		Student student= new Student();
		student.setName(sname);
		student.setMarks(marks);
		student.setEmail(email);
		student.setPassword(password);
		
		
		
		
		String result= dao.registerStudent2(student);
		
		System.out.println(result);
	}

}
