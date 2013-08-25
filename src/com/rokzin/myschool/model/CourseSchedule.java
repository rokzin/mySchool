package com.rokzin.myschool.model;

import java.util.ArrayList;

public class CourseSchedule {


	StringBuilder schedule_string = new StringBuilder();
	
	
	public CourseSchedule() {}
	
	public void addDay(ArrayList<Day> days){
		if(days.contains(Day.MONDAY) && 
				days.contains(Day.TUESDAY) &&
				days.contains(Day.WEDNESDAY) &&
				days.contains(Day.THURSDAY) &&
				days.contains(Day.FRIDAY) &&
				!days.contains(Day.SATURDAY) &&
				!days.contains(Day.SUNDAY) ){
			schedule_string.append("M - F");
		}
		else if(days.contains(Day.MONDAY) && 
				days.contains(Day.TUESDAY) &&
				days.contains(Day.WEDNESDAY) &&
				days.contains(Day.THURSDAY) &&
				days.contains(Day.FRIDAY) &&
				days.contains(Day.SATURDAY) &&
				days.contains(Day.SUNDAY)) {
			schedule_string.append("Everyday");
			
		}
		else{
			for (int i = 0; i < days.size(); i++) {
				schedule_string.append(days.get(i).toString());
			}
		}
		
	}
	
	public void setTime(String time){
		schedule_string.append(" | ");
		schedule_string.append(time);
	}
	
	public String getSchString(){
		return schedule_string.toString();
	}

}
