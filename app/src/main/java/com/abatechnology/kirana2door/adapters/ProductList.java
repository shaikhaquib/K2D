package com.abatechnology.kirana2door.adapters;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class ProductList{

	@SerializedName("ProductList")
	private List<ProductListItem> productList;

	public void setProductList(List<ProductListItem> productList){
		this.productList = productList;
	}

	public List<ProductListItem> getProductList(){
		return productList;
	}

	@Override
 	public String toString(){
		return 
			"ProductList{" + 
			"productList = '" + productList + '\'' + 
			"}";
		}
}