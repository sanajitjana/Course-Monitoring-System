package com.cms.dao;

import com.cms.bean.Admin;
import com.cms.exceptions.AdminException;

public interface AdminDao  {
	
	//login admin
	public Admin loginAdmin(String username,String password)throws AdminException;
	
	
	//logout admin
	public void logoutAdmin() throws AdminException;

}
