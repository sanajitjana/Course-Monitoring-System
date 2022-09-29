package com.masai.usecases;

import java.util.List;
import java.util.Scanner;

import com.masai.bean.StudentDTO;
import com.masai.dao.StudentDao;
import com.masai.dao.StudentDaoImpl;

public class GetStudentsFromCourseUseCase {

	public static void main(String[] args) {
		
		
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter the Course Name");
		
		String cname= sc.next();

		
		StudentDao dao = new StudentDaoImpl();
		
		try {
		List<StudentDTO> dtos= dao.getAllStudentsByCname(cname);
		
		dtos.forEach(dto -> System.out.println(dto));
		
		
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}

}
