package com.example.orderfree_user.UI.mainview.data;

import com.google.gson.annotations.SerializedName;
import org.json.JSONException;
import java.util.List;

public class MenudataResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("resultArray")
    private List<MenuData> result_Array;

    public MenudataResponse(List<MenuData> result_array) throws JSONException {
        result_Array = result_array;
    }

    public int getCode() { return code; }
    public List getObject() { return result_Array; }
}
