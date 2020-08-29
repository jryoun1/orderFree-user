package com.example.orderfree_user.UI.order.data;

import com.google.gson.annotations.SerializedName;

public class CheckOrderResponseData {
    @SerializedName("menuName")
    private String menuName;

    @SerializedName("menuCount")
    private int menuCount;

    public String getMenuName() {
        return menuName;
    }

    public int getMenuCount() {
        return menuCount;
    }
}
