package com.example.baitap_22_7_2021.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap_22_7_2021.Model.Chat2;
import com.example.baitap_22_7_2021.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    int checkLeft = 1, checkRight = 2, checkRightImg = 3;
    ArrayList<Chat2> arrayList;

    public ChatAdapter(ArrayList<Chat2> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == checkLeft) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_left, parent, false);
            return new ViewHolerLeft(view);
        } else if (viewType == checkRight) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_right, parent, false);
            return new ViewHolderRight(view);
        } else if (viewType == checkRightImg) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_right_image, parent, false);
            return new ViewHolderRightImage(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chat2 chat2 = arrayList.get(position);

        if (getItemViewType(position) == checkLeft) {
            ((ViewHolerLeft) holder).setDataLeft(arrayList.get(position));
        } else if (getItemViewType(position) == checkRight) {
            ((ViewHolderRight) holder).setDataRight(arrayList.get(position));
        } else if (getItemViewType(position) == checkRightImg) {
            ((ViewHolderRightImage) holder).setDataRightImage(arrayList.get(position));
        }
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (arrayList.get(position).getCheck() == 1) {
            return checkLeft;
        } else if (arrayList.get(position).getCheck() == 2) {
            return checkRight;
        } else
            return checkRightImg;
    }

    public class ViewHolerLeft extends RecyclerView.ViewHolder {
        TextView tv;
        ShapeableImageView imageView;

        public ViewHolerLeft(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView_left);
            imageView = itemView.findViewById(R.id.img_ava_chat_left);
        }

        public void setDataLeft(Chat2 chat2) {
            tv.setText(chat2.getMes());
            Picasso.get().load(chat2.getUrlAvaTrongMes()).placeholder(R.drawable.bean).into(imageView);
        }
    }

    public class ViewHolderRight extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolderRight(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView_right);
        }

        public void setDataRight(Chat2 chat2) {
            tv.setText(chat2.getMes());
        }
    }

    public class ViewHolderRightImage extends RecyclerView.ViewHolder {
        ImageView img;

        public ViewHolderRightImage(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView_chat_right);
        }

        public void setDataRightImage(Chat2 chat2) {
            Bitmap bmAnh = BitmapFactory.decodeByteArray(chat2.getImg(),0,chat2.getImg().length);
            img.setImageBitmap(bmAnh);
        }
    }
}
