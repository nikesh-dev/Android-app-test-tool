package com.example.event;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class DatabaseAdapter extends BaseAdapter implements OnClickListener {

	public ArrayList<database> dataa;
	public static boolean[] checked;
	public Activity context;
	database base = null;
	int position;
	// boolean[] input;
	public static ViewHolder holder;

	public static LayoutInflater inflater = null;

	public DatabaseAdapter(Activity acti, ArrayList<database> data) {
		// super();
		dataa = data;
		context = acti;
		checked = new boolean[data.size()];
		for (int h = 0; h < data.size(); h++) {
			checked[h] = false;
		}
		// input=new boolean[dataa.size()];
		inflater = (LayoutInflater) acti
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	private class ViewHolder {
		TextView apkName;
		CheckBox ck1;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataa.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return dataa.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		// View vi = convertView;
		this.position = position;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();
			convertView.setTag(position + "");

			holder.apkName = (TextView) convertView.findViewById(R.id.database);
			holder.ck1 = (CheckBox) convertView.findViewById(R.id.checkBox1);

			convertView.setTag(holder);

			// holder.ck1.setTag(dataa.get(position));
		} else {

			holder = (ViewHolder) convertView.getTag();
		}
		if (dataa.size() <= 0) {
			holder.apkName.setText("No Data");
			holder.ck1.setChecked(false);
			// holder.text1.setText("No data");
			// holder.text2.setText("No data");
		} else {
			/***** Get each Model object from Arraylist ********/
			base = null;
			base = (database) dataa.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.apkName.setText(base.getName());
			holder.ck1.setChecked(base.getCheck());
			Log.v("check", base.getCheck() + " " + position);

			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent inte = new Intent(context.getApplicationContext(),
							CustomListViewAndroidExample2.class);
					inte.putExtra("cases", dataa.get(position).getName());
					context.startActivity(inte);
				}

			});

			holder.ck1.setOnClickListener(new OnClickListen(position));
			// return convertView;
		}
		return convertView;
	}

	class OnClickListen implements OnClickListener {
		int pos;
		Boolean b;

		public OnClickListen(int position) {
			pos = position;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			dataa.get(pos).setCheck(!dataa.get(pos).getCheck());
			Log.v("click", pos + " " + dataa.get(pos).getCheck());
			checked[pos] = dataa.get(pos).getCheck();
			// tester tes=(tester)context;
			// tes.checkbox(pos, dataa.get(pos).getCheck());
			// tes.checkbox(checked);
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
