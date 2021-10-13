package com.example.nlushop.view.trangChu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nlushop.R;
import com.example.nlushop.adapter.AdapterDanhSachKhuyenMai;
import com.example.nlushop.model.objectClass.KhuyenMai;
import com.example.nlushop.presenter.khuyenMai.PresenterLogicKhuyenMai;
import com.example.nlushop.view.trangChu.ViewKhuyenMai;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FragmentCTKhuyenMai extends Fragment implements ViewKhuyenMai {
    LinearLayout lnHinhKhuyenMai;
    RecyclerView recyclerDanhSachkhuyenMai;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_ctkhuyenmai, container, false);

        lnHinhKhuyenMai = view.findViewById(R.id.lnHinhKhuyenMai);
        recyclerDanhSachkhuyenMai = view.findViewById(R.id.recyclerDanhSachKhuyenMai);

        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
        presenterLogicKhuyenMai.LayDanhSachKhuyenMai();

        return view;
    }

    @Override
    public void HienThiDanhSachKhuyenMai(List<KhuyenMai> khuyenMais) {
        int demhinh = khuyenMais.size();
        if (demhinh > 5) {
            demhinh = 5;
        }

        lnHinhKhuyenMai.removeAllViews();
        for (int i = 0; i < demhinh; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);//set center inside tại đây, không nên set trong Picasso
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250);
            layoutParams.setMargins(0,10,0,0);
            imageView.setLayoutParams(layoutParams);

            Picasso.get().load(khuyenMais.get(i).getHINHKHUYENMAI()).resize(750, 250).into(imageView);

            lnHinhKhuyenMai.addView(imageView);
        }

        AdapterDanhSachKhuyenMai adapterDanhSachKhuyenMai = new AdapterDanhSachKhuyenMai(getContext(), khuyenMais);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerDanhSachkhuyenMai.setLayoutManager(layoutManager);
        recyclerDanhSachkhuyenMai.setAdapter(adapterDanhSachKhuyenMai);

        adapterDanhSachKhuyenMai.notifyDataSetChanged();
    }
}
