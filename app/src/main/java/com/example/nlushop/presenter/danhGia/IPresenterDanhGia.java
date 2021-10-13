package com.example.nlushop.presenter.danhGia;

import android.widget.ProgressBar;

import com.example.nlushop.model.objectClass.DanhGia;

public interface IPresenterDanhGia {
    void ThemDanhGia(DanhGia danhGia);
    void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar);
}
