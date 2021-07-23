package com.example.baitap_22_7_2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.baitap_22_7_2021.Adapter.ChatAdapter;
import com.example.baitap_22_7_2021.Model.Chat2;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rv;
    EditText edt;
    ImageView img;

    ArrayList<Chat2> arrayList;

    ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guiTN();
            }
        });
    }

    private void guiTN() {
        String mes = edt.getText().toString();
        if (mes.isEmpty()) {
            Toast.makeText(this, "Ban chua nhap noi dung", Toast.LENGTH_SHORT).show();
        } else {
            arrayList.add(new Chat2(mes,2));
            arrayList.add(new Chat2("Hello, how are you?",1));
            adapter.notifyDataSetChanged();
            edt.setText("");

            rv.scrollToPosition(arrayList.size()-1);
        }
    }

    private void anhxa() {
        rv = findViewById(R.id.recyclerView);
        edt = findViewById(R.id.edt_mes);
        img = findViewById(R.id.imageView_send_mes);

        arrayList = new ArrayList<>();

        adapter = new ChatAdapter(arrayList);

        rv.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        linearLayoutManager.setStackFromEnd(true);

        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }
}