package com.project.isell_java.pojos.read_data;

import com.google.gson.annotations.SerializedName;

public class RetailersItem{

	@SerializedName("area")
	private String area;

	@SerializedName("uid")
	private int uid;

	@SerializedName("address")
	private String address;

	@SerializedName("balance")
	private double balance;

	@SerializedName("gstn")
	private String gstn;

	@SerializedName("contact")
	private String contact;

	@SerializedName("name")
	private String name;

	@SerializedName("discount")
	private int discount;

	@SerializedName("location")
	private String location;

	@SerializedName("pricingLevel")
	private int pricingLevel;

	public String getArea(){
		return area;
	}

	public int getUid(){
		return uid;
	}

	public String getAddress(){
		return address;
	}

	public double getBalance(){
		return balance;
	}

	public String getGstn(){
		return gstn;
	}

	public String getContact(){
		return contact;
	}

	public String getName(){
		return name;
	}

	public int getDiscount(){
		return discount;
	}

	public String getLocation(){
		return location;
	}

	public int getPricingLevel(){
		return pricingLevel;
	}
}