package com.abatechnology.kirana2door.adapters;


import com.google.gson.annotations.SerializedName;

public class ShopListItem{

	@SerializedName("area_name")
	private String areaName;

	@SerializedName("shipping_charges")
	private String shippingCharges;

	@SerializedName("country")
	private String country;

	@SerializedName("closingtime")
	private String closingtime;

	@SerializedName("mobile_no")
	private String mobileNo;

	@SerializedName("delivery_time")
	private String deliveryTime;

	@SerializedName("location_id")
	private String locationId;

	@SerializedName("oampm")
	private String oampm;

	@SerializedName("city_name")
	private String cityName;

	@SerializedName("state_name")
	private String stateName;

	@SerializedName("landline_no")
	private String landlineNo;

	@SerializedName("id")
	private String id;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("email")
	private String email;

	@SerializedName("pincode")
	private String pincode;

	@SerializedName("address")
	private String address;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("shop_name")
	private String shopName;

	@SerializedName("campm")
	private String campm;

	@SerializedName("created_by")
	private String createdBy;

	@SerializedName("shop_id")
	private String shopId;

	@SerializedName("logo_img")
	private String logoImg;

	@SerializedName("latlang")
	private String latlang;

	@SerializedName("openingtime")
	private String openingtime;

	@SerializedName("updated_by")
	private String updatedBy;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("updated_date")
	private String updatedDate;

	@SerializedName("city_id")
	private String cityId;

	@SerializedName("status")
	private String status;

	public void setAreaName(String areaName){
		this.areaName = areaName;
	}

	public String getAreaName(){
		return areaName;
	}

	public void setShippingCharges(String shippingCharges){
		this.shippingCharges = shippingCharges;
	}

	public String getShippingCharges(){
		return shippingCharges;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
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

	public void setDeliveryTime(String deliveryTime){
		this.deliveryTime = deliveryTime;
	}

	public String getDeliveryTime(){
		return deliveryTime;
	}

	public void setLocationId(String locationId){
		this.locationId = locationId;
	}

	public String getLocationId(){
		return locationId;
	}

	public void setOampm(String oampm){
		this.oampm = oampm;
	}

	public String getOampm(){
		return oampm;
	}

	public void setCityName(String cityName){
		this.cityName = cityName;
	}

	public String getCityName(){
		return cityName;
	}

	public void setStateName(String stateName){
		this.stateName = stateName;
	}

	public String getStateName(){
		return stateName;
	}

	public void setLandlineNo(String landlineNo){
		this.landlineNo = landlineNo;
	}

	public String getLandlineNo(){
		return landlineNo;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
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

	public void setPincode(String pincode){
		this.pincode = pincode;
	}

	public String getPincode(){
		return pincode;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
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

	public void setShopId(String shopId){
		this.shopId = shopId;
	}

	public String getShopId(){
		return shopId;
	}

	public void setLogoImg(String logoImg){
		this.logoImg = logoImg;
	}

	public String getLogoImg(){
		return logoImg;
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
			"ShopListItem{" + 
			"area_name = '" + areaName + '\'' + 
			",shipping_charges = '" + shippingCharges + '\'' + 
			",country = '" + country + '\'' + 
			",closingtime = '" + closingtime + '\'' + 
			",mobile_no = '" + mobileNo + '\'' + 
			",delivery_time = '" + deliveryTime + '\'' + 
			",location_id = '" + locationId + '\'' + 
			",oampm = '" + oampm + '\'' + 
			",city_name = '" + cityName + '\'' + 
			",state_name = '" + stateName + '\'' + 
			",landline_no = '" + landlineNo + '\'' + 
			",id = '" + id + '\'' + 
			",first_name = '" + firstName + '\'' + 
			",email = '" + email + '\'' + 
			",pincode = '" + pincode + '\'' + 
			",address = '" + address + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",shop_name = '" + shopName + '\'' + 
			",campm = '" + campm + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",shop_id = '" + shopId + '\'' + 
			",logo_img = '" + logoImg + '\'' + 
			",latlang = '" + latlang + '\'' + 
			",openingtime = '" + openingtime + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",updated_date = '" + updatedDate + '\'' + 
			",city_id = '" + cityId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}