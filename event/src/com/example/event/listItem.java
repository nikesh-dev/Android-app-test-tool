package com.example.event;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class listItem {

	private String appname;
	private Drawable icon;
	private String url;

	public void setAppname(String name) {
		this.appname = name;
	}

	public void setimage(Drawable drawable) {
		icon = drawable;
	}

	public String getAppname() {
		return appname;
	}

	public Drawable getImage() {
		return icon;
	}

	public void seturl(String url) {
		this.url = url;
	}

	public CharSequence getUrl() {
		// TODO Auto-generated method stub
		return url;
	}
}
