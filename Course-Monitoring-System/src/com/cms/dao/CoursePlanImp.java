package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.bean.Course;
import com.cms.bean.CoursePlan;
import com.cms.exceptions.CourseException;
import com.cms.exceptions.CoursePlanException;
import com.cms.utility.DBUtil;

public class CoursePlanImp implements CoursePlanDao {

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
	public boolean isCoursePlanIdAvailable(int id) throws CoursePlanException {

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

			PreparedStatement pss = conn.prepareStatement("select * from coursePlan where planId=?");

			pss.setInt(1, id);

			ResultSet rs = pss.executeQuery();

			if (rs.next()) {

				PreparedStatement ps = conn.prepareStatement(
						"update coursePlan set batchId=?,dayNumber=?,topic=?,status=? where planId=?");

				ps.setInt(1, coursePlan.getBatchId());
				ps.setInt(2, coursePlan.getDayNumber());
				ps.setString(3, coursePlan.getTopic());
				ps.setString(4, coursePlan.getStatus());

				int res = ps.executeUpdate();

				if (res > 0) {
					message = "Course plan update successfully!";
				}

			} else {
				message = "Course plan does not exist with id : " + id + "";
			}

		} catch (SQLException e) {
			throw new CoursePlanException(e.getMessage());
		}

		return message;

	}

	@Override
	public List<CoursePlan> viewAllCoursePlanDetails() throws CoursePlanException {
		

		List<CoursePlan> coursePlans = new ArrayList<CoursePlan>();
		CoursePlan coursePlan = new CoursePlan();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from coursePlan");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

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
			throw new CoursePlanException("Course Plan Not Found!");

		return coursePlans;

	}

	@Override
	public String coursePlanDeleteById() throws CoursePlanException {
		// TODO Auto-generated method stub

		String message = "You don't have permission to delete";

		return message;

	}

}
