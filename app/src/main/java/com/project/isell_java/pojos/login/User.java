package com.project.isell_java.pojos.login;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("uid")
	private String uid;

	@SerializedName("distro")
	private Distro distro;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("distro_id")
	private int distroId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("validity")
	private String validity;

	@SerializedName("status")
	private String status;

	public String getUid(){
		return uid;
	}

	public Distro getDistro(){
		return distro;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public String getName(){
		return name;
	}

	public String getMobile(){
		return mobile;
	}

	public int getDistroId(){
		return distroId;
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

	public String getStatus(){
		return status;
	}
}