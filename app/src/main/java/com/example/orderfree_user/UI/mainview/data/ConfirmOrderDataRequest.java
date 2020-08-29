package com.example.orderfree_user.UI.mainview.data;

import com.google.gson.annotations.SerializedName;

public class ConfirmOrderDataRequest {

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("ownerStoreName")
    private String ownerStoreName;

    public ConfirmOrderDataRequest(String userEmail, String ownerStoreName) {
        this.userEmail = userEmail;
        this.ownerStoreName = ownerStoreName;
    }
}
