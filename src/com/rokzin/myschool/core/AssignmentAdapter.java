package com.rokzin.myschool.core;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rokzin.myschool.R;
import com.rokzin.myschool.model.Assignment;

public class AssignmentAdapter extends BaseAdapter {
	private static ArrayList<Assignment> assignmentList;

	private LayoutInflater mInflater;

	public AssignmentAdapter(Context context, ArrayList<Assignment> assignments) {
		assignmentList = assignments;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return assignmentList.size();
	}

	public Assignment getItem(int position) {
		return assignmentList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.assignment, null);
			holder = new ViewHolder();
			holder.assignmentName = (TextView) convertView.findViewById(R.id.assignmentName);
			holder.assignmentDescription = (TextView) convertView.findViewById(R.id.assignmentDescription);
			holder.course = (TextView) convertView.findViewById(R.id.course);
			holder.dueDate = (TextView) convertView.findViewById(R.id.dueDate);

			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.assignmentName.setText(assignmentList.get(position).getName());
		holder.assignmentDescription.setText(assignmentList.get(position).getDescription());
		holder.course.setText(assignmentList.get(position).getCourse().getTitle());
		holder.dueDate.setText("this");//assignmentList.get(position).getDueDate().toString());

		return convertView;
	}

	static class ViewHolder {
		TextView assignmentName;
		TextView assignmentDescription;
		TextView course;
		TextView dueDate;
		
	}

	public void remove(int position) {
		assignmentList.remove(position);
	}
	
	public void updateList(ArrayList<Assignment> assignments){
		assignmentList.clear();
		assignmentList = assignments;
		this.notifyDataSetChanged();
	}
}