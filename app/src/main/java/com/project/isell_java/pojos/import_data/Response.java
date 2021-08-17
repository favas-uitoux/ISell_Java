package com.project.isell_java.pojos.import_data;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("data")
	private Data data;

	@SerializedName("version")
	private String version;

	public Data getData(){
		return data;
	}

	public String getVersion(){
		return version;
	}
}