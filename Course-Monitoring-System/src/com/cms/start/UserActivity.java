package com.cms.start;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.cms.usecase.AdminUseCase;
import com.cms.usecase.BatchUseCase;
import com.cms.usecase.CoursePlanUseCase;
import com.cms.usecase.CourseUseCase;
import com.cms.usecase.FacultyUseCase;

public class UserActivity {	
	
	@SuppressWarnings("resource")
	public static void selectUser() {

		
		Scanner sc = new Scanner(System.in);		
		System.out
				.println("\nChoose an options - \n" + "1. Admin Login\n" + "2. Faculty Login\n" + "3. Exit ");

		System.out.println("\nEnter any number from above: ");

		int choice = 0;
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
			FacultyUseCase.facultyLogin();
			break;
		case 3:
			System.out.println("Thank you!");
			break;
		default:
			System.out.println("Invalid choice!");
			System.out.println();

			System.out.println("Try again...");
			UserActivity.selectUser();
		}
	}

	@SuppressWarnings("resource")
	public static void adminOptions() {

		System.out.println("\nChoose an options - ");
		System.out.println("1. Course (Create,Update,View)\r\n" + "2. Batch (Create,Update,View)\r\n"
				+ "3. Faculty (Create,Update,View)\r\n" + "4. Course-Plan (Create, Update, and View)\r\n"
				+ "5. Course Plan for Every Batch (Day wise)\r\n" + "6. Report (for every batch)\r\n"
				+ "7. Exit (Admin Logout)");

		System.out.println("\nEnter any number from above");

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
			UserActivity.coursePlanOptions();
			break;
		}
		case 5: {
			UserActivity.dayWiseUpdateBatch();
			break;
		}
		case 6: {
			UserActivity.reportForBatch();
			break;
		}
		case 7: {
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

	@SuppressWarnings("resource")
	public static void courseOptions() {

		System.out.println("\nCreate, Update, View Course");
		System.out.println("----------------------------------");

		System.out.println("\nChoose an options - \r\n" + "1. Create Course\r\n" + "2. Update Course by Name\r\n"
				+ "3. View All Courses\r\n" + "4. Delete Course by Name\r\n" + "5. Exit (Get Admin Options)");

		Scanner sc = new Scanner(System.in);

		System.out.println("\nEnter any number from above:");

		int choice = 0;
		try {
			choice = sc.nextInt();
		} catch (InputMismatchException e) {

			System.out.println("Invalid input!");

			System.out.println();
			System.out.println("Try again...");

			UserActivity.courseOptions();
		}

		switch (choice) {
		case 1:
			CourseUseCase.courseCreate();
			break;
		case 2:
			CourseUseCase.courseUpdateByName();
			break;
		case 3:
			CourseUseCase.courseViewAll();
			break;
		case 4:
			CourseUseCase.courseDeleteByName();
			break;
		case 5:
			UserActivity.adminOptions();
			break;
		default:
			System.out.println("Invalid choice!");
			System.out.println();

			System.out.println("Try again...");
			UserActivity.courseOptions();
		}

	}

	@SuppressWarnings("resource")
	public static void batchOptions() {

		System.out.println("\nCreate, Update and View Batch");
		System.out.println("----------------------------------");

		System.out.println("\nChoose an options - \r\n" + "1. Create Batch \r\n" + "2. Update Batch by Name\r\n"
				+ "3. View All Batch\r\n" + "4. Delete the Batch\r\n" + "5. Exit (Get Admin Options)");

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("Enter any number from above:");

		int choice = 0;
		try {
			choice = sc.nextInt();
		} catch (InputMismatchException e) {

			System.out.println("Invalid input!");

			System.out.println();
			System.out.println("Try again...");

			UserActivity.batchOptions();
		}

		switch (choice) {
		case 1:
			BatchUseCase.createBatch();
			break;
		case 2:
			BatchUseCase.batchUpdateByName();
			break;
		case 3:
			BatchUseCase.viewAllBatch();
			break;
		case 4:
			BatchUseCase.batchDeleteByName();
			break;
		case 5:
			UserActivity.adminOptions();
			break;
		default:
			System.out.println("Invalid choice!");
			System.out.println();

			System.out.println("Try again...");
			UserActivity.facultyOptions();
		}

	}

	@SuppressWarnings("resource")
	public static void facultyCanDo() {

		System.out.println(
				"\nChoose an options - \r\n" + "1. View the Course Plan\r\n" + "2. Fill up the Course Planner\r\n"
						+ "3. Update your password\r\n" + "4. Exit (Faculty Logout)");

		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nEnter any number from above:");

		int choice = 0;
		try {
			choice = sc.nextInt();
		} catch (InputMismatchException e) {

			System.out.println("\nInvalid input!");
			System.out.println("\nTry again...");

			UserActivity.facultyCanDo();
		}

		switch (choice) {
		case 1:
			FacultyUseCase.viewCoursePlan();
			break;
		case 2:
			FacultyUseCase.fillUpDayWisePlanner();
			break;
		case 3:
			FacultyUseCase.updateFacultyPassword();
			break;
		case 4:
			FacultyUseCase.facultyLogout();
			break;
		default:
			System.out.println("Invalid choice!");
			System.out.println();

			System.out.println("Try again...");
			UserActivity.facultyOptions();
		}

	}

	@SuppressWarnings("resource")
	public static void facultyOptions() {

		System.out.println("\nCreate, Update, View Faculty");
		System.out.println("----------------------------------");

		System.out.println("\nChoose an options - \r\n" + "1. Create Faculty\r\n" + "2. Update Faculty by ID\r\n"
				+ "3. View All Faculty\r\n" + "4. Delete Faculty by ID\r\n" + "5. Exit (Get Admin Options)");

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("Enter any number from above:");

		int choice = 0;
		try {
			choice = sc.nextInt();
		} catch (InputMismatchException e) {

			System.out.println("Invalid input!");

			System.out.println();
			System.out.println("Try again...");

			UserActivity.facultyOptions();
		}

		switch (choice) {
		case 1:
			FacultyUseCase.facultyRegister();
			break;
		case 2:
			FacultyUseCase.facultyUpdateById();
			break;
		case 3:
			FacultyUseCase.facultyView();
			break;
		case 4:
			FacultyUseCase.facultyDeleteById();
			break;
		case 5:
			UserActivity.adminOptions();
			break;
		default:
			System.out.println("Invalid choice!");
			System.out.println();

			System.out.println("Try again...");
			UserActivity.facultyOptions();
		}
	}

	@SuppressWarnings("resource")
	public static void coursePlanOptions() {

		System.out.println("\nCreate, Update, View Course Plan");
		System.out.println("----------------------------------");

		System.out.println("\nChoose an options - \r\n" + "1. Create Course Plan\r\n"
				+ "2. Update Course Plan by ID\r\n" + "3. View All Course Plan\r\n" + "4. Delete Course Plan by ID\r\n"
				+ "5. Exit (Get Admin Options)");

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("Enter any number from above:");

		int choice = 0;
		try {
			choice = sc.nextInt();
		} catch (InputMismatchException e) {

			System.out.println("Invalid input!");

			System.out.println();
			System.out.println("Try again...");

			UserActivity.coursePlanOptions();
		}

		switch (choice) {
		case 1:
			CoursePlanUseCase.createCoursePlan();
			break;
		case 2:
			CoursePlanUseCase.coursePlanUpdateById();
			break;
		case 3:
			CoursePlanUseCase.viewAllCoursePlans();
			break;
		case 4:
			CoursePlanUseCase.coursePlanDeleteById();
			break;
		case 5:
			UserActivity.adminOptions();
			break;
		default:
			System.out.println("Invalid choice!");
			System.out.println();

			System.out.println("Try again...");
			UserActivity.coursePlanOptions();
		}

	}

	public static void dayWiseUpdateBatch() {

		CoursePlanUseCase.dayWiseCoursePlanUpdateForEveryBatch();
	}

	public static void reportForBatch() {

		BatchUseCase.coursePlanReportForEveryBatch();
	}

}
