package com.nvd.qrcode.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nvd.qrcode.R;
import com.nvd.qrcode.database.Item_Qr;
import com.nvd.qrcode.database.QRDatabase;
import com.nvd.qrcode.database.QrAdapter;

import java.util.List;

public class HistoryFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    private QrAdapter qrAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = view.findViewById(R.id.recycleView);

        qrAdapter = new QrAdapter(getContext());
        loadData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(qrAdapter);


        return view;
    }


    private void loadData(){
        List<Item_Qr> mListQr = QRDatabase.getInstance(getContext()).userDAO().getListUser();
        qrAdapter.setData(mListQr);
    }

}
