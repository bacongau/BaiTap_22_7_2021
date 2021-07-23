package com.example.baitap_22_7_2021.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap_22_7_2021.Model.NguoiDung;
import com.example.baitap_22_7_2021.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.ViewHolder> {

    ArrayList<NguoiDung> arrayList;
    private OnItemClickListener mListener;

    public NguoiDungAdapter(ArrayList<NguoiDung> arrayList) {
        this.arrayList = arrayList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    @NonNull
    @Override
    public NguoiDungAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nguoidung, parent, false);

        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NguoiDungAdapter.ViewHolder holder, int position) {
        NguoiDung nguoiDung = arrayList.get(position);

        String avaUrl = nguoiDung.getAvaUrl();
        String ten = nguoiDung.getTen();
        String tncuoi = nguoiDung.getTnCuoi();

        holder.setData(avaUrl, ten, tncuoi);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAva;
        TextView tv_ten, tv_lastMes, tv_time;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            imgAva = itemView.findViewById(R.id.img_avaND);
            tv_ten = itemView.findViewById(R.id.tv_tenND);
            tv_lastMes = itemView.findViewById(R.id.tv_lastTNND);
            tv_time = itemView.findViewById(R.id.tv_timetn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void setData(String avaurl, String ten, String tncuoi) {
            Picasso.get()
                    .load(avaurl)
                    .placeholder(R.drawable.bean)
                    .into(imgAva);
            tv_ten.setText(ten);

            tv_lastMes.setText(tncuoi);
            tv_time.setText("  now");

        }
    }

}
