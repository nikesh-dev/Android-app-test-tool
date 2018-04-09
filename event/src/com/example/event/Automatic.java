package com.example.event;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.event.Nativenput.InputDevice;

public class Automatic extends Activity {
	// Date d;
	String pkg;
	public static String filename;
	Nativenput ni = new Nativenput();
	Intent i, j;
	// public static String dbname=null;
	public static Boolean recordd;
	public EditText e1;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.automatic);
		e1 = (EditText) findViewById(R.id.autoname);
		pkg = "package";

		// Toast.makeText(getBaseContext(),System.currentTimeMillis()+" ",Toast.LENGTH_LONG).show();
	}

	public void startrecording(View v) {
		try {

			filename = e1.getText().toString();
			recordd = true;
			i = new Intent(this, recorder.class);
			i.putExtra("pkg", CustomListViewAndroidExample.pkg);
			// Toast.makeText(getBaseContext(),
			// "service started",Toast.LENGTH_LONG).show();
			startService(i);

		} catch (Exception e) {
			Toast.makeText(getBaseContext(), "Enter Filename!",
					Toast.LENGTH_LONG).show();
		}
	}

	public void stoprecording(View v) {
		recordd = false;
		stopService(i);
		// MyDBHandler dbHandler = new MyDBHandler(Automatic.this, filename,
		// null, 1);
		// Cursor cu=MyDBHandler.cursor;
		// Toast.makeText(getBaseContext(), "Recording stopped " +cu.getCount(),
		// Toast.LENGTH_LONG).show();

	}

	public void starttesting(View v) {
		try {
			filename = e1.getText().toString();
			Toast.makeText(getBaseContext(), "not yet added implemented!!",
					Toast.LENGTH_LONG).show();
			// String cases=t1.getText().toString();
			j = new Intent(this, ghost.class);
			j.putExtra("cases", filename);
			j.putExtra("pkg", CustomListViewAndroidExample.pkg);
			startService(j);
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), "Enter Filename!",
					Toast.LENGTH_LONG).show();
		}
	}

	public void checkstatus(View v) {
		try {
			filename = e1.getText().toString();
			MyDBHandler dbHandler = new MyDBHandler(this,
					CustomListViewAndroidExample.pkg, filename, null, 1);
			Product product = dbHandler.findProduct(1);
			Cursor c = MyDBHandler.cursor;
			c.moveToFirst();
			Toast.makeText(getBaseContext(), "" + c.getCount(),
					Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), "Enter Filename!",
					Toast.LENGTH_LONG).show();
		}

	}

	public void cleandata(View v) {
		try {
			// stopService(j);
			filename = e1.getText().toString();
			MyDBHandler dbHandler = new MyDBHandler(Automatic.this,
					CustomListViewAndroidExample.pkg, filename, null, 1);
			Product poo = dbHandler.findProduct(1);
			Cursor cu = MyDBHandler.cursor;
			// MyDBHandler dbHandl = new MyDBHandler(testcase.this, "testcases",
			// null, 1);
			int count = 1;
			cu.moveToFirst();
			int type, value1, value2;// ,time;
			type = Integer.parseInt(cu.getString(0));
			value1 = Integer.parseInt(cu.getString(1));
			value2 = Integer.parseInt(cu.getString(2));
			// time=Integer.parseInt(cu.getString(4));
			// Toast.makeText(getBaseContext()," count"+cu.getCount(),Toast.LENGTH_SHORT).show();
			// Toast.makeText(getBaseContext()," refresh started"+cu.getString(0),Toast.LENGTH_SHORT).show();

			// Toast.makeText(getBaseContext()," refresh started"+cu.getString(0),Toast.LENGTH_SHORT).show();
			cu.moveToNext();

			while (!cu.isAfterLast()) {

				Toast.makeText(
						getBaseContext(),/*
										 * Integer.parseInt(cu.getString(0))+" "+
										 * Integer.parseInt(cu.getString(1))+" "
										 */
						+Integer.parseInt(cu.getString(2)) + " "
								+ Integer.parseInt(cu.getString(3)) + " "
								+ cu.getString(4), Toast.LENGTH_LONG).show();
				// Toast.makeText(getBaseContext(),"OLD :"+type+" "+value1+" "+value2,Toast.LENGTH_SHORT).show();

				/*
				 * if((type==Integer.parseInt(cu.getString(1)))&&(value1==Integer
				 * .parseInt(cu.getString(2)))&&(value2==Integer.parseInt(cu.
				 * getString(3)))) { Toast.makeText(getBaseContext(),
				 * "repeat"+Integer.parseInt(cu.getString(0))+" deleted",
				 * Toast.LENGTH_SHORT).show();
				 * dbHandler.deleteProduct(Integer.parseInt(cu.getString(0))); }
				 * else { type=Integer.parseInt(cu.getString(1));
				 * value1=Integer.parseInt(cu.getString(2));
				 * value2=Integer.parseInt(cu.getString(3));
				 * //Toast.makeText(getBaseContext(), "not repeat",
				 * Toast.LENGTH_LONG).show(); count++;
				 * 
				 * }
				 */

				/*
				 * if(Integer.parseInt(cu.getString(0))==type) {
				 * 
				 * Toast.makeText(getBaseContext(),cu.getString(0)+" repeat",Toast
				 * .LENGTH_SHORT).show();
				 * 
				 * /* if(Integer.parseInt(cu.getString(1))==2) {
				 * if((Integer.parseInt(cu.getString(2))==value1) &&
				 * (Integer.parseInt(cu.getString(3))==value2)) { //
				 * cu.moveToNext();
				 * Toast.makeText(getBaseContext(),cu.getString(
				 * 0)+" repeat",Toast.LENGTH_SHORT).show(); } else {
				 * type=Integer.parseInt(cu.getString(1));
				 * value1=Integer.parseInt(cu.getString(2));
				 * value2=Integer.parseInt(cu.getString(3)); count++; /*
				 * MyDBHandler dbHandle = new MyDBHandler(testcase.this,
				 * "testcaesss", null, 1); Product po=new
				 * Product(type,value1,value2); dbHandle.addProduct(po); Product
				 * poo=dbHandle.findProduct(1); Cursor cuu=MyDBHandler.cursor;
				 * Toast.makeText(getBaseContext(), "size is "+cuu.getCount(),
				 * Toast.LENGTH_LONG).show();
				 * 
				 * } } if(Integer.parseInt(cu.getString(1))==3) {
				 * if((Integer.parseInt(cu.getString(2))==value1) &&
				 * (Integer.parseInt(cu.getString(3))==value2)) { //
				 * cu.moveToNext();
				 * Toast.makeText(getBaseContext(),cu.getString(
				 * 0)+" repeat",Toast.LENGTH_SHORT).show(); } else {
				 * type=Integer.parseInt(cu.getString(1));
				 * value1=Integer.parseInt(cu.getString(2));
				 * value2=Integer.parseInt(cu.getString(3)); count++; } } } else
				 * { type=Integer.parseInt(cu.getString(1));
				 * value1=Integer.parseInt(cu.getString(2));
				 * value2=Integer.parseInt(cu.getString(3)); count++;
				 * 
				 * }
				 */
				// Product p=new Product();

				cu.moveToNext();
				// cu.moveToNext();
			}
			Toast.makeText(getBaseContext(), "toatal:" + count,
					Toast.LENGTH_LONG).show();

			/*
			 * if(cu.moveToPrevious()) { Toast.makeText(getBaseContext(),
			 * "Last :"+cu.getString(0), Toast.LENGTH_LONG).show(); } else
			 * Toast.makeText(getBaseContext(), "error",
			 * Toast.LENGTH_LONG).show();
			 */

		} catch (Exception e) {
			Toast.makeText(getBaseContext(),
					"Enter Filename!" + e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}
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
		// t1.setText("");
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
									Automatic.this,
									CustomListViewAndroidExample.pkg, filename,
									null, 1);

							// String s=t1.getText().toString();
							// Toast.makeText(getBaseContext(), line,
							// Toast.LENGTH_SHORT).show();
							String v = "";

							long time = System.currentTimeMillis();
							if (line != null) {
								if (line.contains("3,53,"))// ||line.contains("3,54,"))
								{
									String nu = dbHandler.COLUMN_ID;
									Log.v("last id", nu);
									flag = 1;
									v = line.substring(5, line.length());
									type = 2;
									Log.v("previous:", "" + v);
									Product product = new Product(type,
											Integer.parseInt(v), 0, time, -1);
									dbHandler.addProduct(product);

								}
								if (line.contains("3,54,")) {
									flag = 3;
									v = line.substring(5, line.length());
									Product q = dbHandler.findProduct(1);
									Cursor s = MyDBHandler.cursor;
									s.moveToLast();
									s.moveToPrevious();
									int id = s.getInt(2);
									Log.v("prev1:", id + "");
									s.moveToLast();
									id = s.getInt(2);
									Log.v("prev2:", id + "");
									int ty = s.getInt(1);
									int z = s.getInt(2);
									long tim = s.getInt(4);

									dbHandler.updatee(id, ty, z,
											Integer.parseInt(v), tim, -1);
									Log.v("nikes", s.getInt(3) + "");
								}

								if (line.charAt(0) == '1') {
									flag = 4;
									type = 3;
									v = line.substring(2, line.length() - 2);

									Product product = new Product(type,
											Integer.parseInt(v), 0, time, -1);
									dbHandler.addProduct(product);

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

}
