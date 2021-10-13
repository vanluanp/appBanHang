package com.example.nlushop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.nlushop.R;
import com.example.nlushop.model.objectClass.LoaiSanPham;
import com.example.nlushop.model.trangChu.xuLyMenu.XuLyJSONMenu;
import com.example.nlushop.view.hienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;

import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context context;
    List<LoaiSanPham> loaiSanPhamList;
    ViewHolderMenu viewHolderMenu;
    public ExpandAdapter(Context context, List<LoaiSanPham> loaiSanPhamList){
        this.context = context;
        this.loaiSanPhamList = loaiSanPhamList;

        XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();

        int count = loaiSanPhamList.size();
        for (int i=0; i<count; i++){
            int maloaiSP = loaiSanPhamList.get(i).getMALOAISP();
            //gan list loai san pham con vao list get(i) tuong ung
            loaiSanPhamList.get(i).setLoaiSanPhamListCon(xuLyJSONMenu.layLoaiSanPhamTheoMaLoai(maloaiSP));
        }

    }

    @Override
    public int getGroupCount() {
        return loaiSanPhamList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(loaiSanPhamList.get(groupPosition).getLoaiSanPhamListCon().size() !=0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return loaiSanPhamList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return loaiSanPhamList.get(groupPosition).getLoaiSanPhamListCon().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return loaiSanPhamList.get(groupPosition).getMALOAISP();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return loaiSanPhamList.get(groupPosition).getLoaiSanPhamListCon().get(childPosition).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //Test sử dụng View Holder
    public class ViewHolderMenu{
        TextView txtTenLoaiSP;
        ImageView imgMenu;

    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View viewGroupCha = convertView;
        if(viewGroupCha == null){
            viewHolderMenu = new ViewHolderMenu();

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_group_cha, parent, false);

            viewHolderMenu.txtTenLoaiSP = viewGroupCha.findViewById(R.id.txtTenLoaiSanPham);
            viewHolderMenu.imgMenu = viewGroupCha.findViewById(R.id.imMenuPlus);

            viewGroupCha.setTag(viewHolderMenu);
        }else{
            viewHolderMenu = (ViewHolderMenu) viewGroupCha.getTag();
        }

        viewHolderMenu.txtTenLoaiSP.setText(loaiSanPhamList.get(groupPosition).getTENLOAISP());

        int countListCon = loaiSanPhamList.get(groupPosition).getLoaiSanPhamListCon().size();
        //Ẩn đi nếu không có list con
        if(countListCon > 0){
            viewHolderMenu.imgMenu.setVisibility(View.VISIBLE);
        }else {
            viewHolderMenu.imgMenu.setVisibility(View.INVISIBLE);
        }
        //Hiển thị icon phù hợp khi mở hay thu gọn menu
        if(isExpanded){
            viewHolderMenu.imgMenu.setImageResource(R.drawable.ic_remove_black_24dp);
//            viewGroupCha.setBackgroundResource(R.color.colorGray);
        }else{
            viewHolderMenu.imgMenu.setImageResource(R.drawable.ic_add_black_24dp);
//            viewGroupCha.setBackgroundResource(R.color.white);
        }

        viewGroupCha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                Log.d("maloaisp", loaiSanPhamList.get(groupPosition).getTENLOAISP());
                FragmentManager fragmentManager = ((AppCompatActivity)context) .getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                HienThiSanPhamTheoDanhMucActivity hienThiSanPhamTheoDanhMucActivity = new HienThiSanPhamTheoDanhMucActivity();
                Bundle bundle = new Bundle();
                bundle.putInt("MALOAI", loaiSanPhamList.get(groupPosition).getMALOAISP());
                bundle.putBoolean("KIEMTRA", false);
                bundle.putString("TENLOAI", loaiSanPhamList.get(groupPosition).getTENLOAISP());

                hienThiSanPhamTheoDanhMucActivity.setArguments(bundle);

                fragmentTransaction.addToBackStack("TrangChuActivity");//add quay trở về
                fragmentTransaction.replace(R.id.themFragment, hienThiSanPhamTheoDanhMucActivity);
                fragmentTransaction.commit();

                return false;
            }
        });

        return viewGroupCha;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.custom_layout_group_con, parent, false);

