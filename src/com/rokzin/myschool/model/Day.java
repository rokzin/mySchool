package com.rokzin.myschool.model;


public enum Day {
	
	MONDAY("M"),TUESDAY("T"),WEDNESDAY("W"),THURSDAY("R"),FRIDAY("F"),SATURDAY("S"),SUNDAY("Su"); 
	
	
	String name;
	private Day(String name){
		this.name = name;
	}
	public String toString(){
		return name;
	}
	
}


