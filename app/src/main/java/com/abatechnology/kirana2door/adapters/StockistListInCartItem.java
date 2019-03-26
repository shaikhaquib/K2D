package com.abatechnology.kirana2door.adapters;


import com.google.gson.annotations.SerializedName;


public class StockistListInCartItem{

	@SerializedName("shop_id")
	private String shopId;

	@SerializedName("logo_img")
	private String logoImg;

	@SerializedName("oampm")
	private String oampm;

	@SerializedName("openingtime")
	private String openingtime;

	@SerializedName("closingtime")
	private String closingtime;

	@SerializedName("item_cnt")
	private String itemCnt;

	@SerializedName("shop_name")
	private String shopName;

	@SerializedName("campm")
	private String campm;

	@SerializedName("status")
	private String status;

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

	public void setOampm(String oampm){
		this.oampm = oampm;
	}

	public String getOampm(){
		return oampm;
	}

	public void setOpeningtime(String openingtime){
		this.openingtime = openingtime;
	}

	public String getOpeningtime(){
		return openingtime;
	}

	public void setClosingtime(String closingtime){
		this.closingtime = closingtime;
	}

	public String getClosingtime(){
		return closingtime;
	}

	public void setItemCnt(String itemCnt){
		this.itemCnt = itemCnt;
	}

	public String getItemCnt(){
		return itemCnt;
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

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"StockistListInCartItem{" + 
			"shop_id = '" + shopId + '\'' + 
			",logo_img = '" + logoImg + '\'' + 
			",oampm = '" + oampm + '\'' + 
			",openingtime = '" + openingtime + '\'' + 
			",closingtime = '" + closingtime + '\'' + 
			",item_cnt = '" + itemCnt + '\'' + 
			",shop_name = '" + shopName + '\'' + 
			",campm = '" + campm + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}