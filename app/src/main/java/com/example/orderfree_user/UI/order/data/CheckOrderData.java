package com.example.orderfree_user.UI.order.data;

import com.google.gson.annotations.SerializedName;

public class CheckOrderData {
    @SerializedName("userEmail")
    private String userEmail;

    public CheckOrderData(String userEmail){
        this.userEmail = userEmail;
    }
}
