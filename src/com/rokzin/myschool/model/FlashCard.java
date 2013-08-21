package com.rokzin.myschool.model;



public class FlashCard {
	
	private String question;
	private String answer;
	private int id;
	
	public FlashCard(String title, String description) {
		this.question = title;
		this.answer = description;
	}
	
	
	public FlashCard(int id,String title, String description) {
		this.id = id;
		this.question = title;
		this.answer = description;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}
	
	public int getID() {
		return id;
	}


	public void setID(int id) {
		this.id = id;
	}

}
