package com.example.event;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class CustomListViewAndroidExample extends Activity {
	ListView list;
	customAdapter1 adapter;
	public static String pkg = null;
	public CustomListViewAndroidExample CustomListView = null;

	// public ArrayList<listItem> CustomListViewValuesArr = new
	// ArrayList<listItem>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_list_view_android_example);

		CustomListView = this;

		Resources res = getResources();
		list = (ListView) findViewById(R.id.list); // List defined in XML ( See
													// Below )
		// Toast.makeText(getBaseContext(),
		// ""+MainActivity.CustomListViewValuesArr.size(),
		// Toast.LENGTH_LONG).show();
		/**************** Create Custom Adapter *********/
		adapter = new customAdapter1(CustomListView,
				MainActivity.CustomListViewValuesArr, res);
		list.setAdapter(adapter);

	}

	/***************** This function used by adapter ****************/
	public void onItemClick(int mPosition) {
		listItem tempValues = (listItem) MainActivity.CustomListViewValuesArr
				.get(mPosition);
		// SHOW ALERT
		Intent k = new Intent(this, router.class);
		pkg = tempValues.getAppname();
		k.putExtra("pkg", pkg);
		startActivity(k);
		// Toast.makeText(CustomListView,""+tempValues.getAppname()+" Image:"+tempValues.getImage(),Toast.LENGTH_LONG).show();
	}

}
