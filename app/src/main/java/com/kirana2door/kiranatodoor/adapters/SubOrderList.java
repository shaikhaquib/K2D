package com.kirana2door.kiranatodoor.adapters;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class SubOrderList {

	@SerializedName("SubOrderHistoryProductList")
	private List<SubOrderHistoryProductListItem> subOrderHistoryProductList;

	public void setSubOrderHistoryProductList(List<SubOrderHistoryProductListItem> subOrderHistoryProductList){
		this.subOrderHistoryProductList = subOrderHistoryProductList;
	}

	public List<SubOrderHistoryProductListItem> getSubOrderHistoryProductList(){
		return subOrderHistoryProductList;
	}

	@Override
 	public String toString(){
		return 
			"SubOrderList{" +
			"subOrderHistoryProductList = '" + subOrderHistoryProductList + '\'' + 
			"}";
		}
}