package com.kirana2door.kiranatodoor.adapters;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class OrderHistoryList{

	@SerializedName("OrderHistoryList")
	private List<OrderHistoryListItem> orderHistoryList;

	public void setOrderHistoryList(List<OrderHistoryListItem> orderHistoryList){
		this.orderHistoryList = orderHistoryList;
	}

	public List<OrderHistoryListItem> getOrderHistoryList(){
		return orderHistoryList;
	}

	@Override
 	public String toString(){
		return 
			"OrderHistoryList{" + 
			"orderHistoryList = '" + orderHistoryList + '\'' + 
			"}";
		}
}