package com.example.nlushop.model.chiTietSanPham;

import com.example.nlushop.connectInternet.DownloadJson;
import com.example.nlushop.model.objectClass.ChiTietKhuyenMai;
import com.example.nlushop.model.objectClass.ChiTietSanPham;
import com.example.nlushop.model.objectClass.DanhGia;
import com.example.nlushop.model.objectClass.SanPham;
import com.example.nlushop.view.trangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelChiTietSanPham {

    public List<SanPham> LayDanhSachSanPhamSoSanh(String method, String tenMang, int masp){
        List<SanPham> sanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        //Truyền method vào để thực hiện function trong file php
        HashMap<String, String> hsMethod = new HashMap<>();
        hsMethod.put("method", method);
        HashMap<String, String> hsMasp = new HashMap<>();
        hsMasp.put("masp", String.valueOf(masp));

        attrs.add(hsMethod);
        attrs.add(hsMasp);
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

                List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();//Lưu ý phải khai báo ở đây, mỗi lần chạy sản phẩm mới nó sẽ tạo lại ko thì luôn bị trùng dữ liệu vị trí đầu tiên
                JSONArray jsonArrayThongSoKyThuat = object.getJSONArray("THONGSOKYTHUAT");
                for (int j = 0; j < jsonArrayThongSoKyThuat.length(); j++) {
                    JSONObject jsonObjectThongSoKyThuat = jsonArrayThongSoKyThuat.getJSONObject(j);
                    for (int k = 0; k < jsonObjectThongSoKyThuat.length(); k++) {
                        //Lấy name() tức là lấy cái key tương ứng trong Json
                        String tenchitiet = jsonObjectThongSoKyThuat.names().getString(k);
                        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                        chiTietSanPham.setTENCHITIET(tenchitiet);
                        //Truyền tên chi tiết (cũng là key tương ứng trong value) để lấy value, video 31 lúc 12'
                        chiTietSanPham.setGIATRI(jsonObjectThongSoKyThuat.getString(tenchitiet));

                        chiTietSanPhamList.add(chiTietSanPham);
                    }
                }
                sanPham.setChiTietSanPhamList(chiTietSanPhamList);

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

    public SanPham LayChiTietSanPham(String method, String tenMang, int masp) {
        SanPham sanPham = new SanPham();
        List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        //Truyền method vào để thực hiện function trong file php
        HashMap<String, String> hsMethod = new HashMap<>();
        hsMethod.put("method", method);

        HashMap<String, String> hsMasp = new HashMap<>();
        hsMasp.put("masp", String.valueOf(masp));

        attrs.add(hsMethod);
        attrs.add(hsMasp);
        DownloadJson downloadJson = new DownloadJson(duongdan, attrs);
//        DownloadJson downloadJson = new DownloadJson(duongdan);
        downloadJson.execute();

        try {
            dataJSON = downloadJson.get();
            //Parser JSON
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray(tenMang);
            int count = jsonArrayDanhSachSanPham.length();
            for (int i = 0; i < count; i++) {
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);

                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHNHO(object.getString("ANHNHO"));
                sanPham.setSOLUONG(object.getInt("SOLUONG"));
                sanPham.setTHONGTIN(object.getString("THONGTIN"));
                sanPham.setMALOAISP(object.getInt("MALOAISP"));
                sanPham.setMATHUONGHIEU(object.getInt("MATHUONGHIEU"));
                sanPham.setMANV(object.getInt("MANV"));
                sanPham.setTENNHANVIEN(object.getString("TENNV"));
                sanPham.setLUOTMUA(object.getInt("LUOTMUA"));

                ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                chiTietKhuyenMai.setPHANTRAMKM(object.getInt("PHANTRAMKM"));
                sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);

                JSONArray jsonArrayThongSoKyThuat = object.getJSONArray("THONGSOKYTHUAT");
                for (int j = 0; j < jsonArrayThongSoKyThuat.length(); j++) {
                    JSONObject jsonObjectThongSoKyThuat = jsonArrayThongSoKyThuat.getJSONObject(j);
                    for (int k = 0; k < jsonObjectThongSoKyThuat.length(); k++) {
                        //Lấy name() tức là lấy cái key tương ứng trong Json
                        String tenchitiet = jsonObjectThongSoKyThuat.names().getString(k);
                        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                        chiTietSanPham.setTENCHITIET(tenchitiet);
                        //Truyền tên chi tiết (cũng là key tương ứng trong value) để lấy value, video 31 lúc 12'
                        chiTietSanPham.setGIATRI(jsonObjectThongSoKyThuat.getString(tenchitiet));

                        chiTietSanPhamList.add(chiTietSanPham);
                    }
                }
                sanPham.setChiTietSanPhamList(chiTietSanPhamList);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPham;
    }
}
