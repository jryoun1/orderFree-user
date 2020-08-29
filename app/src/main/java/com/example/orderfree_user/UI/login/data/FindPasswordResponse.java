package com.example.orderfree_user.UI.login.data;

import com.google.gson.annotations.SerializedName;

public class FindPasswordResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("token")
    private String token;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getToken() {
        return token;
    }

}
