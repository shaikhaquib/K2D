package com.kirana2door.kiranatodoor.models;

import com.google.gson.annotations.SerializedName;

public class ProdbanItem{

	@SerializedName("shop_id")
	private String shopId;

	@SerializedName("offerban_id")
	private String offerbanId;

	@SerializedName("offpic_path")
	private String offpicPath;

	@SerializedName("updated_by")
	private String updatedBy;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("updated_date")
	private String updatedDate;

	@SerializedName("offproductid")
	private String offproductid;

	@SerializedName("created_by")
	private String createdBy;

	@SerializedName("status")
	private String status;

	public void setShopId(String shopId){
		this.shopId = shopId;
	}

	public String getShopId(){
		return shopId;
	}

	public void setOfferbanId(String offerbanId){
		this.offerbanId = offerbanId;
	}

	public String getOfferbanId(){
		return offerbanId;
	}

	public void setOffpicPath(String offpicPath){
		this.offpicPath = offpicPath;
	}

	public String getOffpicPath(){
		return offpicPath;
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

	public void setOffproductid(String offproductid){
		this.offproductid = offproductid;
	}

	public String getOffproductid(){
		return offproductid;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
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
			"ProdbanItem{" + 
			"shop_id = '" + shopId + '\'' + 
			",offerban_id = '" + offerbanId + '\'' + 
			",offpic_path = '" + offpicPath + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",updated_date = '" + updatedDate + '\'' + 
			",offproductid = '" + offproductid + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}