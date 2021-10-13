package com.example.nlushop.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nlushop.R;
import com.example.nlushop.model.objectClass.ChiTietKhuyenMai;
import com.example.nlushop.model.objectClass.SanPham;
import com.example.nlushop.view.chiTietSanPham.ChiTietSanPhamActivity;
import com.example.nlushop.view.chiTietSanPham.SoSanhActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterTopDienThoaiDienTu extends RecyclerView.Adapter<AdapterTopDienThoaiDienTu.ViewHolderTopDienThoai> {
    Context context;
    List<SanPham> sanPhamList;
    int layout, sosanh;
    SanPham sanPhamSS;

    public AdapterTopDienThoaiDienTu(Context context, int layout, List<SanPham> sanPhamList, int sosanh) {//sosanh này ko cần thiết mà lười xóa
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
        this.sosanh = sosanh;
    }

    public AdapterTopDienThoaiDienTu(Context context, int layout, List<SanPham> sanPhamList, int sosanh, SanPham sanPhamSS) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
        this.sosanh = sosanh;
        this.sanPhamSS = sanPhamSS;
    }

    public class ViewHolderTopDienThoai extends RecyclerView.ViewHolder {
        ImageView imHinhSanPham;
        TextView txtTenSP, txtGiaTien, txtGiamGia, txtPhanTramGiam;
        CardView cardView;
        Button btnSoSanh;

        public ViewHolderTopDienThoai(@NonNull View itemView) {
            super(itemView);

            imHinhSanPham = itemView.findViewById(R.id.imTopDienThoaiDienTu);
            txtTenSP = itemView.findViewById(R.id.txtTieuDeTopDienThoaiDienTu);
            txtGiaTien = itemView.findViewById(R.id.txtGiaDienTu);
            txtGiamGia = itemView.findViewById(R.id.txtGiamGiaDienTu);
            txtPhanTramGiam = itemView.findViewById(R.id.txtPhanTramGiam);
            cardView = itemView.findViewById(R.id.cardView);
            btnSoSanh = itemView.findViewById(R.id.btnSoSanh);
        }
    }

    @NonNull
    @Override
    public ViewHolderTopDienThoai onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout, parent, false);

        ViewHolderTopDienThoai viewHolderTopDienThoai = new ViewHolderTopDienThoai(view);

        return viewHolderTopDienThoai;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTopDienThoai holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        Picasso.get().load(sanPham.getANHLON()).into(holder.imHinhSanPham);
        holder.txtTenSP.setText(sanPham.getTENSP());

        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();
        int giatien = sanPham.getGIA();

        if (chiTietKhuyenMai != null) {
            NumberFormat numberFormat = new DecimalFormat("###,###");
            String giaGoc = numberFormat.format(giatien);
            int phantramkm = chiTietKhuyenMai.getPHANTRAMKM();

            holder.txtGiamGia.setVisibility(View.VISIBLE);
            holder.txtPhanTramGiam.setVisibility(View.VISIBLE);
            holder.txtPhanTramGiam.setText("(-" + phantramkm + "%)");
            holder.txtGiamGia.setPaintFlags(holder.txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);//set dấu gạch ngang cho giá giảm
            holder.txtGiamGia.setText(giaGoc + " VND");
            holder.txtGiamGia.setTextSize(12f);

            giatien = giatien - ((giatien * phantramkm) / 100);
        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        holder.txtGiaTien.setText(gia + " VND");

        holder.cardView.setTag(sanPham.getMASP());//set Tag
//        holder.btnSoSanh.setTag(sanPham.getMASP());

        if(sosanh==1){
            holder.btnSoSanh.setVisibility(View.VISIBLE);
            holder.btnSoSanh.bringToFront();
            holder.btnSoSanh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent iSoSanh = new Intent(context, SoSanhActivity.class);

                    iSoSanh.putExtra("objectsosanh", sanPhamSS);
                    iSoSanh.putExtra("objectcurrent", sanPham);

//                iSoSanh.putExtra("masp", (int) v.getTag());
                    context.startActivity(iSoSanh);
                }
            });
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietSanPham = new Intent(context, ChiTietSanPhamActivity.class);
                iChiTietSanPham.putExtra("masp", (int) v.getTag());
                context.startActivity(iChiTietSanPham);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

}
