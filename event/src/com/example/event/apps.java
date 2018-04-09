package com.example.event;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class apps extends ListActivity {

	Intent j, logger;
	String[] s;
	public static String pkg;

	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		/*
		 * List<PackageInfo> apps = getPackageManager().getInstalledPackages(0);
		 * 
		 * int n; n=0; for(PackageInfo a : apps) { try {
		 * if(getPackageManager().getLaunchIntentForPackage
		 * (a.packageName)!=null) { n++; } } catch(Exception e) {
		 * Toast.makeText(getBaseContext(), ""+e.getMessage(),
		 * Toast.LENGTH_LONG).show(); } } s=new String[n]; int i=0;
		 * for(PackageInfo a : apps) { try {
		 * if(getPackageManager().getLaunchIntentForPackage
		 * (a.packageName)!=null) { s[i]=a.packageName;
		 * 
		 * // Toast.makeText(getBaseContext(), ""+s[i],
		 * Toast.LENGTH_LONG).show(); i++; } }
		 * 
		 * catch(Exception e) { Toast.makeText(getBaseContext(),
		 * ""+e.getMessage(), Toast.LENGTH_LONG).show(); }
		 * 
		 * } Toast.makeText(getBaseContext(),
		 * "Apps:"+i,Toast.LENGTH_SHORT).show();
		 * 
		 * ArrayAdapter<String> a=new
		 * ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s);
		 */
		ProgressDialog p = new ProgressDialog(this);
		p.setTitle("Loading...");
		p.setMessage("Please wait!!!");
		p.show();
		// setListAdapter(MainActivity.ap);

	}

	public void onListItemClick(ListView parent, View v, int pos, long id) {
		super.onListItemClick(parent, v, pos, id);
		Intent k = new Intent(this, router.class);
		pkg = s[pos];
		// k=new Intent(this,simple.class);
		// Toast.makeText(getBaseContext(), ""+s[pos],
		// Toast.LENGTH_LONG).show();
		k.putExtra("pkg", pkg);
		startActivity(k);
		// Toast.makeText(getBaseContext(), "ghost service started",
		// Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
