package com.kirana2door.kiranatodoor.models;

import com.google.gson.annotations.SerializedName;

public class CatItem{

	@SerializedName("shop_id")
	private String shopId;

	@SerializedName("category_name")
	private String categoryName;

	@SerializedName("category_id")
	private String categoryId;

	@SerializedName("updated_by")
	private String updatedBy;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("updated_date")
	private String updatedDate;

	@SerializedName("created_by")
	private String createdBy;

	@SerializedName("category_img")
	private String categoryImg;

	@SerializedName("status")
	private String status;

	@SerializedName("cnt")
	private String cnt;

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	public void setShopId(String shopId){
		this.shopId = shopId;
	}

	public String getShopId(){
		return shopId;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setUpdatedBy(String updatedBy){
		this.updatedBy = updatedBy;
	}

	public String getUpdatedBy(){
		return updatedBy;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setUpdatedDate(String updatedDate){
		this.updatedDate = updatedDate;
	}

	public String getUpdatedDate(){
		return updatedDate;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	public void setCategoryImg(String categoryImg){
		this.categoryImg = categoryImg;
	}

	public String getCategoryImg(){
		return categoryImg;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"CatItem{" + 
			"shop_id = '" + shopId + '\'' + 
			",category_name = '" + categoryName + '\'' + 
			",category_id = '" + categoryId + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",updated_date = '" + updatedDate + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",category_img = '" + categoryImg + '\'' + 
			",status = '" + status + '\'' + 
			",cnt = '" + cnt + '\'' +
			"}";
		}
}