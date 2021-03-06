package com.example.nlushop.view.chiTietSanPham;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.nlushop.R;
import com.example.nlushop.adapter.AdapterDanhGia;
import com.example.nlushop.adapter.AdapterTopDienThoaiDienTu;
import com.example.nlushop.adapter.AdapterViewPagerSilder;
import com.example.nlushop.model.danhGia.ModelDanhGia;
import com.example.nlushop.model.objectClass.ChiTietKhuyenMai;
import com.example.nlushop.model.objectClass.ChiTietSanPham;
import com.example.nlushop.model.objectClass.DanhGia;
import com.example.nlushop.model.objectClass.SanPham;
import com.example.nlushop.presenter.chiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.nlushop.view.danhGia.DanhSachDanhGiaActivity;
import com.example.nlushop.view.danhGia.ThemDanhGiaActivity;
import com.example.nlushop.view.gioHang.GioHangActivity;
import com.example.nlushop.view.thanhToan.ThanhToanActivity;
import com.example.nlushop.view.trangChu.TrangChuActivity;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements View.OnClickListener, ViewChiTietSanPham, ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView[] txtDots;
    LinearLayout layoutDots, lnThongSoKyThuat;
    TextView txtTenSanPham, txtGiaTien, txtTenCuaHangDongGoi, txtThongTinChiTiet, txtXemTatCaNhanXet, txtGioHang, txtGiamGia, txtPhanTramGiam;
    Toolbar toolbar;
    RatingBar ratingBar;
    ImageView imThemGioHang, imXemThemChiTiet;
    boolean kiemtraxochitiet = false;
    TextView txtVietDanhGia;
    Button btnMuaNgay;
    RecyclerView recyclerDanhGiaChiTiet, recyclerSoSanh;
    List<Fragment> fragmentList;
    List<DanhGia> danhGiaListSoSao;
    int masp;
    SanPham sanPhamGioHang;
    List<DanhGia> danhGiaListTrangChiTiet = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chi_tiet_san_pham);

        viewPager = findViewById(R.id.viewpagerSlider);
        layoutDots = findViewById(R.id.layoutDots);
        txtTenSanPham = findViewById(R.id.txtTenSanPham);
        txtGiaTien = findViewById(R.id.txtGiaTien);
        toolbar = findViewById(R.id.toolbar);
        txtTenCuaHangDongGoi = findViewById(R.id.tenCHDongGoi);
        txtThongTinChiTiet = findViewById(R.id.txtThongTinChiTiet);
        imXemThemChiTiet = findViewById(R.id.imXemThemChiTiet);
        lnThongSoKyThuat = findViewById(R.id.lnThongSoKyThuat);
        txtVietDanhGia = findViewById(R.id.txtVietDanhGia);
        recyclerDanhGiaChiTiet = findViewById(R.id.recyclerDanhGiaChiTiet);
        recyclerSoSanh = findViewById(R.id.recyclerSoSanh);
        txtXemTatCaNhanXet = findViewById(R.id.txtXemTatCaNhanXet);
        txtGiamGia = findViewById(R.id.txtGiamGia);
        txtPhanTramGiam = findViewById(R.id.txtPhanTramGiam);
        ratingBar = findViewById(R.id.rbDanhGia);

        toolbar.setTitle("Chi ti???t s???n ph???m");
        setSupportActionBar(toolbar);

        masp = getIntent().getIntExtra("masp", 0);
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSanPham(masp);
        presenterLogicChiTietSanPham.LayDanhSachDanhGiaTheoSanPham(masp, 0);
        presenterLogicChiTietSanPham.LayDanhSachSanPhamSoSanh(masp);

        imThemGioHang = findViewById(R.id.imThemGioHang);
        btnMuaNgay = findViewById(R.id.btnMuaNgay);

        txtVietDanhGia.setOnClickListener(this);
        imThemGioHang.setOnClickListener(this);
        btnMuaNgay.setOnClickListener(this);
        txtXemTatCaNhanXet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.txtVietDanhGia:
                Intent iThemDanhGia = new Intent(ChiTietSanPhamActivity.this, ThemDanhGiaActivity.class);
                iThemDanhGia.putExtra("masp", masp);
                startActivity(iThemDanhGia);
                break;

            case R.id.txtXemTatCaNhanXet:
                Intent iDanhSachDanhGia = new Intent(ChiTietSanPhamActivity.this, DanhSachDanhGiaActivity.class);
                iDanhSachDanhGia.putExtra("masp", masp);
                startActivity(iDanhSachDanhGia);
                break;

            case R.id.imThemGioHang:
