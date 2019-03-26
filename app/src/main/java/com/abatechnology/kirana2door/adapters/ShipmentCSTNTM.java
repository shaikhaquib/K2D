package com.abatechnology.kirana2door.adapters;


import com.google.gson.annotations.SerializedName;


public class ShipmentCSTNTM{

	@SerializedName("shipping_charges")
	private String shippingCharges;

	@SerializedName("delivery_time")
	private String deliveryTime;

	public void setShippingCharges(String shippingCharges){
		this.shippingCharges = shippingCharges;
	}

	public String getShippingCharges(){
		return shippingCharges;
	}

	public void setDeliveryTime(String deliveryTime){
		this.deliveryTime = deliveryTime;
	}

	public String getDeliveryTime(){
		return deliveryTime;
	}

	@Override
 	public String toString(){
		return 
			"ShipmentCSTNTM{" + 
			"shipping_charges = '" + shippingCharges + '\'' + 
			",delivery_time = '" + deliveryTime + '\'' + 
			"}";
		}
}