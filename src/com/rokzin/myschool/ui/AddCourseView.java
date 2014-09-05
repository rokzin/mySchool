package com.rokzin.myschool.ui;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.rokzin.myschool.R;
import com.rokzin.myschool.core.CustomTimePicker;
import com.rokzin.myschool.core.SemesterPicker;
import com.rokzin.myschool.model.Course;
import com.rokzin.myschool.model.CourseSchedule;
import com.rokzin.myschool.model.Day;
import com.rokzin.myschool.model.database.DBHandler;
import com.rokzin.myschool.utils.F;

public class AddCourseView extends Dialog {
	
	private class CustomTextListener implements TextWatcher{

		@Override
		public void afterTextChanged(Editable s) {
			if(s.length()==0){
				doneButton.setImageResource(R.drawable.button_done_disabled);
				doneButton.setId(0);
			}
			else{
				doneButton.setImageResource(R.drawable.button_done);
				doneButton.setId(1);
			}
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {}
		
	}

	private Context context;
	private ImageView doneButton;
	private CheckBox sun;
	private CheckBox m;
	private CheckBox t;
	private CheckBox w;
	private CheckBox r;
	private CheckBox f;
	private CheckBox s;
	private CustomTimePicker timePicker;

	public AddCourseView(Context context) {
		super(context,android.R.style.Theme_Holo_Light);
		this.context = context;
		setTitle("AddCourse");
		init();
		
		
	}
	
	public void init(){
		
		RelativeLayout layout = new RelativeLayout(context);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layout.setPadding(25, 25, 25, 25);

		

		final EditText courseTitle = new EditText(context);
		courseTitle.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS | InputType.TYPE_CLASS_TEXT |  InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
		courseTitle.setHint("Title");
		courseTitle.setTextSize(22);
		courseTitle.setFilters(new InputFilter[] {new InputFilter.LengthFilter(15)});
		courseTitle.setId(2992);
		courseTitle.setHintTextColor(Color.parseColor("#C45252"));
		courseTitle.addTextChangedListener(new CustomTextListener());

		
		RelativeLayout.LayoutParams courseTitleParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 90);
		courseTitleParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		courseTitleParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		sun = getCheckBox("S",3000);
		m = getCheckBox("M",2999);
		t = getCheckBox("T",2998);
		w = getCheckBox("W",2997);
		r = getCheckBox("R",2996);
		f = getCheckBox("F",2995);
		s = getCheckBox("S",2994);
		
		

		final EditText courseDescription = new EditText(context);
		courseDescription.setHint("Description");
		courseDescription.setId(2985);
		courseDescription.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS | InputType.TYPE_CLASS_TEXT |  InputType.TYPE_TEXT_FLAG_AUTO_CORRECT);
		
