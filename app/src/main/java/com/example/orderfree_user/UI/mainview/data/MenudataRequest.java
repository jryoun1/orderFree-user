package com.example.orderfree_user.UI.mainview.data;

import com.google.gson.annotations.SerializedName;

public class MenudataRequest {
    @SerializedName("qrcodeParsing")
    private String qrcodeParsing;


    public MenudataRequest(String qrcodeParsing) {
        this.qrcodeParsing = qrcodeParsing;
    }
}
