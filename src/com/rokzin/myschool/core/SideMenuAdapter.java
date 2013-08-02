package com.rokzin.myschool.core;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.rokzin.myschool.R;

public class SideMenuAdapter extends BaseAdapter {
	private static ArrayList<Integer> sidemenu_items;

	private LayoutInflater mInflater;

	public SideMenuAdapter(Context context, ArrayList<Integer> results) {
		sidemenu_items = results;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return sidemenu_items.size();
	}

	public Object getItem(int position) {
		return sidemenu_items.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.sidemenu_item, null);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView.findViewById(R.id.sidemenu_item);

			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.image.setImageResource(sidemenu_items.get(position));

		return convertView;
	}

	static class ViewHolder {
		ImageView image;
	}
}