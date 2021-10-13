package com.example.nlushop.view.dangNhap_dangKy.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nlushop.R;
import com.example.nlushop.model.dangNhap_dangKy.ModelDangNhap;
import com.example.nlushop.view.trangChu.TrangChuActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;

public class FragmentDangNhap extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    Button btnDangNhapFB, btnDangNhapGG, btnDangNhap;
    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    public static int SIGN_IN_GOOGLE_PLUS = 8888;
    ProgressDialog progressDialog;
    EditText edTenDangNhap, edMatKhau;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);

        //Tạo đăng nhập GG
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        //Đặt id 0 cho fragment dang nhap
        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(getContext(), 0, this);


        //Tạo đăng nhập FB
        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("kiemtra","Dang nhap FB thanh cong!");
                progressDialog.cancel();//Tắt vòng xoay chờ
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }

            @Override
            public void onCancel() {
                Log.d("kiemtra","Dang nhap FB thoat!");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("kiemtra","Dang nhap FB error!");

            }
        });

        btnDangNhapFB = view.findViewById(R.id.btnDangNhapFB);
        btnDangNhapGG = view.findViewById(R.id.btnDangNhapGG);
        btnDangNhap = view.findViewById(R.id.btnDangNhap);
        edTenDangNhap = view.findViewById(R.id.edDiaChiEmailDangNhap);
        edMatKhau = view.findViewById(R.id.edMatKhauDangNhap);

        btnDangNhapFB.setOnClickListener(this);
        btnDangNhapGG.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDangNhapFB:
                //Hiển thị vòng xoay chờ
                showProgressDialog();
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile")) ;
                break;

            case R.id.btnDangNhapGG:
                //Hiển thị vòng xoay chờ
//                showProgressDialog();
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                //Lưu ý: start sẽ chạy vào onActivityForResult
                startActivityForResult(signInIntent, SIGN_IN_GOOGLE_PLUS);
                break;

            case R.id.btnDangNhap:
                //Hiển thị vòng xoay chờ
                showProgressDialog();
                String tendangnhap = edTenDangNhap.getText().toString();
                String matkhau = edMatKhau.getText().toString();
                ModelDangNhap modelDangNhap = new ModelDangNhap();
                boolean kiemtra = modelDangNhap.KiemTraDangNhap(getActivity(), tendangnhap, matkhau);
                if(kiemtra){
                    progressDialog.cancel();//Tắt vòng xoay chờ
                    Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                    startActivity(iTrangChu);
                }else{
                    progressDialog.cancel();//Tắt vòng xoay chờ
                    Toast.makeText(getActivity(), "Tên đăng nhập và mật khẩu không đúng !", Toast.LENGTH_SHORT).show();//27'
                }
                break;
        }

    }

    //Hiển thị vòng xoay chờ
    private void showProgressDialog(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Vui lòng đợi");
            progressDialog.setIndeterminate(true);
        }
        progressDialog.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //gọi login with FB
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //nếu requestcode nhận được == SIGN_IN_GOOGLE_PLUS
        if(requestCode == SIGN_IN_GOOGLE_PLUS){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            Log.d("googlemail", result.getSignInAccount().getEmail());
            if(result.isSuccess()){
                progressDialog.cancel();//Tắt vòng xoay chờ
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }

        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        progressDialog.cancel();//khi đăng nhập lỗi tắt vòng xoay chờ

    }

}
