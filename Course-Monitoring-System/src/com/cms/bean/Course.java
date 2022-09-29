package com.cms.bean;

public class Course {
	
	private int courseId;
	private String courseName;
	private int fee;
	private String courseDescription;
	
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	
	public Course(int courseId, String courseName, int fee, String courseDescription) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.fee = fee;
		this.courseDescription = courseDescription;
	}
	
	public Course() {
		super();
	}
	
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", fee=" + fee + ", courseDescription="
				+ courseDescription + "]";
	}

	
}
