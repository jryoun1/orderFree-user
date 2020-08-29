package com.example.orderfree_user.UI.login.data;

import com.google.gson.annotations.SerializedName;

public class JoinData {

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("userPwd")
    private String userPwd;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userPhoneNumber")
    private String userPhoneNumber;


    public JoinData(String userEmail, String userPwd,
                    String userName, String userPhoneNumber) {
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userPhoneNumber=userPhoneNumber;
    }
}