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

public class manualAdapter extends BaseAdapter implements OnClickListener {

	public Activity activity;
	public ArrayList<listItem1> data;
	public Resources res;
	public ImageView img;
	int loc;
	public static ViewHolde holder;
	listItem1 tempValues = null;
	public LayoutInflater inflater;

	public manualAdapter(final Activity a, ArrayList<listItem1> d,
			Resources resLocal) {
		/********** Take passed values **********/
		activity = a;
		data = d;
		res = resLocal;
		img = (ImageView) a.findViewById(R.id.imageView2);
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
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	private static class ViewHolde {

		public TextView text;
		public ImageView image;
		public TextView text1;
		public TextView text2;

	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View vi = arg1;
		loc = arg0;

		if (arg1 == null) {
			vi = inflater.inflate(R.layout.tabitem2, null);

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			// vi.setTag(arg0+"");
			// j++;
			/****** View Holder Object to contain tabitem1.xml file elements ******/

			holder = new ViewHolde();

			// holder = new ViewHolder();
			holder.text = (TextView) vi.findViewById(R.id.text);
			holder.text1 = (TextView) vi.findViewById(R.id.text1);
			holder.text2 = (TextView) vi.findViewById(R.id.text2);
			holder.image = (ImageView) vi.findViewById(R.id.imageView2);
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
			Log.v("debug", "data<0");
		} else {
			/***** Get each Model object from Arraylist ********/
			tempValues = null;
			tempValues = (listItem1) data.get(arg0);

			/************ Set Model values in Holder elements ***********/

			holder.text.setText(tempValues.getAppname());
			holder.text1.setText(tempValues.getUrl());
			holder.text2.setText(tempValues.gettext2());
			// holder.text1.setTag("hello");
			// holder.image.setBackgroundDrawable(tempValues.getImage());
			/******** Set Item Click Listner for LayoutInflater for each row *******/

			vi.setOnClickListener(new OnItemClickListener(arg0));
			holder.image.setOnClickListener(new OnItemClickListene(arg0));

		}

		return vi;
	}

	class OnItemClickListener implements OnClickListener {
		private int mPosition;

		OnItemClickListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View arg0) {

			manualList sct = (manualList) activity;

			/****
			 * Call onItemClick Method inside CustomListViewAndroidExample Class
			 * ( See Below )
			 ****/
			// Log.v("item", ""+mPosition);
			sct.onItemClick(mPosition);
		}
	}

	class OnItemClickListene implements OnClickListener {
		private int mPosition;

		OnItemClickListene(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			manualList sct = (manualList) activity;

			/****
			 * Call onItemClick Method inside CustomListViewAndroidExample Class
			 * ( See Below )
			 ****/
			// Log.v("value", ""+mPosition);
			sct.red(mPosition);

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