//        ExpandableListView expandableListView = view.findViewById(R.id.epMenuCon);
        //Tự xử dụng expandListView mà custom ở dưới
        SecondExpandable secondExpanable = new SecondExpandable(context);
        ExpandAdapter secondAdapter = new ExpandAdapter(context, loaiSanPhamList.get(groupPosition).getLoaiSanPhamListCon());
        secondExpanable.setAdapter(secondAdapter);

        secondExpanable.setGroupIndicator(null);
        notifyDataSetChanged();

        return secondExpanable;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public class SecondExpandable extends ExpandableListView{

        public SecondExpandable(Context context) {
            super(context);
        }

        //Để set kích thước tùy theo màn hình cụ thể, chứ ko set cố định
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            //Lấy kích thước màn hình của thiết bị hiện tại
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int width = size.x;
            int height = size.y;
//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    //Lấy danh sách con thứ 2
//    public class SecondAdapter extends BaseExpandableListAdapter{
//        List<LoaiSanPham> listCon;
//        public SecondAdapter(List<LoaiSanPham> listCon){
//            this.listCon = listCon;
//
//            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
//
//            int count = listCon.size();
//            for (int i=0; i<count; i++){
//                int maloaiSP = listCon.get(i).getMALOAISP();
//                //gan list loai san pham con vao list get(i) tuong ung
//                listCon.get(i).setLoaiSanPhamListCon(xuLyJSONMenu.layLoaiSanPhamTheoMaLoai(maloaiSP));
//            }
//
//        }
//
//        @Override
//        public int getGroupCount() {
//            return listCon.size();
//        }
//
//        @Override
//        public int getChildrenCount(int groupPosition) {
//            return listCon.get(groupPosition).getLoaiSanPhamListCon().size();
//        }
//
//        @Override
//        public Object getGroup(int groupPosition) {
//            return listCon.get(groupPosition);
//        }
//
//        @Override
//        public Object getChild(int groupPosition, int childPosition) {
//            return listCon.get(groupPosition).getLoaiSanPhamListCon().get(childPosition);
//        }
//
//        @Override
//        public long getGroupId(int groupPosition) {
//            return listCon.get(groupPosition).getMALOAISP();
//        }
//
//        @Override
//        public long getChildId(int groupPosition, int childPosition) {
//            return listCon.get(groupPosition).getLoaiSanPhamListCon().get(childPosition).getMALOAISP();
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return false;
//        }
//
//        @Override
//        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View view = layoutInflater.inflate(R.layout.custom_layout_group_cha, parent, false);
//            TextView txtTenLoaiSanPham = view.findViewById(R.id.txtTenLoaiSanPham);
//            txtTenLoaiSanPham.setText(listCon.get(groupPosition).getTENLOAISP());
//
//            return view;
//        }
//
//        @Override
//        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
////            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////            View view = layoutInflater.inflate(R.layout.custom_layout_group_cha, parent, false);
////            TextView txtTenLoaiSanPham = view.findViewById(R.id.txtTenLoaiSanPham);
////            txtTenLoaiSanPham.setText(listCon.get(groupPosition).getLoaiSanPhamListCon().get(childPosition).getTENLOAISP());
//            TextView textView = new TextView(context);
//            textView.setText(listCon.get(groupPosition).getLoaiSanPhamListCon().get(childPosition).getTENLOAISP());
//            textView.setPadding(15, 5, 5, 5);
//            textView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//
//            return textView;
//        }
//
//        @Override
//        public boolean isChildSelectable(int groupPosition, int childPosition) {
//            return false;
//        }
//    }

}
