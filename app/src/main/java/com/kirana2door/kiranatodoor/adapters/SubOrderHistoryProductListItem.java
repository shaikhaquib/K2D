package com.kirana2door.kiranatodoor.adapters;

import com.google.gson.annotations.SerializedName;


public class SubOrderHistoryProductListItem{

	@SerializedName("product_weight")
	private String productWeight;

	@SerializedName("product_mrp")
	private String productMrp;

	@SerializedName("product_price")
	private String productPrice;

	@SerializedName("units")
	private String units;

	@SerializedName("product_discription_2")
	private String productDiscription2;

	@SerializedName("product_discription_1")
	private String productDiscription1;

	@SerializedName("category_id")
	private String categoryId;

	@SerializedName("product_id")
	private String productId;

	@SerializedName("id")
	private String id;

	@SerializedName("mastprodid")
	private String mastprodid;

	@SerializedName("current_product_weight")
	private String currentProductWeight;

	@SerializedName("quantity")
	private String quantity;

	@SerializedName("current_product_unit")
	private String currentProductUnit;

	@SerializedName("hsn_code")
	private String hsnCode;

	@SerializedName("product_name")
	private String productName;

	@SerializedName("created_by")
	private String createdBy;

	@SerializedName("shop_id")
	private String shopId;

	@SerializedName("product_thamb_image")
	private String productThambImage;

	@SerializedName("updated_by")
	private String updatedBy;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("updated_date")
	private String updatedDate;

	@SerializedName("order_product_price")
	private String orderProductPrice;

	@SerializedName("minimum_quantity")
	private String minimumQuantity;

	@SerializedName("customer_id")
	private String customerId;

	@SerializedName("order_id")
	private String orderId;

	@SerializedName("gst_id")
	private String gstId;

	@SerializedName("status")
	private String status;

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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setMastprodid(String mastprodid){
		this.mastprodid = mastprodid;
	}

	public String getMastprodid(){
		return mastprodid;
	}

	public void setCurrentProductWeight(String currentProductWeight){
		this.currentProductWeight = currentProductWeight;
	}

	public String getCurrentProductWeight(){
		return currentProductWeight;
	}

	public void setQuantity(String quantity){
		this.quantity = quantity;
	}

	public String getQuantity(){
		return quantity;
	}

	public void setCurrentProductUnit(String currentProductUnit){
		this.currentProductUnit = currentProductUnit;
	}

	public String getCurrentProductUnit(){
		return currentProductUnit;
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

	public void setProductThambImage(String productThambImage){
		this.productThambImage = productThambImage;
	}

	public String getProductThambImage(){
		return productThambImage;
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

	public void setOrderProductPrice(String orderProductPrice){
		this.orderProductPrice = orderProductPrice;
	}

	public String getOrderProductPrice(){
		return orderProductPrice;
	}

	public void setMinimumQuantity(String minimumQuantity){
		this.minimumQuantity = minimumQuantity;
	}

	public String getMinimumQuantity(){
		return minimumQuantity;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
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
			"SubOrderHistoryProductListItem{" + 
			"product_weight = '" + productWeight + '\'' + 
			",product_mrp = '" + productMrp + '\'' + 
			",product_price = '" + productPrice + '\'' + 
			",units = '" + units + '\'' + 
			",product_discription_2 = '" + productDiscription2 + '\'' + 
			",product_discription_1 = '" + productDiscription1 + '\'' + 
			",category_id = '" + categoryId + '\'' + 
			",product_id = '" + productId + '\'' + 
			",id = '" + id + '\'' + 
			",mastprodid = '" + mastprodid + '\'' + 
			",current_product_weight = '" + currentProductWeight + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",current_product_unit = '" + currentProductUnit + '\'' + 
			",hsn_code = '" + hsnCode + '\'' + 
			",product_name = '" + productName + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",shop_id = '" + shopId + '\'' + 
			",product_thamb_image = '" + productThambImage + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",created_date = '" + createdDate + '\'' + 
			",updated_date = '" + updatedDate + '\'' + 
			",order_product_price = '" + orderProductPrice + '\'' + 
			",minimum_quantity = '" + minimumQuantity + '\'' + 
			",customer_id = '" + customerId + '\'' + 
			",order_id = '" + orderId + '\'' + 
			",gst_id = '" + gstId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}