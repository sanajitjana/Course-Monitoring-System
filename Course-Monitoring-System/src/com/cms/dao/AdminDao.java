package com.cms.dao;

import com.cms.bean.Admin;
import com.cms.exceptions.AdminException;

public interface AdminDao  {
	
	public Admin loginAdmin(String username,String password)throws AdminException;

}