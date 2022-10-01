package com.cms.dao;

import java.util.List;

import com.cms.bean.Faculty;
import com.cms.exceptions.FacultyException;

public interface FacultyDao {

	// login faculty
	public Faculty loginFaculty(String username, String password) throws FacultyException;

	// logout faculty
	public void logoutFaculty() throws FacultyException;

	// register faculty
	public String registerFaculty(Faculty faculty) throws FacultyException;

	// update faculty by id
	public String upadteFacultyById(int id, Faculty faculty) throws FacultyException;

	// view all faculty details
	public List<Faculty> viewAllFaculty() throws FacultyException;

	// delete faculty by id
	public String deleteFacultyById() throws FacultyException;

	// before update password check is it valid or not
	public boolean checkUsernamePassword(String username, String old_password) throws FacultyException;

	// update faculty password
	public String updateFacultyPassword(String username, String new_password) throws FacultyException;

}
