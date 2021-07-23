package com.example.baitap_22_7_2021.Model;

import java.io.Serializable;

public class NguoiDung implements Serializable {
    String ten;
    String avaUrl;
    String tnCuoi;
    int id;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getAvaUrl() {
        return avaUrl;
    }

    public void setAvaUrl(String avaUrl) {
        this.avaUrl = avaUrl;
    }

    public String getTnCuoi() {
        return tnCuoi;
    }

    public void setTnCuoi(String tnCuoi) {
        this.tnCuoi = tnCuoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NguoiDung(String ten, String avaUrl, String tnCuoi, int id) {
        this.ten = ten;
        this.avaUrl = avaUrl;
        this.tnCuoi = tnCuoi;
        this.id = id;
    }

    public NguoiDung() {
    }
}
