package com.example.event;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class manual extends Activity {

	TextView t1;
	public static String dbname;
	EditText ed1;
	final Context context = this;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.manual);
		Button b = (Button) findViewById(R.id.button1);
		t1 = (TextView) findViewById(R.id.textView1);
		// registerForContextMenu(b);
		ed1 = (EditText) findViewById(R.id.editText1);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {

					String filename = ed1.getText().toString();
					MyDBHandler handy = new MyDBHandler(manual.this,
							CustomListViewAndroidExample.pkg, filename, null, 1);
					Intent intent = new Intent(manual.this, manualList.class);
					intent.putExtra("fileName", filename);
					startActivity(intent);
				} catch (Exception e) {
					Toast.makeText(getBaseContext(), "Eroor!",
							Toast.LENGTH_LONG).show();
				}
			}

		});
	}
	/*
	 * public void save(View v) { //String s=ed1.getText().toString();
	 * MyDBHandler dbHandler = new MyDBHandler(manual.this,
	 * ed1.getText().toString(), null, 1); Product p=dbHandler.findProduct(1);
	 * Toast.makeText(getBaseContext(),MyDBHandler.cases+": "+
	 * MyDBHandler.cursor.getCount(), Toast.LENGTH_LONG).show(); }
	 * 
	 * @Override public void onCreateContextMenu(ContextMenu menu, View
	 * v,ContextMenuInfo menuInfo) { super.onCreateContextMenu(menu, v,
	 * menuInfo); menu.setHeaderTitle("Context Menu"); menu.add(0, v.getId(), 0,
	 * "Key Event"); menu.add(0, v.getId(), 0, "Touch Event"); }
	 * 
	 * @Override public boolean onContextItemSelected(MenuItem item) {
	 * if(item.getTitle()=="Key Event"){function1("Key Event");} else
	 * if(item.getTitle()=="Touch Event"){function2("Touch Event");} else
	 * {return false;} return true; }
	 * 
	 * private void function2(String string) { // TODO Auto-generated method
	 * stub
	 * 
	 * LayoutInflater li = LayoutInflater.from(context); View promptsView
	 * =li.inflate(R.layout.prompts1, null);
	 * 
	 * AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	 * context);
	 * 
	 * // set prompts.xml to alertdialog builder
	 * alertDialogBuilder.setView(promptsView); final EditText x_val =
	 * (EditText) promptsView.findViewById(R.id.editText10); final EditText
	 * y_val = (EditText) promptsView.findViewById(R.id.editText9); // set
	 * dialog message alertDialogBuilder .setCancelable(false)
	 * .setPositiveButton("OK", new DialogInterface.OnClickListener() { public
	 * void onClick(DialogInterface dialog,int id) { // get user input and set
	 * it to result // edit text // Toast.makeText(getBaseContext(),
	 * "ok clicked", Toast.LENGTH_LONG).show(); // dbname="xyz.db"; MyDBHandler
	 * dbHandler = new MyDBHandler(manual.this, ed1.getText().toString(), null,
	 * 1); Toast.makeText(getBaseContext(), "Database created",
	 * Toast.LENGTH_LONG).show(); // int
	 * typee=Integer.parseInt(type.getText().toString()); int
	 * value11=Integer.parseInt(x_val.getText().toString()); int
	 * value22=Integer.parseInt(y_val.getText().toString()); Product product
	 * =new Product(2,value11,value22,1000);//typee,value11,value22);
	 * 
	 * // Product product =new Product(1,3,3); dbHandler.addProduct(product);
	 * dbHandler.close(); String x,y; x=x_val.getText().toString();
	 * y=y_val.getText().toString(); if(t1.getText().toString().equals("empty"))
	 * {
	 * 
	 * t1.setText("TOUCH: "+x_val.getText().toString()+","+y_val.getText().toString
	 * ());
	 * 
	 * } else {
	 * 
	 * t1.setText(t1.getText().toString()+"\nTOUCH: "+x_val.getText().toString()+
	 * ","+y_val.getText().toString()); } /* in[ptr]=new inputt(); //inputt
	 * i=new inputt(); //i.type=2; in[ptr].type=2;
	 * 
	 * 
	 * // for touch events in[ptr].key1=x; in[ptr].key2=y; ptr++; //
	 * Toast.makeText(getBaseContext(),
	 * ""+x_val.getText().toString()+"  "+y_val.getText().toString(),
	 * Toast.LENGTH_LONG).show();
	 * 
	 * 
	 * } }) .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	 * public void onClick(DialogInterface dialog,int id) { dialog.cancel(); }
	 * });
	 * 
	 * // create alert dialog AlertDialog alertDialog =
	 * alertDialogBuilder.create();
	 * 
	 * // show it alertDialog.show();
	 * 
	 * 
	 * }
	 * 
	 * private void function1(String string) { // TODO Auto-generated method
	 * stub // TODO Auto-generated method stub
	 * //Toast.makeText(getBaseContext(),s+" clicked",
	 * Toast.LENGTH_SHORT).show(); LayoutInflater li =
	 * LayoutInflater.from(context); View promptsView
	 * =li.inflate(R.layout.prompts, null);
	 * 
	 * AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	 * context);
	 * 
	 * // set prompts.xml to alertdialog builder
	 * alertDialogBuilder.setView(promptsView); final EditText userInput =
	 * (EditText) promptsView .findViewById(R.id.editTextDialogUserInput);
	 * 
	 * // set dialog message alertDialogBuilder .setCancelable(false)
	 * .setPositiveButton("OK", new DialogInterface.OnClickListener() { public
	 * void onClick(DialogInterface dialog,int id) { // get user input and set
	 * it to result // edit text // result.setText(userInput.getText());
	 * MyDBHandler dbHandler = new MyDBHandler(manual.this,
	 * ed1.getText().toString(), null, 1); Toast.makeText(getBaseContext(),
	 * "Database created", Toast.LENGTH_LONG).show(); // int
	 * typee=Integer.parseInt(type.getText().toString()); int
	 * value11=Integer.parseInt(userInput.getText().toString()); // int
	 * value22=Integer.parseInt(value2.getText().toString()); Product product
	 * =new Product(3,value11,0,1000);//typee,value11,value22);
	 * 
	 * // Product product =new Product(1,3,3); dbHandler.addProduct(product);
	 * dbHandler.close(); if(t1.getText().toString().equals("empty")) {
	 * t1.setText("KEY: "+userInput.getText().toString()); } else {
	 * t1.setText(t1
	 * .getText().toString()+"\nKEY: "+userInput.getText().toString()); } /*
	 * in[ptr]=new inputt(); in[ptr].type=3; //for keypad events
	 * in[ptr].key1=userInput.getText().toString(); in[ptr].key2="-1"; ptr++;
	 * 
	 * //Toast.makeText(getBaseContext(), ""+userInput.getText().toString(),
	 * Toast.LENGTH_LONG).show(); } }) .setNegativeButton("Cancel", new
	 * DialogInterface.OnClickListener() { public void onClick(DialogInterface
	 * dialog,int id) { dialog.cancel(); } });
	 * 
	 * // create alert dialog AlertDialog alertDialog =
	 * alertDialogBuilder.create();
	 * 
	 * // show it alertDialog.show(); }
	 */
}
