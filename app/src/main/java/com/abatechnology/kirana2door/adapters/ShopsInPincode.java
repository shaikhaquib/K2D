package com.abatechnology.kirana2door.adapters;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ShopsInPincode{

	@SerializedName("ShopList")
	private List<ShopListItem> shopList;

	public void setShopList(List<ShopListItem> shopList){
		this.shopList = shopList;
	}

	public List<ShopListItem> getShopList(){
		return shopList;
	}

	@Override
 	public String toString(){
		return 
			"ShopsInPincode{" + 
			"shopList = '" + shopList + '\'' + 
			"}";
		}
}