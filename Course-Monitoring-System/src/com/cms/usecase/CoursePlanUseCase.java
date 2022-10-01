package com.cms.usecase;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cms.bean.Batch;
import com.cms.bean.CoursePlan;
import com.cms.bean.ReportDayWiseDTO;
import com.cms.dao.BatchDaoImp;
import com.cms.dao.CoursePlanDao;
import com.cms.dao.CoursePlanDaoImp;
import com.cms.exceptions.BatchException;
import com.cms.exceptions.CoursePlanException;
import com.cms.start.UserActivity;

public class CoursePlanUseCase {

	public static void createCoursePlan() {

		CoursePlanDao dao = new CoursePlanDaoImp();
		CoursePlan coursePlan = new CoursePlan();

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter require course plan details - ");

		System.out.print("\nAvailable Batches\n");

		try {
			List<Batch> batches = new BatchDaoImp().availableBatch();

			batches.forEach(b -> {
				System.out.print(b.getBatchId() + "(" + b.getBatchName() + ")");
			});

		} catch (BatchException e) {

			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			UserActivity.coursePlanOptions();
		}

		try {

			System.out.println("\n\nEnter batch id");
			int batchId = sc.nextInt();

			try {
				boolean res = dao.isBatchIdAvailable(batchId);

				if (res == false) {
					System.out.println("\nThis batch id doesn't exists!" + "\nPlease select from above");

					System.out.println("\nTry again...");
					UserActivity.coursePlanOptions();
				}

			} catch (CoursePlanException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			System.out.println("Enter day number of week between(1 for Monday) to (7 for Sunday))");
			int dayNumber = sc.nextInt();

			if ((dayNumber < 1) || (dayNumber > 7)) {

				System.out.println("\nInvalid entry (choose between 1 to 7)");

				System.out.println("\nTry again...");
				UserActivity.coursePlanOptions();
			}

			System.out.println("Enter course plan topic");
			String topic = sc.next();

			// select status for course plan
			String status = "Pending";

			coursePlan.setBatchId(batchId);
			coursePlan.setDayNumber(dayNumber);
			coursePlan.setTopic(topic);
			coursePlan.setStatus(status);

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

		int planId = 0;
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

			System.out.println("Enter day number of week between(1 for Monday) to (7 for Sunday))");
			int dayNumber = sc.nextInt();

			if ((dayNumber < 1) || (dayNumber > 7)) {

				System.out.println("\nInvalid entry (choose between 1 to 7)");

				System.out.println("\nTry again...");
				UserActivity.coursePlanOptions();
			}

			System.out.println("Enter course plan topic");
			String topic = sc.next();

			String status = "Pending";

			coursePlan.setBatchId(batchId);
			coursePlan.setDayNumber(dayNumber);
			coursePlan.setTopic(topic);
			coursePlan.setStatus(status);

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

				int day = cp.getDayNumber();

				switch (day) {
				case 1 -> System.out.println("Course Plan Day Number : " + day + "(Monday)");
				case 2 -> System.out.println("Course Plan Day Number : " + day + "(TuesDay)");
				case 3 -> System.out.println("Course Plan Day Number : " + day + "(Wednesday)");
				case 4 -> System.out.println("Course Plan Day Number : " + day + "(Thursday)");
				case 5 -> System.out.println("Course Plan Day Number : " + day + "(Friday)");
				case 6 -> System.out.println("Course Plan Day Number : " + day + "(Satarday)");
				case 7 -> System.out.println("Course Plan Day Number : " + day + "(Sunday)");
				}

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

	public static void dayWiseCoursePlanUpdateForEveryBatch() {

		try {

			List<ReportDayWiseDTO> res = new CoursePlanDaoImp().dayWiseCoursePlanForBatch();

			System.out.println("\nDay wise course plan update of every batch");
			System.out.println("--------------------------------------------\n");

			res.forEach(dto -> {

				int day = dto.getDayNumber();

				switch (day) {
				case 1 -> System.out.println("Day Number : " + day + "(Monday)");
				case 2 -> System.out.println("Day Number : " + day + "(TuesDay)");
				case 3 -> System.out.println("Day Number : " + day + "(Wednesday)");
				case 4 -> System.out.println("Day Number : " + day + "(Thursday)");
				case 5 -> System.out.println("Day Number : " + day + "(Friday)");
				case 6 -> System.out.println("Day Number : " + day + "(Satarday)");
				case 7 -> System.out.println("Day Number : " + day + "(Sunday)");
				}

				System.out.println("Course Name : " + dto.getCourseName() + "(" + dto.getCourseId() + ")");
				System.out.println("Course Status : " + dto.getStatus());
				System.out.println("Batch Name : " + dto.getBatchName() + "(" + dto.getBatchId() + ")");
				System.out.println("Faculty Name : " + dto.getFacultyName() + "(" + dto.getFacultyId() + ")");

				System.out.println("============================\n");
			});

		} catch (CoursePlanException e) {
			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			UserActivity.adminOptions();

		}

		UserActivity.adminOptions();

	}

}
