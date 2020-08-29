package com.example.orderfree_user.UI.mainview.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.orderfree_user.R;
import com.example.orderfree_user.UI.Payment.PaymentActivity;
import com.example.orderfree_user.network.RetrofitClient;
import com.example.orderfree_user.network.ServiceApi;
import com.example.orderfree_user.UI.mainview.data.ConfirmOrderData;
import com.example.orderfree_user.UI.mainview.data.ConfirmOrderDataRequest;
import com.example.orderfree_user.UI.mainview.data.ConfirmOrderDataResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ConfirmOrderActivity extends AppCompatActivity {
    private ServiceApi service;
    private TextView storeName;
    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView menuCount;
    private TextView menuPrice;
    private TextView goBack;
    private Button confirmButton;
    List<ConfirmOrderData> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmorder);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        SharedPreferences mPrefs = getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE);
        String userEmail = mPrefs.getString("userEmail","");
        SharedPreferences mPref = getSharedPreferences("qrCodeRecord",MODE_PRIVATE);
        String ownerStoreName = mPref.getString("ownerStoreName","");

        storeName = (TextView)findViewById(R.id.textView_StoreName);
        menuCount = (TextView)findViewById(R.id.menu_count);
        menuPrice = (TextView)findViewById(R.id.menu_price);
        goBack = (TextView)findViewById(R.id.goback);
        confirmButton=(Button)findViewById(R.id.order_confirm_button);

        storeName.setText(ownerStoreName);

        //메뉴이름, 메뉴 설명, 메뉴 사진 , 메뉴 가격  받아오기
        getBasketData(new ConfirmOrderDataRequest(userEmail,ownerStoreName));

        //뒤로가기 누르면 메뉴화면
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(goBack.isClickable()){
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                intent.putExtra("store", ownerStoreName);
                intent.putExtra("price", getItemTotalPrice(list));
                startActivity(intent);
            }
        });
    }


    private void getBasketData(ConfirmOrderDataRequest data){
        service.confirmOrder(data).enqueue(new Callback<ConfirmOrderDataResponse>() {
            @Override
            public void onResponse(Call<ConfirmOrderDataResponse> call, Response<ConfirmOrderDataResponse> response) {
                ConfirmOrderDataResponse result = response.body();
                if (result.getCode()==200) {
                    list.clear();
                    list = result.getObject();
                    recyclerView = (RecyclerView)findViewById(R.id.menu_recyclerView); //아이디 연결
                    recyclerView.setHasFixedSize(true); // 리사이클뷰 기존성능 강화
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    adapter = new ConfirmOrderAdapter(list);
                    recyclerView.setAdapter(adapter);
//                    (ConfirmOrderAdapter)adapter.setOnItemClickListener(new ConfirmOrderAdapter.OnItemClickListener(){
//                        @Override
//                        public void onItemClick(View v, int pos) {
//
//                        }
//                    });


                    menuPrice.setText(Integer.toString(getItemTotalPrice(list)));
                    menuCount.setText(Integer.toString(getItemTotalCount(list)));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ConfirmOrderDataResponse> call, Throwable t) {
                Toast.makeText(ConfirmOrderActivity.this, "장바구니 데이터 에러", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
                Log.e("장바구니 데이터 에러", t.getMessage());
            }
        });
    }

    public int getItemTotalPrice(List<ConfirmOrderData> list){
        int sum=0;
        if(list!=null) {
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i).getPrice();
            }
        } else sum=0;
        return sum;
    }
    public int getItemTotalCount(List<ConfirmOrderData> list){
        int sum=0;
        if(list!=null) {
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i).getCount();
            }
        } else sum=0;
        return sum;
    }


}