//                Fragment fragment = fragmentList.get(viewPager.getCurrentItem());//L???y item t???m h??nh hi???n t???i trong slider h??nh ???nh sp
                try {
                    Fragment fragment = fragmentList.get(0);//L???y m???c ?????nh h??nh ?????u ti??n
                    View view = fragment.getView();
                    ImageView imageView = view.findViewById(R.id.imHinhSlider);//L???y h??nh b??n slider
                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);//compress (n??n) bitmap th??nh d???ng png v?? l??u v??o byteArrayOutputStream
                    byte[] hinhSanPhamGioHang = byteArrayOutputStream.toByteArray();//chuy???n th??nh m???ng byte

                    sanPhamGioHang.setHinhGioHang(hinhSanPhamGioHang);
                    sanPhamGioHang.setSOLUONG(1);//m???c ?????nh l???n ?????u nh???n l?? 1

                    presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang, this);
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;

            case R.id.btnMuaNgay:
                Fragment fragment2 = fragmentList.get(0);//L???y m???c ?????nh h??nh ?????u ti??n
                View view2 = fragment2.getView();
                ImageView imageView2 = view2.findViewById(R.id.imHinhSlider);//L???y h??nh b??n slider
                Bitmap bitmap2 = ((BitmapDrawable) imageView2.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                bitmap2.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream2);//compress (n??n) bitmap th??nh d???ng png v?? l??u v??o byteArrayOutputStream
                byte[] hinhSanPhamGioHang2 = byteArrayOutputStream2.toByteArray();//chuy???n th??nh m???ng byte

                sanPhamGioHang.setHinhGioHang(hinhSanPhamGioHang2);
                sanPhamGioHang.setSOLUONG(1);//m???c ?????nh l???n ?????u nh???n l?? 1

                presenterLogicChiTietSanPham.ThemGioHang(sanPhamGioHang, this);

                Intent iMuaNgay = new Intent(ChiTietSanPhamActivity.this, ThanhToanActivity.class);
                startActivity(iMuaNgay);
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);

        MenuItem iGioHang = menu.findItem(R.id.itGioHang);//L???y item gi??? h??ng
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);//G???i ?????n giao di???n custom gi??? h??ng
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);//G??n id v??o cho textView

        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));

        //X??? l?? nh???n v??o gi??? h??ng, giaoDienjCustomGioHang l??c n??y l?? c??i layout gi??? h??ng, ch??? kh??ng c??n l?? itGioHang n???a, video 41.
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        return true;
    }

    @Override
    public void HienThiChiTietSanPham(final SanPham sanPham) {
        sanPhamGioHang = sanPham;
        sanPhamGioHang.setSOLUONGTONKHO(sanPham.getSOLUONG());

        masp = sanPham.getMASP();

        txtTenSanPham.setText(sanPham.getTENSP());

        int giatien = sanPham.getGIA();
        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();
        if (chiTietKhuyenMai != null) {
            int phantramkm = chiTietKhuyenMai.getPHANTRAMKM();
            if (phantramkm != 0) {
                NumberFormat numberFormat = new DecimalFormat("###,###");
                String giaGoc = numberFormat.format(giatien);

                txtPhanTramGiam.setVisibility(View.VISIBLE);
                txtPhanTramGiam.setText("(-" + phantramkm + "%)");
                txtGiamGia.setVisibility(View.VISIBLE);
                txtGiamGia.setPaintFlags(txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);//set d???u g???ch ngang cho gi?? gi???m
                txtGiamGia.setText(giaGoc + " VND");

                giatien = giatien - ((giatien * phantramkm) / 100);
            }

        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        txtGiaTien.setText(gia + " VND");
        txtTenCuaHangDongGoi.setText(sanPham.getTENNHANVIEN());

        if (sanPham.getTHONGTIN().length() < 100) {
            imXemThemChiTiet.setVisibility(View.GONE);
            txtThongTinChiTiet.setText(sanPham.getTHONGTIN());
        } else {
            imXemThemChiTiet.setVisibility(View.VISIBLE);
            txtThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0, 100));

            imXemThemChiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    kiemtraxochitiet = !kiemtraxochitiet;
                    if (kiemtraxochitiet) {
                        //sau khi x??? chi ti???t
                        txtThongTinChiTiet.setText(sanPham.getTHONGTIN());
                        imXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_up_black_24dp));
                        lnThongSoKyThuat.setVisibility(View.VISIBLE);
                        HienThiThongSoKyThuat(sanPham);
                    } else {
                        //sau khi ????ng chi ti???t
                        txtThongTinChiTiet.setText(sanPham.getTHONGTIN().substring(0, 100));
                        imXemThemChiTiet.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_down_black_24dp));
                        lnThongSoKyThuat.setVisibility(View.GONE);
                    }
                }
            });
        }

        //Set s??? sao c???a sp
        ModelDanhGia modelDanhGia = new ModelDanhGia();
        danhGiaListSoSao = modelDanhGia.LayDanhSachDanhGiaCuaSanPham(masp, 0);
        int tongsao = 0;
        int sosao = 0;
        for (int i = 0; i < danhGiaListSoSao.size(); i++) {
            tongsao += danhGiaListSoSao.get(i).getSOSAO();
        }
        if (danhGiaListSoSao.size() > 0) {
            sosao = tongsao / danhGiaListSoSao.size();
        }
        ratingBar.setRating(sosao);
    }

    private void HienThiThongSoKyThuat(SanPham sanPham) {
        List<ChiTietSanPham> chiTietSanPhamList = sanPham.getChiTietSanPhamList();
        lnThongSoKyThuat.removeAllViews();//x??a view c?? ??i, ko l?? n?? l???p l???i
        TextView txtTieuDeThongSoKyThuat = new TextView(this);
        txtTieuDeThongSoKyThuat.setText("Th??ng s??? k?? thu???t");//set l???i text khi x??a view
        txtTieuDeThongSoKyThuat.setTypeface(null, Typeface.BOLD);
        txtTieuDeThongSoKyThuat.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        lnThongSoKyThuat.addView(txtTieuDeThongSoKyThuat);
        for (int i = 0; i < chiTietSanPhamList.size(); i++) {
            LinearLayout lnChiTiet = new LinearLayout(this);
            lnChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lnChiTiet.setOrientation(LinearLayout.HORIZONTAL);

            TextView txtTenThongSo = new TextView(this);
            txtTenThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txtTenThongSo.setText(chiTietSanPhamList.get(i).getTENCHITIET());

            TextView txtGiaTriThongSo = new TextView(this);
            txtGiaTriThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txtGiaTriThongSo.setText(chiTietSanPhamList.get(i).getGIATRI());

            lnChiTiet.addView(txtTenThongSo);
            lnChiTiet.addView(txtGiaTriThongSo);

            lnThongSoKyThuat.addView(lnChiTiet);
        }
    }

    @Override
    public void HienThiSliderSanPham(String[] linkhinhsanpham) {
        fragmentList = new ArrayList<>();

        for (int i = 0; i < linkhinhsanpham.length; i++) {
            FragmentSliderChiTietSanPham fragmentSliderChiTietSanPham = new FragmentSliderChiTietSanPham();
            //T???o bundle ????? truy???n qua b??n FragmentSliderChiTietSanPham ????? get
            Bundle bundle = new Bundle();
            bundle.putString("linkhinh", TrangChuActivity.SERVER + linkhinhsanpham[i]);
            fragmentSliderChiTietSanPham.setArguments(bundle);

            fragmentList.add(fragmentSliderChiTietSanPham);
        }

        AdapterViewPagerSilder adapterViewPagerSilder = new AdapterViewPagerSilder(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapterViewPagerSilder);
        adapterViewPagerSilder.notifyDataSetChanged();

        ThemDotsSlider(0);//M???c ?????nh th??m dots l?? 0
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public void HienThiDanhGia(List<DanhGia> danhGiaList) {
        danhGiaListTrangChiTiet = danhGiaList;
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this, danhGiaList, 3);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerDanhGiaChiTiet.setLayoutManager(layoutManager);
        recyclerDanhGiaChiTiet.setAdapter(adapterDanhGia);

        adapterDanhGia.notifyDataSetChanged();

    }

    @Override
    public void ThemGioHangThanhCong() {
        Toast.makeText(this, "S???n ph???m ???? th??m v??o gi??? h??ng", Toast.LENGTH_SHORT).show();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));
    }

    @Override
    public void ThemGioHangThatBai() {
        Toast.makeText(this, "S???n ph???m ???? t???n t???i trong gi??? h??ng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void HienThiSanPhamSoSanh(List<SanPham> sanPhamList) {
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(this, R.layout.custom_layout_topdienthoaivamaytinhbang, sanPhamList, 1, sanPhamGioHang);

        RecyclerView.LayoutManager layoutManagerTopSP = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerSoSanh.setLayoutManager(layoutManagerTopSP);
        recyclerSoSanh.setAdapter(adapterTopDienThoaiDienTu);

        adapterTopDienThoaiDienTu.notifyDataSetChanged();
    }

    private void ThemDotsSlider(int vitrihientai) {
        txtDots = new TextView[fragmentList.size()];
        //lam moi layout moi lan doi d???u ch???m ???nh
        layoutDots.removeAllViews();

        //T???o ra nh???ng d???u ch???m t????ng ???ng vs s??? ???nh
        for (int i = 0; i < fragmentList.size(); i++) {
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));//M?? k?? t??? ?????t bi???t c???a h??nh tr??n trong html
            txtDots[i].setTextSize(40);
            //set m??u th?????ng cho chung t???t c???
            txtDots[i].setTextColor(getIdColor(R.color.colorSliderInActive));

            layoutDots.addView(txtDots[i]);
        }
        //set m??u ?????m h??n cho ch???m ???nh hi???n t???i
        txtDots[vitrihientai].setTextColor(getIdColor(R.color.bgToolBar));
    }

    private int getIdColor(int idColor) {

        int color = 0;
        if (Build.VERSION.SDK_INT > 21) {
            color = ContextCompat.getColor(this, idColor);
        } else {
            color = getResources().getColor(idColor);
        }
        return color;
    }

    private Drawable getHinhChiTiet(int idDrawable) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT > 21) {
            drawable = ContextCompat.getDrawable(this, idDrawable);
        } else {
            drawable = getResources().getDrawable(idDrawable);
        }
        return drawable;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ThemDotsSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Do ch???y v??o onResume khi ch???y l???n ?????u n??n txtGioHang s??? null -> l???i
        if (txtGioHang != null) {
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));//set s??? l?????ng sp gi??? h??ng
        }

        //X??t b??nh lu???n khi v???a b??nh lu???n xong
        presenterLogicChiTietSanPham.LayDanhSachDanhGiaTheoSanPham(masp, 0);//Ph???i g???i l???i h??m n??y n???u ko th?? danhGiaListTrangChiTiet s??? ko thay ?????i
        if (danhGiaListTrangChiTiet.size() > 0) {
            AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this, danhGiaListTrangChiTiet, 3);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerDanhGiaChiTiet.setLayoutManager(layoutManager);
            recyclerDanhGiaChiTiet.setAdapter(adapterDanhGia);

            adapterDanhGia.notifyDataSetChanged();
        }


    }
}
