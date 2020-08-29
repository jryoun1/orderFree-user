package com.example.orderfree_user.UI.mainview.data;

import com.google.gson.annotations.SerializedName;


public class SelectMenuDataResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("resultMenuSpecification")
    private SelectMenuData resultObject;

    public int getCode() { return code; }
    public SelectMenuData getMenuData() { return resultObject; }

}
