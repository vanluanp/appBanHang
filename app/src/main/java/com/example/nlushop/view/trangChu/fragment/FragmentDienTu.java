package com.example.nlushop.view.trangChu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nlushop.R;
import com.example.nlushop.adapter.AdapterDienTu;
import com.example.nlushop.adapter.AdapterThuongHieuLon;
import com.example.nlushop.adapter.AdapterThuongHieuLonDienTu;
import com.example.nlushop.adapter.AdapterTopDienThoaiDienTu;
import com.example.nlushop.model.objectClass.DienTu;
import com.example.nlushop.model.objectClass.SanPham;
import com.example.nlushop.model.objectClass.ThuongHieu;
import com.example.nlushop.presenter.trangchu_dientu.PresenterLogicDienTu;
import com.example.nlushop.view.trangChu.ViewDienTu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentDienTu extends Fragment implements ViewDienTu {
    RecyclerView recyclerView, recyclerTopThuongHieuLon, recyclerHangMoiVe;
    List<DienTu> dienTuList;
    PresenterLogicDienTu presenterLogicDienTu;
    ImageView imSanPham1, imSanPham2, imSanPham3;
    TextView txtSanPham1, txtSanPham2, txtSanPham3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dientu, container, false);

        recyclerView = view.findViewById(R.id.recyclerDienTu);
        recyclerTopThuongHieuLon = view.findViewById(R.id.recyclerTopThuongHieuLon);
        recyclerHangMoiVe = view.findViewById(R.id.recyclerHangMoiVe);
        imSanPham1 = view.findViewById(R.id.imSanPham1);
        imSanPham2 = view.findViewById(R.id.imSanPham2);
        imSanPham3 = view.findViewById(R.id.imSanPham3);
        txtSanPham1 = view.findViewById(R.id.txtSanPham1);
        txtSanPham2 = view.findViewById(R.id.txtSanPham2);
        txtSanPham3 = view.findViewById(R.id.txtSanPham3);

        presenterLogicDienTu = new PresenterLogicDienTu(this);

        dienTuList = new ArrayList<>();

        presenterLogicDienTu.LayDanhSachDienTu();
        presenterLogicDienTu.LayDanhSachLogoThuongHieu();
        presenterLogicDienTu.LayDanhSachSanPhamMoi();
        return view;
    }

    @Override
    public void HienThiDanhSach(List<DienTu> dienTus) {
        dienTuList = dienTus;

        AdapterDienTu adapterDienTu = new AdapterDienTu(getContext(), dienTuList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienTu);

        adapterDienTu.notifyDataSetChanged();

    }

    @Override
    public void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieuList) {
        AdapterThuongHieuLonDienTu adapterThuongHieuLonDienTu = new AdapterThuongHieuLonDienTu(getContext(), thuongHieuList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);

        recyclerTopThuongHieuLon.setLayoutManager(layoutManager);
        recyclerTopThuongHieuLon.setAdapter(adapterThuongHieuLonDienTu);

        adapterThuongHieuLonDienTu.notifyDataSetChanged();
    }

    @Override
    public void LoiLayDuLieu() {

    }

    @Override
    public void HienThiSanPhamMoi(List<SanPham> sanPhamList) {
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(getContext(), R.layout.custom_layout_topdienthoaivamaytinhbang, sanPhamList, 0);

        RecyclerView.LayoutManager layoutManagerTopSP = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerHangMoiVe.setLayoutManager(layoutManagerTopSP);
        recyclerHangMoiVe.setAdapter(adapterTopDienThoaiDienTu);

        adapterTopDienThoaiDienTu.notifyDataSetChanged();

        Random random = new Random();

        int vitri1 =random.nextInt(sanPhamList.size());
        if(vitri1 > 30){
            vitri1 = 7;
        }
        Picasso.get().load(sanPhamList.get(vitri1).getANHLON()).fit().centerInside().into(imSanPham1);
        txtSanPham1.setText(sanPhamList.get(vitri1).getTENSP());

        int vitri2 =random.nextInt(sanPhamList.size());
        if(vitri2 > 30){
            vitri2 = 8;
        }
        Picasso.get().load(sanPhamList.get(vitri2).getANHLON()).fit().centerInside().into(imSanPham2);
        txtSanPham2.setText(sanPhamList.get(vitri2).getTENSP());

        int vitri3 =random.nextInt(sanPhamList.size());
        if(vitri3 > 30){
            vitri3 = 9;
        }
        Picasso.get().load(sanPhamList.get(vitri3).getANHLON()).fit().centerInside().into(imSanPham3);
        txtSanPham3.setText(sanPhamList.get(vitri3).getTENSP());
    }
}
