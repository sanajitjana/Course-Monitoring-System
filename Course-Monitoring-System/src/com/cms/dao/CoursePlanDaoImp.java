package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.bean.CoursePlan;
import com.cms.bean.ReportDayWiseDTO;
import com.cms.exceptions.CoursePlanException;
import com.cms.utility.DBUtil;

public class CoursePlanDaoImp implements CoursePlanDao {

	@Override
	public boolean isBatchIdAvailable(int id) throws CoursePlanException {

		boolean result = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement pss = conn.prepareStatement("select * from batch where batchId=?");

			pss.setInt(1, id);
			ResultSet rs = pss.executeQuery();

			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		return result;
	}

	@Override
	public String createCoursePlan(CoursePlan coursePlan) throws CoursePlanException {
		// TODO Auto-generated method stub

		String message = "Failed!";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("insert into coursePlan(batchId,dayNumber,topic,status) values(?,?,?,?)");

			ps.setInt(1, coursePlan.getBatchId());
			ps.setInt(2, coursePlan.getDayNumber());
			ps.setString(3, coursePlan.getTopic());
			ps.setString(4, coursePlan.getStatus());

			int res = ps.executeUpdate();

			if (res > 0) {
				message = "Course plan created successfully!";
			}

		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		return message;
	}

	@Override
	public boolean isPlanIdAvailable(int id) throws CoursePlanException {

		boolean result = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement pss = conn.prepareStatement("select * from coursePlan where planId=?");

			pss.setInt(1, id);
			ResultSet rs = pss.executeQuery();

			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		return result;
	}

	@Override
	public String upadteCoursePlanById(int id, CoursePlan coursePlan) throws CoursePlanException {
		// TODO Auto-generated method stub

		String message = "Failed!";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("update coursePlan set batchId=?,dayNumber=?,topic=?,status=? where planId=?");

			ps.setInt(1, coursePlan.getBatchId());
			ps.setInt(2, coursePlan.getDayNumber());
			ps.setString(3, coursePlan.getTopic());
			ps.setString(4, coursePlan.getStatus());
			ps.setInt(5, id);

			int res = ps.executeUpdate();

			if (res > 0) {
				message = "Course plan update successfully!";
			}

		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		return message;

	}

	@Override
	public List<CoursePlan> viewAllCoursePlanDetails() throws CoursePlanException {

		List<CoursePlan> coursePlans = new ArrayList<CoursePlan>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from coursePlan");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				CoursePlan coursePlan = new CoursePlan();

				int planId = rs.getInt("planId");
				int batchId = rs.getInt("batchId");
				int dayNumber = rs.getInt("dayNumber");
				String topic = rs.getString("topic");
				String status = rs.getString("status");

				coursePlan.setPlanId(planId);
				coursePlan.setBatchId(batchId);
				coursePlan.setDayNumber(dayNumber);
				coursePlan.setTopic(topic);
				coursePlan.setStatus(status);

				coursePlans.add(coursePlan);
			}

		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		if (coursePlans.size() == 0)
			throw new CoursePlanException("Empty!");

		return coursePlans;

	}

	@Override
	public String coursePlanDeleteById() throws CoursePlanException {
		// TODO Auto-generated method stub

		String message = "You don't have permission to delete";

		return message;

	}

	@Override
	public List<ReportDayWiseDTO> dayWiseCoursePlanForBatch() throws CoursePlanException {

		List<ReportDayWiseDTO> dayWiseDTO = new ArrayList<>();

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

				ReportDayWiseDTO dto = new ReportDayWiseDTO(dayNumber, status, courseId, courseName, batchId, batchName,
						facultyId, facultyName);

				dayWiseDTO.add(dto);

			}

		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		if (dayWiseDTO.isEmpty())
			throw new CoursePlanException("Empty Course Plan!" + "\nAllocate Batch to Course Plan...");

		return dayWiseDTO;

	}

	@Override
	public List<CoursePlan> pendingCoursePlan() throws CoursePlanException {

		List<CoursePlan> coursePlans = new ArrayList<CoursePlan>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from coursePlan where status='Pending'");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int planId = rs.getInt("planId");

				CoursePlan cp = new CoursePlan();
				cp.setPlanId(planId);

				coursePlans.add(cp);
			}

		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		if (coursePlans.size() == 0)
			throw new CoursePlanException("Empty!");

		return coursePlans;
	}

	@Override
	public String updateCoursePlanStatus(int id) throws CoursePlanException {

		String message = "Failed!";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("update coursePlan set status='Completed' where planId=?");

			ps.setInt(1, id);
			int res = ps.executeUpdate();

			if (res > 0) {
				message = "Course plan fill-up successfully!";
			}

		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		return message;
	}

	@Override
	public boolean isIdAvaillableAndStatusPending(int id) throws CoursePlanException {
		// TODO Auto-generated method stub

		boolean result = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from coursePlan where planId=? AND status='Pending'");

			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();

			if (res.next()) {
				result=true;
			}

		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		return result;
	}

}
