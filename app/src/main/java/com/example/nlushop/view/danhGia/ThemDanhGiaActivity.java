package com.example.nlushop.view.danhGia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nlushop.R;
import com.example.nlushop.model.objectClass.DanhGia;
import com.example.nlushop.presenter.danhGia.PresenterLogicDanhGia;
import com.example.nlushop.view.chiTietSanPham.ChiTietSanPhamActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class ThemDanhGiaActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, ViewDanhGia, View.OnClickListener {
    TextInputLayout input_edTieuDe, input_edNoiDung;
    EditText edTieuDe, edNoiDung;
    RatingBar rbDanhGia;
    int masp = 0;
    int sosao = 0;
    Button btnDongYDanhGia;
    PresenterLogicDanhGia presenterLogicDanhGia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themdanhgia);

        input_edTieuDe = findViewById(R.id.input_edTieuDe);
        input_edNoiDung = findViewById(R.id.input_ednoiDung);
        edTieuDe = findViewById(R.id.edTieuDe);
        edNoiDung = findViewById(R.id.edNoiDung);
        rbDanhGia = findViewById(R.id.rbDanhGia);
        btnDongYDanhGia = findViewById(R.id.btnDongYDanhGia);

        masp = getIntent().getIntExtra("masp", 0);

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);

        rbDanhGia.setOnRatingBarChangeListener(this);
        btnDongYDanhGia.setOnClickListener(this);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        sosao = (int) rating;
    }

    @Override
    public void DanhGiaThanhCong() {
        Toast.makeText(this, "????nh gi?? th??nh c??ng!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DanhGiaThatBai() {
        Toast.makeText(this, "B???n kh??ng th??? ????nh gi?? s???n ph???m n??y!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void HienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGiaList) {

    }

    @Override
    public void onClick(View v) {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//        String madg = telephonyManager.getDeviceId();//tr??? serial number thi???t b???
        String madg = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        String tenthietbi = Build.MODEL;//tr??? t??n thi???t b???
        String tieude = edTieuDe.getText().toString();
        String noidung = edNoiDung.getText().toString();

        if(tieude.trim().length()>0){
            input_edTieuDe.setErrorEnabled(false);
            input_edTieuDe.setError("");
        }else{
            input_edTieuDe.setErrorEnabled(true);
            input_edTieuDe.setError("B???n ch??a nh???p ti??u ?????!");
        }

        if(noidung.trim().length()>0){
            input_edNoiDung.setErrorEnabled(false);
            input_edNoiDung.setError("");
        }else{
            input_edNoiDung.setErrorEnabled(true);
            input_edNoiDung.setError("B???n ch??a nh???p n???i dung!");
        }

        //n???u c??? hai kh??ng c?? l???i
        if(!input_edNoiDung.isErrorEnabled() && !input_edTieuDe.isErrorEnabled()){
            DanhGia danhGia = new DanhGia();
            danhGia.setMASP(masp);
            danhGia.setMADG(madg);
            danhGia.setTIEUDE(tieude);
            danhGia.setNOIDUNG(noidung);
            danhGia.setSOSAO(sosao);
            danhGia.setTENTHIETBI(tenthietbi);

            presenterLogicDanhGia.ThemDanhGia(danhGia);
            finish();//????ng khung ????nh gi?? l???i
        }

    }
}
