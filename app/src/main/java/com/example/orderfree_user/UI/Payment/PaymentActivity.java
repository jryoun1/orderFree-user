package com.example.orderfree_user.UI.Payment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfree_user.R;
import com.example.orderfree_user.UI.Payment.data.ConfirmOrderData;
import com.example.orderfree_user.UI.Payment.data.ConfirmOrderResponse;
import com.example.orderfree_user.UI.order.CheckOrderAdapter;
import com.example.orderfree_user.UI.order.OrderCompleteActivity;
import com.example.orderfree_user.UI.order.data.CheckOrderData;
import com.example.orderfree_user.UI.order.data.CheckOrderResponse;
import com.example.orderfree_user.network.RetrofitClient;
import com.example.orderfree_user.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {
    private ServiceApi service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        Button btn = findViewById(R.id.btn_done_payment);
        TextView price = findViewById(R.id.payment_price);
        price.setText(String.valueOf(getIntent().getIntExtra("price", 0)));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE).getString("userEmail", "err");
                service.confirmOrder(new ConfirmOrderData(userEmail, getIntent().getStringExtra("store"))).enqueue(new Callback<ConfirmOrderResponse>() {
                    @Override
                    public void onResponse(Call<ConfirmOrderResponse> call, Response<ConfirmOrderResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<ConfirmOrderResponse> call, Throwable t) {

                    }
                });
                Intent intent = new Intent(getApplicationContext(), OrderCompleteActivity.class);
                startActivity(intent);
            }
        });
    }
}
