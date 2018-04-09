package com.example.event;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class AppsDb extends SQLiteOpenHelper {

	public static final String IMAGE = "image";
	public static final String NAME = "name";
	private static final int DATABASE_VERSION = 1;
	public static final String COLUMN_ID = "_id";

	public AppsDb(Context context, String name, CursorFactory factory,
			int version) {

		// TODO Auto-generated constructor stub
		super(context, Environment.getExternalStorageDirectory()
				+ File.separator + "NikRoid" + File.separator + "appsdb"
				+ File.separator + name, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_CASES = "CREATE TABLE appsdb" + "(" + COLUMN_ID
				+ " INTEGER PRIMARY KEY," + IMAGE + " image" + ");";

		db.execSQL(CREATE_CASES);
		db.execSQL("create table tableimage(image blob);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
