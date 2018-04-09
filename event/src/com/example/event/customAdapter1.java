package com.example.event;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class customAdapter1 extends BaseAdapter implements OnClickListener {

	private Activity activity;
	private ArrayList data;
	private static LayoutInflater inflater = null;
	public Resources res;
	listItem tempValues = null;
	int i = 0;

	public customAdapter1(Activity a, ArrayList d, Resources resLocal) {

		/********** Take passed values **********/
		activity = a;
		data = d;
		res = resLocal;

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

	private static class ViewHolder {

		public TextView text;
		public ImageView image;
		// public TextView text1;

	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View vi = arg1;
		ViewHolder holder;
		if (arg1 == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			vi = inflater.inflate(R.layout.tabitem, null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();

			// holder = new ViewHolder();
			holder.text = (TextView) vi.findViewById(R.id.text);
			// holder.text1=(TextView)vi.findViewById(R.id.text1);
			holder.image = (ImageView) vi.findViewById(R.id.image);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else
			holder = (ViewHolder) vi.getTag();

		if (data.size() <= 0) {
			holder.text.setText("No Data");
			// holder.text1.setText("No data");
		} else {
			/***** Get each Model object from Arraylist ********/
			tempValues = null;
			tempValues = (listItem) data.get(arg0);

			/************ Set Model values in Holder elements ***********/

			holder.text.setText(tempValues.getAppname());
			// holder.text1.setText( tempValues.getUrl() );
			holder.image.setBackgroundDrawable(tempValues.getImage());
			/******** Set Item Click Listner for LayoutInflater for each row *******/

			vi.setOnClickListener(new OnItemClickListener(arg0));
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

			CustomListViewAndroidExample sct = (CustomListViewAndroidExample) activity;

			/****
			 * Call onItemClick Method inside CustomListViewAndroidExample Class
			 * ( See Below )
			 ****/

			sct.onItemClick(mPosition);
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
