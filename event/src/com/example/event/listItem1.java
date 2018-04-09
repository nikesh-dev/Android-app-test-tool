package com.example.event;

import android.graphics.drawable.Drawable;

public class listItem1 {

	private String appname;
	// private Drawable icon;
	private String url, text2, time;

	public void setAppname(String name) {
		this.appname = name;
	}

	public String getAppname() {
		return appname;
	}

	public CharSequence getUrl() {
		// TODO Auto-generated method stub
		return url;
	}

	public void serurl(String arg0) {
		url = arg0;
	}

	public String gettext2() {

		return text2;
	}

	public void settext2(String arg0) {
		text2 = arg0;
	}

	public void setTime(String arg0) {
		time = arg0;
	}

	public String getTime() {
		return time;
	}
}