		RelativeLayout.LayoutParams courseDescriptionparams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 90);
		courseDescriptionparams.addRule(RelativeLayout.BELOW,courseTitle.getId());
		
		
		final EditText professorName = new EditText(context);
		professorName.setHint("Professor");
		professorName.setId(2993);
		professorName.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);

		RelativeLayout.LayoutParams professorNameParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 90);
		professorNameParams.addRule(RelativeLayout.BELOW,courseDescription.getId());
		
		final EditText locationName = new EditText(context);
		locationName.setHint("Location");
		locationName.setId(2986);
		locationName.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
		
		RelativeLayout.LayoutParams locationParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 90);
		locationParams.addRule(RelativeLayout.BELOW,professorName.getId());
		
		RelativeLayout semesterAndTime = new RelativeLayout(context);
		semesterAndTime.setId(2977);
		RelativeLayout.LayoutParams semesterAndTimeParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		semesterAndTimeParams.addRule(RelativeLayout.BELOW,locationName.getId());
		semesterAndTimeParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		final SemesterPicker semesterPicker = new SemesterPicker(context);
		semesterPicker.setId(2980);
		RelativeLayout.LayoutParams spp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		spp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		spp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		
		
		timePicker = new CustomTimePicker(context);
		timePicker.setId(2978);
		RelativeLayout.LayoutParams timePickerParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		timePickerParams.addRule(RelativeLayout.RIGHT_OF, semesterPicker.getId());
		
		RelativeLayout days = new RelativeLayout(context);
		days.setId(2991);
		days.setPadding(5, 5, 5, 5);
		days.setBackgroundColor(Color.parseColor("#47423F"));
		days.setGravity(Gravity.CENTER_HORIZONTAL);
		
		RelativeLayout.LayoutParams sunParams = new RelativeLayout.LayoutParams(85,75);
		sunParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		sunParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		
		RelativeLayout.LayoutParams mParams = new RelativeLayout.LayoutParams(85,75);
		mParams.addRule(RelativeLayout.RIGHT_OF, sun.getId());

		RelativeLayout.LayoutParams tParams = new RelativeLayout.LayoutParams(85,75);
		tParams.addRule(RelativeLayout.RIGHT_OF, m.getId());
		
		RelativeLayout.LayoutParams wParams = new RelativeLayout.LayoutParams(85,75);
		wParams.addRule(RelativeLayout.RIGHT_OF, t.getId());
		
		RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(85,75);
		rParams.addRule(RelativeLayout.RIGHT_OF, w.getId());
		
		RelativeLayout.LayoutParams fParams = new RelativeLayout.LayoutParams(85,75);
		fParams.addRule(RelativeLayout.RIGHT_OF, r.getId());
		
		RelativeLayout.LayoutParams sParams = new RelativeLayout.LayoutParams(85,75);
		sParams.addRule(RelativeLayout.RIGHT_OF, f.getId());
		

		days.addView(m, mParams);
		days.addView(t, tParams);
		days.addView(w, wParams);
		days.addView(r, rParams);
		days.addView(f, fParams);
		days.addView(s, sParams);
		days.addView(sun, sunParams);
		
		RelativeLayout.LayoutParams daysParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		daysParams.addRule(RelativeLayout.BELOW, semesterAndTime.getId());

		Spinner duration = new Spinner(context);
		duration.setId(2989);
		
		RelativeLayout.LayoutParams durationParams = new RelativeLayout.LayoutParams(context.getResources().getDisplayMetrics().widthPixels/2,LayoutParams.WRAP_CONTENT);
		durationParams.addRule(RelativeLayout.BELOW, days.getId());
	
		final Spinner creditHours = new Spinner(context);
		creditHours.setId(2987);
		
		RelativeLayout.LayoutParams creditHoursParams = new RelativeLayout.LayoutParams(context.getResources().getDisplayMetrics().widthPixels/2 ,LayoutParams.WRAP_CONTENT);
		creditHoursParams.addRule(RelativeLayout.BELOW, days.getId());
		creditHoursParams.addRule(RelativeLayout.RIGHT_OF, duration.getId());
	
		
		ArrayList<String> spValues = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			int j = 30 + (i*10);
			spValues.add(String.valueOf(j));
			
		}
		ArrayList<String> creditHoursValues = new ArrayList<String>();
		for (int i = 1; i < 9; i++) {
			creditHoursValues.add(String.valueOf(i));
			
		}
		
		SpinnerAdapter durationAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, spValues);
		
		duration.setAdapter(durationAdapter);
		
		SpinnerAdapter creditHoursAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, creditHoursValues);
		
		
		creditHours.setAdapter(creditHoursAdapter);
		layout.addView(courseTitle, courseTitleParams);
		layout.addView(courseDescription, courseDescriptionparams);
		layout.addView(professorName, professorNameParams);
		layout.addView(locationName, locationParams);
		
		

		
		semesterAndTime.addView(semesterPicker, spp );
		semesterAndTime.addView(timePicker, timePickerParams);
		
		
		doneButton = new ImageView(context);
		doneButton.setImageResource(R.drawable.button_done_disabled);
		doneButton.setId(0);
		RelativeLayout.LayoutParams doneButtonparams = new RelativeLayout.LayoutParams(100, 100);
		doneButtonparams.addRule(RelativeLayout.BELOW,duration.getId());
		doneButtonparams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		doneButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(doneButton.getId() == 0){
					Toast.makeText(context, "Please enter the required fields.", Toast.LENGTH_SHORT).show();
					int year = Calendar.getInstance().get(Calendar.YEAR);
					Log.d("myschool", String.valueOf(year));
				}
				else{
				Course course = new Course();
				course.setTitle(F.getUppercaseText(courseTitle));
				course.setName(F.getText(courseDescription));
				course.setProfessor(F.getText(professorName));
				course.setLocation(F.getText(locationName));
				course.setCreditHours(creditHours.getSelectedItemPosition()+1);
				course.setCourseSchedule(computeSchedule()); 
				course.setSemester(semesterPicker.getSemesterAndYear());
				
				DBHandler db = new DBHandler(context);
				db.addCourse(course);
				db.close();
				dismiss();
				}
				
			}

			
		});

		layout.addView(semesterAndTime, semesterAndTimeParams);
		layout.addView(days, daysParams);
		layout.addView(duration,durationParams);

		layout.addView(creditHours,creditHoursParams);
		
		layout.addView(doneButton, doneButtonparams);
		
		
		ScrollView scrollView = new ScrollView(context);
		RelativeLayout.LayoutParams scrollViewParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		scrollViewParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		scrollViewParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		scrollView.addView(layout, params);
		
		addContentView(scrollView, scrollViewParams);

	}

	private CheckBox getCheckBox(String value, int id) {
		CheckBox cb = new CheckBox(context);
		cb.setId(id);
		cb.setText(value);
		cb.setTextColor(Color.WHITE);
		return cb;
	}
	
	private String computeSchedule() {
		CourseSchedule cs = new CourseSchedule();
		ArrayList<Day> days = new ArrayList<Day>();
			if(sun.isChecked()){
				days.add(Day.SUNDAY);
			}
			if(m.isChecked()){
				days.add(Day.MONDAY);
			}
			if(t.isChecked()){
				days.add(Day.TUESDAY);
			}
			if(w.isChecked()){
				days.add(Day.WEDNESDAY);
			}
			if(r.isChecked()){
				days.add(Day.THURSDAY);
			}
			if(f.isChecked()){
				days.add(Day.FRIDAY);
			}
			if(s.isChecked()){
				days.add(Day.SATURDAY);
			}
		cs.addDay(days);
		cs.setTime(timePicker.getTime());
		
		return cs.getSchString();
	}

}
