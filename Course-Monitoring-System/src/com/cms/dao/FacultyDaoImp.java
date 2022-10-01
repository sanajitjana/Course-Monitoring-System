package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.bean.Faculty;
import com.cms.exceptions.FacultyException;
import com.cms.start.UserActivity;
import com.cms.utility.DBUtil;

public class FacultyDaoImp implements FacultyDao {

	@Override
	public Faculty loginFaculty(String username, String password) throws FacultyException {

		// initialize faculty object as null
		Faculty faculty = null;

		try (Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from faculty where username=? AND password=?");

			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int id = rs.getInt("facultyId");
				String name = rs.getString("facultyName");
				String address = rs.getString("facultyAddress");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				String user = rs.getString("username");
				String pass = rs.getString("password");

				faculty = new Faculty(id, name, address, mobile, email, user, pass);
			} else {
				throw new FacultyException("Invalid username or password!");
			}

		} catch (SQLException e) {
			throw new FacultyException(e.getMessage());
		}

		// return faculty object
		return faculty;
	}

	@Override
	public void logoutFaculty() throws FacultyException {
		// TODO Auto-generated method stub

		// redirect to the previous menu
		UserActivity.selectUser();
	}

	@Override
	public String registerFaculty(Faculty faculty) throws FacultyException {
		// TODO Auto-generated method stub

		String message = "Failed!";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"insert into faculty(facultyName,facultyAddress,mobile,email,username,password) values(?,?,?,?,?,?)");

			ps.setString(1, faculty.getFacultyName());
			ps.setString(2, faculty.getFacultyAddress());
			ps.setString(3, faculty.getMobile());
			ps.setString(4, faculty.getEmail());
			ps.setString(5, faculty.getUsername());
			ps.setString(6, faculty.getPassword());

			int res = ps.executeUpdate();

			if (res > 0) {
				message = "Faculty register successfully!";
			}

		} catch (SQLException e) {
			throw new FacultyException(e.getMessage());
		}

		return message;
	}

	@Override
	public String upadteFacultyById(int id, Faculty faculty) throws FacultyException {
		// TODO Auto-generated method stub

		String message = "Failed!";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement pss = conn.prepareStatement("select * from faculty where facultyId=?");

			pss.setInt(1, id);

			ResultSet rs = pss.executeQuery();

			if (rs.next()) {

				PreparedStatement ps = conn.prepareStatement(
						"update faculty set facultyName=?,facultyAddress=?,mobile=?,email=?,username=?,password=? where facultyId=?");

				ps.setString(1, faculty.getFacultyName());
				ps.setString(2, faculty.getFacultyAddress());
				ps.setString(3, faculty.getMobile());
				ps.setString(4, faculty.getEmail());
				ps.setString(5, faculty.getUsername());
				ps.setString(6, faculty.getPassword());
				ps.setInt(7, id);

				int res = ps.executeUpdate();

				if (res > 0) {
					message = "Faculty update successfully!";
				}

			} else {
				message = "Faculty does not exist with ID : " + id + "";
			}

		} catch (SQLException e) {
			throw new FacultyException(e.getMessage());
		}

		return message;
	}

	@Override
	public List<Faculty> viewAllFaculty() throws FacultyException {
		// TODO Auto-generated method stub

		List<Faculty> faculties = new ArrayList<Faculty>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from faculty");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("facultyId");
				String name = rs.getString("facultyName");
				String address = rs.getString("facultyAddress");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = "********";

				Faculty faculty = new Faculty();

				faculty.setFacultyId(id);
				faculty.setFacultyName(name);
				faculty.setFacultyAddress(address);
				faculty.setMobile(mobile);
				faculty.setEmail(email);
				faculty.setUsername(username);
				faculty.setPassword(password);

				faculties.add(faculty);
			}

		} catch (SQLException e) {
			throw new FacultyException(e.getMessage());
		}

		if (faculties.size() == 0)
			throw new FacultyException("Empty!");

		return faculties;

	}

	@Override
	public String deleteFacultyById() throws FacultyException {

		String message = "You don't have permission to delete";

		return message;
	}

	@Override
	public boolean checkUsernamePassword(String username, String old_password) throws FacultyException {

		boolean result = false;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from faculty where username=? AND password=?");

			ps.setString(1, username);
			ps.setString(2, old_password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			throw new FacultyException(e.getMessage());
		}

		return result;
	}

	@Override
	public String updateFacultyPassword(String username, String new_password) throws FacultyException {

		String result = "Failed!";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("update faculty set password=? where username=?");

			ps.setString(1, new_password);
			ps.setString(2, username);

			int rs = ps.executeUpdate();

			if (rs > 0) {
				result = "Password update successfully!";
			}

		} catch (SQLException e) {
			throw new FacultyException(e.getMessage());
		}

		return result;

	}

}
