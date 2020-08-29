package com.example.orderfree_user.UI.mainview.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfree_user.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;


public class QRScannerActivity extends AppCompatActivity {
    //view Objects
    private Button buttonScan;

    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanqrcode);

        buttonScan = (Button) findViewById(R.id.buttonScan);

        qrScan = new IntentIntegrator(this);

        //button onClick
        buttonScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scan option
                qrScan.setPrompt("Scanning...");
                //qrScan.setOrientationLocked(false);
                qrScan.initiateScan();
            }
        });
    }

    //scan 결과
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(QRScannerActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                String divisionCode[]=result.getContents().split("/");
                //qrcode 결과가 있으면
                Toast.makeText(QRScannerActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();
                    //data를 json으로 변환
                try {
                    JSONObject obj = new JSONObject(result.getContents());
                } catch (JSONException e) {
                    Intent intent = new Intent(getApplicationContext(), com.example.orderfree_user.UI.mainview.activity.MenuActivity.class );
                    e.printStackTrace();
                    Log.v("태그",divisionCode[divisionCode.length-1]);
                    //Parsing한 QRcode의 sotore ID를 menuActivity로 put
                    SharedPreferences mPref = getSharedPreferences("qrCodeRecord",MODE_PRIVATE);
                    SharedPreferences.Editor editor = mPref.edit();
                    editor.putString("qrcodeParsing",divisionCode[divisionCode.length-1]);
                    editor.commit();
                    //intent.putExtra("qrcodeParsing",divisionCode[divisionCode.length-1]);
                    startActivity(intent);
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}