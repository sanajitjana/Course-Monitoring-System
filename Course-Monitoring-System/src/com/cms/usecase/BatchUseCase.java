package com.cms.usecase;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cms.bean.Batch;
import com.cms.bean.ReportForBatchDTO;
import com.cms.dao.BatchDao;
import com.cms.dao.BatchDaoImp;
import com.cms.exceptions.BatchException;
import com.cms.start.UserActivity;

public class BatchUseCase {

	public static void createBatch() {

		BatchDao dao = new BatchDaoImp();
		Batch batch = new Batch();

		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter batch details - ");

		try {

			String name = null;
			try {

				System.out.println("Enter batch name");
				name = sc.next();

				boolean res = dao.isBatchNameUnique(name);

				if (res) {
					System.out.println("\nThis batch name is already exists!");

					System.out.println("\nTry again...");
					UserActivity.batchOptions();
				}

			} catch (BatchException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			int courseId = 0;
			try {

				System.out.println("Enter course Id");
				courseId = sc.nextInt();

				boolean res = dao.isCourseIdPresent(courseId);

				if (res == false) {
					System.out.println("\nThis course Id doesn't exists!");

					System.out.println("\nTry again...");
					UserActivity.batchOptions();
				}

			} catch (BatchException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			int facultyId = 0;
			try {

				System.out.println("Enter faculty Id");
				facultyId = sc.nextInt();

				boolean res = dao.isFacultyIdPresent(facultyId);

				if (res == false) {
					System.out.println("\nThis faculty Id doesn't exists!");

					System.out.println("\nTry again...");
					UserActivity.batchOptions();
				}

			} catch (BatchException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			System.out.println("Enter no of students");
			int numberOfStudents = sc.nextInt();

			System.out.println("Enter batch start-date (yyyy-mm-dd)");
			String batchStartDate = sc.next();

			System.out.println("Enter duration of the batch");
			String duration = sc.next();

			batch.setBatchName(name);
			batch.setCourseId(courseId);
			batch.setFacultyId(facultyId);
			batch.setNumberOfStudents(numberOfStudents);
			batch.setBatchStartDate(batchStartDate);
			batch.setDuration(duration);

		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block

			System.out.println("\nInvalid data-type!");

			System.out.println("\nTry again...");
			UserActivity.batchOptions();
		}

		try {
			String result = dao.createBatch(batch);
			System.out.println("\n" + result);

		} catch (BatchException e) {
			// TODO Auto-generated catch block

			System.out.println("\n" + e.getMessage());

			System.out.println("\nTry again...");
			UserActivity.batchOptions();

		}

		UserActivity.batchOptions();

	}

	public static void batchUpdateByName() {

		BatchDao dao = new BatchDaoImp();
		Batch batch = new Batch();

		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter batch name to update - ");

		String old_name = null;

		try {

			try {

				System.out.println("Enter old batch name");
				old_name = sc.next();

				boolean res = dao.isBatchNameUnique(old_name);

				if (res == false) {
					System.out.println("\nThis batch name is not exists!");

					System.out.println("\nTry again...");
					UserActivity.batchOptions();
				}

			} catch (BatchException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			String new_name = null;
			try {

				System.out.println("Enter new batch name");
				new_name = sc.next();

				boolean res = dao.isBatchNameUnique(new_name);

				if (res) {
					System.out.println("\nThis batch name is already exists!");

					System.out.println("\nTry again...");
					UserActivity.batchOptions();
				}

			} catch (BatchException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			int courseId = 0;
			try {

				System.out.println("Enter course Id");
				courseId = sc.nextInt();

				boolean res = dao.isCourseIdPresent(courseId);

				if (res == false) {
					System.out.println("\nThis course Id doesn't exists!");

					System.out.println("\nTry again...");
					UserActivity.batchOptions();
				}

			} catch (BatchException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			int facultyId = 0;
			try {

				System.out.println("Enter faculty Id");
				facultyId = sc.nextInt();

				boolean res = dao.isFacultyIdPresent(facultyId);

				if (res == false) {
					System.out.println("\nThis faculty Id doesn't exists!");

					System.out.println("\nTry again...");
					UserActivity.batchOptions();
				}

			} catch (BatchException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
			}

			System.out.println("Enter number Of Students");
			int numberOfStudents = sc.nextInt();

			System.out.println("Enter batch start-date (yyyy-mm-dd)");
			String batchStartDate = sc.next();

			System.out.println("Enter duration of the batch");
			String duration = sc.next();

			batch.setBatchName(new_name);
			batch.setCourseId(courseId);
			batch.setFacultyId(facultyId);
			batch.setNumberOfStudents(numberOfStudents);
			batch.setBatchStartDate(batchStartDate);
			batch.setDuration(duration);

		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block

			System.out.println("\nInvalid data-type!");

			System.out.println("\nTry again...");
			UserActivity.batchOptions();
		}

		String result;
		try {
			result = dao.upadteBatchByName(old_name, batch);
			System.out.println("\n" + result);

		} catch (BatchException e) {
			// TODO Auto-generated catch block

			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			UserActivity.batchOptions();
		}

		UserActivity.batchOptions();

	}

	public static void viewAllBatch() {

		try {

			List<Batch> batches = new BatchDaoImp().viewAllBatchDetails();

			batches.forEach(b -> {

				System.out.println("Batch ID : " + b.getBatchId());
				System.out.println("Batch Name : " + b.getBatchName());
				System.out.println("Allocate Course ID : " + b.getCourseId());
				System.out.println("Allocate Faculty ID : " + b.getFacultyId());
				System.out.println("Batch Having no of Student: " + b.getNumberOfStudents());
				System.out.println("Batch Start Date : " + b.getBatchStartDate());
				System.out.println("Batch Duration : " + b.getDuration());

				System.out.println("==================================");
			});

		} catch (BatchException e) {
			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			UserActivity.batchOptions();

		}

		UserActivity.batchOptions();

	}

	public static void batchDeleteByName() {

		try {

			String response = new BatchDaoImp().batchDeleteByName();
			System.out.println("\n" + response);

		} catch (BatchException e) {
			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			UserActivity.batchOptions();

		}

		UserActivity.batchOptions();

	}

	public static void coursePlanReportForEveryBatch() {

		try {

			List<ReportForBatchDTO> res = new BatchDaoImp().coursePlanReportForBatch();

			System.out.println("\nDay wise course plan update of every batch");
			System.out.println("--------------------------------------------\n");

			res.forEach(dto -> {

				System.out.println("Batch Name : " + dto.getBatchName());
				System.out.println("Course Status : " + dto.getStatus());
				System.out.println("Course Name : " + dto.getCourseName());
				System.out.println("Faculty Name : " + dto.getFacultyName());

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

				System.out.println("============================\n");
			});

		} catch (BatchException e) {
			System.out.println(e.getMessage());

			System.out.println();
			System.out.println("Try again...");
			UserActivity.adminOptions();

		}

		UserActivity.adminOptions();

	}

}
