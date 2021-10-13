package com.example.nlushop.presenter.trangchu_dientu;

import com.example.nlushop.model.objectClass.DienTu;
import com.example.nlushop.model.objectClass.SanPham;
import com.example.nlushop.model.objectClass.ThuongHieu;
import com.example.nlushop.model.trangchu_dientu.ModelDienTu;
import com.example.nlushop.view.trangChu.ViewDienTu;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicDienTu implements IPresenterDienTu{
    ViewDienTu viewDienTu;
    ModelDienTu modelDienTu;

    public PresenterLogicDienTu(ViewDienTu viewDienTu){
        this.viewDienTu = viewDienTu;
        modelDienTu = new ModelDienTu();
    }

    @Override
    public void LayDanhSachDienTu() {
        List<DienTu> dienTuList = new ArrayList<>();

        List<ThuongHieu> thuongHieuList = modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachCacThuongHieuLon", "DANHSACHTHUONGHIEU");
        List<SanPham> sanPhamList =  modelDienTu.LayDanhSachSanPhamTop("LayDanhSachTopDienThoaiVaMayTinhBang", "TOPDIENTHOAIVAMAYTINHBANG");

        DienTu dienTu1 = new DienTu();
        dienTu1.setThuongHieuList(thuongHieuList);
        dienTu1.setSanPhamList(sanPhamList);
        dienTu1.setTenNoiBat("Thương hiệu");
        dienTu1.setTenTopNoiBat("Top điện thoại và máy tính bảng");
        dienTu1.setThuonghieu(true);
        dienTuList.add(dienTu1);

        List<ThuongHieu> topPhuKienList = modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachPhuKien", "DANHSACHPHUKIEN");
        List<SanPham> phuKienList = modelDienTu.LayDanhSachSanPhamTop("LayDanhSachTopPhuKien", "TOPPHUKIEN");

        DienTu dienTu2 = new DienTu();
        dienTu2.setThuongHieuList(topPhuKienList);
        dienTu2.setSanPhamList(phuKienList);
        dienTu2.setTenNoiBat("Phụ kiện");
        dienTu2.setTenTopNoiBat("Top phụ kiện");
        dienTu2.setThuonghieu(false);
        dienTuList.add(dienTu2);

        List<ThuongHieu> topTienIchList = modelDienTu.LayDanhSachThuongHieuLon("LayTopTienIch", "TOPTIENICH");
        List<SanPham> tienIchList = modelDienTu.LayDanhSachSanPhamTop("LayDanhSachTienIch", "DANHSACHTIENICH");

        DienTu dienTu3 = new DienTu();
        dienTu3.setThuongHieuList(topTienIchList);
        dienTu3.setSanPhamList(tienIchList);
        dienTu3.setTenNoiBat("Tiện ích");
        dienTu3.setTenTopNoiBat("Top Video & Tivi");
        dienTu3.setThuonghieu(false);
        dienTuList.add(dienTu3);

        if(thuongHieuList.size()>0){
            viewDienTu.HienThiDanhSach(dienTuList);
        }

    }

    @Override
    public void LayDanhSachLogoThuongHieu() {
        List<ThuongHieu> thuongHieuList = modelDienTu.LayDanhSachThuongHieuLon("LayLogoThuongHieuLon", "DANHSACHLOGOTHUONGHIEU");
        if(thuongHieuList.size()>0){
            viewDienTu.HienThiLogoThuongHieu(thuongHieuList);
        }else{
            viewDienTu.LoiLayDuLieu();
        }
    }

    @Override
    public void LayDanhSachSanPhamMoi() {
        List<SanPham> sanPhamList = modelDienTu.LayDanhSachSanPhamTop("LayDanhSachSanPhamMoi", "DANHSACHSANPHAMMOI");
        if(sanPhamList.size()>0){
            viewDienTu.HienThiSanPhamMoi(sanPhamList);
        }else{
            viewDienTu.LoiLayDuLieu();
        }
    }
}
