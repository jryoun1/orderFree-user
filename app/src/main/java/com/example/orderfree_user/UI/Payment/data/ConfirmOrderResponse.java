package com.example.orderfree_user.UI.Payment.data;

import com.google.gson.annotations.SerializedName;

public class ConfirmOrderResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
