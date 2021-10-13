package com.example.nlushop.presenter.dangKy;

import com.example.nlushop.model.dangNhap_dangKy.ModelDangKy;
import com.example.nlushop.model.objectClass.NhanVien;
import com.example.nlushop.view.dangNhap_dangKy.ViewDangKy;

public class PresenterLogicDangKy implements IPresenterDangKy{
    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public PresenterLogicDangKy(ViewDangKy viewDangKy){
        this.viewDangKy = viewDangKy;
        modelDangKy = new ModelDangKy();
    }

    @Override
    public void ThucHienDangKy(NhanVien nhanVien) {
        boolean kiemtra = modelDangKy.DangKyThanhVien(nhanVien);
        if(kiemtra){
            viewDangKy.DangKyThanhCong();
        }else{
            viewDangKy.DangKyThatBai();
        }

    }
}
