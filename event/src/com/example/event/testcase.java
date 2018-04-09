package com.example.event;

import com.example.event.Nativenput.InputDevice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class testcase extends Activity {

	class inputt {
		int type;
		String key1, key2;
	}

	int count;
	public static String dbname;
	public static Boolean recordd;
	Nativenput ni = new Nativenput();
	String st;
	public static inputt[] in;
	public static int ptr;
	Button b;
	TextView t1;
	final Context context = this;
	String pkg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		in = new inputt[50];
		st = "";
		setContentView(R.layout.testcase_home);
		pkg = getIntent().getCharSequenceExtra("pkg").toString();
		b = (Button) findViewById(R.id.button1);
		t1 = (TextView) findViewById(R.id.textView1);
		registerForContextMenu(b);
		ptr = 0;
		// dbname="testcases1";
		/*
		 * MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1); Product
		 * product = dbHandler.findProduct(1); Cursor c=MyDBHandler.cursor;
		 * Product produ=new Product(); c.moveToFirst(); while(!c.isAfterLast())
		 * { String str = null;
		 * 
		 * produ.setID(Integer.parseInt(c.getString(0)));
		 * produ.setType(Integer.parseInt(c.getString(1)));
		 * produ.setValue1(Integer.parseInt(c.getString(2)));
		 * produ.setValue2(Integer.parseInt(c.getString(3)));
		 * if(produ.getType()==2) {
		 * str=(c.getPosition()+1)+" TOUCH: "+produ.getValue1
		 * ()+","+produ.getValue2()+"\n"; } else { if(produ.getType()==3) {
		 * str=(c.getPosition()+1)+" KEY: "+produ.getValue1()+"\n"; } else {
		 * str="N/A: "+produ.getValue1()+","+produ.getValue2()+"\n"; } }
		 * c.moveToNext(); t1.setText(t1.getText().toString()+str);
		 * //Toast.makeText(getBaseContext(), t1.getText().toString(),
		 * Toast.LENGTH_LONG).show(); }
		 */
		// registerForContextMenu(b);
		/*
		 * b.setOnClickListener(new OnClickListener(){
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Toast.makeText(getBaseContext(), "button clicked",
		 * Toast.LENGTH_SHORT).show(); }
		 * 
		 * });
		 */
	}

	public void test(View v) {
		Toast.makeText(getBaseContext(), count + "", Toast.LENGTH_LONG).show();

		String s = tester.database;

		String cases = t1.getText().toString();
		Intent i = new Intent(this, ghost.class);
		i.putExtra("cases", s);
		i.putExtra("pkg", apps.pkg);
		startService(i);
		// StartEventMonitor();
	}

	public void stopp(View v) {
		recordd = false;
		// MyDBHandler dbHandler = new MyDBHandler(testcase.this, null, null,
		// 1);
		Cursor cu = MyDBHandler.cursor;
		Toast.makeText(getBaseContext(), "Recording stopped " + cu.getCount(),
				Toast.LENGTH_LONG).show();

	}

	public void record(View v) {
		Intent i = new Intent(this, recorder.class);
		i.putExtra("pkg", pkg);
		startService(i);
		recordd = true;
	}

	/*
	 * FOR DATA CLEANING.... DONOT DISTURB THIS!!!
	 */
	public void refresh(View v) {
		/*
		 * for(int i=0;i<100;i++) {
		 * Toast.makeText(getBaseContext(),i+"lkdjaklfjkd;mfld,j"
		 * ,Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 */
		count = 0;
		MyDBHandler dbHandler = new MyDBHandler(testcase.this,
				CustomListViewAndroidExample.pkg, dbname, null, 1);
		Cursor cu = MyDBHandler.cursor;
		// MyDBHandler dbHandl = new MyDBHandler(testcase.this, "testcases",
		// null, 1);
		int count = 1;
		cu.moveToFirst();
		int type, value1, value2;
		type = Integer.parseInt(cu.getString(0));
		value1 = Integer.parseInt(cu.getString(1));
		value2 = Integer.parseInt(cu.getString(2));
		// Toast.makeText(getBaseContext()," count"+cu.getCount(),Toast.LENGTH_SHORT).show();
		// Toast.makeText(getBaseContext()," refresh started"+cu.getString(0),Toast.LENGTH_SHORT).show();

		// Toast.makeText(getBaseContext()," refresh started"+cu.getString(0),Toast.LENGTH_SHORT).show();
		cu.moveToNext();

		while (!cu.isAfterLast()) {
			Toast.makeText(
					getBaseContext(),
					Integer.parseInt(cu.getString(0)) + " "
							+ Integer.parseInt(cu.getString(1)) + " "
							+ Integer.parseInt(cu.getString(2)) + " "
							+ Integer.parseInt(cu.getString(3)),
					Toast.LENGTH_LONG).show();
			Toast.makeText(getBaseContext(),
					"OLD :" + type + " " + value1 + " " + value2,
					Toast.LENGTH_SHORT).show();
			if ((type == Integer.parseInt(cu.getString(1)))
					&& (value1 == Integer.parseInt(cu.getString(2)))
					&& (value2 == Integer.parseInt(cu.getString(3)))) {
				Toast.makeText(getBaseContext(), "repeat", Toast.LENGTH_LONG)
						.show();
			} else {
				type = Integer.parseInt(cu.getString(1));
				value1 = Integer.parseInt(cu.getString(2));
				value2 = Integer.parseInt(cu.getString(3));
				// Toast.makeText(getBaseContext(), "not repeat",
				// Toast.LENGTH_LONG).show();
				count++;

			}

			/*
			 * if(Integer.parseInt(cu.getString(0))==type) {
			 * 
			 * Toast.makeText(getBaseContext(),cu.getString(0)+" repeat",Toast.
			 * LENGTH_SHORT).show();
			 * 
			 * /* if(Integer.parseInt(cu.getString(1))==2) {
			 * if((Integer.parseInt(cu.getString(2))==value1) &&
			 * (Integer.parseInt(cu.getString(3))==value2)) { //
			 * cu.moveToNext();
			 * Toast.makeText(getBaseContext(),cu.getString(0)+" repeat"
			 * ,Toast.LENGTH_SHORT).show(); } else {
			 * type=Integer.parseInt(cu.getString(1));
			 * value1=Integer.parseInt(cu.getString(2));
			 * value2=Integer.parseInt(cu.getString(3)); count++; /* MyDBHandler
			 * dbHandle = new MyDBHandler(testcase.this, "testcaesss", null, 1);
			 * Product po=new Product(type,value1,value2);
			 * dbHandle.addProduct(po); Product poo=dbHandle.findProduct(1);
			 * Cursor cuu=MyDBHandler.cursor; Toast.makeText(getBaseContext(),
			 * "size is "+cuu.getCount(), Toast.LENGTH_LONG).show();
			 * 
			 * } } if(Integer.parseInt(cu.getString(1))==3) {
			 * if((Integer.parseInt(cu.getString(2))==value1) &&
			 * (Integer.parseInt(cu.getString(3))==value2)) { //
			 * cu.moveToNext();
			 * Toast.makeText(getBaseContext(),cu.getString(0)+" repeat"
			 * ,Toast.LENGTH_SHORT).show(); } else {
			 * type=Integer.parseInt(cu.getString(1));
			 * value1=Integer.parseInt(cu.getString(2));
			 * value2=Integer.parseInt(cu.getString(3)); count++; } } } else {
			 * type=Integer.parseInt(cu.getString(1));
			 * value1=Integer.parseInt(cu.getString(2));
			 * value2=Integer.parseInt(cu.getString(3)); count++;
			 * 
			 * }
			 */
			Product p = new Product();

			cu.moveToNext();
			// cu.moveToNext();
		}
		Toast.makeText(getBaseContext(), "toatal:" + count, Toast.LENGTH_LONG)
				.show();

		if (cu.moveToPrevious()) {
			Toast.makeText(getBaseContext(), "Last :" + cu.getString(0),
					Toast.LENGTH_LONG).show();
		} else
			Toast.makeText(getBaseContext(), "error", Toast.LENGTH_LONG).show();

	}

	public void StartEventMonitor() {
		// m_bMonitorOn = true;

		ni.intEnableDebug(1);
		ni.Init();
		int i, l;
		l = ni.m_Devs.size();
		for (i = 0; i < l; i++) {
			InputDevice k = ni.m_Devs.get(i);
			k.Open(true);
			if (k.getOpen()) {
				// Toast.makeText(getBaseContext(),
				// "Opened: "+k.getName()+" "+k.getId(),
				// Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(getBaseContext(), "closed" + k.getPath(),
						Toast.LENGTH_LONG).show();

			}
		}
		String sp2;
		t1.setText("");
		Thread b = new Thread(new Runnable() {
			public void run() {
				String line1 = "";
				while (true) {
					for (InputDevice idev : ni.m_Devs) {
						// Open more devices to see their messages
						if (idev.getOpen() && (0 == idev.getPollingEvent())) {

							final String line = idev.getSuccessfulPollingType()
									+ "," + idev.getSuccessfulPollingCode()
									+ "," + idev.getSuccessfulPollingValue();
							// line1=line;
							// Toast.makeText(getBaseContext(), line,
							// Toast.LENGTH_LONG).show();
							// Log.d("LT", "Event:"+line);
							int type;
							int flag = 0;
							MyDBHandler dbHandler = new MyDBHandler(
									testcase.this,
									CustomListViewAndroidExample.pkg, dbname,
									null, 1);

							String s = t1.getText().toString();
							// Toast.makeText(getBaseContext(), line,
							// Toast.LENGTH_SHORT).show();
							String v = "";
							long d = System.currentTimeMillis();
							if (line != st) {
								st = line;

								if (line != null) {
									if (line.contains("3,53,"))// ||line.contains("3,54,"))
									{
										flag = 1;
										v = line.substring(5, line.length());
										type = 2;

										Product product = new Product(type,
												Integer.parseInt(v), 0, d, -1);
										dbHandler.addProduct(product);

									}
									if (line.contains("3,54,")) {
										flag = 3;
										v = line.substring(5, line.length());
									}

									if (line.charAt(0) == '1') {
										flag = 4;
										type = 3;
										v = line.substring(2, line.length() - 2);

										Product product = new Product(type,
												Integer.parseInt(v), 0, d, -1);
										dbHandler.addProduct(product);

									}
								}
							}
							/*
							 * if(v!=""){ if(s!="") { if(flag==1) {
							 * t1.setText(s+"\n"+v); } else t1.setText(s+","+v);
							 * if(flag==4) t1.setText(s+"\n"+v); } else
							 * t1.setText(v); } }
							 */
							// update textview to show data
							// if (idev.getSuccessfulPollingValue() != 0)
							/*
							 * t1.post(new Runnable() { public void run() { //
							 * String st; int type; int flag=0; MyDBHandler
							 * dbHandler = new MyDBHandler(testcase.this, null,
							 * null, 1);
							 * 
							 * String s=t1.getText().toString(); //
							 * Toast.makeText(getBaseContext(), line,
							 * Toast.LENGTH_SHORT).show(); String v="";
							 * 
							 * if(line!=st) { st=line;
							 * 
							 * if(line!=null) {
							 * if(line.contains("3,53,"))//||line
							 * .contains("3,54,")) { flag=1; v=line.substring(5,
							 * line.length()); type=2;
							 * 
							 * Product product =new
							 * Product(type,Integer.parseInt(v),0);
							 * dbHandler.addProduct(product);
							 * 
							 * } if(line.contains("3,54,")) { flag=3;
							 * v=line.substring(5,line.length()); }
							 * 
							 * 
							 * if(line.charAt(0)=='1') { flag=4; type=3;
							 * v=line.substring(2,line.length()-2);
							 * 
							 * Product product =new
							 * Product(type,Integer.parseInt(v),0);
							 * dbHandler.addProduct(product);
							 * 
							 * } } if(v!=""){ if(s!="") { if(flag==1) {
							 * t1.setText(s+"\n"+v); } else t1.setText(s+","+v);
							 * if(flag==4) t1.setText(s+"\n"+v); } else
							 * t1.setText(v); } } } });
							 */
						}

					}
				}
			}
		});
		b.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Context Menu");
		menu.add(0, v.getId(), 0, "Key Event");
		menu.add(0, v.getId(), 0, "Touch Event");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Key Event") {
			function1("Key Event");
		} else if (item.getTitle() == "Touch Event") {
			function2("Touch Event");
		} else {
			return false;
		}
		return true;
	}

	public void newProduct(View v) {
		t1.setText("");
		MyDBHandler dbHandler = new MyDBHandler(this,
				CustomListViewAndroidExample.pkg, dbname, null, 1000);

		Product product = dbHandler.findProduct(1);
		Cursor c = MyDBHandler.cursor;
		c.moveToFirst();
		Toast.makeText(getBaseContext(), "" + c.getCount(), Toast.LENGTH_LONG)
				.show();

		// Toast.makeText(getBaseContext(), "size="+c.getCount(),
		// Toast.LENGTH_SHORT).show();
	}

	// FOR TOUCH EVENT
	private void function2(String s) {
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.prompts1, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);
		final EditText x_val = (EditText) promptsView
				.findViewById(R.id.editText10);
		final EditText y_val = (EditText) promptsView
				.findViewById(R.id.editText9);
		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						// edit text
						// Toast.makeText(getBaseContext(), "ok clicked",
						// Toast.LENGTH_LONG).show();

						MyDBHandler dbHandler = new MyDBHandler(testcase.this,
								CustomListViewAndroidExample.pkg, dbname, null,
								1);
						Toast.makeText(getBaseContext(), "Database created",
								Toast.LENGTH_LONG).show();
						// int
						// typee=Integer.parseInt(type.getText().toString());
						int value11 = Integer.parseInt(x_val.getText()
								.toString());
						int value22 = Integer.parseInt(y_val.getText()
								.toString());
						Product product = new Product(2, value11, value22,
								1000, -1);// typee,value11,value22);

						// Product product =new Product(1,3,3);
						dbHandler.addProduct(product);
						dbHandler.close();
						String x, y;
						x = x_val.getText().toString();
						y = y_val.getText().toString();
						if (t1.getText().toString().equals("empty")) {

							t1.setText("TOUCH: " + x_val.getText().toString()
									+ "," + y_val.getText().toString());

						} else {

							t1.setText(t1.getText().toString() + "TOUCH: "
									+ x_val.getText().toString() + ","
									+ y_val.getText().toString());
						}
						/*
						 * in[ptr]=new inputt(); //inputt i=new inputt();
						 * //i.type=2; in[ptr].type=2;
						 * 
						 * 
						 * // for touch events in[ptr].key1=x; in[ptr].key2=y;
						 * ptr++; // Toast.makeText(getBaseContext(),
						 * ""+x_val.getText
						 * ().toString()+"  "+y_val.getText().toString(),
						 * Toast.LENGTH_LONG).show();
						 */

					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}

	// FOR KEY EVENT
	private void function1(String s) {
		// TODO Auto-generated method stub
		Toast.makeText(getBaseContext(), s + " clicked", Toast.LENGTH_SHORT)
				.show();
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.prompts, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);
		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput);

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						// edit text
						// result.setText(userInput.getText());
						MyDBHandler dbHandler = new MyDBHandler(testcase.this,
								CustomListViewAndroidExample.pkg, dbname, null,
								1);
						Toast.makeText(getBaseContext(), "Database created",
								Toast.LENGTH_LONG).show();
						// int
						// typee=Integer.parseInt(type.getText().toString());
						int value11 = Integer.parseInt(userInput.getText()
								.toString());
						// int
						// value22=Integer.parseInt(value2.getText().toString());
						Product product = new Product(3, value11, 0, 1000, -1);// typee,value11,value22);

						// Product product =new Product(1,3,3);
						dbHandler.addProduct(product);
						dbHandler.close();
						if (t1.getText().toString().equals("empty")) {
							t1.setText("KEY: " + userInput.getText().toString());
						} else {
							t1.setText(t1.getText().toString() + "KEY: "
									+ userInput.getText().toString());
						}
						/*
						 * in[ptr]=new inputt(); in[ptr].type=3; //for keypad
						 * events in[ptr].key1=userInput.getText().toString();
						 * in[ptr].key2="-1"; ptr++;
						 */
						// Toast.makeText(getBaseContext(),
						// ""+userInput.getText().toString(),
						// Toast.LENGTH_LONG).show();
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}
}