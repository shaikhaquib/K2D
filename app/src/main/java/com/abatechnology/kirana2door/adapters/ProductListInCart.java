package com.abatechnology.kirana2door.adapters;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ProductListInCart{

	@SerializedName("ProductListInCart")
	private List<ProductListInCartItem> productListInCart;

	public void setProductListInCart(List<ProductListInCartItem> productListInCart){
		this.productListInCart = productListInCart;
	}

	public List<ProductListInCartItem> getProductListInCart(){
		return productListInCart;
	}

	@Override
 	public String toString(){
		return 
			"ProductListInCart{" + 
			"productListInCart = '" + productListInCart + '\'' + 
			"}";
		}
}