package com.kirana2door.kiranatodoor.models;

import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.List;

public class MainPageResponse {
    private boolean error;
    private String message;
    private JSONArray stockist;
    private JSONArray banner;
    private JSONArray catban;
    private JSONArray prodban;
    private JSONArray cat;

    public MainPageResponse(boolean error, String message, JSONArray stockist, JSONArray banner, JSONArray catban, JSONArray prodban, JSONArray cat) {
        this.error = error;
        this.message = message;
        this.stockist = stockist;
        this.banner = banner;
        this.catban = catban;
        this.prodban = prodban;
        this.cat = cat;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public JSONArray getStockist() {
        return stockist;
    }

    public JSONArray getBanner() {
        return banner;
    }

    public JSONArray getCatban() {
        return catban;
    }

    public JSONArray getProdban() {
        return prodban;
    }

    public JSONArray getCat() {
        return cat;
    }

    /*public MainPageResponse(boolean error, String message, Stockist stockist, List<BannerType> banner, List<CatBann> catban, List<ProdBann> prodban, List<CatList> cat) {
        this.error = error;
        this.message = message;
        this.stockist = stockist;
        this.banner = banner;
        this.catban = catban;
        this.prodban = prodban;
        this.cat = cat;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Stockist getStockist() {
        return stockist;
    }

    public List<BannerType> getBanner() {
        return banner;
    }

    public List<CatBann> getCatban() {
        return catban;
    }

    public List<ProdBann> getProdban() {
        return prodban;
    }

    public List<CatList> getCat() {
        return cat;
    }*/
}
