package com.example.nlushop.model.objectClass;

import java.io.Serializable;

public class ChiTietSanPham implements Serializable {
    String TENCHITIET, GIATRI;

    public String getTENCHITIET() {
        return TENCHITIET;
    }

    public void setTENCHITIET(String TENCHITIET) {
        this.TENCHITIET = TENCHITIET;
    }

    public String getGIATRI() {
        return GIATRI;
    }

    public void setGIATRI(String GIATRI) {
        this.GIATRI = GIATRI;
    }
}
