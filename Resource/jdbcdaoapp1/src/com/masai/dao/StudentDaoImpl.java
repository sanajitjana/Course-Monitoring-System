package com.masai.dao;

import java.awt.image.DataBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.bean.Student;
import com.masai.bean.StudentDTO;
import com.masai.exceptions.CourseException;
import com.masai.exceptions.StudentException;
import com.masai.utility.DBUtil;

public class StudentDaoImpl implements StudentDao{

	@Override
	public String registerStudent(String name, int marks, String email, String password) {
	
		String message = "Not Inserted..";
	
		
	
		
		try(Connection conn= DBUtil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement
					("insert into student(name,marks,email,password) values(?,?,?,?)");
			
			
			
			ps.setString(1, name);
			ps.setInt(2, marks);
			ps.setString(3, email);
			ps.setString(4, password);
			
			int x= ps.executeUpdate();
			
			
			if(x > 0)
				message = "Student Registered Sucessfully !";
			
			
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		
	
	
		
		return message;
	}

	@Override
	public String registerStudent2(Student student) {
		
	
	String message = "Not Inserted..";
	
		
	
		
		try(Connection conn= DBUtil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement
					("insert into student(name,marks,email,password) values(?,?,?,?)");
			
			
			
			ps.setString(1, student.getName());
			ps.setInt(2, student.getMarks());
			ps.setString(3, student.getEmail());
			ps.setString(4, student.getPassword());
			
			int x= ps.executeUpdate();
			
			
			if(x > 0)
				message = "Student Registered Sucessfully !";
			
			
			
		} catch (SQLException e) {
			message = e.getMessage();
		}
		
		
	
	
		
		return message;
	
	
	
	
	
	
	
	
	}

	@Override
	public Student getStudentByRoll(int roll)throws StudentException {
	
		Student student =null;
		
		
		try (Connection conn= DBUtil.provideConnection()){
			
			PreparedStatement ps= conn.prepareStatement("select * from student where roll=?");
			
			
			ps.setInt(1, roll);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				
				int r= rs.getInt("roll");
				String n= rs.getString("name");
				int m= rs.getInt("marks");
				String e= rs.getString("email");
				String p= rs.getString("password");
				
				
			student=new Student(r, n, m, e, p);	
				
				
			}else
				throw new StudentException("Student does not exist with roll "+roll);
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			throw new StudentException(e.getMessage());
			
			
		}
		
	
	
	
		return student;
	
	}

	@Override
	public Student loginStudent(String username, String password) throws StudentException {
		
		
		Student student = null;
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			
			PreparedStatement ps= conn.prepareStatement("select * from student where email = ? AND password = ?");			
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs= ps.executeQuery();
			
			
				if(rs.next()) {
				
				int r= rs.getInt("roll");
				String n= rs.getString("name");
				int m= rs.getInt("marks");
				String e= rs.getString("email");
				String p= rs.getString("password");
				
				
			student=new Student(r, n, m, e, p);	
				
				
			}else
				throw new StudentException("Invalid Username or password.. ");
			
			
			
			
		} catch (SQLException e) {
			throw new StudentException(e.getMessage());
		}
		
		
		
		
		
		
		
		
		
		
		return student;
		
		
	}

	@Override
	public List<Student> getAllStudentDetails() throws StudentException {
		
		List<Student> students= new ArrayList<>();
		
		
		try(Connection conn= DBUtil.provideConnection()) {
			
			PreparedStatement ps= conn.prepareStatement("select * from student");
			
			
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				
				
				int r= rs.getInt("roll");
				String n= rs.getString("name");
				int m= rs.getInt("marks");
				String e= rs.getString("email");
				String p= rs.getString("password");
				
				
			Student student=new Student(r, n, m, e, p);	
				
			students.add(student);
				
				
				
			}
			
			
			
			
			
		} catch (SQLException e) {
			throw new StudentException(e.getMessage());
		}
		
		
		if(students.size() == 0)
			throw new StudentException("No Student found..");
		
		
		
		
		return students;
	}

	@Override
	public String registerStudentInsideACourse(int cid, int roll) throws StudentException, CourseException {
		
			String message ="Not Resgistered";
	
	
			try(Connection conn= DBUtil.provideConnection()) {
				
			 	PreparedStatement ps= conn.prepareStatement("select * from student where roll =?");
				
			 	
			 	ps.setInt(1, roll);
			 	
			 	ResultSet rs= ps.executeQuery();
				
			 	if(rs.next()) {
			 		
			 		PreparedStatement ps2= conn.prepareStatement("select * from course where cid=?");
			 		
			 		ps2.setInt(1, cid);

			 		ResultSet rs2= ps2.executeQuery();
			 		
			 		if(rs2.next()) {
			 			
	
			 			PreparedStatement ps3= conn.prepareStatement("insert into course_student values(?,?)");
			 			
			 			
			 			ps3.setInt(1, cid);
			 			ps3.setInt(2, roll);
			 			
			 			int x= ps3.executeUpdate();
			 			
			 			if(x > 0)
			 				message = "Student registered inside the Course Sucessfully.. ";
			 			else
			 				throw new StudentException("Techical error..");
			 			
			 			
			 			
			 		}
			 		else
			 			throw new CourseException("Invalid Course...");
			 		
			 		
			 		
			 		
			 	}else
			 		throw new StudentException("Invalid Student...");
				
				
				
				
				
				
			} catch (SQLException e) {
				throw new StudentException(e.getMessage());
			}
			
			
			
	
			
			
			
			return message;
	}

	@Override
	public List<StudentDTO> getAllStudentsByCname(String cname) throws CourseException {
		
		
		List<StudentDTO> dtos = new ArrayList<>();
		
		
		
		try (Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps= conn.prepareStatement("select s.roll, s.name,s.email, c.cname, c.fee "
														+ "from  student s INNER JOIN course c INNER JOIN course_student cs "
														+ "ON s.roll = cs.roll AND c.cid = cs.cid AND c.cname= ?");
			
			ps.setString(1, cname);
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				
				int r= rs.getInt("roll");
				String sn= rs.getString("name");
				String em= rs.getString("email");
				
				String cn= rs.getString("cname");
				int f= rs.getInt("fee");
				
				StudentDTO dto = new StudentDTO(r, sn, em, cn, f);
				
				dtos.add(dto);
				
				
				
			}
			
			
			
			
			
			
		} catch (SQLException e) {
			throw new CourseException(e.getMessage());
		}
		
		
		
		if(dtos.isEmpty())
			throw new CourseException("No Student found in that course");
		
		
		
		
		return dtos;
		
	}

}
