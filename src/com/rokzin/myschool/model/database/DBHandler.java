package com.rokzin.myschool.model.database;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rokzin.myschool.model.Assignment;
import com.rokzin.myschool.model.Course;

public class DBHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "mySchoolDB";

	// Course table name 
	private static final String TABLE_COURSES = "courses";

	// course Table Columns names
	private static final String COURSE_ID = "id";
	private static final String TITLE = "title";
	private static final String NAME = "name";
	private static final String PROFESSOR = "professor";
	private static final String LOCATION = "location";
	private static final String CREDIT_HOURS = "credithours";
	private static final String SCHEDULE = "schedule";



	// Course table name 
	private static final String TABLE_ASSIGNMENTS = "assignments";

	// course Table Columns names
	private static final String ASSIGNMENT_ID = "id";
	private static final String ASSIGNMENT_NAME = "name";
	private static final String DESCRIPTION = "description";
	private static final String COURSE = "course";
	private static final String DUEDATE = "duedate";
	private static final String STATUS = "isactive";
	private static final String FORCOURSE_ID = "course_id";



	
	public DBHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {

		//id,title,name,professor,location,credithours, schedule
		String CREATE_COURSES_TABLE = "CREATE TABLE " + TABLE_COURSES + "("
				+ COURSE_ID + " INTEGER PRIMARY KEY," + 
				TITLE + " TEXT," + 
				NAME + " TEXT," + 
				PROFESSOR + " TEXT," + 
				LOCATION + " TEXT,"+
				CREDIT_HOURS +" TEXT, "+
				SCHEDULE +" TEXT" +")";
		db.execSQL(CREATE_COURSES_TABLE);
		
		String CREATE_ASSIGNMENTS_TABLE = "CREATE TABLE " + TABLE_ASSIGNMENTS + "("
				+ ASSIGNMENT_ID + " INTEGER PRIMARY KEY," //ID, Name , desc, course, duedate, status
				+ NAME + " TEXT,"
				+ DESCRIPTION + " TEXT," 
				+ COURSE + " TEXT," 
				+ DUEDATE + " TEXT,"
				+ STATUS + " INTEGER,"
				+ FORCOURSE_ID +" INTEGER," 
				+ "FOREIGN KEY ("+FORCOURSE_ID+") REFERENCES "+TABLE_COURSES+ " ("+ COURSE_ID + "));";
		Log.d("myschool", "Creating table");
		db.execSQL(CREATE_ASSIGNMENTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new COURSE
		public void addCourse(Course course) {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(TITLE, course.getTitle()); // course title
			values.put(NAME, course.getName()); // course name
			values.put(PROFESSOR, course.getProfessor()); // professor name
			values.put(LOCATION, course.getLocation()); // location
			values.put(CREDIT_HOURS, String.valueOf(course.getCreditHours())); // credit hours
			values.put(SCHEDULE, course.getCourseSchedule());//schedule

			// Inserting Row
			db.insert(TABLE_COURSES, null, values);
			db.close(); // Closing database connection
		}

	
	// Getting All courses
		public ArrayList<Course> getAllCourses() {
			ArrayList<Course> courseList = new ArrayList<Course>();
			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_COURSES;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					//id,title,name,professor,location,credithours, schedule
					Course course = new Course();
					course.setID(cursor.getString(0));
					course.setTitle(cursor.getString(1));
					course.setName(cursor.getString(2));
					course.setProfessor(cursor.getString(3));
					course.setLocation(cursor.getString(4));
					course.setCreditHours(Integer.parseInt(cursor.getString(5)));
					course.setCourseSchedule(cursor.getString(6));
					// Adding course to list
					courseList.add(course);
				} while (cursor.moveToNext());
			}

			cursor.close();
			db.close();
			// return course list
			return courseList;
		}

	
	// Updating single course
	public int updateCourse(Course course) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TITLE, course.getTitle());
		values.put(NAME, course.getName());
		values.put(PROFESSOR, course.getProfessor());
		values.put(LOCATION, course.getLocation());
		values.put(CREDIT_HOURS, String.valueOf(course.getCreditHours()));
		
		db.close();
		// updating row
		return db.update(TABLE_COURSES, values, "KEY_ID" + " = ?",
				new String[] { /*TODO*/ course.getName() });
		
	}


	// Getting course Count
	public int getCourseCount() {
		String countQuery = "SELECT  * FROM " + TABLE_COURSES;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		
		// return count
		return cursor.getCount();
	}
	
	// Deleting single Course
	public void deleteCourse(Course course) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_COURSES, COURSE_ID + " = ?",
				new String[] { String.valueOf(course.getID()) });
		db.close();
	}

	public void deleteAllCourses(){
		SQLiteDatabase sdb= this.getWritableDatabase();
	    sdb.delete(TABLE_COURSES, null, null);
	    sdb.close();
	}
	
	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new ASSIGNMENT
		public void addAssignment(Assignment assignment) {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(ASSIGNMENT_NAME, assignment.getName()); // Assignment name
			values.put(DESCRIPTION, assignment.getDescription()); // Assignment desc
			values.put(COURSE, assignment.getCourse().getTitle()); // Assignment for course
			values.put(DUEDATE, assignment.getDueDate().toString()); // Assignment duedate
			values.put(STATUS, assignment.getStatus()); // Assignment duedate
			values.put(FORCOURSE_ID, assignment.getCourse().getID()); // Assignment COURSE ID
			
			// Inserting Row
			db.insert(TABLE_ASSIGNMENTS, null, values);
			db.close(); // Closing database connection
		}

	
	// Getting All Assignments
		public ArrayList<Assignment> getAllAssignments() {
			ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_ASSIGNMENTS;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					 //ID, Name , desc, course, duedate, status
					Assignment assignment = new Assignment();
					assignment.setID(cursor.getString(0));
					assignment.setName(cursor.getString(1));
					assignment.setDescription(cursor.getString(2));
					assignment.setCourse(getCourseFromTitle(cursor.getString(3)));

//					Date date = null;
//					try {
//						date = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(cursor.getString(4));
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
					assignment.setDueDate(new Date());
					assignment.setStatus(Integer.parseInt(cursor.getString(5)));
					// Adding course to list
					assignmentList.add(assignment);
				} while (cursor.moveToNext());
			}

			cursor.close();
			db.close();
			// return course list
			return assignmentList;
		}
		
		// Getting All Assignments
				public ArrayList<Assignment> getAllActiveAssignments() {
					ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
					// Select All Query
					String selectQuery = "SELECT  * FROM " + TABLE_ASSIGNMENTS +" WHERE "+STATUS + " = 1";

					SQLiteDatabase db = this.getWritableDatabase();
					Cursor cursor = db.rawQuery(selectQuery, null);

					// looping through all rows and adding to list
					if (cursor.moveToFirst()) {
						do {
							 //ID, Name , desc, course, duedate, status
							Assignment assignment = new Assignment();
							assignment.setID(cursor.getString(0));
							assignment.setName(cursor.getString(1));
							assignment.setDescription(cursor.getString(2));
							assignment.setCourse(getCourseFromTitle(cursor.getString(3)));

//							Date date = null;
//							try {
//								date = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(cursor.getString(4));
//							} catch (ParseException e) {
//								e.printStackTrace();
//							}
							assignment.setDueDate(new Date());
							assignment.setStatus(Integer.parseInt(cursor.getString(5)));
							// Adding course to list
							assignmentList.add(assignment);
						} while (cursor.moveToNext());
					}

					cursor.close();
					db.close();
					// return course list
					return assignmentList;
				}

				// Getting All Assignments
				public ArrayList<Assignment> getAllInactiveAssignments() {
					ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
					// Select All Query
					String selectQuery = "SELECT  * FROM " + TABLE_ASSIGNMENTS +" WHERE "+STATUS + " = 1";

					SQLiteDatabase db = this.getWritableDatabase();
					Cursor cursor = db.rawQuery(selectQuery, null);

					// looping through all rows and adding to list
					if (cursor.moveToFirst()) {
						do {
							 //ID, Name , desc, course, duedate, status
							Assignment assignment = new Assignment();
							assignment.setID(cursor.getString(0));
							assignment.setName(cursor.getString(1));
							assignment.setDescription(cursor.getString(2));
							assignment.setCourse(getCourseFromTitle(cursor.getString(3)));

//							Date date = null;
//							try {
//								date = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(cursor.getString(4));
//							} catch (ParseException e) {
//								e.printStackTrace();
//							}
							assignment.setDueDate(new Date());
							assignment.setStatus(Integer.parseInt(cursor.getString(5)));
							// Adding course to list
							assignmentList.add(assignment);
						} while (cursor.moveToNext());
					}

					cursor.close();
					db.close();
					// return course list
					return assignmentList;
				}
				
		// Getting All Assignments
			public ArrayList<Assignment> getAssignmentFrom(String courseTitle) {
				ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
				
				Course course = getCourseFromTitle(courseTitle);
				// Select All Query
				String selectQuery = "SELECT  * FROM " + TABLE_ASSIGNMENTS+ " WHERE " + FORCOURSE_ID + "="+course.getID()+" AND "+STATUS+" = 0";

				SQLiteDatabase db = this.getWritableDatabase();
				Cursor cursor = db.rawQuery(selectQuery, null);

				// looping through all rows and adding to list
				if (cursor.moveToFirst()) {
					do {
						//ID, Name , desc, course, duedate, status
						Assignment assignment = new Assignment();
						assignment.setID(cursor.getString(0));
						assignment.setName(cursor.getString(1));
						assignment.setDescription(cursor.getString(2));
						Log.d("myschool", cursor.getString(2));
						assignment.setCourse(getCourseFromTitle(cursor.getString(3)));
						assignment.setStatus(Integer.parseInt(cursor.getString(5)));

						
						assignment.setDueDate(new Date());
						// Adding course to list
						assignmentList.add(assignment);
					} while (cursor.moveToNext());
				}

				cursor.close();
				db.close();
				// return course list
				return assignmentList;
			}

		private Course getCourseFromTitle(String title) {
			for (Course course : getAllCourses()) {
				if(course.getTitle().trim().equals(title.trim())){
					return course;
				}
			}
			return null;
		}
	
