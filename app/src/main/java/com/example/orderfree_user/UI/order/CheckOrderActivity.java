package com.example.orderfree_user.UI.order;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfree_user.R;
import com.example.orderfree_user.UI.order.data.CheckOrderData;
import com.example.orderfree_user.UI.order.data.CheckOrderResponse;
import com.example.orderfree_user.UI.order.data.CheckOrderResponseData;
import com.example.orderfree_user.network.RetrofitClient;
import com.example.orderfree_user.network.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOrderActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<CheckOrderResponseData> data;
    private ServiceApi service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_order);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        Button backBtn = findViewById(R.id.btn_back_check_order);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Log.e("test", "test");
        getDatas();
    }

    public void getDatas(){
        String userEmail = getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE).getString("userEmail", "err");
        service.checkOrder(new CheckOrderData(userEmail)).enqueue(new Callback<CheckOrderResponse>() {
            @Override
            public void onResponse(Call<CheckOrderResponse> call, Response<CheckOrderResponse> response) {
                Log.e("success", String.valueOf(response.code()));
                data = response.body().getResultArray();
                recyclerView = (RecyclerView)findViewById(R.id.recycler_ordercheck);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new CheckOrderAdapter(data);
                recyclerView.setAdapter(adapter);
                TextView store = findViewById(R.id.tv_checkorder_store);
                TextView date = findViewById(R.id.tv_checkorder_date);
                store.setText(response.body().getOwnerStoreName());
                Log.e("date", response.body().getOrderedDate());
                Log.e("date1", response.body().getOrderedDate().substring(10));
                Log.e("date2", response.body().getOrderedDate().substring(11, 16));
                date.setText(response.body().getOrderedDate().substring(0, 10) + "  " + response.body().getOrderedDate().substring(11, 16));
            }

            @Override
            public void onFailure(Call<CheckOrderResponse> call, Throwable t) {
                Log.e("fail", t.getMessage());
            }
        });
    }
}
