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

import com.example.baitap_22_7_2021.Adapter.Chat2Adapter;
import com.example.baitap_22_7_2021.Model.Chat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rv;
    EditText edt;
    ImageView img;

    ArrayList<Chat> arrayListRight;

    Chat2Adapter adapter;

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
            arrayListRight.add(new Chat(mes));
            arrayListRight.add(new Chat(""));
            adapter.notifyDataSetChanged();
            edt.setText("");
            Toast.makeText(this, "" + arrayListRight.size(), Toast.LENGTH_SHORT).show();

        }
    }

    private void anhxa() {
        rv = findViewById(R.id.recyclerView);
        edt = findViewById(R.id.edt_mes);
        img = findViewById(R.id.imageView_send_mes);

        arrayListRight = new ArrayList<>();
        adapter = new Chat2Adapter(arrayListRight);

        rv.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        linearLayoutManager.getReverseLayout();

        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
    }
}