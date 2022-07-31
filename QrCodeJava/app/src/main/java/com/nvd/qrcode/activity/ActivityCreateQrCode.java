package com.nvd.qrcode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nvd.qrcode.R;
import com.nvd.qrcode.database.Item_Qr;
import com.nvd.qrcode.database.QRDatabase;

public class ActivityCreateQrCode extends AppCompatActivity {
    EditText edtInput;
    Button btnGenerate;
    ImageView imgBack;
    TextView txtInstruction, txtTitle;
    String my_title, my_Instruction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_qr_code);

        edtInput = findViewById(R.id.edtInput);
        btnGenerate = findViewById(R.id.btnGenerate);
        imgBack = findViewById(R.id.img_back);
        txtInstruction = findViewById(R.id.textNote);
        txtTitle = findViewById(R.id.textTitle);

        Intent intent = getIntent();
        String textHint = intent.getStringExtra("hint");
        int reciverTitle = intent.getIntExtra("title", 0);
        int reciverInstruction = intent.getIntExtra("instruction", 0);

        my_title = getResources().getString(reciverTitle);
        my_Instruction = getResources().getString(reciverInstruction);

        txtTitle.setText(my_title);
        edtInput.setHint(textHint);
        txtInstruction.setText(my_Instruction);


        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText = edtInput.getText().toString().trim();
                if (sText.length() == 0){
                    Toast.makeText(ActivityCreateQrCode.this, "Enter text", Toast.LENGTH_SHORT).show();
                } else {
                    if(sText.contains("https://")){
                        Intent i = new Intent(ActivityCreateQrCode.this, ActivityQrResultCode.class);
                        i.putExtra("resultQr", sText);
                        i.putExtra("my_title",my_title );
                        startActivity(i);
                    } else if (sText.contains("@gmail.com")){
                        Intent i = new Intent(ActivityCreateQrCode.this, ActivityCreateQREmail.class);
                        i.putExtra("resultQr", sText);
                        i.putExtra("my_title",my_title );
                        startActivity(i);
                    } else if(my_title.contains("Telephone")){
                        Intent i = new Intent(ActivityCreateQrCode.this, ActivityCreateTelephoneQR.class);
                        i.putExtra("resultQr", sText);
                        i.putExtra("my_title",my_title );
                        startActivity(i);
                    } else if (my_title.contains("Text") || my_title.contains("Message")){
                        Intent i = new Intent(ActivityCreateQrCode.this, ActivityResultTextQr.class);
                        i.putExtra("resultQr", sText);
                        i.putExtra("my_title",my_title );
                        startActivity(i);
                    }
                }

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
