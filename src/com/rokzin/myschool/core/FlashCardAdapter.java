package com.rokzin.myschool.core;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ViewFlipper;

import com.rokzin.myschool.R;
import com.rokzin.myschool.model.FlashCard;


public class FlashCardAdapter extends BaseAdapter {
	private static ArrayList<FlashCard> flashCardItems;

	private LayoutInflater mInflater;
	private Context context; 
	public FlashCardAdapter(Context context, ArrayList<FlashCard> results) {
		this.context = context;
		flashCardItems = results;
		mInflater = LayoutInflater.from(context);
		
	}

	public int getCount() {
		return flashCardItems.size();
	}

	public Object getItem(int position) {
		return flashCardItems.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.flashcard, null);
			holder = new ViewHolder();
			holder.flipper = (ViewFlipper) convertView.findViewById(R.id.flashcard);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.flipper.addView(FlashCard.createQuestionCard(context,flashCardItems.get(position).getQuestion()));
	

		return convertView;
	}

	static class ViewHolder {
		ViewFlipper flipper;
	}

}
