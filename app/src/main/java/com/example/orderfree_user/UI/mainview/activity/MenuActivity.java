package com.example.orderfree_user.UI.mainview.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfree_user.R;
import com.example.orderfree_user.UI.login.activity.MainActivity;
import com.example.orderfree_user.network.RetrofitClient;
import com.example.orderfree_user.network.ServiceApi;
import com.example.orderfree_user.UI.mainview.data.MenuData;
import com.example.orderfree_user.UI.mainview.data.MenudataRequest;
import com.example.orderfree_user.UI.mainview.data.MenudataResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    public TextView mGobackView;
    public TextView ownerStoreName;
    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<MenuData> list = new ArrayList<>();
    private ServiceApi service;
    private TextView Category0;
    private TextView Category1;
    private TextView Category2;
    private TextView Category3;
    private Button basketButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ownerStoreName=(TextView) findViewById(R.id.textview_storename);
        mGobackView=(TextView)findViewById(R.id.goback);
        basketButton=(Button)findViewById(R.id.basket_button);
        Category0=(TextView)findViewById(R.id.category0);
        Category1=(TextView)findViewById(R.id.category1);
        Category2=(TextView)findViewById(R.id.category2);
        Category3=(TextView)findViewById(R.id.category3);
        Category0.setText("조식뷔페");
        Category1.setText("A코너");
        Category2.setText("B코너");
        Category3.setText("푸드코트");
        //listview =(ScrollView)findViewById(R.id.listview);
        SharedPreferences mPref = getSharedPreferences("qrCodeRecord", MODE_PRIVATE);
        String qrcodeParsing =mPref.getString("qrcodeParsing","");
        service = RetrofitClient.getClient().create(ServiceApi.class);

        //뒤로가기 누르면 메인화면
        mGobackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGobackView.isClickable()){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        basketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGobackView.isClickable()){
                    Intent intent = new Intent(getApplicationContext(),ConfirmOrderActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        Category0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGobackView.isClickable()){
                    getData(new MenudataRequest(qrcodeParsing),0);
                }
            }
        });
        Category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGobackView.isClickable()){
                    getData(new MenudataRequest(qrcodeParsing),1);
                }
            }
        });
        Category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGobackView.isClickable()){
                    getData(new MenudataRequest(qrcodeParsing),2);
                }
            }
        });
        Category3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGobackView.isClickable()){
                    getData(new MenudataRequest(qrcodeParsing),3);
                }
            }
        });
        //getData(new MenudataRequest(qrcodeParsing),0);
    }

    private void getData(MenudataRequest data,int category) {
        service.userMenuData(data).enqueue(new Callback<MenudataResponse>() {
            @Override
            public void onResponse(Call<MenudataResponse> call, Response<MenudataResponse> response) {
                MenudataResponse result = response.body();
                List<MenuData> list=new ArrayList<>();
                if (result.getCode()==200) {
                    list.clear();
                    list = result.getObject();
                    List<MenuData> tmpList=new ArrayList<>();
                    for(int i=0;i<list.size();i++)
                    {
                        if(list.get(i).getmenuCategory()==category){
                            tmpList.add(list.get(i));
                        }
                    }
                    SharedPreferences mPref = getSharedPreferences("qrCodeRecord",MODE_PRIVATE);
                    SharedPreferences.Editor editor = mPref.edit();
                    editor.putString("ownerStoreName",list.get(0).getOwnerStoreName());
                    editor.commit();
                    ownerStoreName.setText(list.get(0).getOwnerStoreName());
                    recyclerView = (RecyclerView)findViewById(R.id.menu_recyclerView); //아이디 연결
                    recyclerView.setHasFixedSize(true); // 리사이클뷰 기존성능 강화
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    adapter = new MenuAdapter(tmpList);
                    List<MenuData> finalList = tmpList;
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MenudataResponse> call, Throwable t) {
                Toast.makeText(MenuActivity.this, "판매 데이터 에러", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                Log.e("판매 데이터 에러", t.getMessage());
            }
        });
    }
}
