package com.rokzin.myschool.ui;

import java.util.ArrayList;

import android.R.color;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.TextView;

import com.rokzin.myschool.core.CourseAdapter;
import com.rokzin.myschool.core.SwipeDismissListViewTouchListener;
import com.rokzin.myschool.model.Course;
import com.rokzin.myschool.model.database.DBHandler;

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
                                	db.deleteCourse(courseAdapter.getItem(position-1));
                                	courseAdapter.remove(position-1);
                                	
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
		
		AddCourseView addCourse = new AddCourseView(context);
		addCourse.setOnDismissListener(new DialogInterface.OnDismissListener() {
			
			@Override
			public void onDismiss(DialogInterface dialog) {
				setResults();
				
			}
		});
		addCourse.show();

		
	}

}
