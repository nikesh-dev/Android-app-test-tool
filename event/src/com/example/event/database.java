package com.example.event;

public class database {
	String name;
	boolean ischecked;

	public database() {
		name = "";
		ischecked = false;
	}

	public void setName(String name) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setCheck(Boolean check) {
		ischecked = check;
	}

	public boolean getCheck() {
		return ischecked;
	}

}
