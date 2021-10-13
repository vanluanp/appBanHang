package com.example.nlushop.model.dangNhap_dangKy;

import android.util.Log;

import com.example.nlushop.connectInternet.DownloadJson;
import com.example.nlushop.model.objectClass.NhanVien;
import com.example.nlushop.view.trangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangKy {

    public Boolean DangKyThanhVien(NhanVien nhanVien){
        String duongdan = TrangChuActivity.SERVER_NAME;
        boolean kiemtra = false;

        List<HashMap<String, String>> attrs = new ArrayList<>();

        //Gọi function
        HashMap<String, String> hsMethod = new HashMap<>();
        hsMethod.put("method", "DangKyThanhVien");

        HashMap<String, String> hsTenNV = new HashMap<>();
        hsTenNV.put("tennv", nhanVien.getTenNV());

        HashMap<String, String> hsTenDangNhap = new HashMap<>();
        hsTenDangNhap.put("tendangnhap", nhanVien.getTenDangNhap());

        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau", nhanVien.getMatKhau());

        HashMap<String, String> hsMaLoaiNV = new HashMap<>();
        hsMaLoaiNV.put("maloainv", String.valueOf(nhanVien.getMaLoaiNV()));

        HashMap<String, String> hsEmailDocQuyen = new HashMap<>();
        hsEmailDocQuyen.put("emaildocquyen", nhanVien.getEmailDocQuyen());

        attrs.add(hsMethod);
        attrs.add(hsTenNV);
        attrs.add(hsTenDangNhap);
        attrs.add(hsMatKhau);
        attrs.add(hsMaLoaiNV);
        attrs.add(hsEmailDocQuyen);

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
