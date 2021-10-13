package com.example.nlushop.model.objectClass;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ChiTietKhuyenMai implements Serializable {

    int MAKM, MASP, PHANTRAMKM;

    public int getMAKM() {
        return MAKM;
    }

    public void setMAKM(int MAKM) {
        this.MAKM = MAKM;
    }

    public int getMASP() {
        return MASP;
    }

    public void setMASP(int MASP) {
        this.MASP = MASP;
    }

    public int getPHANTRAMKM() {
        return PHANTRAMKM;
    }

    public void setPHANTRAMKM(int PHANTRAMKM) {
        this.PHANTRAMKM = PHANTRAMKM;
    }
}
