package com.example.nlushop.view.trangChu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nlushop.R;
import com.example.nlushop.adapter.AdapterTopDienThoaiDienTu;
import com.example.nlushop.model.objectClass.SanPham;
import com.example.nlushop.presenter.trangchu_dientu.PresenterLogicNongNghiep;
import com.example.nlushop.view.trangChu.ViewNongNghiep;

import java.util.List;

public class FragmentThuongHieu extends Fragment implements ViewNongNghiep {
    RecyclerView recyclerNN1, recyclerNN2, recyclerNN3;
    PresenterLogicNongNghiep presenterLogicNongNghiep;
    int check;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_thuonghieu, container, false);

        recyclerNN1 = view.findViewById(R.id.recyclerNN1);
        recyclerNN2 = view.findViewById(R.id.recyclerNN2);
        recyclerNN3 = view.findViewById(R.id.recyclerNN3);

        presenterLogicNongNghiep = new PresenterLogicNongNghiep(this);

        presenterLogicNongNghiep.LayDanhSachNongNghiep(148);//vì lười nên set cứng cho xong lẹ :v
        presenterLogicNongNghiep.LayDanhSachNongNghiep(149);
        presenterLogicNongNghiep.LayDanhSachNongNghiep(150);

        return view;
    }

    @Override
    public void HienThiSanPhamNongNghiep(List<SanPham> sanPhamList, int maloai) {
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(getContext(), R.layout.custom_layout_topdienthoaivamaytinhbang, sanPhamList, 0);

        RecyclerView.LayoutManager layoutManagerTopSP = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        if(maloai == 148){
            recyclerNN1.setLayoutManager(layoutManagerTopSP);
            recyclerNN1.setAdapter(adapterTopDienThoaiDienTu);
        }
        if(maloai == 149){
            recyclerNN2.setLayoutManager(layoutManagerTopSP);
            recyclerNN2.setAdapter(adapterTopDienThoaiDienTu);
        }
        if(maloai == 150){
            recyclerNN3.setLayoutManager(layoutManagerTopSP);
            recyclerNN3.setAdapter(adapterTopDienThoaiDienTu);
        }

        adapterTopDienThoaiDienTu.notifyDataSetChanged();
    }
}
