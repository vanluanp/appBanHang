package com.example.nlushop.presenter.chiTietSanPham;

import android.content.Context;

import com.example.nlushop.model.chiTietSanPham.ModelChiTietSanPham;
import com.example.nlushop.model.gioHang.ModelGioHang;
import com.example.nlushop.model.objectClass.DanhGia;
import com.example.nlushop.model.objectClass.SanPham;
import com.example.nlushop.view.chiTietSanPham.ViewChiTietSanPham;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham{
    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;
    ModelGioHang modelGioHang;

    public PresenterLogicChiTietSanPham(){
        modelGioHang = new ModelGioHang();
    }

    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham){
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();
        modelGioHang = new ModelGioHang();
    }

    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = modelChiTietSanPham.LayChiTietSanPham("LaySanPhamVaChiTietTheoMaSP", "CHITIETSANPHAM", masp);
        if(sanPham.getMASP()>0){
            String[] linkhinhanh = sanPham.getANHNHO().split(",");
            viewChiTietSanPham.HienThiSliderSanPham(linkhinhanh);
            viewChiTietSanPham.HienThiChiTietSanPham(sanPham);
        }
    }

    @Override
    public void LayDanhSachDanhGiaTheoSanPham(int masp, int limit) {
        List<DanhGia> danhGiaList = modelChiTietSanPham.LayDanhSachDanhGiaCuaSanPham(masp, limit);

        if(danhGiaList.size()>0){
            viewChiTietSanPham.HienThiDanhGia(danhGiaList);
        }
    }

    @Override
    public void ThemGioHang(SanPham sanPham, Context context) {
        modelGioHang.MoKetNoiSQL(context);//Phải mở kết nối nếu ko sẽ có lỗi
        boolean kiemtra = modelGioHang.ThemGioHang(sanPham);
        if(kiemtra){
            viewChiTietSanPham.ThemGioHangThanhCong();
        }else{
            viewChiTietSanPham.ThemGioHangThatBai();
        }
    }

    @Override
    public void LayDanhSachSanPhamSoSanh(int masp) {
        List<SanPham> sanPhamList = modelChiTietSanPham.LayDanhSachSanPhamSoSanh("LayDanhSachSanPhamSoSanh", "DANHSACHSANPHAMSOSANH", masp);

        if(sanPhamList.size()>0){
            viewChiTietSanPham.HienThiSanPhamSoSanh(sanPhamList);
        }
    }

    public int DemSanPhamCoTrongGioHang(Context context){
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        int dem = sanPhamList.size();
        return dem;
    }

}
