package com.example.orderfree_user.UI.mainview.data;

import com.google.gson.annotations.SerializedName;

public class DeleteShoppingListRequest {

    @SerializedName("userEmail")
    private String userEmail;

    @SerializedName("menuName")
    private String menuName;

    public DeleteShoppingListRequest(String userEmail, String menuName) {
        this.userEmail = userEmail;
        this.menuName = menuName;
    }
}
