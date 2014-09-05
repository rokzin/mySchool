package com.rokzin.myschool.utils;

import java.util.Calendar;

import android.widget.DatePicker;
import android.widget.EditText;

public class F {

	public static String getText(EditText editText){
		return editText.getText().toString();
	}
	
	public static String getUppercaseText(EditText editText){
		return editText.getText().toString().toUpperCase();
	}
	
	/**
	 * 
	 * @param datePicker
	 * @return a java.util.Date
	 */
	public static java.util.Date getDateFromDatePicket(DatePicker datePicker){
	    int day = datePicker.getDayOfMonth();
	    int month = datePicker.getMonth();
	    int year =  datePicker.getYear();

	    Calendar calendar = Calendar.getInstance();
	    calendar.set(year, month, day);

	    return calendar.getTime();
	}
	
	public static boolean isEmptyOrNull(EditText et){
		if(et == null || et.getText().toString().equals("")){
			return true;
		}
		return false;
	}
}
