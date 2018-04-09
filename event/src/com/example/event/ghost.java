package com.example.event;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.event.Nativenput.InputDevice;

public class ghost extends Service {

	Nativenput ni = new Nativenput();
	String[] cases;
	String o;
	int i;
	Intent j = null;
	Cursor c;
	Message msg;
	int databases;

	// private PullScreenAsyncTask Puller;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent in, int flags, int startid) {
		i = router.count;
		// Toast.makeText(getBaseContext(),"service started",
		// Toast.LENGTH_LONG).show();
		o = (String) in.getCharSequenceExtra("pkg");
		j = new Intent(this, logg.class);

		startService(j);
		cases = (String[]) in.getStringArrayExtra("cases");
		databases = cases.length;
		/*
		 * Temporarily closed
		 * 
		 * //Intent collector=new Intent(this,logg.class);
		 * //startService(collector);
		 */
		Intent intent = getPackageManager().getLaunchIntentForPackage(o);
		if (intent != null) {
			/* we found the activity now start the activity */
			Toast.makeText(getBaseContext(), "App launched", Toast.LENGTH_LONG)
					.show();
			for (int i = 0; i < databases; i++) {
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				startInjectingEvents(cases[i]);
			}
			// Puller = new PullScreenAsyncTask();
			// Puller.execute((Void[])null);

		}

