package com.project.isell_java.pojos.import_data;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class InventoriesItem{

	@SerializedName("rate4")
	private double rate4;

	@SerializedName("sgst")
	private double sgst;

	@SerializedName("rate1")
	private double rate1;

	@SerializedName("rate2")
	private double rate2;

	@SerializedName("rate3")
	private double rate3;

	@SerializedName("discount")
	private double discount;

	@SerializedName("tax")
	private double tax;

	@SerializedName("cgst")
	private double cgst;

	@SerializedName("shortcode")
	private String shortcode;

	@SerializedName("cess")
	private double cess;

	@SerializedName("uid")
	private int uid;

	@SerializedName("hsncode")
	private String hsncode;

	@SerializedName("unit")
	private List<UnitItem> unit;

	@SerializedName("gstn")
	private String gstn;

	@SerializedName("name")
	private String name;

	@SerializedName("category")
	private String category;

	@SerializedName("stock")
	private double stock;

	@SerializedName("group")
	private String group;

	@SerializedName("kfcess")
	private double kfcess;

	public double getRate4(){
		return rate4;
	}

	public double getSgst(){
		return sgst;
	}

	public double getRate1(){
		return rate1;
	}

	public double getRate2(){
		return rate2;
	}

	public double getRate3(){
		return rate3;
	}

	public double getDiscount(){
		return discount;
	}

	public double getTax(){
		return tax;
	}

	public double getCgst(){
		return cgst;
	}

	public String getShortcode(){
		return shortcode;
	}

	public double getCess(){
		return cess;
	}

	public int getUid(){
		return uid;
	}

	public String getHsncode(){
		return hsncode;
	}

	public List<UnitItem> getUnit(){
		return unit;
	}

	public String getGstn(){
		return gstn;
	}

	public String getName(){
		return name;
	}

	public String getCategory(){
		return category;
	}

	public double getStock(){
		return stock;
	}

	public String getGroup(){
		return group;
	}

	public double getKfcess(){
		return kfcess;
	}
}