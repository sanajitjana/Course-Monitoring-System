package com.cms.usecase;

import java.util.List;
import java.util.Scanner;

import com.cms.bean.Course;
import com.cms.bean.Faculty;
import com.cms.dao.CourseDao;
import com.cms.dao.CourseDaoImp;
import com.cms.dao.FacultyDao;
import com.cms.dao.FacultyDaoImp;
import com.cms.exceptions.CourseException;
import com.cms.exceptions.FacultyException;
import com.cms.start.UserActivity;

public class CourseUseCase {

	public static void courseCreate() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter course details - ");

		System.out.println("Enter course name");
		String name = sc.next();

		System.out.println("Enter course fee");
		int fee = sc.nextInt();

		System.out.println("Enter course description");
		String description = sc.next();

		CourseDao dao = new CourseDaoImp();
		Course course = new Course();

		course.setCourseName(name);
		course.setFee(fee);
		course.setCourseDescription(description);

		try {
			String result = dao.createCourse(course);
			System.out.println(result);

		} catch (CourseException e) {
			// TODO Auto-generated catch block

			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			UserActivity.courseOptions();

		}

		UserActivity.courseOptions();

	}

	public static void courseUpdateByName() {

		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter course name to update - ");

		System.out.println("Enter the course name");
		String old_name = sc.next();

		System.out.println("Enter new name");
		String new_name = sc.next();

		System.out.println("Enter new fee");
		int fee = sc.nextInt();

		System.out.println("Enter new description");
		String description = sc.next();

		CourseDao dao = new CourseDaoImp();
		Course course = new Course();

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
				System.out.println("Course Name :" + c.getCourseName());
				System.out.println("Course Address : " + c.getFee());
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
