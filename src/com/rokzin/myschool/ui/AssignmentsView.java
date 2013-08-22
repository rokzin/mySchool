package com.rokzin.myschool.ui;

import java.util.ArrayList;

import android.R.color;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.rokzin.myschool.R;
import com.rokzin.myschool.core.AssignmentAdapter;
import com.rokzin.myschool.core.SwipeDismissListViewTouchListener;
import com.rokzin.myschool.model.Assignment;
import com.rokzin.myschool.model.Course;
import com.rokzin.myschool.model.database.DBHandler;
import com.rokzin.myschool.utils.F;

public class AssignmentsView extends ListView {

	private Context context;
	private AssignmentAdapter assignmentAdapter;
	
	public AssignmentsView(Context context) {
		super(context);
		this.context = context;
		setDividerHeight(0);
		setSelector(color.transparent);
		setPadding(0, 0, 0, 20);
		addHeaderView(getHeader());
		
		DBHandler db = new DBHandler(context);
		ArrayList<Assignment> a = db.getAllAssignments();
		assignmentAdapter = new AssignmentAdapter(context, a);
		setAdapter(assignmentAdapter);
		init();
		
	}
	
	

	private View getHeader() {
		RelativeLayout header = new RelativeLayout(context);
		header.setBackgroundColor(Color.parseColor("#475F77"));
		header.setPadding(10, 0, 10, 10);
		
		final TextView title = new TextView(context);
		title.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
		title.setTextColor(Color.WHITE);
		title.setText("Assignments: All");
		title.setTextSize(18);
		title.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				DBHandler db = new DBHandler(context);
				ArrayList<Course> courses = db.getAllCourses();
				ArrayList<String> titles = new ArrayList<String>();
				titles.add("All");
				titles.add("Completed");
				for (Course course : courses) {
					titles.add(course.getTitle());
				}
				
				final CharSequence[] options = titles.toArray(new CharSequence[titles.size()]);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(context);

				builder.setItems(options, new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				    	title.setText("Assignments: "+ options[which]);
				    	if(options[which].equals("All")){
				    		
				    		DBHandler db = new DBHandler(context);
				    		assignmentAdapter.updateList(db.getAllAssignments());
				    	}
				    	else if(options[which].equals("Completed")){
				    		
				    		DBHandler db = new DBHandler(context);
				    		assignmentAdapter.updateList(db.getAllInactiveAssignments());
				    	}
				    	else{
				    		DBHandler db = new DBHandler(context);
				    		assignmentAdapter.updateList(db.getAssignmentFrom(options[which].toString()));
				    	}
				    	
				    }



				});
				builder.show();
				
			}
		});
		
		RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 80);
		titleParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		titleParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		
		ImageView corner = new ImageView(context);
		corner.setImageResource(R.drawable.dropdown_corner);
		
		RelativeLayout.LayoutParams cornerParams = new RelativeLayout.LayoutParams(20,20);
		cornerParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		cornerParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		
		
		header.addView(title,titleParams);
		header.addView(corner, cornerParams);
		return header;
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
                        			Assignment assignment = assignmentAdapter.getItem(position-1);
                        			Log.d("myschool", String.valueOf(position-1));
                        			db.setInactive(assignment);
                        			assignmentAdapter.remove(position-1);
                                	
                                }
                            	assignmentAdapter.notifyDataSetChanged();
                            	
                                
                            }

                        });
        setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
        setOnScrollListener(touchListener.makeScrollListener());

        
    }



	private void setResults() {
		DBHandler db = new DBHandler(context);
		assignmentAdapter.updateList(db.getAllAssignments());
	}

	public AssignmentsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}


	public void showAddCourseDialog(){
		
		RelativeLayout layout = new RelativeLayout(context);
		
		final EditText assignmentName = new EditText(context);
		assignmentName.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS | InputType.TYPE_CLASS_TEXT);
		assignmentName.setHint("Name");
		assignmentName.setId(2992);
		
		RelativeLayout.LayoutParams assignmentNameParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 90);
		assignmentNameParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		assignmentNameParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		final EditText assignmentDescription = new EditText(context);
		assignmentDescription.setHint("Description");
		assignmentDescription.setId(2985);
		assignmentDescription.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
		
		RelativeLayout.LayoutParams assignmentDescriptionparams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 90);
		assignmentDescriptionparams.addRule(RelativeLayout.BELOW,assignmentName.getId());

		final Spinner coursesSpinner = new Spinner(context);
		coursesSpinner.setId(2989);
		
		RelativeLayout.LayoutParams courseParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		courseParams.addRule(RelativeLayout.BELOW, assignmentDescription.getId());
		
		
		ArrayList<String> spValues = new ArrayList<String>();
		DBHandler db = new DBHandler(context);
		ArrayList<Course> allcourses = db.getAllCourses();

		for (Course course : allcourses) {
			spValues.add(course.getTitle());
		}
		
		SpinnerAdapter coursesAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, spValues);
		
		coursesSpinner.setAdapter(coursesAdapter);
				
		final DatePicker dp = new DatePicker(context);
		dp.setId(2982);
		
		RelativeLayout.LayoutParams dpParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		dpParams.addRule(RelativeLayout.BELOW, coursesSpinner.getId());
		
		
		layout.addView(assignmentName, assignmentNameParams);
		layout.addView(assignmentDescription, assignmentDescriptionparams);
		layout.addView(coursesSpinner, courseParams);
		layout.addView(dp,dpParams);
		
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Add Course");
		builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Assignment assignment = new Assignment();
				assignment.setName(F.getText(assignmentName));
				assignment.setDescription(F.getText(assignmentDescription));
				assignment.setDueDate(F.getDateFromDatePicket(dp));
				assignment.setCourse(getCourseFromTitle(coursesSpinner.getSelectedItem().toString()));
				assignment.setStatus(1);
				DBHandler db = new DBHandler(context);
				db.addAssignment(assignment);
				db.close();
				setResults();
			}

			
		});
		
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		
		builder.setView(layout);
		builder.show();

	}
	
	private Course getCourseFromTitle(String title) {
		DBHandler db = new DBHandler(context);
		for (Course course : db.getAllCourses()) {
			if(course.getTitle().trim().equals(title.trim())){
				return course;
			}
		}
		return null;
	}
}
