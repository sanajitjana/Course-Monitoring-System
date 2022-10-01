package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.bean.Batch;
import com.cms.bean.ReportForBatchDTO;
import com.cms.exceptions.BatchException;
import com.cms.utility.DBUtil;

public class BatchDaoImp implements BatchDao {

	@Override
	public boolean isCourseIdPresent(int courseId) throws BatchException {

		boolean result = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from course where courseId=?");

			ps.setInt(1, courseId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			throw new BatchException(e.getMessage());
		}

		return result;
	}

	@Override
	public boolean isFacultyIdPresent(int facultyId) throws BatchException {

		boolean result = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from faculty where facultyId=?");

			ps.setInt(1, facultyId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			throw new BatchException(e.getMessage());
		}

		return result;
	}

	@Override
	public boolean isBatchNameUnique(String name) throws BatchException {

		boolean result = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from batch where batchName=?");

			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			throw new BatchException(e.getMessage());
		}

		return result;
	}

	@Override
	public String createBatch(Batch batch) throws BatchException {
		// TODO Auto-generated method stub

		String message = "Failed!";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"insert into batch(courseId,facultyId,numberOfStudents,batchStartDate,duration,batchName) values(?,?,?,?,?,?)");

			ps.setInt(1, batch.getCourseId());
			ps.setInt(2, batch.getFacultyId());
			ps.setInt(3, batch.getNumberOfStudents());
			ps.setString(4, batch.getBatchStartDate());
			ps.setString(5, batch.getDuration());
			ps.setString(6, batch.getBatchName());

			int res = ps.executeUpdate();

			if (res > 0) {
				message = "Batch created successfully!";
			}

		} catch (SQLException e) {
			throw new BatchException(e.getMessage());
		}

		return message;
	}

	@Override
	public String upadteBatchByName(String old_name, Batch batch) throws BatchException {
		// TODO Auto-generated method stub

		String message = "Failed!";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"update batch set courseId=?,facultyId=?,numberOfStudents=?,batchStartDate=?,duration=?,batchName=? where batchName=?");

			ps.setInt(1, batch.getCourseId());
			ps.setInt(2, batch.getFacultyId());
			ps.setInt(3, batch.getNumberOfStudents());
			ps.setString(4, batch.getBatchStartDate());
			ps.setString(5, batch.getDuration());
			ps.setString(6, batch.getBatchName());
			ps.setString(7, old_name);

			int res = ps.executeUpdate();

			if (res > 0) {
				message = "Batch update successfully!";
			}

		} catch (SQLException e) {
			throw new BatchException(e.getMessage());
		}

		return message;

	}

	@Override
	public List<Batch> viewAllBatchDetails() throws BatchException {
		// TODO Auto-generated method stub

		List<Batch> batches = new ArrayList<Batch>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from batch");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int bid = rs.getInt("batchId");
				int cid = rs.getInt("courseId");
				int fid = rs.getInt("facultyId");
				int noOfStudents = rs.getInt("numberOfStudents");
				String batchStart = rs.getString("batchStartDate");
				String duration = rs.getString("duration");
				String batchName = rs.getString("batchName");

				Batch batch = new Batch();

				batch.setBatchId(bid);
				batch.setCourseId(cid);
				batch.setFacultyId(fid);
				batch.setNumberOfStudents(noOfStudents);
				batch.setBatchStartDate(batchStart);
				batch.setDuration(duration);
				batch.setBatchName(batchName);

				batches.add(batch);
			}

		} catch (SQLException e) {
			throw new BatchException(e.getMessage());
		}

		if (batches.size() == 0)
			throw new BatchException("Empty!");

		return batches;

	}

	@Override
	public String batchDeleteByName() throws BatchException {
		// TODO Auto-generated method stub

		String message = "You don't have permission to delete";
		return message;

	}

	@Override
	public List<ReportForBatchDTO> coursePlanReportForBatch() throws BatchException {

		List<ReportForBatchDTO> reportForBatchDTO = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"select cp.dayNumber,cp.status,c.courseId,c.courseName,b.batchId,b.batchName,f.facultyId,f.facultyName from coursePlan cp INNER JOIN batch b ON cp.batchId=b.batchId INNER JOIN course c ON c.courseId=b.courseId INNER JOIN faculty f ON f.facultyId=b.facultyId group by batchId");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int dayNumber = rs.getInt("dayNumber");
				String status = rs.getString("status");

				int courseId = rs.getInt("courseId");
				String courseName = rs.getString("courseName");

				int batchId = rs.getInt("batchId");
				String batchName = rs.getString("batchName");

				int facultyId = rs.getInt("facultyId");
				String facultyName = rs.getString("facultyName");

				ReportForBatchDTO dto = new ReportForBatchDTO(dayNumber, status, courseId, courseName, batchId,
						batchName, facultyId, facultyName);

				reportForBatchDTO.add(dto);

			}

		} catch (SQLException e) {
			throw new BatchException(e.getMessage());
		}

		if (reportForBatchDTO.isEmpty())
			throw new BatchException("Empty Course Plan!" + "\nPlease Allocate Batch to Course Plan...");

		return reportForBatchDTO;

	}
	
	public List<Batch> availableBatch() throws BatchException{
		
		List<Batch> batches = new ArrayList<Batch>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from batch");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int bid = rs.getInt("batchId");
				String batchName = rs.getString("batchName");

				Batch batch = new Batch();

				batch.setBatchId(bid);
				batch.setBatchName(batchName);

				batches.add(batch);
			}

		} catch (SQLException e) {
			throw new BatchException(e.getMessage());
		}

		if (batches.size() == 0)
			throw new BatchException("Empty!");

		return batches;
		
	}

}
