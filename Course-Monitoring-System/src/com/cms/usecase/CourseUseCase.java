package com.cms.usecase;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cms.bean.Course;
import com.cms.dao.CourseDao;
import com.cms.dao.CourseDaoImp;
import com.cms.exceptions.CourseException;
import com.cms.start.UserActivity;

public class CourseUseCase {

	public static void courseCreate() {

		CourseDao dao = new CourseDaoImp();
		Course course = new Course();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter course details - ");

		System.out.println("Enter course name");
		String name = sc.next();

		try {
			boolean res = dao.isNameUnique(name);

			if (res) {
				System.out.println("\nThis course name already exists!");

				System.out.println("\nTry again...");
				UserActivity.courseOptions();
			}

		} catch (CourseException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}

		int fee = 0;
		try {

			System.out.println("Enter course fee");
			fee = sc.nextInt();

		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block

			System.out.println("\nInvalid input!");

			System.out.println("\nTry again...");
			UserActivity.courseOptions();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Enter course description");
		String description = sc.next();

		course.setCourseName(name);
		course.setFee(fee);
		course.setCourseDescription(description);

		try {
			String result = dao.createCourse(course);
			System.out.println("\n" + result);

		} catch (CourseException e) {
			// TODO Auto-generated catch block

			System.out.println("\n" + e.getMessage());

			System.out.println("\nTry again...");
			UserActivity.courseOptions();

		}

		UserActivity.courseOptions();

	}

	public static void courseUpdateByName() {

		CourseDao dao = new CourseDaoImp();
		Course course = new Course();

		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter course name to update - ");

		System.out.println("Enter old course name");
		String old_name = sc.next();

		try {
			boolean res = dao.isCourseNameAvailable(old_name);

			if (res==false) {
				System.out.println("\nThis course is not exists!");

				System.out.println("\nTry again...");
				UserActivity.courseOptions();
			}

		} catch (CourseException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}

		System.out.println("Enter new course name");
		String new_name = sc.next();

		int fee = 0;
		try {

			System.out.println("Enter new fee");
			fee = sc.nextInt();

		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block

			System.out.println("\nFee require numeric value!");

			System.out.println("\nTry again...");
			UserActivity.courseOptions();
		}

		System.out.println("Enter new description");
		String description = sc.next();

		course.setCourseName(new_name);
		course.setFee(fee);
		course.setCourseDescription(description);

		String result;
		try {
			result = dao.upadteCourseByName(old_name, course);
			System.out.println("\n" + result);

		} catch (CourseException e) {
			// TODO Auto-generated catch block

			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			UserActivity.courseOptions();
		}

		UserActivity.courseOptions();

	}

	public static void courseViewAll() {

		try {

			List<Course> courses = new CourseDaoImp().viewAllCourseDetails();

			courses.forEach(c -> {

				System.out.println("Course ID : " + c.getCourseId());
				System.out.println("Course Name : " + c.getCourseName());
				System.out.println("Course Fee : " + c.getFee());
				System.out.println("Course Description : " + c.getCourseDescription());

				System.out.println("==================================");
			});

		} catch (CourseException e) {
			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			UserActivity.courseOptions();

		}

		UserActivity.courseOptions();

	}

	public static void courseDeleteByName() {

		try {

			String response = new CourseDaoImp().courseDeleteByName();
			System.out.println("\n" + response);

		} catch (CourseException e) {
			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			UserActivity.courseOptions();

		}

		UserActivity.courseOptions();

	}

}
