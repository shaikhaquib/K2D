package com.abatechnology.kirana2door.models;

import com.google.gson.annotations.SerializedName;

public class BannerItem{

	@SerializedName("shop_id")
	private String shopId;

	@SerializedName("banner_img")
	private String bannerImg;

	@SerializedName("updated_by")
	private String updatedBy;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("updated_date")
	private String updatedDate;

	@SerializedName("created_by")
	private String createdBy;

	@SerializedName("ban_id")
	private String banId;

	@SerializedName("status")
	private String status;

	public void setShopId(String shopId){
		this.shopId = shopId;
	}

	public String getShopId(){
		return shopId;
	}

	public void setBannerImg(String bannerImg){
		this.bannerImg = bannerImg;
	}

	public String getBannerImg(){
		return bannerImg;
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

	public void setBanId(String banId){
		this.banId = banId;
	}

	public String getBanId(){
		return banId;
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
			"BannerItem{" + 
			"shop_id = '" + shopId + '\'' + 
			",banner_img = '" + bannerImg + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",updated_date = '" + updatedDate + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",ban_id = '" + banId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}