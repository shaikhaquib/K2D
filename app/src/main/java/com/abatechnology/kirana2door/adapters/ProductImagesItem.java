package com.abatechnology.kirana2door.adapters;


import com.google.gson.annotations.SerializedName;


public class ProductImagesItem{

	@SerializedName("product_id")
	private String productId;

	@SerializedName("product_img_id")
	private String productImgId;

	@SerializedName("product_img")
	private String productImg;

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setProductImgId(String productImgId){
		this.productImgId = productImgId;
	}

	public String getProductImgId(){
		return productImgId;
	}

	public void setProductImg(String productImg){
		this.productImg = productImg;
	}

	public String getProductImg(){
		return productImg;
	}

	@Override
 	public String toString(){
		return 
			"ProductImagesItem{" + 
			"product_id = '" + productId + '\'' + 
			",product_img_id = '" + productImgId + '\'' + 
			",product_img = '" + productImg + '\'' + 
			"}";
		}
}