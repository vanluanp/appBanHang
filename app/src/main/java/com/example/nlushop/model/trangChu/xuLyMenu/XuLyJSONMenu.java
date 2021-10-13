package com.example.nlushop.model.trangChu.xuLyMenu;

import android.os.Bundle;

import com.example.nlushop.connectInternet.DownloadJson;
import com.example.nlushop.model.objectClass.LoaiSanPham;
import com.example.nlushop.view.trangChu.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class XuLyJSONMenu {

    public List<LoaiSanPham>  ParserJSONMenu(String duLieuJSON){
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(duLieuJSON);
            JSONArray loaisanpham = jsonObject.getJSONArray("LOAISANPHAM");
            int count = loaisanpham.length();
            for(int i=0; i<count; i++){
                JSONObject value = loaisanpham.getJSONObject(i);

                LoaiSanPham dataLoaiSanPham = new LoaiSanPham();
                dataLoaiSanPham.setMALOAISP(Integer.parseInt(value.getString("MALOAISP")));
                dataLoaiSanPham.setMALOAICHA(Integer.parseInt(value.getString("MALOAI_CHA")));
                dataLoaiSanPham.setTENLOAISP(value.getString("TENLOAISP"));
                loaiSanPhamList.add(dataLoaiSanPham);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;
    }

    public List<LoaiSanPham> layLoaiSanPhamTheoMaLoai(int maloaiSP){
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;
//        String duongdan = "http://192.168.1.11:84/nlshop/loaisanpham.php";

        //Truyền method vào để thực hiện function trong file php
        HashMap<String, String> hsMethod = new HashMap<>();
        hsMethod.put("method", "LayDanhSachMenu");

        HashMap<String, String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha", String.valueOf(maloaiSP));

        attrs.add(hsMethod);
        attrs.add(hsMaLoaiCha);
        DownloadJson downloadJson = new DownloadJson(duongdan, attrs);
//        DownloadJson downloadJson = new DownloadJson(duongdan);
        downloadJson.execute();

        try {
            dataJSON = downloadJson.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.ParserJSONMenu(dataJSON);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return loaiSanPhamList;
    }

}
