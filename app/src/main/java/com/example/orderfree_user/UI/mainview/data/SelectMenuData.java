package com.example.orderfree_user.UI.mainview.data;

import com.google.gson.annotations.SerializedName;

public class SelectMenuData {
    @SerializedName("menuName")
    private String menuName;

    @SerializedName("category")
    private int category;

    @SerializedName("imgURL")
    private String imgURL;

    @SerializedName("price")
    private int price;

    @SerializedName("info")
    private String info;

    public String getMenuName() {
        return menuName;
    }

    public int getCategory() {
        return category;
    }

    public String getImgURL() {
        return imgURL;
    }

    public int getPrice() {
        return price;
    }

    public String getInfo() {
        return info;
    }


}
