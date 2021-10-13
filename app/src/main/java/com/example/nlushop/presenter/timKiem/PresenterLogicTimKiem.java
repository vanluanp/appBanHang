package com.example.nlushop.presenter.timKiem;

import com.example.nlushop.model.objectClass.SanPham;
import com.example.nlushop.model.timKiem.ModelTimKiem;
import com.example.nlushop.view.timKiem.ViewTimKiem;

import java.util.List;

public class PresenterLogicTimKiem implements IPresenterTimKiem{
    ViewTimKiem viewTimKiem;
    ModelTimKiem modelTimKiem;

    public PresenterLogicTimKiem(ViewTimKiem viewTimKiem){
        this.viewTimKiem = viewTimKiem;
        modelTimKiem = new ModelTimKiem();
    }

    @Override
    public void TimKiemSanPhamTheoTenSP(String tensp, int limit) {
        List<SanPham> sanPhamList = modelTimKiem.TimKiemSanPhamTheoTen(tensp, "DANHSACHSANPHAM", "TimKiemSanPhamTheoTenSP", limit);

        if(sanPhamList.size()>0){
            viewTimKiem.TimKiemThanhCong(sanPhamList);
        }else{
            viewTimKiem.TimKiemThatBai();
        }
    }
}
