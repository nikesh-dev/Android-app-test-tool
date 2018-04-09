package com.example.event;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class customAdapter2 extends BaseAdapter implements OnClickListener {

	private Activity activity;
	private ArrayList<listItem1> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	public static ViewHolde holder;
	listItem1 tempValues = null;
	ImageView img, img1;
	int i = 0, j = 0, loc;

	public customAdapter2(final Activity a, ArrayList<listItem1> d,
			Resources resLocal) {

		/********** Take passed values **********/
		activity = a;
		data = d;
		res = resLocal;
		// img=(ImageView)a.findViewById(R.id.imageView2);
		// img1=(ImageView)a.findViewById(R.id.imageView1);
		loc = -1;
		/*********** Layout inflator to call external xml layout () ***********/
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (data.size() <= 0)
			return 1;
		return data.size();

	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	private static class ViewHolde {

		public TextView text;
		public ImageView image;
		public ImageView image1;
		public TextView text1;
		public TextView text2;
		public TextView text3;

	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View vi = arg1;
		loc = arg0;
		if (arg1 == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			vi = inflater.inflate(R.layout.tabitem1, null);
			vi.setTag(arg0 + "");
			// j++;
			/****** View Holder Object to contain tabitem1.xml file elements ******/

			holder = new ViewHolde();

			// holder = new ViewHolder();
			holder.text = (TextView) vi.findViewById(R.id.text);
			holder.text1 = (TextView) vi.findViewById(R.id.text1);
			holder.text2 = (TextView) vi.findViewById(R.id.text2);
			holder.image = (ImageView) vi.findViewById(R.id.imageView2);
			holder.image1 = (ImageView) vi.findViewById(R.id.imageView1);
			holder.text3 = (TextView) vi.findViewById(R.id.time);
			// holder.image=(ImageView)vi.findViewById(R.id.image);
			// holder.text1.setTag("got it");
			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else
			;
		holder = (ViewHolde) vi.getTag();

		if (data.size() <= 0) {
			holder.text.setText("No Data");
			holder.text1.setText("No data");
			holder.text2.setText("No data");
			holder.text3.setText("No data");
		} else {
			/***** Get each Model object from Arraylist ********/
			tempValues = null;
			tempValues = (listItem1) data.get(arg0);

			/************ Set Model values in Holder elements ***********/

			holder.text.setText(tempValues.getAppname());
			holder.text1.setText(tempValues.getUrl());
			holder.text2.setText(tempValues.gettext2());
			holder.text3.setText(tempValues.getTime());
			// holder.text1.setTag("hello");
			// holder.image.setBackgroundDrawable(tempValues.getImage());
			/******** Set Item Click Listner for LayoutInflater for each row *******/

			vi.setOnClickListener(new OnItemClickListener(arg0));
			holder.image.setOnClickListener(new OnItemClickListene(arg0));

		}
		holder.image1.setOnClickListener(new OnclickLi(arg0));
		return vi;
	}

	class OnclickLi implements OnClickListener {

		int pos;

		public OnclickLi(int position) {
			pos = position;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// Toast.makeText(activity.getBaseContext(), "hello",
			// Toast.LENGTH_LONG).show();
			Log.v("Cliked", "green");
			CustomListViewAndroidExample2 sct = (CustomListViewAndroidExample2) activity;
			sct.greenclick(pos);
		}

	}

	class OnItemClickListener implements OnClickListener {
		private int mPosition;

		OnItemClickListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View arg0) {

			CustomListViewAndroidExample2 sct = (CustomListViewAndroidExample2) activity;

			/****
			 * Call onItemClick Method inside CustomListViewAndroidExample Class
			 * ( See Below )
			 ****/
			// Log.v("item", ""+mPosition);
			sct.onItemClick(mPosition);
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	class OnItemClickListene implements OnClickListener {
		private int mPosition;

		OnItemClickListene(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.v("red", "Cliekced");
			CustomListViewAndroidExample2 sct = (CustomListViewAndroidExample2) activity;

			/****
			 * Call onItemClick Method inside CustomListViewAndroidExample Class
			 * ( See Below )
			 ****/
			Log.v("value", "" + mPosition);
			sct.red(mPosition);

		}
	}

}
