package com.example.nlushop.view.chiTietSanPham;

import com.example.nlushop.model.objectClass.DanhGia;
import com.example.nlushop.model.objectClass.SanPham;

import java.util.List;

public interface ViewChiTietSanPham {
    void HienThiChiTietSanPham(SanPham sanPham);
    void HienThiSliderSanPham(String[] linkhinhsanpham);
    void HienThiDanhGia(List<DanhGia> danhGiaList);
    void ThemGioHangThanhCong();
    void ThemGioHangThatBai();
    void HienThiSanPhamSoSanh(List<SanPham> sanPhamList);
}
