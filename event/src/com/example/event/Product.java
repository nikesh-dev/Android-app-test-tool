package com.example.event;

public class Product {
	private int _id;
	// private String _productname;
	// private int _quantity;
	private int type;
	private int value1;
	private int value2;
	private long value3;
	private int value4;

	// private long time;

	public Product() {

	}

	/*
	 * public Product(int id, String productname, int quantity) { this._id = id;
	 * this._productname = productname; this._quantity = quantity; }
	 */
	/*
	 * public Product(String productname, int quantity) { this._productname =
	 * productname; this._quantity = quantity; }
	 */
	public Product(int id, int type, int value1, int value2, long value3,
			int value4) {
		this._id = id;
		this.type = type;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.value4 = value4;

	}

	public Product(int type, int value1, int value2, long value3, int value4) {
		this.type = type;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.value4 = value4;

	}

	public void setID(int id) {
		this._id = id;
	}

	public int getID() {
		return this._id;
	}

	/*
	 * public void setProductName(String productname) { this._productname =
	 * productname; }
	 * 
	 * public String getProductName() { return this._productname; }
	 * 
	 * public void setQuantity(int quantity) { this._quantity = quantity; }
	 * 
	 * public int getQuantity() { return this._quantity; }
	 */
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return this.type;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public int getValue1() {
		return this.value1;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}

	public int getValue2() {
		return this.value2;
	}

	public void setValue3(long l) {
		this.value3 = l;
	}

	public long getValue3() {
		return this.value3;
	}

	public int getValue4() {
		// TODO Auto-generated method stub
		return this.value4;
	}

	public void setValue4(int parseInt) {
		// TODO Auto-generated method stub
		this.value4 = parseInt;
	}

}
