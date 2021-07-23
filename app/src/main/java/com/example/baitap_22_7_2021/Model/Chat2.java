package com.example.baitap_22_7_2021.Model;

public class Chat2 {
    String mes;
    int check;
    byte[] img;

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Chat2(String mes, int check, byte[] img) {
        this.mes = mes;
        this.check = check;
        this.img = img;
    }

    public Chat2() {
    }
}
