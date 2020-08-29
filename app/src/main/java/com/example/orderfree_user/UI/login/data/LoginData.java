package com.example.orderfree_user.UI.login.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("userDeviceToken")
    String userDeviceToken;

    @SerializedName("userEmail")
    String userEmail;

    @SerializedName("userPwd")
    String userPwd;

    public LoginData(String userEmail, String userPwd, String userDeviceToken){
        this.userEmail=userEmail;
        this.userPwd=userPwd;
        this.userDeviceToken = userDeviceToken;
    }
}