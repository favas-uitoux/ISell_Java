package com.project.isell_java.pojos.read_data;

import com.google.gson.annotations.SerializedName;

public class Settings{

	@SerializedName("voucherNo")
	private String voucherNo;

	@SerializedName("taxCalculation")
	private String taxCalculation;

	@SerializedName("KFcess")
	private String kFcess;

	@SerializedName("RecieptNo")
	private String recieptNo;

	public String getVoucherNo(){
		return voucherNo;
	}

	public String getTaxCalculation(){
		return taxCalculation;
	}

	public String getKFcess(){
		return kFcess;
	}

	public String getRecieptNo(){
		return recieptNo;
	}
}