		else
			Toast.makeText(getBaseContext(), "empty activity!",
					Toast.LENGTH_LONG).show();
		// stopService(collector);
		return START_STICKY;
	}

	/*
	 * 
	 * 
	 * **** for RUNNING SHELL COMMANDS ******
	 */
	private void runSuShellCommand(String cmd) {
		Runtime runtime = Runtime.getRuntime();
		Process proc = null;
		OutputStreamWriter osw = null;
		StringBuilder sbstdOut = new StringBuilder();
		StringBuilder sbstdErr = new StringBuilder();

		try { // Run Script
			proc = runtime.exec("su");
			Log.v("exec", "running command");
			osw = new OutputStreamWriter(proc.getOutputStream());
			/*
			 * 
			 * Command = "/dev/graphics/fb0 > SDCARD/SSS.RAW
			 */
			osw.write(cmd);
			osw.flush();
			osw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			if (proc != null)
				proc.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		sbstdOut.append(readBufferedReader(new InputStreamReader(proc
				.getInputStream())));
		sbstdErr.append(readBufferedReader(new InputStreamReader(proc
				.getErrorStream())));
		Log.v("appending", "over");
	}

	/*
	 * 
	 * read buffer data from fb0 into sss.raw file
	 */
	private String readBufferedReader(InputStreamReader input) {

		BufferedReader reader = new BufferedReader(input);
		StringBuilder found = new StringBuilder();
		String currLine = null;
		String sep = System.getProperty("line.separator");
		try {
			// Read it all in, line by line.
			while ((currLine = reader.readLine()) != null) {
				found.append(currLine);
				found.append(sep);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/*
	 * 
	 * 
	 * ************* FOR INJECTING EVENTS********************
	 */
	public void startInjectingEvents(String databasename) {
		// Toast.makeText(getBaseContext(),"injecting",Toast.LENGTH_LONG).show();
		// int ok=1;
		// long c1 = 0;
		ni.intEnableDebug(1);
		ni.Init();
		// ni.mess();
		// Toast.makeText(getBaseContext(), "next ", Toast.LENGTH_LONG).show();
		// Toast.makeText(getBaseContext(),
		// ni.nikes()+" hjvklf",Toast.LENGTH_LONG).show();
		MyDBHandler dbHandle = new MyDBHandler(this, o, databasename, null, 1);
		Product produc = dbHandle.findProduct(1);
		c = MyDBHandler.cursor;
		// Toast.makeText(getBaseContext(),
		// c.getCount(),Toast.LENGTH_LONG).show();

		if (c.moveToFirst()) {
			c.moveToFirst();
			// Toast.makeText(getBaseContext(),""+
			// c.getCount(),Toast.LENGTH_LONG).show();
			Product product = new Product();
			while (!c.isAfterLast()) {
				Log.v("test", "check");
				Log.v("nikesh", ni.nikes());
				product.setID(Integer.parseInt(c.getString(0)));
				product.setType(Integer.parseInt(c.getString(1)));
				product.setValue1(Integer.parseInt(c.getString(2)));
				product.setValue2(Integer.parseInt(c.getString(3)));
				// Toast.makeText(getBaseContext(),
				// product.getType()+" "+product.getValue1()+" "+product.getValue2(),Toast.LENGTH_LONG).show();
				InputDevice k = ni.m_Devs.get(product.getType());
				int s = product.getType();
				k.Open(true);
				// Toast.makeText(getBaseContext(),
				// "opened="+product.getType()+" "+product.getValue1()+" "+product.getValue2(),
				// Toast.LENGTH_LONG).show();

				if (Long.parseLong(c.getString(4)) > 500) {

					// ******wait for "Long.parseLong(c.getString(4))" amount of
					// time ***\
					long curr = System.currentTimeMillis()
							+ Long.parseLong(c.getString(4));
					while (System.currentTimeMillis() < curr) {

					}
					/*
					 * new CountDownTimer(Long.parseLong(c.getString(4)), 1000)
					 * {
					 * 
					 * public void onTick(long millisUntilFinished) { //
					 * mTextField.setText("seconds remaining: " +
					 * millisUntilFinished / 1000); }
					 * 
					 * public void onFinish() { //
					 * Toast.makeText(getBaseContext(), "time out!!",
					 * Toast.LENGTH_LONG).show(); // startInjectingEvents();
					 * //StartEventMonitor(); } }.start();
					 */

					if (s == 2) // touch evnets
					{
						// k.SendTouchDownAbs(100,80);
						// Toast.makeText(getBaseContext(),k.getName()+"open "+k.getOpen(),Toast.LENGTH_LONG).show();
						// Toast.makeText(getBaseContext(),"touch started "+c.getString(2)+" "+(Integer.parseInt(c.getString(3))),Toast.LENGTH_LONG).show();
						// k.SendTouchDownAbs(100,80);
						k.SendTouchDownAbs(Integer.parseInt(c.getString(2)),
								Integer.parseInt(c.getString(3)));

						ni.mess("sdcard/NikRoid/" + o + "/" + "/image-" + i
								+ ".bmp");
						dbHandle.updatee(Integer.parseInt(c.getString(0)),
								Integer.parseInt(c.getString(1)),
								Integer.parseInt(c.getString(2)),
								Integer.parseInt(c.getString(3)),
								Integer.parseInt(c.getString(4)), i);
						/* Integer.parseInt(c.getString(3))); */
						i++;

						// testcase.in[i].key1),Integer.parseInt(testcase.in[i].key2));
						// k.SendTouchDownAbs(Integer.parseInt(testcase.in[i].key1),Integer.parseInt(testcase.in[i].key2));
					} else {
						if (s == 3) // for key events
						{
							// Toast.makeText(getBaseContext(),"key events sent"+k.getName(),Toast.LENGTH_LONG).show();

							k.SendKey(Integer.parseInt(c.getString(2)), true);
							k.SendKey(Integer.parseInt(c.getString(2)), false);
							ni.mess("sdcard/NikRoid/" + o + "/" + databasename
									+ "/image-" + i + ".bmp");
							dbHandle.updatee(Integer.parseInt(c.getString(0)),
									Integer.parseInt(c.getString(1)),
									Integer.parseInt(c.getString(2)),
									Integer.parseInt(c.getString(3)),
									Integer.parseInt(c.getString(4)), i);
							i++;

						} else
							Toast.makeText(getBaseContext(), "WRONG TYPE",
									Toast.LENGTH_LONG).show();
					}
				}
				// dbHandle.updatee(Integer.parseInt(c.getString(0)),
				// Integer.parseInt(c.getString(1)),
				// Integer.parseInt(c.getString(2)),
				// Integer.parseInt(c.getString(3)),
				// Integer.parseInt(c.getString(4)), i);
				// final MyDBHandler handy=new
				// MyDBHandler(this,CustomListViewAndroidExample.pkg,cases,null,1);

				// else
				// Toast.makeText(getBaseContext(),
				// c.getString(2)+" "+c.getString(3), Toast.LENGTH_LONG).show();
				c.moveToNext();
			}
			// Toast.makeText(getBaseContext(), ""+c.getCount()
			// ,Toast.LENGTH_LONG).show();

			// s=testcase.in[i].type;

			// soast.makeText(getBaseContext(),"rooted="+Shell.isSuAvailable(),Toast.LENGTH_LONG).show();
			/*
			 * if(s==2) //touch evnets {
			 * //Toast.makeText(getBaseContext(),k.getName(),
			 * Toast.LENGTH_LONG).show(); // k.SendTouchDownAbs(100,80); //
			 * Toast
			 * .makeText(getBaseContext(),"touch started",Toast.LENGTH_LONG)
			 * .show();
			 * 
			 * k.SendTouchDownAbs(Integer.parseInt(testcase.in[i].key1),Integer.
			 * parseInt(testcase.in[i].key2)); //
			 * k.SendTouchDownAbs(Integer.parseInt
			 * (testcase.in[i].key1),Integer.parseInt(testcase.in[i].key2)); }
			 * if(s==3) {
			 * Toast.makeText(getBaseContext(),"key events sent"+k.getName
			 * (),Toast.LENGTH_LONG).show();
			 * 
			 * k.SendKey(115, true); k.SendKey(115, false);
			 * //k.SendKey(Integer.parseInt(testcase.in[i].key1), true);
			 * //k.SendKey(Integer.parseInt(testcase.in[i].key1), false); } } }
			 */
			dbHandle.close();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// stopService(j);
	}

}
