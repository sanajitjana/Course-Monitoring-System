package com.cms.utility;

import java.sql.Connection;

public class CheckDBConnetion {

	public static void main(String[] args) {
		
		Connection conn=DBUtil.provideConnection();
		
		if(conn!=null) System.out.println("DB Conected...");
		else System.out.println("DB Not Conected...");
		
	}

}
