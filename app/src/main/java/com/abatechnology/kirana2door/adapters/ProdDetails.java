package com.abatechnology.kirana2door.adapters;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ProdDetails{

	@SerializedName("product_images")
	private List<ProductImagesItem> productImages;

	@SerializedName("ProductDetails")
	private List<ProductDetailsItem> productDetails;

	public void setProductImages(List<ProductImagesItem> productImages){
		this.productImages = productImages;
	}

	public List<ProductImagesItem> getProductImages(){
		return productImages;
	}

	public void setProductDetails(List<ProductDetailsItem> productDetails){
		this.productDetails = productDetails;
	}

	public List<ProductDetailsItem> getProductDetails(){
		return productDetails;
	}

	@Override
 	public String toString(){
		return 
			"ProdDetails{" + 
			"product_images = '" + productImages + '\'' + 
			",productDetails = '" + productDetails + '\'' + 
			"}";
		}
}