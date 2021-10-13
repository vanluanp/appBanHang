package com.example.nlushop.presenter.trangchu_dientu;

import com.example.nlushop.model.objectClass.SanPham;
import com.example.nlushop.model.trangchu_dientu.ModelNongNghiep;
import com.example.nlushop.view.trangChu.ViewNongNghiep;

import java.util.List;

public class PresenterLogicNongNghiep implements IPresenterNongNghiep{
    ViewNongNghiep viewNongNghiep;
    ModelNongNghiep modelNongNghiep;

    public PresenterLogicNongNghiep(ViewNongNghiep viewNongNghiep){
        this.viewNongNghiep = viewNongNghiep;
        this.modelNongNghiep = new ModelNongNghiep();
    }
    @Override
    public void LayDanhSachNongNghiep(int maloai) {
        List<SanPham> sanPhamList = modelNongNghiep.LayDanhSachSanPhamNongNghiep("LayDanhSachSanPhamNongNghiep", "DANHSACHNONGNGHIEP", maloai);
        if(sanPhamList.size()>0){
            viewNongNghiep.HienThiSanPhamNongNghiep(sanPhamList, maloai);
        }else{

        }
    }
}
