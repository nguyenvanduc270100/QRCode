package com.nvd.qrcode.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nvd.qrcode.R;

public class ActivityCreateWifiQR extends AppCompatActivity {
    TextView txtNetworkName, txtPassword, txtInstruction;
    EditText edtName, edtPassword;
    Button btnCreate;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wifi_qr);

        txtNetworkName = findViewById(R.id.textNetworkName);
        txtPassword = findViewById(R.id.textPassword);
        txtInstruction = findViewById(R.id.textInstruction);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        btnCreate = findViewById(R.id.btnGenerate);
        imgBack = findViewById(R.id.img_back);


        Intent intent = getIntent();
        int reciverName = intent.getIntExtra("name", 0);
        int reciverPassword = intent.getIntExtra("password", 0);
        int reciverInstruction = intent.getIntExtra("instruction", 0);


        String name = getResources().getString(reciverName);
        String password = getResources().getString(reciverPassword);
        String instruction = getResources().getString(reciverInstruction);


        txtNetworkName.setText(name);
        txtPassword.setText(password);
        txtInstruction.setText(instruction);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityCreateWifiQR.this, ActivityResultWifiQR.class);
                i.putExtra("wifi_name", edtName.getText().toString().trim());
                i.putExtra("wifi_password", edtPassword.getText().toString().trim());
                startActivity(i);
            }
        });
    }
}