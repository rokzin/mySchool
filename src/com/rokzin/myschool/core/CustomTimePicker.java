package com.rokzin.myschool.core;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

public class CustomTimePicker extends RelativeLayout {

	private Context context;
	private String[] hours = new String[]{"1:", "2:", "3:" ,"4:","5:","6:","7:","8:","9:","10:","11:","12:" };
	private String[] minutes = new String[]{"00", "10", "20" ,"30","40","50" };
	private String[] ampm = new String[]{"AM", "PM"};

	private NumberPicker hourPicker;
	private NumberPicker minutePicker;
	private NumberPicker AMPMPicker;

	public CustomTimePicker(Context context) {
		super(context);
		this.context = context;
		init();
	}
	
	//kasdflkjsad;lfdsaf


	private void init() {
    	hourPicker = new NumberPicker(context);
		hourPicker.setMinValue(0);
		hourPicker.setMaxValue(11);
		hourPicker.setId(2981);
		hourPicker.setDisplayedValues(hours);
		hourPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		minutePicker = new NumberPicker(context);
		minutePicker.setId(2979);
		minutePicker.setMinValue(0);
		minutePicker.setMaxValue(5);
		minutePicker.setDisplayedValues(minutes);
		minutePicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		AMPMPicker = new NumberPicker(context);
		AMPMPicker.setMinValue(0);
		AMPMPicker.setMaxValue(1);
		AMPMPicker.setDisplayedValues(ampm);
		AMPMPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		
		RelativeLayout.LayoutParams hourParams = new RelativeLayout.LayoutParams(45, LayoutParams.WRAP_CONTENT);
		hourParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		hourParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		
		RelativeLayout.LayoutParams minuteParams = new RelativeLayout.LayoutParams(45, LayoutParams.WRAP_CONTENT);
		minuteParams.addRule(RelativeLayout.RIGHT_OF, hourPicker.getId());
		
		RelativeLayout.LayoutParams ampmParams = new RelativeLayout.LayoutParams(55, LayoutParams.WRAP_CONTENT);
		ampmParams.addRule(RelativeLayout.RIGHT_OF, minutePicker.getId());
		
		
		addView(hourPicker, hourParams);
		addView(minutePicker, minuteParams);
		addView(AMPMPicker, ampmParams);
		
		
	}
	
	public String getTime(){
		StringBuilder semesterAndYear = new StringBuilder();
		
		semesterAndYear.append(hours[hourPicker.getValue()]);
		semesterAndYear.append(":");
		semesterAndYear.append(minutes[minutePicker.getValue()]);
		semesterAndYear.append(ampm[AMPMPicker.getValue()]);
		return semesterAndYear.toString();
		
	}

	public CustomTimePicker(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
    

}
