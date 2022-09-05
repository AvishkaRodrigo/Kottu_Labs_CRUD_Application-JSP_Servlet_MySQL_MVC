package com.xadmin.itemmanagement.bean;

public class Item {
	private int itemCode;
	private String Name;
	private int price;
	private String type;

	
	public Item(int itemCode, String name, int price, String type) {
		super();
		this.itemCode = itemCode;
		Name = name;
		this.price = price;
		this.type = type;
	}
	 
	public Item(String name, int price, String type) {
		super();
		Name = name;
		this.price = price;
		this.type = type;
	}

	public int getItemCode() {
		return itemCode;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
