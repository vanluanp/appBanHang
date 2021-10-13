package com.example.nlushop.view.dangNhap_dangKy.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.nlushop.R;
import com.example.nlushop.customView.PasswordEditText;
import com.example.nlushop.model.dangNhap_dangKy.ModelDangNhap;
import com.example.nlushop.model.objectClass.NhanVien;
import com.example.nlushop.presenter.dangKy.PresenterLogicDangKy;
import com.example.nlushop.view.dangNhap_dangKy.ViewDangKy;
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
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FragmentDangKy extends Fragment implements ViewDangKy, View.OnClickListener, View.OnFocusChangeListener, GoogleApiClient.OnConnectionFailedListener {
    PresenterLogicDangKy presenterLogicDangKy;
    Button btnDangKy;
    EditText edHoTen, edDiaChiEmail, edMatKhau, edNhapLaiMatKhau;
    SwitchCompat switchEmailDocQuyen;
    TextInputLayout input_edHoTen, input_edMatKhau, input_edDiaChiEmail, input_edNhapLaiMatKhau;
    String MATCHER_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})";
    Pattern pattern;
    Matcher matcher;
    Boolean kiemtrathongtin = false;
    Button btnDangNhapFBDK, btnDangNhapGGDK;
    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    public static int SIGN_IN_GOOGLE_PLUS = 8888;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangky, container, false);

        //Tạo đăng nhập GG
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        //Đặt id 1 cho fragment dang ky
        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(getContext(), 1, this);


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

        btnDangNhapFBDK = view.findViewById(R.id.btnDangNhapFBDK);
        btnDangNhapGGDK = view.findViewById(R.id.btnDangNhapGGDK);

        btnDangNhapFBDK.setOnClickListener(this);
        btnDangNhapGGDK.setOnClickListener(this);

        presenterLogicDangKy = new PresenterLogicDangKy(this);
        btnDangKy = view.findViewById(R.id.btnDangKy);
        edHoTen = view.findViewById(R.id.edHoTenDangKy);
        edDiaChiEmail = view.findViewById(R.id.edDiaChiEmailDangKy);
        edMatKhau = view.findViewById(R.id.edMatKhauDangKy);
        edNhapLaiMatKhau = view.findViewById(R.id.edNhapLaiMatKhauDangKy);
        switchEmailDocQuyen = view.findViewById(R.id.switchEmailDocQuyen);
        input_edHoTen = view.findViewById(R.id.input_edHoTenDangKy);
        input_edDiaChiEmail = view.findViewById(R.id.input_edDiaChiEmailDangKy);
        input_edMatKhau = view.findViewById(R.id.input_edMatKhauDangKy);
        input_edNhapLaiMatKhau = view.findViewById(R.id.input_edNhapLaiMatKhauDangKy);

//        textInputLayout = view.findViewById(R.id.input_edMatKhauDangKy);
//        textInputLayout.setMinimumWidth(300);
//        passwordEditText = view.findViewById(R.id.edMatKhauDangKy);

        btnDangKy.setOnClickListener(this);

        edHoTen.setOnFocusChangeListener(this);
        edDiaChiEmail.setOnFocusChangeListener(this);
        edMatKhau.setOnFocusChangeListener(this);
        edNhapLaiMatKhau.setOnFocusChangeListener(this);
//        confirmInput(view);

        return view;
    }

//    private boolean validatePassword(){
//        String pass = input_edMatKhau.getEditText().getText().toString().trim();
//
//        if(pass.isEmpty()){
//            input_edMatKhau.setErrorEnabled(true);
//            input_edMatKhau.setError("Không thể bỏ trống!");
//            return false;
//        }else{
//            input_edMatKhau.setErrorEnabled(false);
//            input_edMatKhau.setError(null);
//            return true;
//        }

//    }

