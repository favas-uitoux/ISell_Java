package com.project.isell_java.pojos.read_data;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("retailers")
	private List<RetailersItem> retailers;

	@SerializedName("settings")
	private Settings settings;

	@SerializedName("inventories")
	private List<InventoriesItem> inventories;

	public List<RetailersItem> getRetailers(){
		return retailers;
	}

	public Settings getSettings(){
		return settings;
	}

	public List<InventoriesItem> getInventories(){
		return inventories;
	}
}