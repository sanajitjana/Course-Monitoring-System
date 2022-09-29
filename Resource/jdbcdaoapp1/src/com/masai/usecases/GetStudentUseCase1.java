package com.masai.usecases;

import java.util.Scanner;

import com.masai.bean.Student;
import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;
import com.masai.exceptions.StudentException;

public class GetStudentUseCase1 {

	public static void main(String[] args) {
		
		
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter Student Roll:");
		int roll= sc.nextInt();
		
		
		StudentDao dao= new StudentDaoImpl();
		
		try {
		Student student= dao.getStudentByRoll(roll);
		
			System.out.println(student);
		
		}catch (StudentException se) {
			System.out.println(se.getMessage());
		}
		
		
		

	}

}
