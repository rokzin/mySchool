package com.rokzin.myschool.model;

public class Course {
	
	private String title;
	private String name;
	private String professor;
	private String location;
	private int creditHours;
	private CourseSchedule courseSchedule;
	
	
	public Course(String title, String name, String professor, String location,
			int creditHours, CourseSchedule courseSchedule) {
		super();
		this.title = title;
		this.name = name;
		this.professor = professor;
		this.location = location;
		this.creditHours = creditHours;
		this.courseSchedule = courseSchedule;
	}

	public String getTitle() {
		return title;
	}

	public String getName() {
		return name;
	}

	public String getProfessor() {
		return professor;
	}

	public String getLocation() {
		return location;
	}

	public int getCreditHours() {
		return creditHours;
	}

	public CourseSchedule getCourseSchedule() {
		return courseSchedule;
	}
	
	
	

}
