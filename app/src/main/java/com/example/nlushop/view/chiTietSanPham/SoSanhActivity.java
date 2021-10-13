package com.example.nlushop.view.chiTietSanPham;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nlushop.R;
import com.example.nlushop.model.objectClass.ChiTietSanPham;
import com.example.nlushop.model.objectClass.SanPham;

import java.util.List;

public class SoSanhActivity extends AppCompatActivity {

    LinearLayout lnSoSanh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sosanh);

        lnSoSanh = findViewById(R.id.lnSoSanh);

        SanPham sanPhamSS = (SanPham) getIntent().getSerializableExtra("objectsosanh");//sản phẩm trang hiện tại
        SanPham sanPhamCurrent = (SanPham) getIntent().getSerializableExtra("objectcurrent");//sản phẩm hiện tại được chọn để so sánh

        List<ChiTietSanPham> chiTietSanPhamListSS = sanPhamSS.getChiTietSanPhamList();
        List<ChiTietSanPham> chiTietSanPhamListCurrent = sanPhamCurrent.getChiTietSanPhamList();

        lnSoSanh.removeAllViews();//xóa view cũ đi, ko là nó lặp lại
        TextView txtSoSanh = new TextView(this);
        txtSoSanh.setText("Thông số kĩ thuật");//set lại text khi xóa view
        txtSoSanh.setTypeface(null, Typeface.BOLD);
        LinearLayout.LayoutParams layoutParamsTS = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsTS.setMargins(0, 20, 0, 0);
        txtSoSanh.setLayoutParams(layoutParamsTS);
        txtSoSanh.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        lnSoSanh.addView(txtSoSanh);

        //Tên sp
        LinearLayout lnTenSP = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParamsSP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsSP.setMargins(0, 20, 0, 20);
        lnTenSP.setLayoutParams(layoutParamsSP);
        lnTenSP.setOrientation(LinearLayout.HORIZONTAL);

        TextView txtTenSP0 = new TextView(this);
        txtTenSP0.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        txtTenSP0.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        txtTenSP0.setText("Chi tiết");

        TextView txtTenSP1 = new TextView(this);
        LinearLayout.LayoutParams layoutParamT1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamT1.setMargins(10, 0, 0, 0);
        layoutParamT1.weight = 1.0f;
        txtTenSP1.setLayoutParams(layoutParamT1);
        txtTenSP1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        txtTenSP1.setText(sanPhamSS.getTENSP());

        TextView txtTenSP2 = new TextView(this);
        LinearLayout.LayoutParams layoutParamT2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParamT2.setMargins(10, 0, 20, 0);
        layoutParamT2.weight = 1.0f;
        txtTenSP2.setLayoutParams(layoutParamT2);
        txtTenSP2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        txtTenSP2.setText(sanPhamCurrent.getTENSP());

        lnTenSP.addView(txtTenSP0);
        lnTenSP.addView(txtTenSP1);
        lnTenSP.addView(txtTenSP2);
        lnSoSanh.addView(lnTenSP);

        //Thông số chi tiết
        int dem = 0;
        boolean duyet = true;
        if (chiTietSanPhamListSS.size() > chiTietSanPhamListCurrent.size()) {
            dem = chiTietSanPhamListSS.size();
            duyet = true;
        } else {
            dem = chiTietSanPhamListCurrent.size();
            duyet = false;

        }

        for (int i = 0; i < dem; i++) {//duyet theo size lớn hơn
            LinearLayout lnChiTiet = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10, 0, 10, 10);
            lnChiTiet.setLayoutParams(layoutParams);
            lnChiTiet.setOrientation(LinearLayout.HORIZONTAL);
            //set cho trường hợp đều có cả hai bên là N/A (tức là khi duyệt đến vị trí đó 2 sản phẩm đều có N/A
            // nên phải set thêm layout nếu ko chỉ hiện cho một sản phẩm,
            LinearLayout lnChiTiet2 = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams2.setMargins(10, 0, 10, 10);
            lnChiTiet2.setLayoutParams(layoutParams2);
            lnChiTiet2.setOrientation(LinearLayout.HORIZONTAL);

            TextView txtTenThongSo = new TextView(this);
            txtTenThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
