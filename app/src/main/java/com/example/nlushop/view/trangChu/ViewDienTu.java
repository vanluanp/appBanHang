package com.example.nlushop.view.trangChu;

import com.example.nlushop.model.objectClass.DienTu;
import com.example.nlushop.model.objectClass.SanPham;
import com.example.nlushop.model.objectClass.ThuongHieu;

import java.util.List;

public interface ViewDienTu {
    void HienThiDanhSach(List<DienTu> dienTuList);
    void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieuList);
    void LoiLayDuLieu();
    void HienThiSanPhamMoi(List<SanPham> sanPhamList);
}
