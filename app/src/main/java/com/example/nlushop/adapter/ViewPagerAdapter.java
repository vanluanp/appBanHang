package com.example.nlushop.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.nlushop.view.trangChu.fragment.FragmentCTKhuyenMai;
import com.example.nlushop.view.trangChu.fragment.FragmentDienTu;
import com.example.nlushop.view.trangChu.fragment.FragmentDoGiaDung;
import com.example.nlushop.view.trangChu.fragment.FragmentNoiBat;
import com.example.nlushop.view.trangChu.fragment.FragmentNongNghiep;
import com.example.nlushop.view.trangChu.fragment.FragmentSucKhoe;
import com.example.nlushop.view.trangChu.fragment.FragmentThoiTrang;
import com.example.nlushop.view.trangChu.fragment.FragmentThucPham;
import com.example.nlushop.view.trangChu.fragment.FragmentThuongHieu;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> listFragment = new ArrayList<Fragment>();
    List<String> titleFragment = new ArrayList<String>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);

        listFragment.add(new FragmentDienTu());
//        listFragment.add(new FragmentNoiBat());
        listFragment.add(new FragmentCTKhuyenMai());
        listFragment.add(new FragmentThuongHieu());
//        listFragment.add(new FragmentDienTu());
//        listFragment.add(new FragmentDoGiaDung());
        listFragment.add(new FragmentNongNghiep());
//        listFragment.add(new FragmentSucKhoe());
//        listFragment.add(new FragmentThoiTrang());
//        listFragment.add(new FragmentThucPham());

        titleFragment.add("Điện tử");
//        titleFragment.add("Nổi bật");
        titleFragment.add("Chương trình khuyến mãi");
        titleFragment.add("Laptop chuyên ngành");
//        titleFragment.add("Điện tử");
//        titleFragment.add("Đồ gia dụng");
        titleFragment.add("Nông nghiệp");
//        titleFragment.add("Sức khỏe");
//        titleFragment.add("Thời trang");
//        titleFragment.add("Thực phẩm");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}
