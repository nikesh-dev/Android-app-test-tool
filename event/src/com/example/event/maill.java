package com.example.event;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class maill extends Activity {

	EditText ed1, ed2, ed3;
	String s1, s2, s3;
	Uri uri;

	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.maill);
		ed1 = (EditText) findViewById(R.id.editText1);
		ed2 = (EditText) findViewById(R.id.editText2);
		ed3 = (EditText) findViewById(R.id.editText3);
		ed1.setText("devaki.nikesh@gmail.com");
		ed2.setText("Log file attachement");
		ed3.setText("This mail is auto generated from log reader app. Please find attchements below");
	}

	public void send(View v) {
		s1 = ed1.getText().toString();
		s2 = ed2.getText().toString();
		s3 = ed3.getText().toString();
		File file = new File(Environment.getExternalStorageDirectory()
				+ "/log_dev1.txt");
		uri = Uri.fromFile(file);
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setData(Uri.parse("mailto:"));
		i.setType("text/plain");
		Toast.makeText(this, "uri parse for mailto is " + Uri.parse("mailto:"),
				Toast.LENGTH_LONG).show();
		i.putExtra(Intent.EXTRA_EMAIL, s1);
		// i.putExtra(Intent.EXTRA_CC,"www.w3schools@gmail.net");
		i.putExtra(Intent.EXTRA_SUBJECT, s2);
		i.putExtra(Intent.EXTRA_TEXT, s3);
		i.putExtra(Intent.EXTRA_STREAM, uri);
		// i.setType("message/rfc822");
		startActivity(Intent.createChooser(i, "Email"));
		Toast.makeText(getBaseContext(), "mail sent!", Toast.LENGTH_LONG)
				.show();

	}
}
