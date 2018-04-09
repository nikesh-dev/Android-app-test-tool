package com.example.event;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DatabaseActivity extends Activity {

	int type, value2, value1;

	public static int ptr;
	Button b;
	TextView t1;
	final Context context = this;
	String pkg;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.testcase_home);
		Toast.makeText(getBaseContext(), "entered", Toast.LENGTH_LONG).show();
		// in=new inputt[50];
		// MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

		setContentView(R.layout.testcase_home);
		pkg = getIntent().getCharSequenceExtra("pkg").toString();
		b = (Button) findViewById(R.id.button1);
		t1 = (TextView) findViewById(R.id.textView1);
		registerForContextMenu(b);
		ptr = 0;
	}

	public void test(View v) // FOR TEST BUTTON
	{
		String cases = t1.getText().toString();
		Intent i = new Intent(this, ghost.class);
		i.putExtra("cases", cases);
		i.putExtra("pkg", apps.pkg);
		startService(i);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Context Menu");
		menu.add(0, v.getId(), 0, "Key Event");
		menu.add(0, v.getId(), 0, "Touch Event");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Key Event") {
			function1("Key Event");
		} else if (item.getTitle() == "Touch Event") {
			function2("Touch Event");
		} else {
			return false;
		}
		return true;
	}

	// FOR TOUCH EVENT
	private void function2(String s) {
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.prompts1, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);
		final EditText x_val = (EditText) promptsView
				.findViewById(R.id.editText10);
		final EditText y_val = (EditText) promptsView
				.findViewById(R.id.editText9);
		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						// edit text
						// Toast.makeText(getBaseContext(), "ok clicked",
						// Toast.LENGTH_LONG).show();

						String x, y;
						x = x_val.getText().toString();
						y = y_val.getText().toString();
						// int value1,value2,type;
						value1 = Integer.parseInt(x);
						value2 = Integer.parseInt(y);
						type = 2;

					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}

	public void newProduct(View view) {
		Toast.makeText(getBaseContext(), "button clciked", Toast.LENGTH_SHORT)
				.show();
		MyDBHandler dbHandler = new MyDBHandler(DatabaseActivity.this,
				CustomListViewAndroidExample.pkg, null, null, 1);
		Toast.makeText(getBaseContext(), "button clciked2", Toast.LENGTH_SHORT)
				.show();

		Product product = new Product(type, value1, value2, 1000, -1);
		dbHandler.addProduct(product);
		// Toast.makeText(getBaseContext(), "button clciked3"+MyDBHandler.a,
		// Toast.LENGTH_SHORT).show();

		// int quantity = Integer.parseInt(quantityBox.getText().toString());
		/*
		 * int typee=Integer.parseInt(type.getText().toString()); int
		 * value11=Integer.parseInt(value1.getText().toString()); int
		 * value22=Integer.parseInt(value2.getText().toString()); Product
		 * product =new Product(typee,value11,value22);
		 * dbHandler.addProduct(product);
		 * 
		 * Toast.makeText(getBaseContext(), "data added!!",
		 * Toast.LENGTH_LONG).show(); //productBox.setText("");
		 * //quantityBox.setText(""); type.setText(""); value1.setText("");
		 * value2.setText("");
		 */
	}

	// FOR KEY EVENT
	private void function1(String s) {
		// TODO Auto-generated method stub
		Toast.makeText(getBaseContext(), s + " clicked", Toast.LENGTH_SHORT)
				.show();
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.prompts, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);
		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput);

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input and set it to result
						// edit text
						// result.setText(userInput.getText());

						value1 = Integer.parseInt(userInput.getText()
								.toString());
						value2 = 0;
						type = 3;
						/*
						 * MyDBHandler dbHandler = new
						 * MyDBHandler(getApplicationContext(), null, null, 1);
						 * 
						 * //int quantity =
						 * Integer.parseInt(quantityBox.getText().toString());
						 * int
						 * typee=type;//Integer.parseInt(type.getText().toString
						 * ()); int
						 * value11=value1;//Integer.parseInt(value1.getText
						 * ().toString()); int
						 * value22=value2;//Integer.parseInt(
						 * value2.getText().toString()); Product product =new
						 * Product(typee,value11,value22);
						 * dbHandler.addProduct(product); lookupProduct();
						 * Toast.makeText(getBaseContext(), "data added!!",
						 * Toast.LENGTH_LONG).show();
						 */// productBox.setText("");
							// quantityBox.setText("");
						/*
						 * if(t1.getText().toString().equals("empty")) {
						 * t1.setText("KEY: "+userInput.getText().toString()); }
						 * else {
						 * t1.setText(t1.getText().toString()+"\nKEY: "+userInput
						 * .getText().toString()); } in[ptr]=new inputt();
						 * in[ptr].type=3; //for keypad events
						 * in[ptr].key1=userInput.getText().toString();
						 * in[ptr].key2="-1"; ptr++;
						 */
						// Toast.makeText(getBaseContext(),
						// ""+userInput.getText().toString(),
						// Toast.LENGTH_LONG).show();
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}

	public void lookupProduct() {
		MyDBHandler dbHandler = new MyDBHandler(this,
				CustomListViewAndroidExample.pkg, null, null, 1);

		Product product = dbHandler.findProduct(1);
		Cursor c;
		c = MyDBHandler.cursor;
		Product p = new Product();
		Toast.makeText(getBaseContext(), "" + c.getCount(), Toast.LENGTH_LONG)
				.show();

		/*
		 * if (c.moveToFirst()) { c.moveToFirst(); while(!c.isAfterLast()) {
		 * product.setID(Integer.parseInt(c.getString(0)));
		 * product.setType(Integer.parseInt(c.getString(1)));
		 * product.setValue1(Integer.parseInt(c.getString(2)));
		 * product.setValue2(Integer.parseInt(c.getString(3))); //
		 * Toast.makeText(getBaseContext(),
		 * " "+product.getValue1()+" "+product.getValue2
		 * (),Toast.LENGTH_LONG).show();
		 * 
		 * c.moveToNext(); } Toast.makeText(getBaseContext(), ""+c.getCount()
		 * ,Toast.LENGTH_LONG).show(); }
		 */

		/*
		 * if (product != null) {
		 * idView.setText(String.valueOf(product.getID()));
		 * type.setText(String.valueOf(product.getType()));
		 * Toast.makeText(getBaseContext(),
		 * " "+product.getValue1()+" "+product.getValue2
		 * (),Toast.LENGTH_LONG).show();
		 * //quantityBox.setText(String.valueOf(product.getQuantity())); } else
		 * { idView.setText("No Match Found"); }
		 */
		MyDBHandler.cursor.close();
	}

}
