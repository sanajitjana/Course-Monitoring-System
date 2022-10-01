package com.cms.bean;

public class ReportForBatchDTO {

	// coursePlan
	private int dayNumber;
	private String status;

	// course
	private int courseId;
	private String courseName;

	// batch
	private int batchId;
	private String batchName;

	// faculty
	private int facultyId;
	private String facultyName;

	public ReportForBatchDTO() {
		super();
	}

	public ReportForBatchDTO(int dayNumber, String status, int courseId, String courseName, int batchId,
			String batchName, int facultyId, String facultyName) {
		super();
		this.dayNumber = dayNumber;
		this.status = status;
		this.courseId = courseId;
		this.courseName = courseName;
		this.batchId = batchId;
		this.batchName = batchName;
		this.facultyId = facultyId;
		this.facultyName = facultyName;
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	@Override
	public String toString() {
		return "ReportDayWiseDTO [dayNumber=" + dayNumber + ",status=" + status + ", courseId=" + courseId
				+ ", courseName=" + courseName + ", batchId=" + batchId + ", batchName=" + batchName + ", facultyId="
				+ facultyId + ", facultyName=" + facultyName + "]";
	}

}
