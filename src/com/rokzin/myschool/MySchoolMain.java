package com.rokzin.myschool;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import com.rokzin.myschool.core.CustomMenuItem;
import com.rokzin.myschool.core.SideMenuAdapter;
import com.rokzin.myschool.ui.FlashCardsView;
import com.rokzin.myschool.ui.SettingsView;

public class MySchoolMain extends Activity {

	private ListView sideMenu;
	
	private ViewSwitcher mainViewSwitcher;
	
	private FlashCardsView rFlashCardView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		rFlashCardView = new FlashCardsView(this);
		mainViewSwitcher = (ViewSwitcher) findViewById(R.id.mainViewSwitcher);
		mainViewSwitcher.addView(rFlashCardView);
		createSideMenu();
		
		
		
	}

	private void createSideMenu() {
		sideMenu = (ListView) findViewById(R.id.sidemenu_list);
		ArrayList<CustomMenuItem> a= new ArrayList<CustomMenuItem>();
		a.add(new CustomMenuItem(R.drawable.icon_flashcard,"Flashcards"));
		a.add(new CustomMenuItem(R.drawable.icon_settings,"Settings"));
		sideMenu.setAdapter(new SideMenuAdapter(this, a));
		sideMenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int i,
					long arg3) {
				mainViewSwitcher.removeAllViews();
				if(i == 1){
					
					mainViewSwitcher.addView(new SettingsView(MySchoolMain.this));
				}
				if(i==0){
					mainViewSwitcher.addView(rFlashCardView);
					((FlashCardsView)mainViewSwitcher.getCurrentView()).setResults();
				}
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem add = menu.add("Add");
		add.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		add.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				((FlashCardsView)mainViewSwitcher.getCurrentView()).addFlashCardDialog();
				return true;
			}
		});
		return true;
	}
	

	
}
