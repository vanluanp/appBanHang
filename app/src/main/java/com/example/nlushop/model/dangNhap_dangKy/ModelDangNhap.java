package com.example.nlushop.model.dangNhap_dangKy;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nlushop.connectInternet.DownloadJson;
import com.example.nlushop.view.trangChu.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangNhap {
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;

    public AccessToken LayTokenFacebookCurrent(){

        //Dùng accessTokenTracker để lấy lại token nếu có thay đổi (onCurrentAccessTokenChanged)
        //Thường thì sau 1 thời gian token sẽ mất do bộ nhớ cache
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };
        //Lấy token hiện tại có sẵn - không thay đổi
        accessToken = AccessToken.getCurrentAccessToken();

        return accessToken;
    }

    public String LayCacheDangNhap(Context context){
        SharedPreferences cacheDangNhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        String tennv = cacheDangNhap.getString("tennv", "");

        return tennv;

    }

    //Dang Nhap SharePreference
    public void CapNhatCacheDangNhap(Context context, String tennv){
        SharedPreferences cacheDangNhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cacheDangNhap.edit();
        editor.putString("tennv", tennv);
        editor.commit();
    }

    public boolean KiemTraDangNhap(Context context, String tendangnhap, String matkhau){
        boolean kiemtra = false;
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsMethod = new HashMap<>();
        hsMethod.put("method", "KiemTraDangNhap");

        HashMap<String, String> hsTenDangNhap = new HashMap<>();
        hsTenDangNhap.put("tendangnhap", tendangnhap);

        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau", matkhau);

        attrs.add(hsMethod);
        attrs.add(hsTenDangNhap);
        attrs.add(hsMatKhau);
        DownloadJson downloadJson = new DownloadJson(duongdan, attrs);
        downloadJson.execute();

        try {
            String dulieu = downloadJson.get();
            JSONObject jsonObject = new JSONObject(dulieu);
            String ketqua = jsonObject.getString("ketqua");
            if(ketqua.equals("true")){
                kiemtra = true;
                String tennv = jsonObject.getString("tennv");

                CapNhatCacheDangNhap(context, tennv);

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

    public void huyTokenTracker(){
        accessTokenTracker.stopTracking();
    }

    //Tạo đăng nhập GG
    public GoogleApiClient LayGoogleApiClient(Context context, int id, GoogleApiClient.OnConnectionFailedListener failedListener){
        GoogleApiClient mGoogleApiClient;

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        //Vì context là cha của tất cả activity nên ép kiểu về AppCompatActivity.
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage(((AppCompatActivity)context), id, failedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API,  gso).build();

//        Cách 2:
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder()
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
//                .enableAutoManage(getActivity(), this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API,  gso).build();

        return mGoogleApiClient;
    }

    public GoogleSignInResult LayThongTinDangNhapGoogle(GoogleApiClient googleApiClient){
        OptionalPendingResult<GoogleSignInResult> optionalPendingResult = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(optionalPendingResult.isDone()){
            return optionalPendingResult.get();
        }else{
            return null;
        }
    }

}
