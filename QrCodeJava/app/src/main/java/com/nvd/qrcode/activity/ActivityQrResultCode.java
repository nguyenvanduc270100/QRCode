package com.nvd.qrcode.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nvd.qrcode.MainActivity;
import com.nvd.qrcode.R;
import com.nvd.qrcode.database.Item_Qr;
import com.nvd.qrcode.database.QRDatabase;
import com.nvd.qrcode.until.Until;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class ActivityQrResultCode extends AppCompatActivity {
    private String textResult;
    ImageView imgResultQR, imgBack;
    Bitmap bitmap;
    ImageView imgShare, imgCopy, imgDownload, imgOpen;
    TextView textQR ;
    TextView textTitle ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_result_code);


        imgResultQR = findViewById(R.id.img_result);
        imgShare = findViewById(R.id.img_share);
        imgDownload = findViewById(R.id.img_download);
        imgCopy = findViewById(R.id.img_copy);
        imgOpen = findViewById(R.id.img_open);
        imgBack = findViewById(R.id.img_back);
        textQR = findViewById(R.id.link_result);
        textTitle = findViewById(R.id.textTitle);

        Intent intent = getIntent();
        if (intent != null){
            textResult = intent.getStringExtra("resultQr");
            textQR.setText(textResult);

            // add database
            Item_Qr qrCode = new Item_Qr(textResult);
            QRDatabase.getInstance(this).userDAO().insertUser(qrCode);
        }

        Until.createQrCode(getApplicationContext(), textResult, bitmap, imgResultQR);

        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgResultQR.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                Date now = new Date();
                String fileName = (now.getTime() / 1000) + ".png";
                try {
                    File file = new File(getExternalCacheDir(), File.separator + fileName);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Uri uri = FileProvider.getUriForFile(getApplicationContext(), "com.nvd.qrcode", file);
                    intent.putExtra(Intent.EXTRA_STREAM, uri);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setType("image/*");
                    startActivity(Intent.createChooser(intent, "Share image via: "));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        imgDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Until.saved(imgResultQR, getApplicationContext());
            }
        });

        imgCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text", textResult);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(ActivityQrResultCode.this, R.string.copied, Toast.LENGTH_SHORT).show();
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityQrResultCode.this, MainActivity.class);
                startActivity(i);
            }
        });

        imgOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Uri uri = Uri.parse(textResult);
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });
    }


}