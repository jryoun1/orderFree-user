package com.example.orderfree_user.UI.mainview.data;

import com.google.gson.annotations.SerializedName;

public class SelectMenuDataRequest {
    @SerializedName("menuName")
    private String menuName;

    @SerializedName("ownerStoreName")
    private String ownerStoreName;


    public SelectMenuDataRequest( String menuName, String ownerStoreName) {
        this.ownerStoreName = ownerStoreName;
        this.menuName =menuName;
    }
}
