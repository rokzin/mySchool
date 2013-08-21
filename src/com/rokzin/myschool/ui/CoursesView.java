package com.rokzin.myschool.ui;

import java.util.ArrayList;

import android.R.color;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.rokzin.myschool.core.CourseAdapter;
import com.rokzin.myschool.core.SwipeDismissListViewTouchListener;
import com.rokzin.myschool.model.Course;
import com.rokzin.myschool.model.CourseSchedule;
import com.rokzin.myschool.model.database.DBHandler;
import com.rokzin.myschool.utils.F;

public class CoursesView extends ListView {

	private Context context;
	private CourseAdapter courseAdapter;
	
	public CoursesView(Context context) {
		super(context);
		this.context = context;
		setDividerHeight(0);
		setSelector(color.transparent);
		setPadding(0, 0, 0, 20);
		addHeaderView(getHeader());
		DBHandler db = new DBHandler(context);
		ArrayList<Course> a = db.getAllCourses();
		courseAdapter = new CourseAdapter(context, a);
		setAdapter(courseAdapter);
		init();
		
	}
	
	private TextView getHeader() {
		TextView title = new TextView(context);
		title.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
		title.setHeight(65);
		title.setBackgroundColor(Color.parseColor("#88382D"));
		title.setTextColor(Color.WHITE);
		title.setText("Courses");
		return title;
	}

	private void init() {
        // Create a ListView-specific touch listener. ListViews are given special treatment because
        // by default they handle touches for their list items... i.e. they're in charge of drawing
        // the pressed state (the list selector), handling list item clicks, etc.
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        this,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                	DBHandler db = new DBHandler(context);
                                	db.deleteCourse(courseAdapter.getItem(position));
                                	courseAdapter.remove(position);
                                	
                                }
                                courseAdapter.notifyDataSetChanged();
                            }
                        });
        setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
        setOnScrollListener(touchListener.makeScrollListener());

        
    }



	private void setResults() {
		DBHandler db = new DBHandler(context);
		courseAdapter.updateList(db.getAllCourses());
	}

	public CoursesView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}


	public void showAddCourseDialog(){
		
		
		CheckBox sun = new CheckBox(context);
		sun.setId(3000);
		sun.setText("S");
		
		CheckBox m = new CheckBox(context);
		m.setId(2999);
		m.setText("M");

		CheckBox t = new CheckBox(context);
		t.setId(2998);
		t.setText("T");
		
		CheckBox w = new CheckBox(context);
		w.setId(2997);
		w.setText("W");
		
		CheckBox r = new CheckBox(context);
		r.setId(2996);
		r.setText("R");
		
		CheckBox f = new CheckBox(context);
		f.setId(2995);
		f.setText("F");
		
		CheckBox s = new CheckBox(context);
		s.setId(2994);
		s.setText("S");
		
		RelativeLayout layout = new RelativeLayout(context);
		
		final EditText courseTitle = new EditText(context);
		courseTitle.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS | InputType.TYPE_CLASS_TEXT);
		courseTitle.setHint("Title");
		courseTitle.setId(2992);
		courseTitle.setHintTextColor(Color.parseColor("#C45252"));
		
		RelativeLayout.LayoutParams courseTitleParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 90);
		courseTitleParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		courseTitleParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		final EditText courseDescription = new EditText(context);
		courseDescription.setHint("Description");
		courseDescription.setId(2985);
		courseDescription.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
		
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
		
		
		RelativeLayout days = new RelativeLayout(context);
		days.setId(2991);
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
		daysParams.addRule(RelativeLayout.BELOW, locationName.getId());

		Spinner duration = new Spinner(context);
		duration.setId(2989);
		
		RelativeLayout.LayoutParams durationParams = new RelativeLayout.LayoutParams(200,LayoutParams.WRAP_CONTENT);
		durationParams.addRule(RelativeLayout.BELOW, days.getId());
	
		final Spinner creditHours = new Spinner(context);
		creditHours.setId(2987);
		
		RelativeLayout.LayoutParams creditHoursParams = new RelativeLayout.LayoutParams(200,LayoutParams.WRAP_CONTENT);
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
		
		layout.addView(days, daysParams);
		layout.addView(duration,durationParams);

		layout.addView(creditHours,creditHoursParams);
		
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Add Course");
		builder.setView(layout);
		builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		

		final AlertDialog dialog = builder.create();
		dialog.show();
		dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(F.isEmptyOrNull(courseTitle)){
					Toast.makeText(context, "Please enter the required fields.", Toast.LENGTH_SHORT).show();
				}
				else{
				Course course = new Course(F.getUppercaseText(courseTitle),
						F.getText(courseDescription), 
						F.getText(professorName), 
						F.getText(locationName), creditHours.getSelectedItemPosition(), new CourseSchedule()); 
				DBHandler db = new DBHandler(context);
				db.addCourse(course);
				db.close();
				setResults();
				dialog.dismiss();
				}
			}
		
		});

		
		
	}

}