//	// Updating single course
//	public int updateCourse(Course course) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(TITLE, course.getTitle());
//		values.put(NAME, course.getName());
//		values.put(PROFESSOR, course.getProfessor());
//		values.put(LOCATION, course.getLocation());
//		values.put(CREDIT_HOURS, String.valueOf(course.getCreditHours()));
//		
//		db.close();
//		// updating row
//		return db.update(TABLE_ASSIGNMENTS, values, "KEY_ID" + " = ?",
//				new String[] { /*TODO*/ course.getName() });
//		
//	}


	// Getting Assignment Count
	public int getAssignmentCount() {
		String countQuery = "SELECT  * FROM " + TABLE_ASSIGNMENTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		
		// return count
		return cursor.getCount();
	}
	
	// Deleting single Assignment
	public void deleteAssignment(Assignment assignment) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_ASSIGNMENTS, ASSIGNMENT_ID + " = ?",
				new String[] { String.valueOf(assignment.getID()) });
		db.close();
	}

	public void setInactive(Assignment assignment) {
		String setInactiveQuery = "UPDATE " + TABLE_ASSIGNMENTS +" SET "+STATUS +"=1 "+" WHERE "+ASSIGNMENT_ID+"="+assignment.getID()+";";
		//UPDATE Books SET Author='Lev Nikolayevich Tolstoy' WHERE Id=1;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(setInactiveQuery, null);
		cursor.moveToFirst();
		cursor.close();
		db.close();
		
	}
//	
//	// Updating single course
//	public int updateAssignment(Assignment assignment) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		
//		ContentValues values = new ContentValues();
//		values.put(ASSIGNMENT_NAME, assignment.getName()); // Assignment name
//		values.put(DESCRIPTION, assignment.getDescription()); // Assignment desc
//		values.put(COURSE, assignment.getCourse().getTitle()); // Assignment for course
//		values.put(DUEDATE, assignment.getDueDate().toString()); // Assignment duedate
//		values.put(STATUS, assignment.getStatus()); // Assignment due date
//		values.put(FORCOURSE_ID, assignment.getCourse().getID()); // Assignment COURSE ID
//		
//		
//		//db.close();
//		// updating row
//		return db.update(TABLE_ASSIGNMENTS, values, "id" + " = ?",
//				new String[] { assignment.getID() });
//		
//	}


	

}
