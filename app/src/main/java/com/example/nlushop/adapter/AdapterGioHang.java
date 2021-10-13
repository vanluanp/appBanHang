package com.example.nlushop.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nlushop.R;
import com.example.nlushop.model.gioHang.ModelGioHang;
import com.example.nlushop.model.objectClass.SanPham;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolderGioHang> {
    Context context;
    List<SanPham> sanPhamList;
    ModelGioHang modelGioHang;

    public AdapterGioHang(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {
        TextView txtTenTieuDeGioHang, txtGiaTienGioHang;
        ImageView imHinhGioHang, imXoaSanPhamGioHang;
        ImageView imGiamSoLuongSPTrongGioHang, imTangSoLuongSPTrongGioHang;
        TextView txtSoLuongSPTrongGioHang;

        public ViewHolderGioHang(@NonNull View itemView) {
            super(itemView);

            txtTenTieuDeGioHang = itemView.findViewById(R.id.txtTieuDeGioHang);
            txtGiaTienGioHang = itemView.findViewById(R.id.txtGiaGioHang);
            imHinhGioHang = itemView.findViewById(R.id.imHinhGioHang);
            imXoaSanPhamGioHang = itemView.findViewById(R.id.imXoaGioHang);
            txtSoLuongSPTrongGioHang = itemView.findViewById(R.id.txtSoLuongSPTrongGioHang);
            imGiamSoLuongSPTrongGioHang = itemView.findViewById(R.id.imGiamSoLuongSPTrongGioHang);
            imTangSoLuongSPTrongGioHang = itemView.findViewById(R.id.imTangSoLuongSPTrongGioHang);
        }
    }

    @NonNull
    @Override
    public ViewHolderGioHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_giohang, parent, false);

        ViewHolderGioHang viewHolderGioHang = new ViewHolderGioHang(view);

        return viewHolderGioHang;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGioHang holder, int position) {
        SanPham sanPham = sanPhamList.get(position);

        holder.txtTenTieuDeGioHang.setText(sanPham.getTENSP());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        holder.txtGiaTienGioHang.setText(gia + " VND");

        byte[] hinhSanpham = sanPham.getHinhGioHang();
        Bitmap bitmapHinhGioHang = BitmapFactory.decodeByteArray(hinhSanpham, 0, hinhSanpham.length);//đọc mảng byte ra bitmap
        holder.imHinhGioHang.setImageBitmap(bitmapHinhGioHang);//từ ảnh ra bitmap rồi ra byte[] lưu vào sqllite, từ sqllite gọi ngược byte[] ra bitmap rồi set cho imHinhGioHang

        holder.imXoaSanPhamGioHang.setTag(sanPham.getMASP());

        holder.imXoaSanPhamGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelGioHang modelGioHang = new ModelGioHang();
                modelGioHang.MoKetNoiSQL(context);
                modelGioHang.XoaSanPhamTrongGioHang((int) v.getTag());//Xử lý xóa sp
                sanPhamList.remove(position);//Xóa sp trong list
                notifyDataSetChanged();//load lại data
            }
        });

        holder.txtSoLuongSPTrongGioHang.setText(String.valueOf(sanPham.getSOLUONG()));
        holder.imGiamSoLuongSPTrongGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtSoLuongSPTrongGioHang.getText().toString());

                if (soluong > 1) {
                    soluong--;
                }
                modelGioHang.CapNhatSoLuongSanPhamGioHang(sanPham.getMASP(), soluong);
                holder.txtSoLuongSPTrongGioHang.setText(String.valueOf(soluong));
            }
        });

        holder.imTangSoLuongSPTrongGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtSoLuongSPTrongGioHang.getText().toString());
                int soluongtonkho = sanPham.getSOLUONGTONKHO();

                if (soluong < soluongtonkho) {
                    soluong++;
                } else {
                    Toast.makeText(context, "Số lượng sản phẩm bạn mua quá số lượng có trong kho của cửa hàng !", Toast.LENGTH_SHORT).show();
                }

                modelGioHang.CapNhatSoLuongSanPhamGioHang(sanPham.getMASP(), soluong);
                holder.txtSoLuongSPTrongGioHang.setText(String.valueOf(soluong));
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

}
