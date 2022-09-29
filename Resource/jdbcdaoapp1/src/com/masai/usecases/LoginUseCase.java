package com.masai.usecases;

import java.util.Scanner;

import com.masai.bean.Student;
import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;
import com.masai.exceptions.StudentException;

public class LoginUseCase {
	
	
	public static void main(String[] args) {
		
		
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter Username:");
		String uname = sc.next();
		
		System.out.println("Enter Password:");
		String pass = sc.next();
		
		StudentDao dao = new StudentDaoImpl();
		
		try {
			Student student= dao.loginStudent(uname, pass);
		
	 		System.out.println("Welcome Student :"+student.getName());
	 	
	 	
		}catch (StudentException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		
	}

}
