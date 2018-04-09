package com.example.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class router extends Activity {

	public static int count;
	EditText ti;
	String pkg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.router);
		pkg = getIntent().getCharSequenceExtra("pkg").toString();
		TextView t = (TextView) findViewById(R.id.appname);
		t.setText(CustomListViewAndroidExample.pkg);
		ti = (EditText) findViewById(R.id.counter);
	}

	public void test(View v) {
		count = Integer.parseInt(ti.getText().toString());
		if (pkg != null) {
			Intent i = new Intent(this, tester.class);
			i.putExtra("pkg", pkg);
			startActivity(i);
		} else
			Toast.makeText(getBaseContext(), "Package empty!",
					Toast.LENGTH_LONG).show();

	}

	public void record(View v) {
		if (pkg != null) {
			Intent j = new Intent(this, RecordChoices.class);
			j.putExtra("pkg", pkg);
			startActivity(j);
		}
	}
}
