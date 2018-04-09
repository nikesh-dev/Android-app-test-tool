package com.example.event;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class MyDBHandler extends SQLiteOpenHelper {

	public static int n;
	private static final int DATABASE_VERSION = 1;
	private static String DATABASE_NAME;
	// public static final String TABLE_PRODUCTS = "products";
	public static String cases;
	public static Cursor cursor;
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_VALUE1 = "value1";
	public static final String COLUMN_VALUE2 = "value2";
	public static final String COLUMN_TIME = "time";
	public static final String COLUMN_IMAGE = "image";

	// public static final String PACKAGE="package";
	// public SQLiteDatabase db2;
	// public static final String COLUMN_PRODUCTNAME = "productname";
	// public static final String COLUMN_QUANTITY = "quantity";

	public MyDBHandler(Context context, String dbname, String name,
			CursorFactory factory, int version) {

		super(context, Environment.getExternalStorageDirectory()
				+ File.separator + "NikRoid" + File.separator + dbname
				+ File.separator + name, factory, DATABASE_VERSION);
		cases = name;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
		// TABLE_PRODUCTS + "("
		// + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PRODUCTNAME
		// + " TEXT," + COLUMN_QUANTITY + " INTEGER" + ")";
		String CREATE_CASES = "CREATE TABLE " + cases + "(" + COLUMN_ID
				+ " INTEGER PRIMARY KEY," + COLUMN_TYPE + " INTEGER,"
				+ COLUMN_VALUE1 + " INTEGER," + COLUMN_VALUE2 + " INTEGER,"
				+ COLUMN_TIME + " LONG," + COLUMN_IMAGE + " INTEGER" + ");";
		// db.execSQL(CREATE_PRODUCTS_TABLE);

		db.execSQL(CREATE_CASES);
		// db2=db;
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
		onCreate(db);
		// TODO Auto-generated method stub

	}

	public void addProduct(Product product) {

		ContentValues values = new ContentValues();
		// values.put(COLUMN_PRODUCTNAME, product.getProductName());
		// values.put(COLUMN_QUANTITY, product.getQuantity());
		values.put(COLUMN_VALUE1, product.getValue1());
		values.put(COLUMN_VALUE2, product.getValue2());
		values.put(COLUMN_TYPE, product.getType());
		values.put(COLUMN_TIME, product.getValue3());
		values.put(COLUMN_IMAGE, product.getValue4());

		// values.put(PACKAGE, product.getpackage());
		SQLiteDatabase db = this.getWritableDatabase();
		db.insert(cases, null, values);
		// db.insert(TABLE_PRODUCTS, null, values);
		db.close();
	}

	/*
	 * public void addProductt(Product product) { ContentValues values = new
	 * ContentValues(); values.put(COLUMN_ID, product.getID());
	 * values.put(COLUMN_VALUE1, product.getValue1()); values.put(COLUMN_VALUE2,
	 * product.getValue2()); values.put(COLUMN_TYPE, product.getType());
	 * values.put(COLUMN_TIME, product.getValue3());
	 * 
	 * // values.put(PACKAGE, product.getpackage()); SQLiteDatabase db =
	 * this.getWritableDatabase(); db.insert(cases, null, values); db.close(); }
	 */
	public Product findProduct(int id) {
		// String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " +
		// COLUMN_PRODUCTNAME + " =  \"" + productname + "\"";
		// String query="Select * FROM "+ cases + " WHERE "+COLUMN_ID
		// +" = \""+id+"\"";
		try {
			String query = "Select * FROM " + cases;
			SQLiteDatabase db = this.getWritableDatabase();

			cursor = db.rawQuery(query, null);

			n = cursor.getCount();
			Product product = new Product();

			if (cursor.moveToFirst()) {
				cursor.moveToFirst();
				while (!cursor.isAfterLast()) {
					product.setID(Integer.parseInt(cursor.getString(0)));
					product.setType(Integer.parseInt(cursor.getString(1)));
					product.setValue1(Integer.parseInt(cursor.getString(2)));
					product.setValue2(Integer.parseInt(cursor.getString(3)));
					product.setValue3(Long.parseLong((cursor.getString(4))));
					product.setValue4(Integer.parseInt((cursor.getString(5))));
					// product.setpackage(cursor.getString(5));

					cursor.moveToNext();
				}

				// product.setProductName(cursor.getString(1));
				// product.setQuantity(Integer.parseInt(cursor.getString(2)));
				// cursor.close();
			} else {
				product = null;
			}
			db.close();
			return product;
		} catch (Exception e) {
			return null;
		}
	}

	public String deletetable(String tablename) {
		String h = cases;
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE " + cases);
		return cases;

	}

	public boolean deleteProduct(int id) {

		boolean result = false;
		SQLiteDatabase db = this.getWritableDatabase();

		// String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " +
		// COLUMN_PRODUCTNAME + " =  \"" + productname + "\"";
		/*
		 * String query="Select * FROM "+cases+" WHERE "+COLUMN_ID
		 * +" = \""+id+"\""; SQLiteDatabase db = this.getWritableDatabase();
		 * 
		 * Cursor cursor = db.rawQuery(query, null);
		 * 
		 * Product product = new Product();
		 * 
		 * if (cursor.moveToFirst()) {
		 * product.setID(Integer.parseInt(cursor.getString(0)));
		 */
		if (db.delete(cases, COLUMN_ID + " = ?", new String[] { id + ""/*
																		 * String.
																		 * valueOf
																		 * (
																		 * product
																		 * .
																		 * getID
																		 * ())
																		 */}) > 0) {
			// cursor.close();
			result = true;
		}
		db.close();
		return result;
	}

	public void updatee(int id, int ty, int z, int v, long tim, int img) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();

		cv.put("_id", id); // These Fields should be your String values of
							// actual column names
		cv.put("type", ty);
		cv.put("value1", z);
		cv.put("value2", v);
		cv.put("time", tim);
		cv.put("image", img);
		// Log.v("cv", id+"");

		db.update(cases, cv, "_id" + "=" + id, null);
	}

	public void updateee(int oldid, int newid, int ty, int z, int v, long tim,
			int img) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();

		cv.put("_id", newid); // These Fields should be your String values of
								// actual column names
		cv.put("type", ty);
		cv.put("value1", z);
		cv.put("value2", v);
		cv.put("time", tim);
		cv.put("image", img);
		db.update(cases, cv, "_id" + "=" + oldid, null);
	}
}
