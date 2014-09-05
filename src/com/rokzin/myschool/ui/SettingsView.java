package com.rokzin.myschool.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.rokzin.myschool.model.database.DBHandler;

public class SettingsView extends RelativeLayout {

	private Context context;

	public SettingsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public SettingsView(Context context) {
		super(context);
		this.context = context;
		init();
	}
	 



	private void init() {
		Button b = new Button(context);
		b.setText("delete");
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DBHandler db = new DBHandler(context);
				db.deleteAllCourses();
				db.close();
				
			}
		});
		addView(b);
		
		
		
	}
}
