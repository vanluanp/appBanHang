package com.example.nlushop.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nlushop.R;
import com.example.nlushop.model.objectClass.ThuongHieu;
import com.example.nlushop.view.hienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterThuongHieuLon extends RecyclerView.Adapter<AdapterThuongHieuLon.ViewHolderThuongHieu>{
    Context context;
    List<ThuongHieu> thuongHieuList;
    boolean kiemtra;

    public AdapterThuongHieuLon(Context context, List<ThuongHieu> thuongHieuList, boolean kiemtra){
        this.context = context;
        this.thuongHieuList = thuongHieuList;
        this.kiemtra = kiemtra;
    }

    //Hàm chạy thứ 2, sau khi hàm onCreateViewHolder() trả kết quả
    public class ViewHolderThuongHieu extends RecyclerView.ViewHolder {
        TextView txtTieuDeThuongHieu;
        ImageView imHinhThuongHieu;
        ProgressBar progressBar;
        LinearLayout linearLayout;
        public ViewHolderThuongHieu(@NonNull View itemView) {
            super(itemView);

            txtTieuDeThuongHieu = itemView.findViewById(R.id.txtTieuDeThuongHieuLonDienTu);
            imHinhThuongHieu = itemView.findViewById(R.id.imHinhThuongHieuLonDienTu);
            progressBar = itemView.findViewById(R.id.progress_bar_download);
            linearLayout = itemView.findViewById(R.id.lnThuongHieuLon);
        }
    }

    //Hàm chạy đầu tiên, khởi tạo view
    @NonNull
    @Override
    public ViewHolderThuongHieu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycler_thuonghieulon, parent, false);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_recycler_thuonghieulon, parent, false);

        ViewHolderThuongHieu viewHolderThuongHieu = new ViewHolderThuongHieu(view);

        return viewHolderThuongHieu;
    }

    //Hàm chạy thứ 3
    @Override
    //ViewHolder này nhận từ ViewHolder từ hàm chạy thứ 2
    public void onBindViewHolder(@NonNull ViewHolderThuongHieu holder, int position) {
        final ThuongHieu thuongHieu = thuongHieuList.get(position);
        holder.txtTieuDeThuongHieu.setText(thuongHieu.getTENTHUONGHIEU());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity)context) .getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                HienThiSanPhamTheoDanhMucActivity hienThiSanPhamTheoDanhMucActivity = new HienThiSanPhamTheoDanhMucActivity();
                Bundle bundle = new Bundle();
                bundle.putInt("MALOAI", thuongHieu.getMATHUONGHIEU());
                bundle.putBoolean("KIEMTRA", kiemtra);
                bundle.putString("TENLOAI", thuongHieu.getTENTHUONGHIEU());

                hienThiSanPhamTheoDanhMucActivity.setArguments(bundle);

                fragmentTransaction.addToBackStack("TrangChuActivity");//add quay trở về
                fragmentTransaction.replace(R.id.themFragment, hienThiSanPhamTheoDanhMucActivity);
                fragmentTransaction.commit();
//                Intent iHienThiSanPhamTheoDanhMuc = new Intent(context, HienThiSanPhamTheoDanhMucActivity.class);
//                iHienThiSanPhamTheoDanhMuc.putExtra("MALOAI", thuongHieu.getMATHUONGHIEU());
//                iHienThiSanPhamTheoDanhMuc.putExtra("TENLOAI", thuongHieu.getTENTHUONGHIEU());
//                iHienThiSanPhamTheoDanhMuc.putExtra("KIEMTRA", kiemtra);
//                context.startActivity(iHienThiSanPhamTheoDanhMuc);
//                Log.d("dc", "abc");
//                Toast.makeText(context, "hey! "+ thuongHieu.getTENTHUONGHIEU(), Toast.LENGTH_SHORT).show();
            }
        });

        Picasso.get().load(thuongHieu.getHINHTHUONGHIEU()).resize(120,120).into(holder.imHinhThuongHieu, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return thuongHieuList.size();
    }
}
