package com.example.orderfree_user.UI.login.data;

import com.google.gson.annotations.SerializedName;

public class FindPasswordData {
    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userPhoneNumber")
    private String userPhoneNumber;

    public FindPasswordData(String userEmail, String userName, String userPhoneNumber){
        this.userEmail=userEmail;
        this.userName=userName;
        this.userPhoneNumber=userPhoneNumber;
    }
}
