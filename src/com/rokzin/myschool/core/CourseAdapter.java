package com.rokzin.myschool.core;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rokzin.myschool.R;
import com.rokzin.myschool.model.Course;

public class CourseAdapter extends BaseAdapter {
	private static ArrayList<Course> courseList;

	private LayoutInflater mInflater;

	public CourseAdapter(Context context, ArrayList<Course> results) {
		courseList = results;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return courseList.size();
	}

	public Course getItem(int position) {
		return courseList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.course, null);
			holder = new ViewHolder();
			holder.courseTitle = (TextView) convertView.findViewById(R.id.courseTitle);
			holder.courseDescription = (TextView) convertView.findViewById(R.id.courseDescription);
			holder.courseProfessor = (TextView) convertView.findViewById(R.id.courseProfessor);
			holder.courseLocation = (TextView) convertView.findViewById(R.id.courseLocation);
			holder.courseSchedule = (TextView) convertView.findViewById(R.id.courseSchedule);
			
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.courseTitle.setText(String.valueOf(courseList.get(position).getTitle()));
		holder.courseDescription.setText(String.valueOf(courseList.get(position).getName()));
		holder.courseProfessor.setText(String.valueOf(courseList.get(position).getProfessor()));
		holder.courseLocation.setText(String.valueOf(courseList.get(position).getLocation()));
		holder.courseSchedule.setText(String.valueOf(courseList.get(position).getCourseSchedule()));

		return convertView;
	}

	static class ViewHolder {
		TextView courseTitle;
		TextView courseDescription;
		TextView courseProfessor;
		TextView courseLocation;
		TextView courseSchedule;
		
	}

	public void remove(int position) {
		courseList.remove(position);
	}
	
	public void updateList(ArrayList<Course> courses){
		courseList.clear();
		courseList = courses;
		this.notifyDataSetChanged();
	}
}