//    private void confirmInput(View view){
////        if(!validatePassword()){
////            return;
////        }
//        this.pattern = Pattern.compile(MATCHER_PATTERN);
//
//        input_edMatKhau = view.findViewById(R.id.input_edMatKhauDangKy);
//        edMatKhau = view.findViewById(R.id.edMatKhauDangKy);
//
//        edMatKhau.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(!hasFocus){
//                    String chuoi = edMatKhau.getText().toString();
//                    matcher = pattern.matcher(chuoi);
//
//                    if(matcher.matches()){
//                        input_edMatKhau.setError(null);
//                        input_edMatKhau.setErrorEnabled(false);
//                    }else{
//                        input_edMatKhau.setErrorEnabled(true);
//                        input_edMatKhau.setError("Mật khẩu phải bao gồm 6 kí tự, 1 chữ hoa và 1 chữ số");
//                    }
//                }
//            }
//        });
//
//    }

    @Override
    public void DangKyThanhCong() {
        Toast.makeText(getActivity(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangKyThatBai() {
//        Toast.makeText(getActivity(), "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
        //Có như không, vì không chạy hàm presenterLogicDangKy.ThucHienDangKy(nhanVien) nếu kiemtrathongtin = false
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.btnDangKy:
                btnDangKy();
                break;

            case R.id.btnDangNhapFBDK:
                //Hiển thị vòng xoay chờ
                showProgressDialog();
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangKy.this, Arrays.asList("public_profile")) ;
                break;

            case R.id.btnDangNhapGGDK:
                //Hiển thị vòng xoay chờ
                showProgressDialog();
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                //Lưu ý: start sẽ chạy vào onActivityForResult
                startActivityForResult(signInIntent, SIGN_IN_GOOGLE_PLUS);
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

    //Mặc định là true vì switch mặc định là true
    String emailDocQuyen = "true";

    private void btnDangKy() {
        String hoten = edHoTen.getText().toString();
        String email = edDiaChiEmail.getText().toString();
        String matKhau = edMatKhau.getText().toString();
//        String nhapLaiMatKhau = edNhapLaiMatKhau.getText().toString();
        switchEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                emailDocQuyen = isChecked + "";
            }
        });

        if (kiemtrathongtin) {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setTenNV(hoten);
            nhanVien.setTenDangNhap(email);
            nhanVien.setMatKhau(matKhau);
            nhanVien.setEmailDocQuyen(emailDocQuyen);
            nhanVien.setMaLoaiNV(2);

            presenterLogicDangKy.ThucHienDangKy(nhanVien);

        } else {
            Toast.makeText(getActivity(), "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
            Log.d("kiemtradk", "Dang ky that bai!");
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id) {

            case R.id.edHoTenDangKy:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.trim().equals(null)) {
                        kiemtrathongtin = false;
                        input_edHoTen.setError("Vui không để trống mục này!");
                        input_edHoTen.setErrorEnabled(true);
                    } else {
                        kiemtrathongtin = true;
                        input_edHoTen.setError("");
                        input_edHoTen.setErrorEnabled(false);
                    }
                }
                break;

            case R.id.edDiaChiEmailDangKy:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.trim().equals(null)) {
                        kiemtrathongtin = false;
                        input_edDiaChiEmail.setError("Vui không để trống mục này!");
                        input_edDiaChiEmail.setErrorEnabled(true);
                    } else {
                        Boolean kiemTraEmail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if (!kiemTraEmail) {
                            kiemtrathongtin = false;
                            input_edDiaChiEmail.setError("Đây không phải địa chỉ email!");
                            input_edDiaChiEmail.setErrorEnabled(true);
                        } else {
                            kiemtrathongtin = true;
                            input_edDiaChiEmail.setError("");
                            input_edDiaChiEmail.setErrorEnabled(false);
                        }
                    }
                }

                break;

            case R.id.edMatKhauDangKy:
                this.pattern = Pattern.compile(MATCHER_PATTERN);

                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    matcher = pattern.matcher(chuoi);
                    if (matcher.matches()) {
                        kiemtrathongtin = true;
                        input_edMatKhau.setError("");
                        input_edMatKhau.setErrorEnabled(false);
                    } else {
                        kiemtrathongtin = false;
                        input_edMatKhau.setErrorEnabled(true);
                        input_edMatKhau.setError("Mật khẩu phải bao gồm 6 kí tự, 1 chữ hoa và 1 chữ số");
                    }
                }

                break;

            case R.id.edNhapLaiMatKhauDangKy:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    String matkhau = edMatKhau.getText().toString();
                    if (!chuoi.equals(matkhau)) {
                        kiemtrathongtin = false;
                        input_edNhapLaiMatKhau.setErrorEnabled(true);
                        input_edNhapLaiMatKhau.setError("Vui lòng nhập trùng mật khẩu!");
                    } else {
                        kiemtrathongtin = true;
                        input_edNhapLaiMatKhau.setError("");
                        input_edNhapLaiMatKhau.setErrorEnabled(false);
                    }
                }

                break;
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        progressDialog.cancel();//khi đăng nhập lỗi tắt vòng xoay chờ
    }

}
