package com.example.event;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.event.Nativenput.InputDevice;

public class recorder extends Service {

	Nativenput ni = new Nativenput();
	String st;

	// Boolean recordd;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent in, int flags, int startid) {
		// Toast.makeText(getBaseContext(),"service started",
		// Toast.LENGTH_LONG).show();
		String o = CustomListViewAndroidExample.pkg;// (String)
													// in.getCharSequenceExtra("pkg");
		Intent intent = getPackageManager().getLaunchIntentForPackage(o);
		if (intent != null) {
			/* we found the activity now start the activity */
			// Toast.makeText(getBaseContext(), "App launched",
			// Toast.LENGTH_LONG).show();
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// startActivity(intent);
			startActivity(intent);

			StartEventMonitor();
		}

		else
			Toast.makeText(getBaseContext(), "empty activity!",
					Toast.LENGTH_LONG).show();
		return START_STICKY;
	}

	public void StartEventMonitor() {
		// m_bMonitorOn = true;
		try {
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
					Toast.makeText(getBaseContext(),
							"closed" + k.getPath() + "", Toast.LENGTH_LONG)
							.show();
					Toast.makeText(getBaseContext(), "Root your device!!!",
							Toast.LENGTH_LONG).show();
				}
			}
			// String sp2;
			// t1.setText("");

			Thread b = new Thread(new Runnable() {
				public void run() {
					// String line1="";
					Long d, j;
					MyDBHandler dbHandler = new MyDBHandler(recorder.this,
							CustomListViewAndroidExample.pkg,
							Automatic.filename, null, 1);
					j = System.currentTimeMillis();
					while (Automatic.recordd) {

						for (InputDevice idev : ni.m_Devs) {
							// Open more devices to see their messages
							d = null;
							if (idev.getOpen() && (0 == idev.getPollingEvent())) {

								final String line = idev
										.getSuccessfulPollingType()
										+ ","
										+ idev.getSuccessfulPollingCode()
										+ ","
										+ idev.getSuccessfulPollingValue();
								// line1=line;
								int type;
								// Toast.makeText(getBaseContext(), line,
								// Toast.LENGTH_LONG).show();
								Log.d("LT", "Event:" + line);
								String v = null;
								d = System.currentTimeMillis();
								if (line != st) {
									st = line;
									if (line != null) {
										if (line.contains("3,53,"))// ||line.contains("3,54,"))
										{
											String nu = dbHandler.COLUMN_ID;
											Log.v("last id", nu);
											v = line.substring(5, line.length());
											type = 2;
											// d=(long)2;
											Product product = new Product(type,
													Integer.parseInt(v), 0,
													(d - j), -1);

											dbHandler.addProduct(product);

										}
										if (line.contains("3,54,")) {

											/*
											 * Product
											 * p=dbHandler.findProduct(1);
											 * Cursor h=MyDBHandler.cursor;
											 * h.moveToLast();
											 */
											v = line.substring(5, line.length());
											// Log.v("nikes",v)
											Product q = dbHandler
													.findProduct(1);
											Cursor s = MyDBHandler.cursor;
											s.moveToLast();
											// s.moveToPrevious();
											// int idd=s.getInt(0);
											// Log.v("id",idd+"");
											int id = s.getInt(0);
											int ty = s.getInt(1);
											int z = s.getInt(2);
											long tim = d - j;
											j = d;

											dbHandler.updatee(id, ty, z,
													Integer.parseInt(v), tim,
													-1);
											Log.v("nikeshh", s.getInt(3) + "");
											;
										}

										if (line.charAt(0) == '1') {
											type = 3;
											v = line.substring(2,
													line.length() - 2);

											Product product = new Product(type,
													Integer.parseInt(v), 0, d
															- j, -1);
											j = d;
											dbHandler.addProduct(product);

										}
									}
								}

								// update textview to show data
								// if (idev.getSuccessfulPollingValue() != 0)
								/*
								 * t1.post(new Runnable() { public void run() {
								 * // String st; int type; int flag=0;
								 * MyDBHandler dbHandler = new
								 * MyDBHandler(recorder.this, null, null, 1);
								 * 
								 * //String s=t1.getText().toString(); //
								 * Toast.makeText(getBaseContext(), line,
								 * Toast.LENGTH_SHORT).show(); String v="";
								 * 
								 * if(line!=st) { st=line;
								 * 
								 * if(line!=null) {
								 * if(line.contains("3,53,"))//||
								 * line.contains("3,54,")) { flag=1;
								 * v=line.substring(5, line.length()); type=2;
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
								 * t1.setText(s+"\n"+v); } else
								 * t1.setText(s+","+v); if(flag==4)
								 * t1.setText(s+"\n"+v); } else t1.setText(v); }
								 * } } });
								 */
								// end past
							}

						}
					}
				}
			});
			b.start();
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), "Enter Filename!",
					Toast.LENGTH_LONG).show();
		}
	}

}
