package com.example.baitap_22_7_2021.Model;

public class Chat2 {
    String mes;
    int check;
    byte[] img;
    String urlAvaTrongMes;

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

    public String getUrlAvaTrongMes() {
        return urlAvaTrongMes;
    }

    public void setUrlAvaTrongMes(String urlAvaTrongMes) {
        this.urlAvaTrongMes = urlAvaTrongMes;
    }

    public Chat2(String mes, int check, byte[] img, String urlAvaTrongMes) {
        this.mes = mes;
        this.check = check;
        this.img = img;
        this.urlAvaTrongMes = urlAvaTrongMes;
    }

    public Chat2() {
    }
}
