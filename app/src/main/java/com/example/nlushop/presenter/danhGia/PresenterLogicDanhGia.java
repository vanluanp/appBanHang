package com.example.nlushop.presenter.danhGia;

import android.view.View;
import android.widget.ProgressBar;

import com.example.nlushop.model.danhGia.ModelDanhGia;
import com.example.nlushop.model.objectClass.DanhGia;
import com.example.nlushop.view.danhGia.ViewDanhGia;

import java.util.List;

public class PresenterLogicDanhGia implements IPresenterDanhGia{
    ViewDanhGia viewDanhGia;
    ModelDanhGia modelDanhGia;

    public PresenterLogicDanhGia(ViewDanhGia viewDanhGia){
        this.viewDanhGia = viewDanhGia;
        modelDanhGia = new ModelDanhGia();
    }

    @Override
    public void ThemDanhGia(DanhGia danhGia) {
        boolean kiemtra = modelDanhGia.ThemDanhGia(danhGia);
        if(kiemtra){
            viewDanhGia.DanhGiaThanhCong();
        }else{
            viewDanhGia.DanhGiaThatBai();
        }
    }

    @Override
    public void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        List<DanhGia> danhGiaList = modelDanhGia.LayDanhSachDanhGiaCuaSanPham(masp, limit);

        if(danhGiaList.size()>0){
            viewDanhGia.HienThiDanhSachDanhGiaTheoSanPham(danhGiaList);
        }else{
            progressBar.setVisibility(View.GONE);
        }

    }
}
