package com.abatechnology.kirana2door.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MainResponse {

	@SerializedName("catban")
	private List<CatbanItem> catban;

	@SerializedName("prodban")
	private List<ProdbanItem> prodban;

	@SerializedName("cat")
	private List<CatItem> cat;

	@SerializedName("banner")
	private List<BannerItem> banner;

	@SerializedName("error")
	private boolean error;

	@SerializedName("stockist")
	private List<StockistItem> stockist;

	@SerializedName("errormsg")
	private String errormsg;

	public void setCatban(List<CatbanItem> catban){
		this.catban = catban;
	}

	public List<CatbanItem> getCatban(){
		return catban;
	}

	public void setProdban(List<ProdbanItem> prodban){
		this.prodban = prodban;
	}

	public List<ProdbanItem> getProdban(){
		return prodban;
	}

	public void setCat(List<CatItem> cat){
		this.cat = cat;
	}

	public List<CatItem> getCat(){
		return cat;
	}

	public void setBanner(List<BannerItem> banner){
		this.banner = banner;
	}

	public List<BannerItem> getBanner(){
		return banner;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setStockist(List<StockistItem> stockist){
		this.stockist = stockist;
	}

	public List<StockistItem> getStockist(){
		return stockist;
	}

	public void setErrormsg(String errormsg){
		this.errormsg = errormsg;
	}

	public String getErrormsg(){
		return errormsg;
	}

	@Override
 	public String toString(){
		return 
			"MainResponse{" +
			"catban = '" + catban + '\'' + 
			",prodban = '" + prodban + '\'' + 
			",cat = '" + cat + '\'' + 
			",banner = '" + banner + '\'' + 
			",error = '" + error + '\'' + 
			",stockist = '" + stockist + '\'' + 
			",errormsg = '" + errormsg + '\'' + 
			"}";
		}
}