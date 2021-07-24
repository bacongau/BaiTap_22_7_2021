package com.example.baitap_22_7_2021;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baitap_22_7_2021.Adapter.ChatAdapter;
import com.example.baitap_22_7_2021.Model.Chat2;
import com.example.baitap_22_7_2021.Model.NguoiDung;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rv;
    EditText edt;
    ImageView img, img_chupanh, img_chonanh;
    ImageView imgAva, img_back;
    TextView tv_tenchat;

    ArrayList<Chat2> arrayList;

    ChatAdapter adapter;

    NguoiDung nguoiDung;

    final int ChupHinh = 100;
    final int ChonHinh = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(toolbar);


        NhanDuLieu();
        anhxa();
        setDuLieu();
        back();
        String[] items = {"Chụp ảnh", "Chọn ảnh"};
        AlertDialog.Builder b = new AlertDialog.Builder(this);
//Thiết lập title
        b.setTitle("Chọn phương thức");
//Thiết lập item
        b.setItems(items, new DialogInterface.OnClickListener() {
            //Xử lý sự kiện
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    chupAnh();
                }else{
                    chonAnh();
                }
            }
        });
//Hiển thị dialog
        img_chupanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.show();
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guiTN();
            }
        });
    }


    private void chonAnh() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, ChonHinh);
    }

    private void chupAnh() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, ChupHinh);
    }

    private void back() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastMes;
                int id = 0;
                if (arrayList.isEmpty()) {
                    lastMes = "Hãy gửi tin nhắn cho bạn bè";
                } else {
                    lastMes = arrayList.get(arrayList.size() - 2).getMes();
                    if (lastMes == ""){
                        lastMes = "Bạn vừa gửi 1 ảnh";
                    }
                    id = nguoiDung.getId();
                }

                Intent returnIntent = new Intent();
                returnIntent.putExtra("lastMes", lastMes);
                returnIntent.putExtra("id", id);
                arrayList.clear();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    private void setDuLieu() {
        tv_tenchat.setText(nguoiDung.getTen());
        Picasso.get().load(nguoiDung.getAvaUrl()).placeholder(R.drawable.bean).into(imgAva);
    }

    private void NhanDuLieu() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dataObj");
        nguoiDung = (NguoiDung) bundle.getSerializable("data");

    }

    private void guiTN() {
        String mes = edt.getText().toString();
        if (mes.isEmpty()) {
            Toast.makeText(this, "Ban chua nhap noi dung", Toast.LENGTH_SHORT).show();
        } else {
            arrayList.add(new Chat2(mes, 2, null));
            arrayList.add(new Chat2("Hello, how are you?", 1, null));
            adapter.notifyDataSetChanged();
            edt.setText("");

            rv.scrollToPosition(arrayList.size() - 1);
        }
    }

    private void anhxa() {
        img_chonanh = findViewById(R.id.img_chonAnh);
        img_chupanh = findViewById(R.id.img_chupanh);
        toolbar = findViewById(R.id.toolbar);
        tv_tenchat = findViewById(R.id.tv_ten_chat);
        img_back = findViewById(R.id.img_back);
        imgAva = findViewById(R.id.img_ava_chat);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((resultCode == RESULT_OK)) {
            if (requestCode == ChonHinh) {
                Uri imageUri = data.getData();
                try {
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    bitmap.recycle();

                    arrayList.add(new Chat2("Bạn vừa gửi 1 ảnh", 3, byteArray));
                    arrayList.add(new Chat2("Bạn vừa chọn ảnh trong thư viện ảnh", 1, null));

                    adapter.notifyDataSetChanged();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == ChupHinh) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                bitmap.recycle();

                arrayList.add(new Chat2("Bạn vừa gửi 1 ảnh", 3, byteArray));
                arrayList.add(new Chat2("Bạn vừa gửi ảnh chụp", 1, null));

                adapter.notifyDataSetChanged();
            }
        }
    }

    private byte[] getByteArrayFromImageView(ImageView imgMatHang) {
        BitmapDrawable drawable = (BitmapDrawable) imgMatHang.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }
}