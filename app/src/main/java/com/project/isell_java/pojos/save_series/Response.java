package com.project.isell_java.pojos.save_series;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("result")
	private String result;

	@SerializedName("message")
	private String message;

	public String getResult(){
		return result;
	}

	public String getMessage(){
		return message;
	}
}