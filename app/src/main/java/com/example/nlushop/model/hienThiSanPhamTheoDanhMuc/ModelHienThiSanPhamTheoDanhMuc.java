package com.example.nlushop.model.hienThiSanPhamTheoDanhMuc;

import com.example.nlushop.connectInternet.DownloadJson;
import com.example.nlushop.model.objectClass.ChiTietKhuyenMai;
import com.example.nlushop.model.objectClass.SanPham;
import com.example.nlushop.view.trangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelHienThiSanPhamTheoDanhMuc {

    public List<SanPham> LayDanhSachSanPhamTheoMaLoai(int masp, String method, String tenMang, int limit){
        List<SanPham> sanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        //Truyền method vào để thực hiện function trong file php
        HashMap<String, String> hsMethod = new HashMap<>();
        hsMethod.put("method", method);

        HashMap<String, String> hsMaLoai = new HashMap<>();
        hsMaLoai.put("maloaisp", String.valueOf(masp));

        HashMap<String, String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));

        attrs.add(hsMethod);
        attrs.add(hsMaLoai);
        attrs.add(hsLimit);
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
                sanPham.setANHNHO(object.getString("HINHSANPHAMNHO"));

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
}
//1h36'