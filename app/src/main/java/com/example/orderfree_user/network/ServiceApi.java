package com.example.orderfree_user.network;

import com.example.orderfree_user.UI.login.data.FindEmailData;
import com.example.orderfree_user.UI.login.data.FindEmailResponse;
import com.example.orderfree_user.UI.login.data.FindPasswordData;
import com.example.orderfree_user.UI.login.data.FindPasswordResponse;
import com.example.orderfree_user.UI.login.data.JoinAvailable;
import com.example.orderfree_user.UI.login.data.JoinAvailableResponse;
import com.example.orderfree_user.UI.login.data.JoinData;
import com.example.orderfree_user.UI.login.data.JoinResponse;
import com.example.orderfree_user.UI.login.data.LoginData;
import com.example.orderfree_user.UI.login.data.LoginResponse;
import com.example.orderfree_user.UI.mainview.data.BasketDataRequest;
import com.example.orderfree_user.UI.mainview.data.BasketDataResponse;
import com.example.orderfree_user.UI.mainview.data.ConfirmOrderDataRequest;
import com.example.orderfree_user.UI.mainview.data.ConfirmOrderDataResponse;
import com.example.orderfree_user.UI.mainview.data.DeleteShoppingListRequest;
import com.example.orderfree_user.UI.mainview.data.DeleteShoppingListResponse;
import com.example.orderfree_user.UI.mainview.data.MenudataRequest;
import com.example.orderfree_user.UI.mainview.data.MenudataResponse;
import com.example.orderfree_user.UI.mainview.data.SelectMenuDataRequest;
import com.example.orderfree_user.UI.mainview.data.SelectMenuDataResponse;
import com.example.orderfree_user.UI.order.data.CheckOrderData;
import com.example.orderfree_user.UI.order.data.CheckOrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @POST("/user/join/emailcheck")
    Call<JoinAvailableResponse> userJoinAvailable(@Body JoinAvailable data);

    @POST("/user/login/emailfind")
    Call<FindEmailResponse> userFindEmail(@Body FindEmailData data);

    @POST("/user/login/pwdfind")
    Call<FindPasswordResponse> userFindPassword(@Body FindPasswordData data);

    @POST("/usermain/qrcode/storeinfo")
    Call<MenudataResponse> userMenuData(@Body MenudataRequest data);

    @POST("/usermain/store/menuSpecification")
    Call<SelectMenuDataResponse> slectMenuData(@Body SelectMenuDataRequest data);

    @POST("/usermain/store/addshoppingList")
    Call<BasketDataResponse> addShoppingList(@Body BasketDataRequest data);

    @POST("/usermain/store/shoppingList")
    Call<ConfirmOrderDataResponse> confirmOrder(@Body ConfirmOrderDataRequest data);

    @POST("/usermain/store/shoppingListDelete")
    Call<DeleteShoppingListResponse> shoppingListDelete(@Body DeleteShoppingListRequest data);

    @POST("/usermain/ordercheck")
    Call<CheckOrderResponse> checkOrder(@Body CheckOrderData data);
}