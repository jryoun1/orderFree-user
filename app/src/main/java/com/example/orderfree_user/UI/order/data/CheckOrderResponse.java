package com.example.orderfree_user.UI.order.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckOrderResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("ownerStoreName")
    private String ownerStoreName;

    @SerializedName("orderedDate")
    private String orderedDate;


    @SerializedName("resultArray")
    private List<CheckOrderResponseData> resultArray;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getOwnerStoreName() {
        return ownerStoreName;
    }

    public String getOrderedDate() {
        return orderedDate;
    }

    public List<CheckOrderResponseData> getResultArray() {
        return resultArray;
    }
}
