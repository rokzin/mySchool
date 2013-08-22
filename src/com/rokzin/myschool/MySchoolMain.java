package com.rokzin.myschool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
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
import com.rokzin.myschool.ui.AssignmentsView;
import com.rokzin.myschool.ui.CoursesView;
import com.rokzin.myschool.ui.SettingsView;

public class MySchoolMain extends Activity {

	private ListView sideMenu;
	
	private ViewSwitcher mainViewSwitcher;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mainViewSwitcher = (ViewSwitcher) findViewById(R.id.mainViewSwitcher);
		mainViewSwitcher.addView(new SettingsView(this));
		createSideMenu();
		copyDbToSdcard();
		
		
	}

	private void createSideMenu() {
		sideMenu = (ListView) findViewById(R.id.sidemenu_list);
		ArrayList<CustomMenuItem> a= new ArrayList<CustomMenuItem>();
		a.add(new CustomMenuItem(R.drawable.icon_courses,"Courses"));
		a.add(new CustomMenuItem(R.drawable.icon_assignments,"Assignments"));
		a.add(new CustomMenuItem(R.drawable.icon_flashcard,"Flashcards"));
		a.add(new CustomMenuItem(R.drawable.icon_settings,"Settings"));
		
		sideMenu.setAdapter(new SideMenuAdapter(this, a));
		sideMenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int i,
					long arg3) {
				mainViewSwitcher.removeAllViews();
				if(i == 1){
					
					mainViewSwitcher.addView(new AssignmentsView(MySchoolMain.this));
				}
				if(i==0){
					mainViewSwitcher.addView(new CoursesView(MySchoolMain.this));
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
				if(mainViewSwitcher.getCurrentView() instanceof CoursesView){
					((CoursesView)mainViewSwitcher.getCurrentView()).showAddCourseDialog();
				}
				if(mainViewSwitcher.getCurrentView() instanceof AssignmentsView){
					((AssignmentsView)mainViewSwitcher.getCurrentView()).showAddCourseDialog();
				}
				return true;
			}
		});
		return true;
	}
	
	private void copyDbToSdcard() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//"+this.getPackageName()+"//databases//mySchoolDB";
                String backupDBPath = "mySchoolDB";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {
        }
        
        
    }

	
}
