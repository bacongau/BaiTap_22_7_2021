package com.example.baitap_22_7_2021.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitap_22_7_2021.Model.Chat2;
import com.example.baitap_22_7_2021.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    int checkLeft = 1, checkRight = 2;
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
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chat2 chat2 = arrayList.get(position);

        if (chat2.getCheck() == checkLeft) {
            String a = chat2.getMes();
            ((ViewHolerLeft) holder).setDataLeft(a);
        } else if (chat2.getCheck() == checkRight) {
            String b = chat2.getMes();
            ((ViewHolderRight)holder).setDataRight(b);
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
        } else {
            return checkRight;
        }
    }

    public class ViewHolerLeft extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolerLeft(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.textView_left);
        }

        public void setDataLeft(String chatLeft) {
            tv.setText(chatLeft);
        }
    }

    public class ViewHolderRight extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolderRight(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.textView_right);
        }

        public void setDataRight(String chatRight) {
            tv.setText(chatRight);
        }
    }
}
