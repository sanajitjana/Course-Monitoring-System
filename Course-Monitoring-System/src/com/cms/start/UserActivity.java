package com.cms.start;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.cms.usecase.AdminUseCase;
import com.cms.usecase.CourseUseCase;

public class UserActivity {

	public static void selectUser() {
		
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("Choose an options - \n1. Admin Login\n2. Faculty Login\n3. Faculty Register");

		System.out.println();
		System.out.println("Enter any number from above: ");
		
		int choice=0;
		try {		
			choice = sc.nextInt();
		} catch (InputMismatchException e) {
			
			System.out.println("Invalid input!");
			
			System.out.println();
			System.out.println("Try again...");
			
			UserActivity.selectUser();
		}
		

		switch (choice) {
		case 1:
			AdminUseCase.adminLogin();
			break;
		case 2:
			CourseUseCase.createFaculty();
			break;
		case 3:
			System.out.println("Admin login required!");
			AdminUseCase.adminLogin();
			break;
		default:
			System.out.println("Invalid choice!");
			System.out.println();
			
			System.out.println("Try again...");
			UserActivity.selectUser();			
		}
	}

	public static void adminOptions() {

		System.out.println("Choose an options - ");
		System.out.println("1. Create, Update, View Course.\r\n"
				+ "2. Create, Update, View Batch. (A batch is related to a course.) \r\n"
				+ "3. Create, Update, View Faculty.\r\n" + "4. Allocate faculty to a batch.\r\n"
				+ "5. Create, Update, View Course plan.\r\n" + "6. View the Day wise update of every batch.\r\n"
				+ "7. Generate Report for every batch.\r\n"+"8. Exit (Admin Logout)");

		System.out.println();
		System.out.println("Enter any number from above");

		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();

		switch (choice) {
		case 1: {
			UserActivity.courseOptions();
			break;
		}
		case 2: {
			UserActivity.batchOptions();
			break;
		}
		case 3: {
			UserActivity.facultyOptions();
			break;
		}
		case 4: {
			UserActivity.allocateFacultyToBatch();
			break;
		}
		case 5: {
			UserActivity.coursePlanOptions();
			break;
		}
		case 6: {
			UserActivity.dayWiseUpdateBatch();
			break;
		}
		case 7: {
			UserActivity.reportForBatch();
			break;
		}
		case 8: {
			AdminUseCase.adminLogout();
			break;
		}
		default:
			System.out.println("Invalid choice!");
			System.out.println();
			
			System.out.println("Try again...");
			UserActivity.adminOptions();
		}

	}

	public static void courseOptions() {

		System.out.println();
		System.out.println("Create, Update, View Course.");

	}

	public static void batchOptions() {

		System.out.println();
		System.out.println("Create, Update, View Batch. (A batch is related to a course.)");

	}
	
	public static void facultyOptions() {

		System.out.println();
		System.out.println("Create, Update, View Faculty.");

	}
	
	
	public static void allocateFacultyToBatch() {

		System.out.println();
		System.out.println("Allocate faculty to a batch.");

	}

	
	
	public static void coursePlanOptions() {

		System.out.println();
		System.out.println("Create, Update, View Faculty.");

	}
	
	
	public static void dayWiseUpdateBatch() {

		System.out.println();
		System.out.println("View the Day wise update of every batch.");

	}
	
	
	public static void reportForBatch() {

		System.out.println();
		System.out.println("Generate Report for every batch.");

	}
	

}
