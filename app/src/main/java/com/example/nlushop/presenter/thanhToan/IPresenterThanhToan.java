package com.example.nlushop.presenter.thanhToan;

import android.content.Context;

import com.example.nlushop.model.objectClass.HoaDon;
import com.example.nlushop.model.objectClass.SanPham;

import java.util.List;

public interface IPresenterThanhToan {
    void ThemHoaDon(HoaDon hoaDon);
    void LayDanhSachSanPhamTrongGioHang();
}
