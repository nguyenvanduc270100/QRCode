package com.nvd.qrcode.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nvd.qrcode.R;
import com.nvd.qrcode.activity.ActivityCreateLocationQR;
import com.nvd.qrcode.activity.ActivityCreateQREmail;
import com.nvd.qrcode.activity.ActivityCreateQrCode;
import com.nvd.qrcode.activity.ActivityCreateTelephoneQR;
import com.nvd.qrcode.activity.ActivityCreateWifiQR;
import com.nvd.qrcode.activity.ActivityQrResultCode;

public class CreateFragment extends Fragment {

    View view;
    ImageView imgTelephone, imgEmail, imgWebsite, imgMessage, imgText, imgChPlay;
    ImageView imgTiktok, imgInsta, imgFacebook, imgSnapchat, imgDiscord, imgYoutube;
    ImageView imgTeleGram, imgTwitter, imgWifi, imgLocation;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create, container, false);



        imgTelephone = view.findViewById(R.id.img_Telephone);

        imgTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateQrCode.class);
                intent.putExtra("title", R.string.title_telephone);
                intent.putExtra("hint", "+84966824817");
                intent.putExtra("instruction", R.string.text_telephone);
                startActivity(intent);
            }
        });

        imgEmail = view.findViewById(R.id.img_email);
        imgEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateQrCode.class);
                intent.putExtra("title", R.string.title_email);
                intent.putExtra("hint", "ssongtuyen@gmail.com");
                intent.putExtra("instruction", R.string.text_email);
                startActivity(intent);
            }
        });

        imgText = view.findViewById(R.id.img_text);
        imgText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateQrCode.class);
                intent.putExtra("title", R.string.title_text);
                intent.putExtra("hint", "Battle R Yale");
                intent.putExtra("instruction", R.string.text_text);
                startActivity(intent);
            }
        });

        imgChPlay = view.findViewById(R.id.img_chPlay);
        imgChPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateQrCode.class);
                intent.putExtra("title", R.string.title_ch_play);
                intent.putExtra("hint", "https://play.google.com/store/");
                intent.putExtra("instruction", R.string.text_ch_play);
                startActivity(intent);
            }
        });

        imgWebsite = view.findViewById(R.id.img_website);
        imgWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateQrCode.class);
                intent.putExtra("title", R.string.website);
                intent.putExtra("hint", "https://google.com");
                intent.putExtra("instruction", R.string.text_website);
                startActivity(intent);
            }
        });

        imgYoutube = view.findViewById(R.id.img_youtube);
        imgYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateQrCode.class);
                intent.putExtra("title", R.string.title_youtube);
                intent.putExtra("hint", "http://youtube.com/ssongtuyen");
                intent.putExtra("instruction", R.string.text_youtube);
                startActivity(intent);
            }
        });

        imgMessage = view.findViewById(R.id.img_message);
        imgMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateQrCode.class);
                intent.putExtra("title", R.string.title_message);
                intent.putExtra("hint", "I love you!");
                intent.putExtra("instruction", R.string.text_message);
                startActivity(intent);
            }
        });

        imgTiktok = view.findViewById(R.id.img_tiktok);
        imgTiktok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateQrCode.class);
                intent.putExtra("title", R.string.title_tiktok);
                intent.putExtra("hint", "http://tiktok.com/ssongtuyen");
                intent.putExtra("instruction", R.string.text_tiktok);
                startActivity(intent);
            }
        });

        imgInsta = view.findViewById(R.id.img_instagram);
        imgInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateQrCode.class);
                intent.putExtra("title", R.string.title_instagram);
                intent.putExtra("hint", "https://www.instagram.com/");
                intent.putExtra("instruction", R.string.text_instagram);
                startActivity(intent);
            }
        });

        imgFacebook = view.findViewById(R.id.img_facebook);
        imgFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateQrCode.class);
                intent.putExtra("title", R.string.title_facebook);
                intent.putExtra("hint", "http://facebook.com/ssongtuyen");
                intent.putExtra("instruction", R.string.text_facebook);
                startActivity(intent);
            }
        });

        imgSnapchat = view.findViewById(R.id.img_snapchat);
        imgSnapchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateQrCode.class);
                intent.putExtra("title", R.string.title_snapchat);
                intent.putExtra("hint", "https://www.snapchat.com/");
                intent.putExtra("instruction", R.string.text_snapchat);
                startActivity(intent);
            }
        });

        imgDiscord = view.findViewById(R.id.img_discord);
        imgDiscord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateQrCode.class);
                intent.putExtra("title", R.string.title_discord);
                intent.putExtra("hint", "#John598");
                intent.putExtra("instruction", R.string.text_discord);
                startActivity(intent);
            }
        });

        imgTwitter = view.findViewById(R.id.img_twitter);
        imgTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateQrCode.class);
                intent.putExtra("title", R.string.title_twitter);
                intent.putExtra("hint", "http://twitter.com/ssongtuyen");
                intent.putExtra("instruction", R.string.text_twitter);
                startActivity(intent);
            }
        });

        imgTeleGram = view.findViewById(R.id.img_telegram);
        imgTeleGram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateQrCode.class);
                intent.putExtra("title", R.string.title_telegram);
                intent.putExtra("hint", "https://web.telegram.org/k/");
                intent.putExtra("instruction", R.string.text_telegram);
                startActivity(intent);
            }
        });

        imgLocation = view.findViewById(R.id.img_location);
        imgLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateLocationQR.class);
                intent.putExtra("titleLongTitude", R.string.title_longtitude);
                intent.putExtra("titleLaTitude", R.string.title_latitude);
                intent.putExtra("instruction", R.string.text_location);
                startActivity(intent);
            }
        });

        imgWifi = view.findViewById(R.id.img_wifi);
        imgWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCreateWifiQR.class);
                intent.putExtra("name", R.string.title_network_name);
                intent.putExtra("password", R.string.title_password);
                intent.putExtra("instruction", R.string.text_wifi);
                startActivity(intent);
            }
        });
        return view;
    }
}
