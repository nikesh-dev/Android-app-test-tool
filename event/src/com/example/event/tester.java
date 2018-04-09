package com.example.event;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class tester extends Activity {

	String pkg;
	DatabaseAdapter adapter;
	ListView list;
	Button startt;
	static Handler handler;
	public TextView success, fail;
	File[] fi;
	public static String database;
	// List<PackageInfo> databases =
	// getPackageManager().getInstalledPackages(0);
	int n, posi, count;
	String[] s;
	public tester thiss = null;
	public ArrayList<database> boo;
	public ArrayList<database> db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.databaselist);

		setListdata();
		thiss = this;
		posi = -1;
		final ArrayList<String> cas = new ArrayList<String>();
		list = (ListView) findViewById(R.id.databaselist1); // List defined in
															// XML ( See Below )
		adapter = new DatabaseAdapter(thiss, db);
		startt = (Button) findViewById(R.id.startt);
		list.setAdapter(adapter);
		boo = new ArrayList<database>();
		// Toast.makeText(getBaseContext(), boo[1].getName()+"",
		// Toast.LENGTH_SHORT).show();

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				success.setText(success.getText().toString() + " " + msg.arg1
						+ "");
			}
		};

		startt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Toast.makeText(getBaseContext(), "Checked "+boo.length,
				// Toast.LENGTH_LONG).show();
				int j = DatabaseAdapter.checked.length;

				int count = 0;
				for (int c = 0; c < j; c++)

				{
					if (DatabaseAdapter.checked[c] == true)
						cas.add(db.get(c).getName());
					// count++;
					// cases[c]=db.get(c).getName();
					// Toast.makeText(getBaseContext(),db.get(c).getName()+" checked",
					// Toast.LENGTH_SHORT).show();
				}
				String[] cases = new String[cas.size()];
				count = cas.size();
				for (int c = 0; c < count; c++) {
					cases[c] = cas.get(c);
				}
				/*
				 * for(int c=0;c<count;c++) { Toast.makeText(getBaseContext(),
				 * cases[c], Toast.LENGTH_LONG).show(); }
				 */
				Intent inte = new Intent(tester.this, ghost.class);
				inte.putExtra("cases", cases);
				inte.putExtra("pkg", CustomListViewAndroidExample.pkg);
				setContentView(R.layout.report);
				TextView testCases = (TextView) findViewById(R.id.testcases);
				success = (TextView) findViewById(R.id.successful);
				fail = (TextView) findViewById(R.id.failed);
				testCases.setText(testCases.getText().toString() + " " + count);
				success.setText(success.getText().toString() + " 0");
				fail.setText(fail.getText().toString());
				startService(inte);
			}

		});

	}

	private void setListdata() {
		// TODO Auto-generated method stub
		db = new ArrayList<database>();
		File fs = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ File.separator
				+ "NikRoid"
				+ File.separator + CustomListViewAndroidExample.pkg);
		// int i=s.length;
		// Toast.makeText(getBaseContext(),Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+CustomListViewAndroidExample.pkg+" contains:",
		// Toast.LENGTH_SHORT).show();

		fi = fs.listFiles();
		int i = -1;
		if (fi != null)
			i = fi.length;
		int y;
		database temp;
		for (y = 0; y < i; y++) {
			temp = new database();
			temp.setName(fi[y].getName());

			// Toast.makeText(getBaseContext(), fi[y].getName(),
			// Toast.LENGTH_SHORT).show();
			temp.setCheck(false);
			db.add(temp);
			// a=new
			// ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s);
		}

	}

	public void checkbox(boolean[] p) {
		// TODO Auto-generated method stub

		Toast.makeText(getBaseContext(), "clicked" + " " + p.length,
				Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate menu from XML resource
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		int j = DatabaseAdapter.checked.length;
		// MyDBHandler dbHandler = null;
		for (int c = 0; c < j; c++) {
			if (DatabaseAdapter.checked[c] == true) {
				fi[c].delete();
				// dbHandler = new
				// MyDBHandler(tester.this,CustomListViewAndroidExample.pkg,
				// db.get(c).getName(), null, 1);
				// String table= dbHandler.deletetable(db.get(c).getName());
				// Toast.makeText(getBaseContext(),table+" deleted",
				// Toast.LENGTH_SHORT).show();
			}
		}
		// dbHandler.close();
		setListdata();
		adapter = new DatabaseAdapter(thiss, db);
		list.setAdapter(adapter);
		// Toast.makeText(getBaseContext(), "delete", Toast.LENGTH_LONG).show();
		return true;
	}
}
