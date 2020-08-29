package com.example.orderfree_user.UI.mainview.data;

import com.google.gson.annotations.SerializedName;

public class BasketDataRequest {

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("ownerStoreName")
    private String ownerStoreName;

    @SerializedName("menuName")
    private String menuName;

    @SerializedName("price")
    private int price;

    @SerializedName("count")
    private int amount;


    public BasketDataRequest( String userEmail, String ownerStoreName, String menuName, int price, int amount) {
        this.userEmail = userEmail;
        this.ownerStoreName =ownerStoreName;
        this.menuName = menuName;
        this.price =price;
        this.amount = amount;
    }
}
