package com.kirana2door.kiranatodoor.adapters;


import com.google.gson.annotations.SerializedName;

public class ProductListItem{

	@SerializedName("mastprodid")
	private String mastprodid;

	@SerializedName("product_weight")
	private String productWeight;

	@SerializedName("product_mrp")
	private String productMrp;

	@SerializedName("product_price")
	private String productPrice;

	@SerializedName("units")
	private String units;

	@SerializedName("hsn_code")
	private String hsnCode;

	@SerializedName("product_name")
	private String productName;

	@SerializedName("created_by")
	private String createdBy;

	@SerializedName("shop_id")
	private String shopId;

	@SerializedName("product_discription_2")
	private String productDiscription2;

	@SerializedName("product_discription_1")
	private String productDiscription1;

	@SerializedName("product_thamb_image")
	private String productThambImage;

	@SerializedName("category_id")
	private String categoryId;

	@SerializedName("product_id")
	private String productId;

	@SerializedName("updated_by")
	private String updatedBy;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("updated_date")
	private String updatedDate;

	@SerializedName("minimum_quantity")
	private String minimumQuantity;

	@SerializedName("gst_id")
	private String gstId;

	@SerializedName("status")
	private String status;

	public String ppri;
	public String finalqty;
	public int minteger;
	public int minimum;

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMinteger() {
		return minteger;
	}

	public void setMinteger(int minteger) {
		this.minteger = minteger;
	}

	public String getFinalqty() {
		return finalqty;
	}

	public void setFinalqty(String finalqty) {
		this.finalqty = finalqty;
	}

	public String getPpri() {
		return ppri;
	}

	public void setPpri(String ppri) {
		this.ppri = ppri;
	}

	public void setMastprodid(String mastprodid){
		this.mastprodid = mastprodid;
	}

	public String getMastprodid(){
		return mastprodid;
	}

	public void setProductWeight(String productWeight){
		this.productWeight = productWeight;
	}

	public String getProductWeight(){
		return productWeight;
	}

	public void setProductMrp(String productMrp){
		this.productMrp = productMrp;
	}

	public String getProductMrp(){
		return productMrp;
	}

	public void setProductPrice(String productPrice){
		this.productPrice = productPrice;
	}

	public String getProductPrice(){
		return productPrice;
	}

	public void setUnits(String units){
		this.units = units;
	}

	public String getUnits(){
		return units;
	}

	public void setHsnCode(String hsnCode){
		this.hsnCode = hsnCode;
	}

	public String getHsnCode(){
		return hsnCode;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
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

	public void setProductDiscription2(String productDiscription2){
		this.productDiscription2 = productDiscription2;
	}

	public String getProductDiscription2(){
		return productDiscription2;
	}

	public void setProductDiscription1(String productDiscription1){
		this.productDiscription1 = productDiscription1;
	}

	public String getProductDiscription1(){
		return productDiscription1;
	}

	public void setProductThambImage(String productThambImage){
		this.productThambImage = productThambImage;
	}

	public String getProductThambImage(){
		return productThambImage;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
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

	public void setMinimumQuantity(String minimumQuantity){
		this.minimumQuantity = minimumQuantity;
	}

	public String getMinimumQuantity(){
		return minimumQuantity;
	}

	public void setGstId(String gstId){
		this.gstId = gstId;
	}

	public String getGstId(){
		return gstId;
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
			"ProductListItem{" + 
			"mastprodid = '" + mastprodid + '\'' + 
			",product_weight = '" + productWeight + '\'' + 
			",product_mrp = '" + productMrp + '\'' + 
			",product_price = '" + productPrice + '\'' + 
			",units = '" + units + '\'' + 
			",hsn_code = '" + hsnCode + '\'' + 
			",product_name = '" + productName + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",shop_id = '" + shopId + '\'' + 
			",product_discription_2 = '" + productDiscription2 + '\'' + 
			",product_discription_1 = '" + productDiscription1 + '\'' + 
			",product_thamb_image = '" + productThambImage + '\'' + 
			",category_id = '" + categoryId + '\'' + 
			",product_id = '" + productId + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",updated_date = '" + updatedDate + '\'' + 
			",minimum_quantity = '" + minimumQuantity + '\'' + 
			",gst_id = '" + gstId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}