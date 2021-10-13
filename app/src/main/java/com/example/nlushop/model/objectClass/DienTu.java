package com.example.nlushop.model.objectClass;

import android.widget.ImageView;

import java.util.List;

public class DienTu {
    List<ThuongHieu> thuongHieuList;
    List<SanPham> sanPhamList;
    String tenNoiBat, tenTopNoiBat;
    boolean thuonghieu;

    public boolean isThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(boolean thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public String getTenNoiBat() {
        return tenNoiBat;
    }

    public void setTenNoiBat(String tenNoiBat) {
        this.tenNoiBat = tenNoiBat;
    }

    public String getTenTopNoiBat() {
        return tenTopNoiBat;
    }

    public void setTenTopNoiBat(String tenTopNoiBat) {
        this.tenTopNoiBat = tenTopNoiBat;
    }

    public List<ThuongHieu> getThuongHieuList() {
        return thuongHieuList;
    }

    public void setThuongHieuList(List<ThuongHieu> thuongHieuList) {
        this.thuongHieuList = thuongHieuList;
    }

    public List<SanPham> getSanPhamList() {
        return sanPhamList;
    }

    public void setSanPhamList(List<SanPham> sanPhamList) {
        this.sanPhamList = sanPhamList;
    }

}
