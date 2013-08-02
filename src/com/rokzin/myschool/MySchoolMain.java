package com.rokzin.myschool;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.rokzin.myschool.core.SideMenuAdapter;

public class MySchoolMain extends Activity {

	private ListView sideMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		createSideMenu();
	}

	private void createSideMenu() {
		sideMenu = (ListView) findViewById(R.id.sidemenu_list);
		ArrayList<Integer> a= new ArrayList<Integer>();
		a.add(R.drawable.icon_flashcard);
		a.add(R.drawable.icon_settings);
		sideMenu.setAdapter(new SideMenuAdapter(this, a));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_school_main, menu);
		return true;
	}

}
