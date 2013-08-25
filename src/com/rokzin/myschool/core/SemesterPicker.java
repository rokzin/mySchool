package com.rokzin.myschool.core;

import java.util.Calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

public class SemesterPicker extends RelativeLayout {

	private Context context;
	private String[] years = getYears();
	private String[] semesters = new String[]{"Spring", "Summer", "Fall" ,"Winter" };
	private NumberPicker semesterPicker;
	private NumberPicker yearPicker;

	public SemesterPicker(Context context) {
		super(context);
		this.context = context;
		init();
	}

    private String[] getYears() {
    	int year = Calendar.getInstance().get(Calendar.YEAR);
		String[] years = new String[7];
		for (int i = 0; i <7 ; i++) {
			years[i] = String.valueOf(year + i-3);
		}
		return years;
	}

	private void init() {
    	semesterPicker = new NumberPicker(context);
		semesterPicker.setMinValue(0);
		semesterPicker.setMaxValue(3);
		semesterPicker.setId(2981);
		semesterPicker.setDisplayedValues( semesters);
		semesterPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		yearPicker = new NumberPicker(context);
		yearPicker.setMinValue(0);
		yearPicker.setMaxValue(6);
		yearPicker.setDisplayedValues(years);
		yearPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		RelativeLayout.LayoutParams semesterParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		semesterParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		semesterParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		
		RelativeLayout.LayoutParams yearParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		yearParams.addRule(RelativeLayout.RIGHT_OF, semesterPicker.getId());
		
		addView(semesterPicker, semesterParams);
		addView(yearPicker, yearParams);
		
	}
	
	public String getSemesterAndYear(){
		StringBuilder semesterAndYear = new StringBuilder();
		semesterAndYear.append(semesters[semesterPicker.getValue()]);
		semesterAndYear.append(" ");
		semesterAndYear.append(years[yearPicker.getValue()]);
		
		return semesterAndYear.toString();
		
	}

	public SemesterPicker(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
    

}
