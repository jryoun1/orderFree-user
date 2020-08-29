package com.example.orderfree_user.UI.mainview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfree_user.R;
import com.example.orderfree_user.network.RetrofitClient;
import com.example.orderfree_user.network.ServiceApi;
import com.example.orderfree_user.UI.mainview.data.DeleteShoppingListRequest;
import com.example.orderfree_user.UI.mainview.data.DeleteShoppingListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteShoppingLIstPopupActivity extends AppCompatActivity {

    private ServiceApi service;
    Button confirmButton;
    Button cancelButton;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.deleteshoppinglist);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        confirmButton = (Button)findViewById(R.id.confirm_button);
        cancelButton = (Button)findViewById(R.id.cancel_button);

        Intent intent = getIntent();
        String userEmail = intent.getStringExtra("userEmail");
        String ownerStoreName = intent.getStringExtra("storeName");


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(confirmButton.isClickable()){
                    requestDelete(new DeleteShoppingListRequest(userEmail,ownerStoreName));
                    Intent intent = new Intent(getApplicationContext(), ConfirmOrderActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cancelButton.isClickable()){
                    Intent intent = new Intent(getApplicationContext(), ConfirmOrderActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

        private void requestDelete(DeleteShoppingListRequest data){
        service.shoppingListDelete(data).enqueue(new Callback<DeleteShoppingListResponse>() {
            @Override
            public void onResponse(Call<DeleteShoppingListResponse> call, Response<DeleteShoppingListResponse> response) {
                if(response.body().getCode() == 200){
                    Toast.makeText(DeleteShoppingLIstPopupActivity.this, "삭제 성공", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ConfirmOrderActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<DeleteShoppingListResponse> call, Throwable t) {
                Toast.makeText(DeleteShoppingLIstPopupActivity.this, "장바구니 데이터 에러", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ConfirmOrderActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
