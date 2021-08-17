package com.project.isell_java.pojos.import_data;

import com.google.gson.annotations.SerializedName;

public class UnitItem{

	@SerializedName("qty")
	private double qty;

	@SerializedName("name")
	private String name;

	public double getQty(){
		return qty;
	}

	public String getName(){
		return name;
	}
}