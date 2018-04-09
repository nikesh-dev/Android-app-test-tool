package com.example.event;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

public class logg extends Service {

	int flag;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent i, int flags, int startid) {
		Toast.makeText(getBaseContext(), "Log collecting service started",
				Toast.LENGTH_LONG).show();
		File f = extractLogToFileAndWeb();
		// Toast.makeText(getBaseContext(), "finished",
		// Toast.LENGTH_LONG).show();
		return START_STICKY;
	}

	private File extractLogToFileAndWeb() {
		// TODO Auto-generated method stub
		// set a file
		Date datum = new Date();
		final String processId = Integer.toString(android.os.Process.myPid());

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALY);
		String fullName = df.format(datum) + "appLog.log";
		File file = new File(Environment.getExternalStorageDirectory(),
				"log_deve.txt");
		Toast.makeText(getApplicationContext(), "executed", Toast.LENGTH_LONG)
				.show();
		// clears a file
		if (file.exists()) {
			file.delete();
		}

		// write log to file
		int pid = android.os.Process.myPid();
		try {
			String command = String.format("logcat -d -v threadtime *:*");
			Process process = Runtime.getRuntime().exec(command);

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			StringBuilder result = new StringBuilder();
			String currentLine = null;

			flag = 0;
			while ((currentLine = reader.readLine()) != null) {

				if (currentLine.contains(processId)) {
					result.append(currentLine);
					result.append("\n");
					// v.setText(result);
					// Toast.makeText(getBaseContext(), "text"+result,
					// Toast.LENGTH_LONG).show();

				} else {
					result.append(currentLine);

					result.append("\n");
					if (currentLine.contains("fatal")
							|| currentLine.contains("Exception")
							|| currentLine.contains("error")
							|| currentLine.contains("Fatal Exception")) {
						Toast.makeText(getBaseContext(), "encountered error!",
								Toast.LENGTH_LONG).show();

						/*
						 * 
						 * The following code is for HIGHLIGHTING with '*' and
						 * '-' which makes tester to easily find where ERROR
						 * occured in the LOG FILE.
						 */

						String temp = "\n* ";
						for (int i = 1; i < 25; i++) {
							temp = temp + " *";
						}
						temp += "\n";
						for (int i = 1; i < 25; i++) {
							temp = temp + "- ";

						}
						temp += "\n";
						for (int i = 1; i < 25; i++) {
							temp = temp + " *";
						}
						temp += "\n";
						result.append(temp);
						/*
						 * FileWriter out = new FileWriter(file); //
						 * v.setText(result.toString()); String extStorageState
						 * = Environment.getExternalStorageState(); if
						 * (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
						 * // Toast.makeText(getBaseContext(), "SDCARD exist",
						 * Toast.LENGTH_LONG).show(); } else
						 * Toast.makeText(getBaseContext(), "empty",
						 * Toast.LENGTH_LONG).show(); //
						 * Toast.makeText(getBaseContext(), result+"log",
						 * Toast.LENGTH_LONG).show();
						 * out.write(result.toString()); //
						 * Toast.makeText(getBaseContext(),"saved",
						 * Toast.LENGTH_LONG).show(); out.close(); try {
						 * Runtime.getRuntime().exec("logcat -c"); } catch
						 * (IOException e) {
						 * Toast.makeText(getApplicationContext(), e.toString(),
						 * Toast.LENGTH_SHORT).show(); }
						 */
						/*
						 * 
						 * This invokes maill activity to send mail with
						 * LOG_deve.TXT file attached.
						 */
						flag = 1;
						break;

					}
				}
			}
			for (int i = 1; i <= 10; i++) {
				if (currentLine.contains(processId)) {
					result.append(currentLine);
					result.append("\n");
					// v.setText(result);
					// Toast.makeText(getBaseContext(), "text"+result,
					// Toast.LENGTH_LONG).show();

				} else {
					result.append(currentLine);

					result.append("\n");
				}
			}
			// Toast.makeText(getBaseContext(),"while end",Toast.LENGTH_LONG).show();
			FileWriter out = new FileWriter(file);
			// v.setText(result.toString());
			String extStorageState = Environment.getExternalStorageState();
			if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
				// Toast.makeText(getBaseContext(), "SDCARD exist",
				// Toast.LENGTH_LONG).show();
			} else
				Toast.makeText(getBaseContext(), "empty", Toast.LENGTH_LONG)
						.show();
			// Toast.makeText(getBaseContext(), result+"log",
			// Toast.LENGTH_LONG).show();
			out.write(result.toString());
			// Toast.makeText(getBaseContext(),"saved",
			// Toast.LENGTH_LONG).show();
			out.close();

			Runtime.getRuntime().exec(
					"logcat -d -v time -f " + file.getAbsolutePath());
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(), e.toString(),
					Toast.LENGTH_SHORT).show();
		}

		// clear the log
		try {
			Runtime.getRuntime().exec("logcat -c");
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(), e.toString(),
					Toast.LENGTH_SHORT).show();
		}
		if (flag == 1) {
			Intent exceptio = new Intent(this, maill.class);
			// Toast.makeText(getBaseContext(), currentLine,
			// Toast.LENGTH_LONG).show();
			exceptio.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(exceptio);
		}
		return file;
	}

}
