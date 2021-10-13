package com.example.nlushop.model.trangchu_dientu;

import android.util.Log;

import com.example.nlushop.connectInternet.DownloadJson;
import com.example.nlushop.model.objectClass.ChiTietKhuyenMai;
import com.example.nlushop.model.objectClass.ChiTietSanPham;
import com.example.nlushop.model.objectClass.SanPham;
import com.example.nlushop.model.objectClass.ThuongHieu;
import com.example.nlushop.model.trangChu.xuLyMenu.XuLyJSONMenu;
import com.example.nlushop.view.trangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDienTu {

    public List<SanPham> LayDanhSachSanPhamTop(String method, String tenMang){
        List<SanPham> sanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        //Truyền method vào để thực hiện function trong file php
        HashMap<String, String> hsMethod = new HashMap<>();
        hsMethod.put("method", method);

        attrs.add(hsMethod);
        DownloadJson downloadJson = new DownloadJson(duongdan, attrs);
//        DownloadJson downloadJson = new DownloadJson(duongdan);
        downloadJson.execute();

        try {
            dataJSON = downloadJson.get();
            //Parser JSON
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPhamTop = jsonObject.getJSONArray(tenMang);
            int count = jsonArrayDanhSachSanPhamTop.length();
            for (int i = 0; i < count; i++){
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDanhSachSanPhamTop.getJSONObject(i);

                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHLON(object.getString("HINHSANPHAM"));

                ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                chiTietKhuyenMai.setPHANTRAMKM(object.getInt("PHANTRAMKM"));
                sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);

                sanPhamList.add(sanPham);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPhamList;
    }

    public List<ThuongHieu> LayDanhSachThuongHieuLon(String method, String tenMang) {
        List<ThuongHieu> thuongHieuList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        //Truyền method vào để thực hiện function trong file php
        HashMap<String, String> hsMethod = new HashMap<>();
        hsMethod.put("method", method);

        attrs.add(hsMethod);
        DownloadJson downloadJson = new DownloadJson(duongdan, attrs);
//        DownloadJson downloadJson = new DownloadJson(duongdan);
        downloadJson.execute();

        try {
            dataJSON = downloadJson.get();
            //Parser JSON
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachThuongHieu = jsonObject.getJSONArray(tenMang);
            int count = jsonArrayDanhSachThuongHieu.length();
            for (int i = 0; i < count; i++){
                ThuongHieu thuongHieu = new ThuongHieu();
                JSONObject object = jsonArrayDanhSachThuongHieu.getJSONObject(i);

                thuongHieu.setMATHUONGHIEU(object.getInt("MASP"));
                thuongHieu.setTENTHUONGHIEU(object.getString("TENSP"));
                thuongHieu.setHINHTHUONGHIEU(object.getString("HINHSANPHAM"));
                thuongHieuList.add(thuongHieu);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return thuongHieuList;
    }

}
