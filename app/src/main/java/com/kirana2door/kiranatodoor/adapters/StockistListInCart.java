package com.kirana2door.kiranatodoor.adapters;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class StockistListInCart{

	@SerializedName("StockistListInCart")
	private List<StockistListInCartItem> stockistListInCart;

	public void setStockistListInCart(List<StockistListInCartItem> stockistListInCart){
		this.stockistListInCart = stockistListInCart;
	}

	public List<StockistListInCartItem> getStockistListInCart(){
		return stockistListInCart;
	}

	@Override
 	public String toString(){
		return 
			"StockistListInCart{" + 
			"stockistListInCart = '" + stockistListInCart + '\'' + 
			"}";
		}
}