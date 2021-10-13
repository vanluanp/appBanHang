package com.example.nlushop.view.trangChu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nlushop.R;
import com.example.nlushop.adapter.AdapterNoiBat;

import java.util.ArrayList;
import java.util.List;

public class FragmentNoiBat extends Fragment {
    RecyclerView recyclerView;
    AdapterNoiBat adapterNoiBat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_noibat, container, false);

        recyclerView = view.findViewById(R.id.recycleNoiBat);
        List<String> dulieu = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String ten = "Nổi bật " + i;
            dulieu.add(ten);
        }

        //Lướt theo danh sách dạng ngang
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        //Lướt theo dạng dọc
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //Dạng grid, spancount là số cột trên 1 dòng
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        adapterNoiBat = new AdapterNoiBat(getActivity(), dulieu);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterNoiBat);

        adapterNoiBat.notifyDataSetChanged();
        return view;
    }
}
