package com.example.nlushop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nlushop.R;
import com.example.nlushop.model.objectClass.ThuongHieu;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterThuongHieuLonDienTu extends RecyclerView.Adapter<AdapterThuongHieuLonDienTu.ViewHolderThuongHieuLon> {
    Context context;
    List<ThuongHieu> thuongHieuList;

    public AdapterThuongHieuLonDienTu(Context context, List<ThuongHieu> thuongHieuList){
        this.context = context;
        this.thuongHieuList = thuongHieuList;
    }

    public class ViewHolderThuongHieuLon extends RecyclerView.ViewHolder {
        ImageView imLogoTopThuongHieuLon;

        public ViewHolderThuongHieuLon(@NonNull View itemView) {
            super(itemView);
            imLogoTopThuongHieuLon = itemView.findViewById(R.id.imLogoTopThuongHieuLon);

        }
    }

    @NonNull
    @Override
    public ViewHolderThuongHieuLon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycler_topthuonghieulon_dientu, parent, false);

        ViewHolderThuongHieuLon viewHolderThuongHieuLon = new ViewHolderThuongHieuLon(view);
        return viewHolderThuongHieuLon;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderThuongHieuLon holder, int position) {
        ThuongHieu thuongHieu = thuongHieuList.get(position);

        Picasso.get().load(thuongHieu.getHINHTHUONGHIEU()).resize(120, 60).centerInside().into(holder.imLogoTopThuongHieuLon);
    }

    @Override
    public int getItemCount() {
        return thuongHieuList.size();
    }

}
