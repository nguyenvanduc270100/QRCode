package com.nvd.qrcode.database;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvd.qrcode.R;
import com.nvd.qrcode.activity.ActivityGetDataBase;
import com.nvd.qrcode.activity.ActivityQrResultCode;

import java.util.List;


public class QrAdapter extends RecyclerView.Adapter<QrAdapter.UserViewHolder>{
    Context context;
    private List<Item_Qr> mList;



    public QrAdapter(Context context) {
        this.context = context;

    }

    public void setData(List<Item_Qr> list){
        mList = list;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qr, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Item_Qr item_qr = mList.get(position);
        if(item_qr == null){
            return;
        }

        holder.txtName.setText(item_qr.getName());
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ActivityGetDataBase.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item_qr);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mList != null){
            return mList.size();
        } else {
            return 0;
        }

    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        RelativeLayout layoutItem;
        ImageView imageView;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtLink);
            layoutItem = itemView.findViewById(R.id.layout_item);
            imageView = itemView.findViewById(R.id.img_qr);
        }
    }
}
