package com.example.orderfree_user.UI.Payment.data;

import com.google.gson.annotations.SerializedName;

public class ConfirmOrderData {
    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("ownerStoreName")
    private String ownerStoreName;

    public ConfirmOrderData(String userEmail, String ownerStoreName){
        this.userEmail = userEmail;
        this.ownerStoreName = ownerStoreName;
    }
}
