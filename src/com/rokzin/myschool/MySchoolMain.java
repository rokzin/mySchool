package com.rokzin.myschool;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MySchoolMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_school_main, menu);
		return true;
	}

}