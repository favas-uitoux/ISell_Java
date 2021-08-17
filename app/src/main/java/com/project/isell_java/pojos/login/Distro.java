package com.project.isell_java.pojos.login;

import com.google.gson.annotations.SerializedName;

public class Distro{

	@SerializedName("uid")
	private String uid;

	@SerializedName("address")
	private String address;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("gstn")
	private String gstn;

	@SerializedName("name")
	private String name;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("validity")
	private String validity;

	@SerializedName("email")
	private String email;

	@SerializedName("max_clients")
	private int maxClients;

	@SerializedName("status")
	private String status;

	public String getUid(){
		return uid;
	}

	public String getAddress(){
		return address;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getGstn(){
		return gstn;
	}

	public String getName(){
		return name;
	}

	public String getMobile(){
		return mobile;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public String getValidity(){
		return validity;
	}

	public String getEmail(){
		return email;
	}

	public int getMaxClients(){
		return maxClients;
	}

	public String getStatus(){
		return status;
	}
}