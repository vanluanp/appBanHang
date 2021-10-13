package com.example.nlushop.presenter.trangChu.xuLyMenu;

import android.util.Log;

import com.example.nlushop.connectInternet.DownloadJson;
import com.example.nlushop.model.dangNhap_dangKy.ModelDangNhap;
import com.example.nlushop.model.objectClass.LoaiSanPham;
import com.example.nlushop.model.trangChu.xuLyMenu.XuLyJSONMenu;
import com.example.nlushop.view.trangChu.TrangChuActivity;
import com.example.nlushop.view.trangChu.ViewXuLyMenu;
import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PresenterLogicXyLyMenu implements IPresenterXuLyMenu{
    ViewXuLyMenu viewXuLyMenu;

    public PresenterLogicXyLyMenu(ViewXuLyMenu viewXuLyMenu){
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void LayDanhSachMenu() {
        List<LoaiSanPham> loaiSanPhamList;
        String dataJSON = "";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        //Lay bang Get
//        String duongdan = "http://10.0.3.2:84/weblazada/loaisanpham.php?maloaicha=";

//        DownloadJson downloadJson = new DownloadJson(duongdan);
        //End method Get
        //Lay bang Post
        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsMethod = new HashMap<>();
        hsMethod.put("method", "LayDanhSachMenu");

        HashMap<String, String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha", "0");

        attrs.add(hsMethod);
        attrs.add(hsMaLoaiCha);
        DownloadJson downloadJson = new DownloadJson(duongdan, attrs);
        //End Method Post
        downloadJson.execute();

        try {
            dataJSON = downloadJson.get();
            Log.d("checkf", dataJSON);
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.ParserJSONMenu(dataJSON);
            viewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccessToken LayTokenNguoiDungFB() {
        //Bước 1 lấy token
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        AccessToken accessToken = modelDangNhap.LayTokenFacebookCurrent();
        //Bước 2 lấy trường name thông qua token vừa lấy được tại class TrangChuActivity.

        return accessToken;
    }
}
//xem IP may ao 59:18" bai 16 hoac bai 9 luc phut 30.