package com.example.orderfree_user.UI.mainview.data;

import com.google.gson.annotations.SerializedName;

public class MenuData {
    @SerializedName("ownerStoreName")
    private String ownerStoreName;

    @SerializedName("menuName")
    private String menuName;

    @SerializedName("category")
    private int category;

    @SerializedName("price")
    private int price;

    public String getOwnerStoreName() { return ownerStoreName; }
    public String getmenuName() { return menuName; }
    public int getmenuCategory() { return category; }
    public int getmenuPrice() { return price; }

}
