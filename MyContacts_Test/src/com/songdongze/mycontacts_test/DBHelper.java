package com.songdongze.mycontacts_test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static int DATABASE_VERSION = 1;
	
	public static String DATABASE_NAME = "mycontacts.db";
	
	public static String CONTACTS_TABLE = "contacts";
	
	private static String DATABASE_CREATETABLE = "CREATE TABLE " + CONTACTS_TABLE 
			+ " ("
			+ ContactColumn._ID_COLUMN + " integer primary key autoincrement, "
			+ ContactColumn.NAME + "text, "
			+ ContactColumn.COMPANY + "text, "
			+ ContactColumn.PRIVATEPHONE + "text, "
			+ ContactColumn.COMPANYPHONE + "text, "
			+ ContactColumn.EMAIL + "text, "
			+ ContactColumn.QQ + "text, "
			+ ");";
	
	

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATETABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE);
		onCreate(db);

	}

}
