package com.example.event;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static List<PackageInfo> apps, apps1;
	// Nativenput ni = new Nativenput();
	ProgressDialog p;
	public static ArrayList<listItem> CustomListViewValuesArr;
	Intent i;

	public static Bitmap drawableToBitmap(Drawable drawable) {
		if (drawable instanceof BitmapDrawable) {
			return ((BitmapDrawable) drawable).getBitmap();
		}

		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);

		return bitmap;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// Toast.makeText(getBaseContext(), Nativenput.nike()+"",
		// Toast.LENGTH_LONG).show();

		File f = new File(Environment.getExternalStorageDirectory()
				+ "/NikRoid");
		if (!f.exists()) {
			f.mkdir();
		}
		File g = new File(Environment.getExternalStorageDirectory()
				+ "/NikRoid/apps");
		if (!g.exists()) {
			g.mkdir();
		} else {
			g.delete();
			g.mkdir();

		}

		apps1 = getPackageManager().getInstalledPackages(0);
		int k = apps1.size();
		// CustomListViewValuesArr = new ArrayList<listItem>();
		for (int i = 0; i < k; i++) {

			if (getPackageManager().getLaunchIntentForPackage(
					MainActivity.apps1.get(i).packageName) != null) {
				try {
					// get icon from package
					String pkg = MainActivity.apps1.get(i).packageName; // your
																		// package
																		// name
					Drawable icon = getBaseContext().getPackageManager()
							.getApplicationIcon(pkg);
					// ImageView imageView;// = new ImageView();
					// imageView.setImageDrawable(icon);
					Bitmap bitmap = drawableToBitmap(icon);

					// save bitmap to sdcard
					FileOutputStream out = null;
					try {
						out = new FileOutputStream(
								Environment.getExternalStorageDirectory()
										+ File.separator + "NikRoid"
										+ File.separator + "apps"
										+ File.separator
										+ MainActivity.apps1.get(i).packageName
										+ ".png");
						bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						// close output stream (important!)
						try {
							out.close();
						} catch (Throwable ignore) {
						}
					}
				} catch (PackageManager.NameNotFoundException ne) {
				}

				// convert drawable to a bitmap

			}

		}

		// collect app names with images

		i = new Intent(this, CustomListViewAndroidExample.class);
		setContentView(R.layout.home);
		// p=(ProgressDialog)findViewById(R.id.progressBa);
		// p.setVisibility(View.VISIBLE);
		p = new ProgressDialog(this);
		p.setMessage("Please wait!!!");
		p.show();
		// start
		final Thread t = new Thread() {
			public void run() {
				apps = getPackageManager().getInstalledPackages(0);
				setListData();
				// p.setVisibility(View.INVISIBLE);
				p.dismiss();
			}

			/****** Function to set data in ArrayList *************/
			public void setListData() {
				// List<PackageInfo> apps =
				// getPackageManager().getInstalledPackages(0);
				int k = apps.size();
				CustomListViewValuesArr = new ArrayList<listItem>();
				for (int i = 0; i < k; i++) {

					if (getPackageManager().getLaunchIntentForPackage(
							MainActivity.apps.get(i).packageName) != null) {
						final listItem sched = new listItem();

						/******* Firstly take data in model object ******/

						try {
							sched.setimage(getPackageManager()
									.getApplicationIcon(
											MainActivity.apps.get(i).packageName));
						} catch (NameNotFoundException e) {
							// TODO Auto-generated catch block
							// Toast.makeText(CustomListView,"Error!",Toast.LENGTH_LONG).show();
							e.printStackTrace();
						}

						sched.setAppname(MainActivity.apps.get(i).packageName);
						/******** Take Model Object in ArrayList **********/
						CustomListViewValuesArr.add(sched);
					}
				}

			}

		};
		t.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

	}

	public void Click(View v) {

		// Intent loggerr=new Intent(this,logg.class);
		// Toast.makeText(getBaseContext(), "log service started "+ni.nikes(),
		// Toast.LENGTH_LONG).show();
		// startService(loggerr);

		startActivity(i);

	}

}