//            txtTenThongSo.setText(chiTietSanPhamListSS.get(i).getTENCHITIET());
            //set cho trường hợp đều có cả hai bên là N/A
            //vì một lần linenear chỉ addview dc 1 cái textview ban đầu, nên phải thêm cái để lưu thèn sau.
            TextView txtTenThongSo2 = new TextView(this);
            txtTenThongSo2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

            TextView txtGiaTriThongSo1 = new TextView(this);
            LinearLayout.LayoutParams layoutParamTS1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParamTS1.setMargins(20, 0, 0, 0);
            layoutParamTS1.weight = 1.0f;
            txtGiaTriThongSo1.setLayoutParams(layoutParamTS1);
//            txtGiaTriThongSo1.setText(chiTietSanPhamListSS.get(i).getGIATRI());
            //set cho trường hợp đều có cả hai bên là N/A
            TextView txtGiaTriThongSo12 = new TextView(this);
            LinearLayout.LayoutParams layoutParamTS12 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParamTS12.setMargins(20, 0, 0, 0);
            layoutParamTS12.weight = 1.0f;
            txtGiaTriThongSo12.setLayoutParams(layoutParamTS12);

            TextView txtGiaTriThongSo2 = new TextView(this);
            LinearLayout.LayoutParams layoutParamTS2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParamTS2.setMargins(20, 0, 0, 0);
            layoutParamTS2.weight = 1.0f;
            txtGiaTriThongSo2.setLayoutParams(layoutParamTS2);
//            txtGiaTriThongSo2.setText(chiTietSanPhamListCurrent.get(i).getGIATRI());
            //set cho trường hợp đều có cả hai bên là N/A
            TextView txtGiaTriThongSo22 = new TextView(this);
            LinearLayout.LayoutParams layoutParamTS22 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParamTS22.setMargins(20, 0, 0, 0);
            layoutParamTS22.weight = 1.0f;
            txtGiaTriThongSo22.setLayoutParams(layoutParamTS22);

            boolean check = false;
            if (duyet) {//tức chiTietSanPhamListSS lớn hơn, duyệt thèn nhỏ hơn
                for (int j = 0; j < chiTietSanPhamListCurrent.size(); j++) {
                    if (chiTietSanPhamListSS.get(i).getTENCHITIET().equalsIgnoreCase(chiTietSanPhamListCurrent.get(j).getTENCHITIET())) {
                        txtTenThongSo.setText(chiTietSanPhamListSS.get(i).getTENCHITIET());
                        txtGiaTriThongSo1.setText(chiTietSanPhamListSS.get(i).getGIATRI());
                        txtGiaTriThongSo2.setText(chiTietSanPhamListCurrent.get(j).getGIATRI());
                        check = true;
                        //Nếu có case1 này xảy ra thì ko có case2
                    }
                }
                if (check == false) {//nếu chiTietSanPhamListSS tại i không bằng tất cả vị trí nào của chiTietSanPhamListCurrent
                    txtTenThongSo.setText(chiTietSanPhamListSS.get(i).getTENCHITIET());
                    txtGiaTriThongSo1.setText(chiTietSanPhamListSS.get(i).getGIATRI());
                    txtGiaTriThongSo2.setText("N/A");
                    //case2 xảy ra thì ko có case1
                }
                boolean check2 = false;
                if (i < chiTietSanPhamListCurrent.size()) {
                    for (int j = 0; j < chiTietSanPhamListSS.size(); j++) {
                        if (chiTietSanPhamListCurrent.get(i).getTENCHITIET().equalsIgnoreCase(chiTietSanPhamListSS.get(j).getTENCHITIET())) {
                            check2 = true;
                        }
                    }
                    if (check2 == false) {//nếu chiTietSanPhamListCurrent tại i nó không bằng tất cả vị trí của chiTietSanPhamListSS
                        txtTenThongSo2.setText(chiTietSanPhamListCurrent.get(i).getTENCHITIET());
                        //case3 là độc lập xảy ra tùy có khác các vị trí còn lại hay ko, nên set layout riêng cho nó
                        txtGiaTriThongSo12.setText("N/A");
                        txtGiaTriThongSo22.setText(chiTietSanPhamListCurrent.get(i).getGIATRI());
                    }
                }

            } else {//chiTietSanPhamListCurrent lớn hơn
                for (int j = 0; j < chiTietSanPhamListSS.size(); j++) {
                    if (chiTietSanPhamListCurrent.get(i).getTENCHITIET().equalsIgnoreCase(chiTietSanPhamListSS.get(j).getTENCHITIET())) {
                        txtTenThongSo.setText(chiTietSanPhamListSS.get(j).getTENCHITIET());
                        txtGiaTriThongSo1.setText(chiTietSanPhamListSS.get(j).getGIATRI());
                        txtGiaTriThongSo2.setText(chiTietSanPhamListCurrent.get(i).getGIATRI());
                        check = true;
                    }
                }
                if (check == false) {
                    txtTenThongSo.setText(chiTietSanPhamListCurrent.get(i).getTENCHITIET());
                    txtGiaTriThongSo1.setText("N/A");
                    txtGiaTriThongSo2.setText(chiTietSanPhamListCurrent.get(i).getGIATRI());
                }
                boolean check2 = false;
                if (i < chiTietSanPhamListSS.size()) {
                    for (int j = 0; j < chiTietSanPhamListCurrent.size(); j++) {
                        if (chiTietSanPhamListSS.get(i).getTENCHITIET().equalsIgnoreCase(chiTietSanPhamListCurrent.get(j).getTENCHITIET())) {
                            check2 = true;
                        }
                    }
                    if (check2 == false) {
                        txtTenThongSo2.setText(chiTietSanPhamListSS.get(i).getTENCHITIET());
                        txtGiaTriThongSo12.setText(chiTietSanPhamListSS.get(i).getGIATRI());
                        txtGiaTriThongSo22.setText("N/A");
                    }
                }

            }

            //case1 hoặc case2
            lnChiTiet.addView(txtTenThongSo);
            lnChiTiet.addView(txtGiaTriThongSo1);
            lnChiTiet.addView(txtGiaTriThongSo2);

            lnSoSanh.addView(lnChiTiet);
            //case3
            lnChiTiet2.addView(txtTenThongSo2);
            lnChiTiet2.addView(txtGiaTriThongSo12);
            lnChiTiet2.addView(txtGiaTriThongSo22);

            lnSoSanh.addView(lnChiTiet2);
        }

