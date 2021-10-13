package com.example.nlushop.model.danhGia;

import com.example.nlushop.connectInternet.DownloadJson;
import com.example.nlushop.model.objectClass.DanhGia;
import com.example.nlushop.view.trangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDanhGia {

    public List<DanhGia> LayDanhSachDanhGiaCuaSanPham(int masp, int limit){
        List<DanhGia> danhGiaList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        //Truyền method vào để thực hiện function trong file php
        HashMap<String, String> hsMethod = new HashMap<>();
        hsMethod.put("method", "LayDanhSachDanhGiaTheoMaSP");

        HashMap<String, String> hsMaSP = new HashMap<>();
        hsMaSP.put("masp", String.valueOf(masp));

        HashMap<String, String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));

        attrs.add(hsMethod);
        attrs.add(hsMaSP);
        attrs.add(hsLimit);
        DownloadJson downloadJson = new DownloadJson(duongdan, attrs);
//        DownloadJson downloadJson = new DownloadJson(duongdan);
        downloadJson.execute();

        try {
            dataJSON = downloadJson.get();
            //Parser JSON
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPhamTop = jsonObject.getJSONArray("DANHSACHDANHGIA");
            int count = jsonArrayDanhSachSanPhamTop.length();
            for (int i = 0; i < count; i++){
                DanhGia danhGia = new DanhGia();
                JSONObject object = jsonArrayDanhSachSanPhamTop.getJSONObject(i);

                danhGia.setTENTHIETBI(object.getString("TENTHIETBI"));
                danhGia.setNOIDUNG(object.getString("NOIDUNG"));
                danhGia.setSOSAO(object.getInt("SOSAO"));
                danhGia.setMASP(object.getInt("MASP"));
                danhGia.setMADG(object.getString("MADG"));
                danhGia.setNGAYDANHGIA(object.getString("NGAYDANHGIA"));
                danhGia.setTIEUDE(object.getString("TIEUDE"));

                danhGiaList.add(danhGia);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return danhGiaList;
    }

    public boolean ThemDanhGia (DanhGia danhGia){
        String duongdan = TrangChuActivity.SERVER_NAME;
        boolean kiemtra = false;

        List<HashMap<String, String>> attrs = new ArrayList<>();

        //Gọi function
        HashMap<String, String> hsMethod = new HashMap<>();
        hsMethod.put("method", "ThemDanhGia");

        HashMap<String, String> hsMaDG = new HashMap<>();
        hsMaDG.put("madg", danhGia.getMADG());

        HashMap<String, String> hsMaSP = new HashMap<>();
        hsMaSP.put("masp", String.valueOf(danhGia.getMASP()));

        HashMap<String, String> hsTieuDe = new HashMap<>();
        hsTieuDe.put("tieude", danhGia.getTIEUDE());

        HashMap<String, String> hsNoiDung = new HashMap<>();
        hsNoiDung.put("noidung", danhGia.getNOIDUNG());

        HashMap<String, String> hsSoSao = new HashMap<>();
        hsSoSao.put("sosao", String.valueOf(danhGia.getSOSAO()));

        HashMap<String, String> hsTenThietBi = new HashMap<>();
        hsTenThietBi.put("tenthietbi", danhGia.getTENTHIETBI());


        attrs.add(hsMethod);
        attrs.add(hsMaDG);
        attrs.add(hsMaSP);
        attrs.add(hsTieuDe);
        attrs.add(hsNoiDung);
        attrs.add(hsSoSao);
        attrs.add(hsTenThietBi);

        DownloadJson downloadJson = new DownloadJson(duongdan, attrs);
        downloadJson.execute();

        try {
            String dulieuJson = downloadJson.get();
            JSONObject jsonObject = new JSONObject(dulieuJson);
            String ketqua = jsonObject.getString("ketqua");
//            Log.d("kiemtradk", dulieu);
            if(ketqua.equals("true")){
                kiemtra = true;
            }else{
                kiemtra = false;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }
}
