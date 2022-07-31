package com.nvd.qrcode.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nvd.qrcode.MainActivity;
import com.nvd.qrcode.R;
import com.nvd.qrcode.activity.ActivityLanguage;
import com.nvd.qrcode.activity.ActivityPolicy;
import com.nvd.qrcode.activity.ActivityTermOfUse;

public class SettingFragment extends Fragment {
    View view;
    RelativeLayout layoutLanguage, layoutPolicy, layoutTermOfUse, layoutShare;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);

        layoutLanguage = view.findViewById(R.id.layout_language);
        layoutLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityLanguage.class);
                startActivity(intent);
            }
        });

        layoutPolicy = view.findViewById(R.id.layout_policy);
        layoutPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityPolicy.class);
                startActivity(intent);
            }
        });

        layoutTermOfUse = view.findViewById(R.id.layout_term_of_use);
        layoutTermOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityTermOfUse.class);
                startActivity(intent);
            }
        });

        layoutShare = view.findViewById(R.id.layout_share);
        layoutShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
                i.putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/watch?v=7zoUYsKztpE&list=RD7zoUYsKztpE&start_radio=1&ab_channel=TH%C6%AF%C6%A0NGV%C3%95OFFICIAL");
                startActivity(Intent.createChooser(i, "Share URL"));
            }
        });
        return view;
    }
}
