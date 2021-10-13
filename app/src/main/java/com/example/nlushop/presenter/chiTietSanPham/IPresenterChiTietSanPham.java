package com.example.nlushop.presenter.chiTietSanPham;

import android.content.Context;

import com.example.nlushop.model.objectClass.SanPham;

public interface IPresenterChiTietSanPham {
    void LayChiTietSanPham(int masp);
    void LayDanhSachDanhGiaTheoSanPham(int masp, int limit);
    void ThemGioHang(SanPham sanPham, Context context);
    void LayDanhSachSanPhamSoSanh(int masp);

}
