package com.example.orderfree_user.UI.mainview.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.example.orderfree_user.R;
import com.example.orderfree_user.UI.login.activity.AccountDeleteActivity;
import com.example.orderfree_user.UI.login.activity.ChangePasswordActivity;
import com.example.orderfree_user.UI.login.activity.LoginActivity;
import com.example.orderfree_user.UI.login.activity.MainActivity;
import com.example.orderfree_user.UI.mainview.data.CheckPasswordData;
import com.example.orderfree_user.UI.mainview.data.CheckPasswordResponse;
import com.example.orderfree_user.network.RetrofitClient;
import com.example.orderfree_user.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class personInfoActivity extends AppCompatActivity {


    private TextView muserNameView;
    private Button mLogoutButton;
    private Button mWitdrawalButton;
    private Button mChangePasswordButton;
    private Button mCompleteButton;
    private TextView muserEmailView;

    private TextView mGobackView;
    private EditText mPasswordCheckView;
    private ServiceApi service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);

        mPasswordCheckView=(EditText) findViewById(R.id.info_password_tv); //원래 비밀번호 입력 tv
        mGobackView = (TextView) findViewById(R.id.goback);
        muserNameView = (TextView) findViewById(R.id.userName) ;
        mChangePasswordButton=(Button)findViewById(R.id.change_password_btn);
        mLogoutButton = (Button) findViewById(R.id.logout_btn);
        mWitdrawalButton = (Button)findViewById(R.id.witdrawal_btn);
        mLogoutButton.setPaintFlags(mLogoutButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);//버튼 밑줄 긋기
        mWitdrawalButton.setPaintFlags(mWitdrawalButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);//버튼 밑줄 긋기
        mCompleteButton = (Button)findViewById(R.id.complete_btn); // 완료 버튼
        muserEmailView =(TextView)findViewById(R.id.info_email_tv); //사용자 이메일 보여주는 tv



        service = RetrofitClient.getClient().create(ServiceApi.class);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String userEmail = intent.getStringExtra("userEmail");

        if(intent.hasExtra("userName")) {
            muserNameView.setText(userName);
        }
        if(intent.hasExtra("userEmail")) {
            muserEmailView.setText(userEmail);
        }

        //뒤로 가기 버튼 눌렀을 때 전 레이아웃인 메인화면으로 전환
        mGobackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mGobackView.isClickable()) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });



        mCompleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                muserEmailView.setText(userEmail);
                finish();
            }
        });

        mWitdrawalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountDeleteActivity.class);
                startActivity(intent);
            }
        });


        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(personInfoActivity.this)
                        .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                        .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //로그아웃 확인을 눌렀을 때 자동로그인 및 저장된 정보들 모두 삭제
                                SharedPreferences mPref = getSharedPreferences("autoLoginRecord",MODE_PRIVATE);
                                SharedPreferences.Editor editor = mPref.edit();
                                editor.clear();
                                editor.commit();

                                //로그아웃 눌렀을 경우 로그인페이지로 보내고 그 전까지의 activity는 다 지워버림
                                Intent intent = new Intent(personInfoActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .show();
            }
        });
    }

    public void mOnPopupClick(View v)
    {
        Intent i = getIntent();
        String userEmail = i.getStringExtra("userEmail");
        mPasswordCheckView.setError(null);

        String password = mPasswordCheckView.getText().toString();

        boolean cancel = false;
        View focusView=null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            mPasswordCheckView.setError("비밀번호를 입력해주세요.");
            focusView = mPasswordCheckView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startChangePassword(new CheckPasswordData(userEmail, password));
        }
    }




    private void startChangePassword(CheckPasswordData data){
        service.userCheckPassowrd(data).enqueue(new Callback<CheckPasswordResponse>(){
            @Override
            public void onResponse(Call<CheckPasswordResponse> call, Response<CheckPasswordResponse> response){
                CheckPasswordResponse result = response.body();
                if(result.getCode()==200) {
                    Intent i = getIntent();
                    String userEmail = i.getStringExtra("userEmail");
                    Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                    intent.putExtra("userEmail", userEmail).putExtra("userPwd", (data.getuserPwd()));
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<CheckPasswordResponse> call, Throwable t) {
                Toast.makeText(personInfoActivity.this, "비밀번호 에러", Toast.LENGTH_SHORT).show();
                Log.e("비밀번호 에러", t.getMessage());
            }
        });
    }

    private boolean isPasswordValid(String password) {
        return ((password.length() >= 8) && (password.length()<=20));
    }
}

