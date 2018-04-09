package com.example.event;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomListViewAndroidExample2 extends Activity {

	public String cases;
	ListView list;
	TextView t1;
	EditText ed1;
	customAdapter2 adapter;
	ImageView img;
	int idd;
	Resources res;
	String[] listindex;
	final Context context = this;
	public static String pkg = null;
	public CustomListViewAndroidExample2 CustomListView = null;
	public ArrayList<listItem1> CustomListViewValuesArr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_list_view_android_example2);
		CustomListView = this;
		cases = (String) this.getIntent().getCharSequenceExtra("cases");
		t1 = (TextView) findViewById(R.id.textView1);
		ed1 = (EditText) findViewById(R.id.editText1);
		list = (ListView) findViewById(R.id.list1); // List defined in XML ( See
													// Below )

		setListData();
		res = getResources();

		/**************** Create Custom Adapter *********/
		adapter = new customAdapter2(CustomListView, CustomListViewValuesArr,
				res);
		list.setAdapter(adapter);
		// int count=list.getCount();

		// Toast.makeText(getBaseContext(),""+count,Toast.LENGTH_SHORT).show();
		// Toast.makeText(getBaseContext(),""+list.getItemAtPosition(1).toString(),Toast.LENGTH_SHORT).show();

		/*
		 * for(int i=0;i<count;i++) { TableLayout
		 * table=(TableLayout)list.getChildAt(i); /* RelativeLayout
		 * lay=(RelativeLayout)table.getChildAt(0); TableLayout
		 * table1=(TableLayout)lay.getChildAt(0); TableRow
		 * row=(TableRow)table1.getChildAt(0); LinearLayout
		 * laayo=(LinearLayout)row.getChildAt(0); TextView
		 * vie=(TextView)laayo.getChildAt(0);
		 * Toast.makeText(getBaseContext(),""+
		 * vie.getText().toString(),Toast.LENGTH_SHORT).show();
		 * 
		 * }
		 */
	}

	private void setListData() {
		// TODO Auto-generated method stub
		// try
		// {
		CustomListViewValuesArr = new ArrayList<listItem1>();
		MyDBHandler dbHandle = new MyDBHandler(this,
				CustomListViewAndroidExample.pkg, cases, null, 1);
		Product pro = dbHandle.findProduct(1);
		Cursor c = MyDBHandler.cursor;
		c.moveToFirst();
		int size = c.getCount();
		listindex = new String[size];

		for (int i = 0; i < size; i++) {
			final listItem1 sched = new listItem1();
			String type, value1, value2, tim;
			// Toast.makeText(getBaseContext(),
			// c.getString(0)+" "+c.getString(2), Toast.LENGTH_LONG).show();
			listindex[i] = c.getString(0);
			tim = c.getString(4);
			if (Integer.parseInt(c.getString(1)) == 2) {
				type = "TOUCH";
				value1 = "X: " + c.getString(2);
				value2 = "Y: " + c.getString(3);
			} else {
				if (Integer.parseInt(c.getString(1)) == 3) {
					type = "KEY";
					value1 = "CODE: " + c.getString(2);
					value2 = "";
				} else {
					type = "OTHER";
					value2 = c.getString(2);
					value1 = c.getString(3);
				}
			}

			sched.setAppname(type);
			sched.serurl(value1);
			sched.settext2(value2);
			sched.setTime(tim);
			// Toast.makeText(getBaseContext(), c.getString(1),
			// Toast.LENGTH_LONG).show();
			CustomListViewValuesArr.add(sched);
			c.moveToNext();
		}
		Toast.makeText(getBaseContext(),
				CustomListViewValuesArr.size() + " Items", Toast.LENGTH_LONG)
				.show();

		c.close();
		// MyDBHandler.cursor.close();
		// }
		/*
		 * catch(Exception e) {
		 * Toast.makeText(getBaseContext(),"cbdkj"+e.toString(),
		 * Toast.LENGTH_LONG).show(); }
		 */
	}

	public void onItemClick(int mPosition) {
		// Toast.makeText(getBaseContext(), "Deleted "+mPosition,
		// Toast.LENGTH_SHORT).show();

		final MyDBHandler handy = new MyDBHandler(context,
				CustomListViewAndroidExample.pkg, cases, null, 1);
		Product arg = handy.findProduct(1);
		final Cursor cur = MyDBHandler.cursor;
		cur.moveToFirst();
		if (cur.move(mPosition)) {
			Toast.makeText(getBaseContext(), cur.getString(2),
					Toast.LENGTH_LONG).show();
		}
		final listItem1 lis = CustomListViewValuesArr.get(mPosition);
		if (lis.getAppname().toString() == "TOUCH") {
			LayoutInflater li = LayoutInflater.from(context);
			View promptsView = li.inflate(R.layout.prompts1, null);
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
			alertDialogBuilder.setView(promptsView);
			final EditText x_val = (EditText) promptsView
					.findViewById(R.id.editText10);
			final EditText y_val = (EditText) promptsView
					.findViewById(R.id.editText9);
			final EditText time = (EditText) promptsView
					.findViewById(R.id.time);
			x_val.setText(cur.getString(2) + "");
			y_val.setText(cur.getString(3));
			time.setText(cur.getString(4));
			alertDialogBuilder
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									int x = Integer.parseInt(x_val.getText()
											.toString());
									int y = Integer.parseInt(y_val.getText()
											.toString());
									int tim = Integer.parseInt(time.getText()
											.toString());
									int idd = Integer.parseInt(cur.getString(0));
									handy.updatee(idd, 2, x, y, tim, -1);
									cur.close();
									setListData();
									adapter = new customAdapter2(
											CustomListView,
											CustomListViewValuesArr, res);
									list.setAdapter(adapter);
								}

							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
			Toast.makeText(getBaseContext(), mPosition + "", Toast.LENGTH_LONG)
					.show();

		} else if (lis.getAppname() == "KEY") {
			Toast.makeText(getBaseContext(), "key", Toast.LENGTH_LONG).show();
			LayoutInflater li = LayoutInflater.from(context);

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);

			View promptsView = li.inflate(R.layout.prompts, null);
			// set prompts.xml to alertdialog builder
			alertDialogBuilder.setView(promptsView);
			final EditText userInput = (EditText) promptsView
					.findViewById(R.id.editTextDialogUserInput);
			final EditText time = (EditText) promptsView
					.findViewById(R.id.time);
			userInput.setText(cur.getString(2));
			time.setText(cur.getString(4));
			// set dialog message
			alertDialogBuilder
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// get user input and set it to result
									int x = Integer.parseInt(userInput
											.getText().toString());
									int tim = Integer.parseInt(time.getText()
											.toString());
									int idd = Integer.parseInt(cur.getString(0));
									handy.updatee(idd, 3, x, 0, tim, -1);
									cur.close();
									setListData();
									adapter = new customAdapter2(
											CustomListView,
											CustomListViewValuesArr, res);
									list.setAdapter(adapter);
								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();

		} else
			Toast.makeText(getBaseContext(), lis.getAppname() + " others",
					Toast.LENGTH_LONG).show();

	}

	public void customstart(View v) {
		Intent k = new Intent(this, ghost.class);
		String[] casee;
		casee = new String[1];
		casee[0] = cases;
		// k.putExtra("cases",cases);
		k.putExtra("cases", casee);
		k.putExtra("pkg", CustomListViewAndroidExample.pkg);
		startService(k);
	}

	public void key(final int idd) {
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.prompts, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);
		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.editTextDialogUserInput);
		final EditText time = (EditText) promptsView.findViewById(R.id.time);
		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// get user input
						MyDBHandler dbHandler = new MyDBHandler(
								CustomListViewAndroidExample2.this,
								CustomListViewAndroidExample.pkg, cases, null,
								1);
						int value11 = Integer.parseInt(userInput.getText()
								.toString());
						int tim = Integer.parseInt(time.getText().toString());
						// int
						// value22=Integer.parseInt(value2.getText().toString());
						Product product = new Product(idd, 3, value11, 0, tim,
								-1);// typee,value11,value22);
						dbHandler.addProduct(product);
						dbHandler.close();
						setListData();
						adapter = new customAdapter2(CustomListView,
								CustomListViewValuesArr, res);
						list.setAdapter(adapter);
						// Toast.makeText(getBaseContext(), value11,
						// Toast.LENGTH_LONG).show();
						Toast.makeText(getBaseContext(), "position " + idd,
								Toast.LENGTH_LONG).show();

					}
				})
				.setNegativeButton("CANCEL",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
								// touch();
							}
						});
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
	}

	public void touch(final int idd) {
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
		final EditText time = (EditText) promptsView.findViewById(R.id.time);
		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						MyDBHandler dbHandler = new MyDBHandler(
								CustomListViewAndroidExample2.this,
								CustomListViewAndroidExample.pkg, cases, null,
								1);
						int value11 = Integer.parseInt(x_val.getText()
								.toString());
						int value22 = Integer.parseInt(y_val.getText()
								.toString());
						int tim = Integer.parseInt(time.getText().toString());

						Product product = new Product(idd, 2, value11, value22,
								tim);// typee,value11,value22);
						// Product product =new Product(1,3,3);
						dbHandler.addProduct(product);
						dbHandler.close();
						setListData();
						adapter = new customAdapter2(CustomListView,
								CustomListViewValuesArr, res);
						list.setAdapter(adapter);
						Toast.makeText(getBaseContext(), "position " + idd,
								Toast.LENGTH_LONG).show();

						Toast.makeText(
								getBaseContext(),
								x_val.getText().toString() + " "
										+ y_val.getText().toString(),
								Toast.LENGTH_LONG).show();
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

	public void greenclick(final int position) {
		// LayoutInflater li = LayoutInflater.from(context);
		// View promptsView =li.inflate(R.layout.prompts1, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		rearrangebeforeadd(position);
		// set prompts.xml to alertdialog builder
		// alertDialogBuilder.setView(promptsView);
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("KEY",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								key(position + 2);
								// Toast.makeText(getBaseContext(),
								// "hello ! green", Toast.LENGTH_LONG).show();
							}
						})
				.setNegativeButton("TOUCH",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// dialog.cancel();
								touch(position + 2);
							}
						});
		AlertDialog alert = alertDialogBuilder.create();
		alert.show();
		/***** addd product with specific ID *******/
		// Toast.makeText(getBaseContext(), "green "+position,
		// Toast.LENGTH_LONG).show();
	}

	public void red(int v) {
		MyDBHandler dbHandle = new MyDBHandler(this,
				CustomListViewAndroidExample.pkg, cases, null, 1);
		if (dbHandle.deleteProduct(Integer.parseInt(listindex[v]))) {
			// Toast.makeText(getBaseContext(), "Deleted "+v,
			// Toast.LENGTH_SHORT).show();
			rearrangeafterremove(v);
		} else
			Toast.makeText(getBaseContext(), "Error!", Toast.LENGTH_SHORT)
					.show();
		setListData();
		adapter = new customAdapter2(CustomListView, CustomListViewValuesArr,
				res);
		list.setAdapter(adapter);
	}

	public void rearrangeafterremove(int v) {
		MyDBHandler dbHandle = new MyDBHandler(this,
				CustomListViewAndroidExample.pkg, cases, null, 1);
		Product p = dbHandle.findProduct(1);
		Cursor cur = MyDBHandler.cursor;
		int count = cur.getCount();
		// Toast.makeText(getBaseContext(),count+"", Toast.LENGTH_LONG).show();
		cur.moveToFirst();
		cur.move(v);
		int size = cur.getCount();
		for (int i = v; i < size; i++) {
			// Toast.makeText(getBaseContext(),cur.getString(0)+" "+Integer.parseInt(cur.getString(1))+" "+Integer.parseInt(cur.getString(2))+" "+Integer.parseInt(cur.getString(3))+" "+cur.getString(4),
			// Toast.LENGTH_LONG).show();
			dbHandle.updateee(Integer.parseInt(cur.getString(0)),
					Integer.parseInt(cur.getString(0)) - 1,
					Integer.parseInt(cur.getString(1)),
					Integer.parseInt(cur.getString(2)),
					Integer.parseInt(cur.getString(3)),
					Long.parseLong(cur.getString(4)), -1);
			// Toast.makeText(getBaseContext(),cur.getString(0)+" "+Integer.parseInt(cur.getString(1))+" "+Integer.parseInt(cur.getString(2))+" "+Integer.parseInt(cur.getString(3))+" "+cur.getString(4),
			// Toast.LENGTH_LONG).show();

			cur.moveToNext();
		}
		/*
		 * cur.move(v+2);
		 */
		cur.close();

	}

	public void rearrangebeforeadd(int v) {
		MyDBHandler dbHandle = new MyDBHandler(this,
				CustomListViewAndroidExample.pkg, cases, null, 1);
		Product p = dbHandle.findProduct(1);
		Cursor cur = MyDBHandler.cursor;
		int count = cur.getCount();
		Toast.makeText(getBaseContext(), count + "", Toast.LENGTH_LONG).show();
		cur.moveToFirst();
		cur.move(v);
		cur.moveToLast();
		int size = cur.getCount();
		for (int i = size - 1; i > v; i--) {
			dbHandle.updateee(Integer.parseInt(cur.getString(0)),
					Integer.parseInt(cur.getString(0)) + 1,
					Integer.parseInt(cur.getString(1)),
					Integer.parseInt(cur.getString(2)),
					Integer.parseInt(cur.getString(3)),
					Long.parseLong(cur.getString(4)), -1);
			cur.moveToPrevious();
		}
		cur.close();
	}
}
