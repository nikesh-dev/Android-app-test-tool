package com.example.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RecordChoices extends Activity {
	TextView t;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.recordchoices);
		t = (TextView) findViewById(R.id.appname);
		t.setText(CustomListViewAndroidExample.pkg);
	}

	public void manual(View v) {
		Intent i = new Intent(this, manual.class);
		startActivity(i);
	}

	public void automatic(View v) {
		Intent i = new Intent(this, Automatic.class);
		startActivity(i);
	}
}