//        for (int i = 0; i < chiTietSanPhamListSS.size(); i++) {
//            LinearLayout lnChiTiet = new LinearLayout(this);
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            layoutParams.setMargins(10, 0, 10, 10);
//            lnChiTiet.setLayoutParams(layoutParams);
//            lnChiTiet.setOrientation(LinearLayout.HORIZONTAL);
//
//            TextView txtTenThongSo = new TextView(this);
//            txtTenThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
//            txtTenThongSo.setText(chiTietSanPhamListSS.get(i).getTENCHITIET());
//
//            TextView txtGiaTriThongSo1 = new TextView(this);
//            LinearLayout.LayoutParams layoutParamTS1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            layoutParamTS1.setMargins(20, 0, 0, 0);
//            layoutParamTS1.weight = 1.0f;
//            txtGiaTriThongSo1.setLayoutParams(layoutParamTS1);
//            txtGiaTriThongSo1.setText(chiTietSanPhamListSS.get(i).getGIATRI());
//
//            TextView txtGiaTriThongSo2 = new TextView(this);
//            LinearLayout.LayoutParams layoutParamTS2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            layoutParamTS2.setMargins(20, 0, 0, 0);
//            layoutParamTS2.weight = 1.0f;
//            txtGiaTriThongSo2.setLayoutParams(layoutParamTS2);
//            txtGiaTriThongSo2.setText(chiTietSanPhamListCurrent.get(i).getGIATRI());
//
//            lnChiTiet.addView(txtTenThongSo);
//            lnChiTiet.addView(txtGiaTriThongSo1);
//            lnChiTiet.addView(txtGiaTriThongSo2);
//
//            lnSoSanh.addView(lnChiTiet);
//        }

        //Button mua
//        LinearLayout lnButton = new LinearLayout(this);
//        LinearLayout.LayoutParams layoutParamsButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        layoutParamsButton.setMargins(0, 20, 0, 20);
//        lnButton.setLayoutParams(layoutParamsButton);
//        lnButton.setOrientation(LinearLayout.HORIZONTAL);
//
//        TextView txtButton = new TextView(this);
//        txtButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
//        txtButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//        txtButton.setText("Mua ngay kẻo lỡ!");
//
//        Button button1= new Button(this);
//        button1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
//        button1.setText("Mua ngay");
//        button1.setBackgroundColor(button1.getContext().getResources().getColor(R.color.bgToolBar));
//
//        Button button2= new Button(this);
//        button2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
//        button2.setText("Xem thêm");
//        button2.setBackgroundColor(button2.getContext().getResources().getColor(R.color.bgToolBar));
//
//        lnButton.addView(txtButton);
//        lnButton.addView(button1);
//        lnButton.addView(button2);
//        lnSoSanh.addView(lnButton);

    }
}
