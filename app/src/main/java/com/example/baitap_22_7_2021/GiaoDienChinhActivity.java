package com.example.baitap_22_7_2021;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.baitap_22_7_2021.Adapter.NguoiDungAdapter;
import com.example.baitap_22_7_2021.Model.NguoiDung;

import java.util.ArrayList;

public class GiaoDienChinhActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 123;
    RecyclerView rv;
    NguoiDungAdapter adapter;
    ArrayList<NguoiDung> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_chinh);

        rv = findViewById(R.id.rv_danhsachNguoi);
        KhoiTao();
    }


    private void KhoiTao() {
        arrayList = new ArrayList<>();
        arrayList.add(new NguoiDung("Iron man", "https://st1.bollywoodlife.com/wp-content/uploads/2019/01/Iron-Man-1.jpg", "Hãy gửi tin nhắn cho bạn bè", 1));
        arrayList.add(new NguoiDung("Captain", "https://icdn.dantri.com.vn/thumb_w/640/2019/03/29/15-1553871967380.jpg", "Hãy gửi tin nhắn cho bạn bè", 2));
        arrayList.add(new NguoiDung("Thor", "https://akns-images.eonline.com/eol_images/Entire_Site/2017920/rs_600x600-171020123519-600.thor-ragnarok-4.102017.jpg?fit=around%7C1080:1080&output-quality=90&crop=1080:1080;center,top", "Hãy gửi tin nhắn cho bạn bè", 3));
        arrayList.add(new NguoiDung("Hulk", "https://anh.24h.com.vn/upload/2-2015/images/2015-05-19/1432029894-vzekavengers9_fubz.jpg", "Hãy gửi tin nhắn cho bạn bè", 4));
        arrayList.add(new NguoiDung("Ant man", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/ant-man-avengers-endgame-paul-rudd-1555594407.jpg", "Hãy gửi tin nhắn cho bạn bè", 5));
        arrayList.add(new NguoiDung("Spiderman", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOy_QLgeRBnkk3p2qsVlC9-E6dnnkibZKQ8g&usqp=CAU", "Hãy gửi tin nhắn cho bạn bè", 6));
        arrayList.add(new NguoiDung("Black Panther", "https://kenh14cdn.com/203336854389633024/2020/11/14/brody-passionate-politics-black-panther-16053399921761331441736.jpg", "Hãy gửi tin nhắn cho bạn bè", 7));
        arrayList.add(new NguoiDung("Black Widow", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDC5vV1eqHhjwukilkuUstzDQmdxVcMifHGw&usqp=CAU", "Hãy gửi tin nhắn cho bạn bè", 8));
        arrayList.add(new NguoiDung("Captain", "https://icdn.dantri.com.vn/thumb_w/640/2019/03/29/15-1553871967380.jpg", "Hãy gửi tin nhắn cho bạn bè", 9));
        arrayList.add(new NguoiDung("Captain", "https://icdn.dantri.com.vn/thumb_w/640/2019/03/29/15-1553871967380.jpg", "Hãy gửi tin nhắn cho bạn bè", 10));
        adapter = new NguoiDungAdapter(arrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new NguoiDungAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(GiaoDienChinhActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                NguoiDung nguoiDung = arrayList.get(position);
                bundle.putSerializable("data", nguoiDung);
                intent.putExtra("dataObj", bundle);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (REQUEST_CODE == requestCode && resultCode == Activity.RESULT_OK) {
            String tnCuoi = data.getStringExtra("lastMes");
            int id = data.getIntExtra("id", -1);

            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).getId() == id){
                    arrayList.get(i).setTnCuoi(tnCuoi);
                    adapter.notifyDataSetChanged();
                    break;
                }
            }
        }
    }

}