package com.masai.usecases;

import java.util.Scanner;

import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;

public class RegisterStudentUseCase1 {
	
	
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
		
		//StudentDaoImpl dao = new StudentDaoImpl();
		
		String result= dao.registerStudent(sname, marks, email, password);
		
		System.out.println(result);
	}

}
