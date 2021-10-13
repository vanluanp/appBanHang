package com.example.nlushop.model.objectClass;

import java.util.List;

public class LoaiSanPham {
    int MALOAISP;
    int MALOAICHA;
    String TENLOAISP;
    List<LoaiSanPham> loaiSanPhamListCon;

    public LoaiSanPham(){

    }

    public int getMALOAISP() {
        return MALOAISP;
    }

    public int getMALOAICHA() {
        return MALOAICHA;
    }

    public String getTENLOAISP() {
        return TENLOAISP;
    }

    public List<LoaiSanPham> getLoaiSanPhamListCon() {
        return loaiSanPhamListCon;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public void setMALOAICHA(int MALOAICHA) {
        this.MALOAICHA = MALOAICHA;
    }

    public void setTENLOAISP(String TENLOAISP) {
        this.TENLOAISP = TENLOAISP;
    }

    public void setLoaiSanPhamListCon(List<LoaiSanPham> loaiSanPhamListCon) {
        this.loaiSanPhamListCon = loaiSanPhamListCon;
    }
}
