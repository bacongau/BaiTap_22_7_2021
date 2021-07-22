package com.example.baitap_22_7_2021.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap_22_7_2021.Model.Chat;
import com.example.baitap_22_7_2021.R;

import java.util.ArrayList;

public class Chat2Adapter extends RecyclerView.Adapter<Chat2Adapter.ViewHolder> {
    ArrayList<Chat> arrayList;

    public Chat2Adapter(ArrayList<Chat> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Chat2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Chat2Adapter.ViewHolder holder, int position) {
        Chat chat = arrayList.get(position);

        String a = chat.getNoidung();

        holder.setData(a);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvgui;
        TextView tvBack;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvgui = itemView.findViewById(R.id.sender_message_text);
            tvBack = itemView.findViewById(R.id.receiver_message_text);
            img = itemView.findViewById(R.id.message_profile_image);


        }

        public void setData(String b){
            tvgui.setText(b);
            if (b.equals("")){
                tvBack.setVisibility(View.VISIBLE);
                img.setVisibility(View.VISIBLE);
            }else {
                tvgui.setText(b);
                tvBack.setVisibility(View.INVISIBLE);
                img.setVisibility(View.INVISIBLE);
            }

        }
    }
}
