package com.example.orderfree_user.UI.mainview.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.orderfree_user.R;
import com.example.orderfree_user.UI.login.activity.MainActivity;
import com.example.orderfree_user.network.RetrofitClient;
import com.example.orderfree_user.network.ServiceApi;
import com.example.orderfree_user.UI.mainview.data.BasketDataRequest;
import com.example.orderfree_user.UI.mainview.data.BasketDataResponse;

import com.example.orderfree_user.UI.mainview.data.SelectMenuData;
import com.example.orderfree_user.UI.mainview.data.SelectMenuDataRequest;
import com.example.orderfree_user.UI.mainview.data.SelectMenuDataResponse;


import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SelectMenuActivity extends AppCompatActivity {

    private ServiceApi service;
    private TextView textViewMenuName;
    private TextView textViewStoreName;
    private TextView textViewMenuInfo;
    private ImageView imageViewMenuImage;
    private TextView menuPrice;
    private TextView menuCount;
    private Button plusButton;
    private Button minusButton;
    private Button addButton;
    private SelectMenuData menuDetail;
    private TextView mGobackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectmenu);
        textViewStoreName = (TextView)findViewById(R.id.textview_storename);
        minusButton = (Button)findViewById(R.id.minus_button);
        plusButton = (Button)findViewById(R.id.plus_button);
        addButton = (Button)findViewById(R.id.add_btn);
        menuCount = (TextView)findViewById(R.id.menu_count);
        menuCount.setText("1");
        mGobackView=(TextView)findViewById(R.id.goback);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        Intent intent = getIntent();
        String menuName = intent.getStringExtra("menuName");
        String storeName = intent.getStringExtra("storeName");

        textViewStoreName.setText(storeName);

        //메뉴이름, 메뉴 설명, 메뉴 사진 , 메뉴 가격  받아오기
        getData(new SelectMenuDataRequest(menuName, storeName));

        mGobackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGobackView.isClickable()){
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                decrement();
            }
        }) ;

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                increment();
            }
        }) ;

        SharedPreferences mPrefs = getSharedPreferences("autoLoginRecord", Context.MODE_PRIVATE);
        String userEmail = mPrefs.getString("userEmail","");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                addBasket(new BasketDataRequest(
                        userEmail,textViewStoreName.getText().toString(),
                        textViewMenuName.getText().toString(),
                        Integer.parseInt((String) menuPrice.getText()),
                        Integer.parseInt((String)menuCount.getText())));
                // String userEmail, String ownerStoreName, String menuName, int price, int amount
            }
        }) ;
    }


    public void increment(){
        int currentNos = Integer.parseInt(menuCount.getText().toString()) ;
        if(currentNos < 99) {
            menuCount.setText(String.valueOf(++currentNos));
            menuCount.getText().toString();
        }
        else
            Toast.makeText(getApplicationContext(), "수량을 확인해 주세요", Toast.LENGTH_LONG).show();
    }

    public void decrement() {
        int currentNos = Integer.parseInt(menuCount.getText().toString());
        if (currentNos > 1 ) {
            menuCount.setText(String.valueOf(--currentNos));
            menuCount.getText().toString();
        }
        else
            Toast.makeText(getApplicationContext(), "수량을 확인해 주세요", Toast.LENGTH_LONG).show();
    }

    private void getData(SelectMenuDataRequest data) {
        service.slectMenuData(data).enqueue(new Callback<SelectMenuDataResponse>() {
            @Override
            public void onResponse(Call<SelectMenuDataResponse> call, Response<SelectMenuDataResponse> response) {
                //Uri uri = Uri.parse(result.getImgURL());
                menuDetail = response.body().getMenuData();
                if(menuDetail == null){
                    Log.e("response data","null");
                }
                    bindMenuInfo();
                    }

            @Override
            public void onFailure(Call<SelectMenuDataResponse> call, Throwable t) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
                Log.e("에러", t.getMessage());
            }
        });
    }

    public void bindMenuInfo(){
        textViewMenuName = (TextView)findViewById(R.id.textView_menuName);
        ImageView image = findViewById(R.id.imageView_menuPicture);
        textViewMenuInfo = (TextView)findViewById(R.id.textView_menuInfo);
        menuPrice = (TextView)findViewById(R.id.textView_menuprice);

        textViewMenuName.setText(menuDetail.getMenuName());
        Glide.with(this).load("https://valueup.s3.ap-northeast-2.amazonaws.com/"+menuDetail.getImgURL()).into(image);
        menuPrice.setText(String.valueOf(menuDetail.getPrice()));
        textViewMenuInfo.setText(menuDetail.getInfo());
    }

    private void addBasket(BasketDataRequest data) {
        service.addShoppingList(data).enqueue(new Callback<BasketDataResponse>() {
            @Override
            public void onResponse(Call<BasketDataResponse> call, Response<BasketDataResponse> response) {
                BasketDataResponse result = response.body();
                if (result.getCode()==200) {
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<BasketDataResponse> call, Throwable t) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
                Log.e("에러", t.getMessage());
            }
        });
    }
}
