package com.rokzin.myschool.model;

public class Course {
	private String ID;


	private String title;
	private String name;
	private String professor;
	private String location;
	private int creditHours;
	private String semester;
	private String courseSchedule;
	
	
	public Course() {}


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getProfessor() {
		return professor;
	}


	public void setProfessor(String professor) {
		this.professor = professor;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public int getCreditHours() {
		return creditHours;
	}


	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}


	public String getSemester() {
		return semester;
	}


	public void setSemester(String semester) {
		this.semester = semester;
	}


	public String getCourseSchedule() {
		return courseSchedule;
	}


	public void setCourseSchedule(String courseSchedule) {
		this.courseSchedule = courseSchedule;
	}


}
