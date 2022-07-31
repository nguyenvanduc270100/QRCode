package com.nvd.qrcode.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
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

public class ActivityGetDataBase extends AppCompatActivity {

    private TextView textQR, txtTitle;

    ImageView imgResultQR, imgDelete, imgBack;
    ImageView imgShare, imgCopy, imgOpen;
    Item_Qr item_qr;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data_base);

        txtTitle = findViewById(R.id.txtTitle);
        textQR = findViewById(R.id.link_result);
        imgResultQR = findViewById(R.id.img_result);
        imgShare = findViewById(R.id.img_share);
        imgCopy = findViewById(R.id.img_copy);
        imgOpen = findViewById(R.id.img_open);
        imgBack = findViewById(R.id.img_back);
        imgDelete = findViewById(R.id.img_delete);

        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getExtras();
            item_qr = (Item_Qr) bundle.getSerializable("item");



            if(item_qr.getName().contains("@gmail.com")){
                txtTitle.setText(R.string.title_email);
            } else if (item_qr.getName().contains("https:")){
                txtTitle.setText(R.string.link);
            } else {
                txtTitle.setText(R.string.text);
            }
            textQR.setText(item_qr.getName());


        }

        Until.createQrCode(getApplicationContext(), item_qr.getName(), bitmap, imgResultQR);

        imgCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text", item_qr.getName());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(ActivityGetDataBase.this, R.string.copied, Toast.LENGTH_SHORT).show();
            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ActivityGetDataBase.this);
                alertDialogBuilder.setTitle(R.string.dialog_message);
                alertDialogBuilder.setPositiveButton(R.string.btn_yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        QRDatabase.getInstance(getApplicationContext()).userDAO().deleteItem(item_qr);
                        Intent i = new Intent(ActivityGetDataBase.this, MainActivity.class);
                        startActivity(i);
                    }
                });

                alertDialogBuilder.setNegativeButton(R.string.btn_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        
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

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item_qr.getName().contains("https://") || item_qr.getName().contains("http://")){
                    Uri uri = Uri.parse(item_qr.getName());
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                } else if(item_qr.getName().contains("@gmail.com")){
                    Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+item_qr.getName()));
                    startActivity(intent1);
                } else {
                    Toast.makeText(ActivityGetDataBase.this, "No Action", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}