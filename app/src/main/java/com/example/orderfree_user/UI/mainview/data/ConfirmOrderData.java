package com.example.orderfree_user.UI.mainview.data;

import com.google.gson.annotations.SerializedName;

public class ConfirmOrderData {
    @SerializedName("menuName")
    private String menuName;

    @SerializedName("price")
    private int price;

    @SerializedName("count")
    private int count;

    public String getmenuName() { return menuName; }
    public int getPrice() { return price; }
    public int getCount() { return count; }

}
