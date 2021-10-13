package com.example.nlushop.presenter.khuyenMai;

import com.example.nlushop.model.khuyenMai.ModelKhuyenMai;
import com.example.nlushop.model.objectClass.KhuyenMai;
import com.example.nlushop.view.trangChu.ViewKhuyenMai;

import java.util.List;

public class PresenterLogicKhuyenMai implements IPresenterKhuyenMai{
    ViewKhuyenMai viewKhuyenMai;
    ModelKhuyenMai modelKhuyenMai;

    public PresenterLogicKhuyenMai(ViewKhuyenMai viewKhuyenMai){
        this.viewKhuyenMai = viewKhuyenMai;
        modelKhuyenMai = new ModelKhuyenMai();
    };

    @Override
    public void LayDanhSachKhuyenMai() {
        List<KhuyenMai> khuyenMaiList = modelKhuyenMai.LayDanhSachSanPhamTheoKhuyenMai("LayDanhSachKhuyenMai", "DANHSACHKHUYENMAI");

        if(khuyenMaiList.size()>0){
            viewKhuyenMai.HienThiDanhSachKhuyenMai(khuyenMaiList);
        }

    }
}
