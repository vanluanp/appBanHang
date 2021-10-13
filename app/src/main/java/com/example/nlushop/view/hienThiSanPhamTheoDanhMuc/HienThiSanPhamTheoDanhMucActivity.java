package com.example.nlushop.view.hienThiSanPhamTheoDanhMuc;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nlushop.R;
import com.example.nlushop.adapter.AdapterTopDienThoaiDienTu;
import com.example.nlushop.model.objectClass.ILoadMore;
import com.example.nlushop.model.objectClass.LoadMoreScroll;
import com.example.nlushop.model.objectClass.SanPham;
import com.example.nlushop.presenter.chiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.nlushop.presenter.hienThiSanPhamTheoDanhMuc.PresenterLogicHienThiSanPhamTheoDanhMuc;
import com.example.nlushop.view.gioHang.GioHangActivity;
import com.example.nlushop.view.trangChu.TrangChuActivity;
import com.example.nlushop.view.trangChu.ViewHienThiSanPhamTheoDanhMuc;

import java.util.List;

public class HienThiSanPhamTheoDanhMucActivity extends Fragment implements ViewHienThiSanPhamTheoDanhMuc, View.OnClickListener, ILoadMore {
    RecyclerView recyclerView;
    Button btnThayDoiTrangThaiRecycler;
    boolean dangGrid = true;
    RecyclerView.LayoutManager layoutManager;
    PresenterLogicHienThiSanPhamTheoDanhMuc sanPhamTheoDanhMuc;
    int masp;
    boolean kiemtra;
    AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu;
    Toolbar toolbar;
    List<SanPham> sanPhamList1;
    ProgressBar progressBar;
    TextView txtGioHang;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_hienthisanphamtheodanhmuc, container, false);

        setHasOptionsMenu(false);//ko set menu

        recyclerView = view.findViewById(R.id.recycleViewHienThiSanPhamTheoDanhMuc);
        btnThayDoiTrangThaiRecycler = view.findViewById(R.id.btnThayDoiTrangThaiRecycler);
        toolbar = view.findViewById(R.id.toolbar);
        progressBar = view.findViewById(R.id.progress_bar);

        Bundle bundle = getArguments();
        masp = bundle.getInt("MALOAI", 0);
        String tensanpham = bundle.getString("TENLOAI");
        kiemtra = bundle.getBoolean("KIEMTRA", false);
//        Toast.makeText(this, kiemtra+"+"+masp, Toast.LENGTH_SHORT).show();

        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
        sanPhamTheoDanhMuc = new PresenterLogicHienThiSanPhamTheoDanhMuc(this);
        sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, kiemtra);

        btnThayDoiTrangThaiRecycler.setOnClickListener(this);

        toolbar.setTitle(tensanpham);

        ((AppCompatActivity)getActivity()) .setSupportActionBar(toolbar);//Lấy activity chứa fragment bằng cách getActivity() và ép kiểu nó về AppCompatActivity để setSupportActionBar()
        //set hiển thị nút back
        ((AppCompatActivity)getActivity()) .getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)getActivity()) .getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack("TrangChuActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE);//gỡ fragment bằng nút back theo key TrangChuActivity
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menutrangchu, menu);
    }

    //    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_hienthisanphamtheodanhmuc);
//
//        recyclerView = findViewById(R.id.recycleViewHienThiSanPhamTheoDanhMuc);
//        btnThayDoiTrangThaiRecycler = findViewById(R.id.btnThayDoiTrangThaiRecycler);
//        toolbar = findViewById(R.id.toolbar);
//        progressBar = findViewById(R.id.progress_bar);
//
//        Intent intent = getIntent();
//        masp = intent.getIntExtra("MALOAI", 0);
//        String tensanpham = intent.getStringExtra("TENLOAI");
//        kiemtra = intent.getBooleanExtra("KIEMTRA", false);
////        Toast.makeText(this, kiemtra+"+"+masp, Toast.LENGTH_SHORT).show();
//
//        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
//        sanPhamTheoDanhMuc = new PresenterLogicHienThiSanPhamTheoDanhMuc(this);
//        sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, kiemtra);
//
//        btnThayDoiTrangThaiRecycler.setOnClickListener(this);
//
//        toolbar.setTitle(tensanpham);
//        setSupportActionBar(toolbar);
//
//    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menutrangchu, menu);
//
//        MenuItem iGioHang = menu.findItem(R.id.itGioHang);//Lấy item giỏ hàng
//        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);//Gọi đến giao diện custom giỏ hàng
//        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);//Gán id vào cho textView
//
//        PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
//        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));
//
//        //Xử lý nhấn vào giỏ hàng, giaoDienjCustomGioHang lúc này là cái layout giỏ hàng, chứ không còn là itGioHang nữa, video 41.
//        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent iGioHang = new Intent(HienThiSanPhamTheoDanhMucActivity.this, GioHangActivity.class);
//                startActivity(iGioHang);
//            }
//        });
//
//        return true;
//    }

    @Override
    public void HienThiDanhSachSanPham(List<SanPham> sanPhamList) {
        //gán list cho lần chạy dữ liệu đầu tiên
        sanPhamList1 = sanPhamList;
        if(dangGrid){
            layoutManager = new GridLayoutManager(getContext(), 2);
            adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(getContext(), R.layout.custom_layout_topdienthoaivamaytinhbang, sanPhamList1, 0);
        }else{
            layoutManager = new LinearLayoutManager(getContext());
            adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(getContext(), R.layout.custom_layout_list_top_dienthoai_va_maytinhbang, sanPhamList1, 0);
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopDienThoaiDienTu);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        adapterTopDienThoaiDienTu.notifyDataSetChanged();

    }

    @Override
    public void LoiHienThiDanhSachSanPham() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnThayDoiTrangThaiRecycler:
                dangGrid = !dangGrid;
                sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, kiemtra);
                break;
        }
    }

    @Override
    public void loadMore(int tongItem) {
        //Khi vuốt hết tongItem thì sẽ load thêm bấy nhiêu sản phẩm tongItem, rồi lại add vào list
        List<SanPham> sanPhamListLoadMore = sanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoaiLoadMore(masp, kiemtra, tongItem, progressBar);
        sanPhamList1.addAll(sanPhamListLoadMore);
        //Lúc này vẫn còn ở hàm onScrolled, nên khi vuốt thêm dữ liệu sẽ tự notifyDataSetChanged và hiển thị tiếp tục bằng hàm HienThiDanhSachSanPham().
        adapterTopDienThoaiDienTu.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        //Do chạy vào onResume khi chạy lần đầu nên txtGioHang sẽ null -> lỗi
        if (txtGioHang != null) {
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(getContext())));//set số lượng sp giỏ hàng
        }
    }
}
