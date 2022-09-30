package com.cms.usecase;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cms.bean.CoursePlan;
import com.cms.dao.CoursePlanDao;
import com.cms.dao.CoursePlanDaoImp;
import com.cms.exceptions.CoursePlanException;
import com.cms.start.UserActivity;

public class CoursePlanUseCase {

	public static void createCoursePlan() {

		CoursePlanDao dao = new CoursePlanDaoImp();
		CoursePlan coursePlan = new CoursePlan();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter course plan details - ");

		try {

			System.out.println("Enter batch id");
			int batchId = sc.nextInt();

			try {
				boolean res = dao.isBatchIdAvailable(batchId);

				if (res == false) {
					System.out.println("\nThis batch id doesn't exists!");

					System.out.println("\nTry again...");
					UserActivity.coursePlanOptions();
				}

			} catch (CoursePlanException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			System.out.println("Enter day number of week between(1-7)");
			int dayNumber = sc.nextInt();

			if ((dayNumber < 1) || (dayNumber > 7)) {

				System.out.println("\nInvalid entry (choose between 1 to 7)");

				System.out.println("\nTry again...");
				UserActivity.coursePlanOptions();
			}

			System.out.println("Enter course plan topic");
			String topic = sc.next();

			// select status for course plan
			System.out.println("Choose course plan status\r\n" + "1. Completed\r\n" + "2. Pending");
			int status = sc.nextInt();

			while (status != 1 || status != 2) {

				System.out.println("\nWrong choice");
				System.out.println("\nTry again...");

				System.out.println("Choose course plan status\r\n" + "1. Completed\r\n" + "2. Pending");
				status = sc.nextInt();

				if (status == 1 || status == 2)
					break;
			}

			String statusText = null;
			if (status == 1)
				statusText = "completed";
			else
				statusText = "pending";

			coursePlan.setBatchId(batchId);
			coursePlan.setDayNumber(dayNumber);
			coursePlan.setTopic(topic);
			coursePlan.setStatus(statusText);

		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block

			System.out.println("\nInvalid data-type");

			System.out.println("\nTry again...");
			UserActivity.coursePlanOptions();
		}

		try {
			String result = dao.createCoursePlan(coursePlan);
			System.out.println("\n" + result);

		} catch (CoursePlanException e) {
			// TODO Auto-generated catch block

			System.out.println("\n" + e.getMessage());

			System.out.println("\nTry again...");
			UserActivity.coursePlanOptions();

		}

		UserActivity.coursePlanOptions();

	}

	public static void coursePlanUpdateById() {

		CoursePlanDao dao = new CoursePlanDaoImp();
		CoursePlan coursePlan = new CoursePlan();

		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter course plan id to update - ");

		int planId=0;
		try {

			System.out.println("Enter course-plan id");
			planId = sc.nextInt();

			try {
				boolean res = dao.isPlanIdAvailable(planId);

				if (res == false) {
					System.out.println("\nThis planId id doesn't exists!");

					System.out.println("\nTry again...");
					UserActivity.coursePlanOptions();
				}

			} catch (CoursePlanException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			System.out.println("Enter batch id");
			int batchId = sc.nextInt();

			try {
				boolean res = dao.isBatchIdAvailable(batchId);

				if (res == false) {
					System.out.println("\nThis batch id doesn't exists!");

					System.out.println("\nTry again...");
					UserActivity.coursePlanOptions();
				}

			} catch (CoursePlanException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			System.out.println("Enter day number of week between(1-7)");
			int dayNumber = sc.nextInt();

			if ((dayNumber < 1) || (dayNumber > 7)) {

				System.out.println("\nInvalid entry (choose between 1 to 7)");

				System.out.println("\nTry again...");
				UserActivity.coursePlanOptions();
			}

			System.out.println("Enter course plan topic");
			String topic = sc.next();

			// select status for course plan
			System.out.println("Choose course plan status\r\n" + "1. Completed\r\n" + "2. Pending");
			int status = sc.nextInt();

			while (status != 1 || status != 2) {

				System.out.println("\nWrong choice");
				System.out.println("\nTry again...");

				System.out.println("Choose course plan status\r\n" + "1. Completed\r\n" + "2. Pending");
				status = sc.nextInt();

				if (status == 1 || status == 2)
					break;
			}

			String statusText = null;
			if (status == 1)
				statusText = "Completed";
			else
				statusText = "Pending";

			coursePlan.setBatchId(batchId);
			coursePlan.setDayNumber(dayNumber);
			coursePlan.setTopic(topic);
			coursePlan.setStatus(statusText);

		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block

			System.out.println("\nInvalid data-type");

			System.out.println("\nTry again...");
			UserActivity.coursePlanOptions();
		}

		String result;
		try {
			result = dao.upadteCoursePlanById(planId, coursePlan);
			System.out.println("\n" + result);

		} catch (CoursePlanException e) {
			// TODO Auto-generated catch block

			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			UserActivity.coursePlanOptions();
		}

		UserActivity.coursePlanOptions();

	}

	public static void viewAllCoursePlans() {

		try {

			List<CoursePlan> coursePlans = new CoursePlanDaoImp().viewAllCoursePlanDetails();

			coursePlans.forEach(cp -> {

				System.out.println("Course Plan ID : " + cp.getPlanId());	
				System.out.println("Course Plan Batch ID : " + cp.getBatchId());
				System.out.println("Course Plan Day Number : " + cp.getDayNumber());
				System.out.println("Course Plan Topic : " + cp.getTopic());
				System.out.println("Course Plan Status : " + cp.getStatus());

				System.out.println("==================================");
			});

		} catch (CoursePlanException e) {
			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			UserActivity.coursePlanOptions();

		}

		UserActivity.coursePlanOptions();

	}

	public static void coursePlanDeleteById() {

		try {

			String response = new CoursePlanDaoImp().coursePlanDeleteById();
			System.out.println("\n" + response);

		} catch (CoursePlanException e) {
			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			UserActivity.coursePlanOptions();

		}

		UserActivity.coursePlanOptions();

	}

}
