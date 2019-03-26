package com.abatechnology.kirana2door.models;

import com.google.gson.annotations.SerializedName;

public class StockistItem{

	@SerializedName("address")
	private String address;

	@SerializedName("closingtime")
	private String closingtime;

	@SerializedName("mobile_no")
	private String mobileNo;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("shop_name")
	private String shopName;

	@SerializedName("campm")
	private String campm;

	@SerializedName("created_by")
	private String createdBy;

	@SerializedName("logo_img")
	private String logoImg;

	@SerializedName("oampm")
	private String oampm;

	@SerializedName("latlang")
	private String latlang;

	@SerializedName("openingtime")
	private String openingtime;

	@SerializedName("landline_no")
	private String landlineNo;

	@SerializedName("updated_by")
	private String updatedBy;

	@SerializedName("id")
	private String id;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("updated_date")
	private String updatedDate;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("email")
	private String email;

	@SerializedName("city_id")
	private String cityId;

	@SerializedName("status")
	private String status;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setClosingtime(String closingtime){
		this.closingtime = closingtime;
	}

	public String getClosingtime(){
		return closingtime;
	}

	public void setMobileNo(String mobileNo){
		this.mobileNo = mobileNo;
	}

	public String getMobileNo(){
		return mobileNo;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setShopName(String shopName){
		this.shopName = shopName;
	}

	public String getShopName(){
		return shopName;
	}

	public void setCampm(String campm){
		this.campm = campm;
	}

	public String getCampm(){
		return campm;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	public void setLogoImg(String logoImg){
		this.logoImg = logoImg;
	}

	public String getLogoImg(){
		return logoImg;
	}

	public void setOampm(String oampm){
		this.oampm = oampm;
	}

	public String getOampm(){
		return oampm;
	}

	public void setLatlang(String latlang){
		this.latlang = latlang;
	}

	public String getLatlang(){
		return latlang;
	}

	public void setOpeningtime(String openingtime){
		this.openingtime = openingtime;
	}

	public String getOpeningtime(){
		return openingtime;
	}

	public void setLandlineNo(String landlineNo){
		this.landlineNo = landlineNo;
	}

	public String getLandlineNo(){
		return landlineNo;
	}

	public void setUpdatedBy(String updatedBy){
		this.updatedBy = updatedBy;
	}

	public String getUpdatedBy(){
		return updatedBy;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
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

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setCityId(String cityId){
		this.cityId = cityId;
	}

	public String getCityId(){
		return cityId;
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
			"StockistItem{" + 
			"address = '" + address + '\'' + 
			",closingtime = '" + closingtime + '\'' + 
			",mobile_no = '" + mobileNo + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",shop_name = '" + shopName + '\'' + 
			",campm = '" + campm + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",logo_img = '" + logoImg + '\'' + 
			",oampm = '" + oampm + '\'' + 
			",latlang = '" + latlang + '\'' + 
			",openingtime = '" + openingtime + '\'' + 
			",landline_no = '" + landlineNo + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",id = '" + id + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",updated_date = '" + updatedDate + '\'' + 
			",first_name = '" + firstName + '\'' + 
			",email = '" + email + '\'' + 
			",city_id = '" + cityId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}