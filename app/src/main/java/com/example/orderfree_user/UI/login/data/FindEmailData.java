package com.example.orderfree_user.UI.login.data;

        import com.google.gson.annotations.SerializedName;

public class FindEmailData {
    @SerializedName("userName")
    private String userName;

    @SerializedName("userPhoneNumber")
    private String userPhoneNumber;

    public FindEmailData(String userName, String userPhoneNumber){
        this.userName=userName;
        this.userPhoneNumber=userPhoneNumber;
    }
}
