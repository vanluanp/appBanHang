package com.example.nlushop.view.trangChu;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.nlushop.R;
import com.example.nlushop.adapter.ExpandAdapter;
import com.example.nlushop.adapter.ViewPagerAdapter;
import com.example.nlushop.model.dangNhap_dangKy.ModelDangNhap;
import com.example.nlushop.model.objectClass.LoaiSanPham;
import com.example.nlushop.presenter.chiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.nlushop.presenter.trangChu.xuLyMenu.PresenterLogicXyLyMenu;
import com.example.nlushop.view.dangNhap_dangKy.DangNhapActivity;
import com.example.nlushop.view.gioHang.GioHangActivity;
import com.example.nlushop.view.timKiem.TimKiemActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu, GoogleApiClient.OnConnectionFailedListener, AppBarLayout.OnOffsetChangedListener, View.OnClickListener {
    //    public static final String SERVER_NAME = "http://10.0.3.2:84/nlshop/loaisanpham.php";
//    public static final String SERVER = "http://10.0.3.2:84/nlshop";
    public static final String SERVER_NAME = "http://192.168.1.8:84/nlshop/loaisanpham.php";
    public static final String SERVER = "http://192.168.1.8:84/nlshop";

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ExpandableListView expandableListView;
    PresenterLogicXyLyMenu presenterLogicXyLyMenu;
    String tennguoidung = "";
    AccessToken accessToken;
    Menu menu;
    ModelDangNhap modelDangNhap;
    MenuItem itemDangNhap, menuItemDangXuat;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInResult googleSignInResult;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView txtGioHang;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    Button btnTimKiem;
    ImageButton magic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //L??u ??: Ph???i kh???i t???o Facebook sdkInitialize tr?????c khi setContentView.
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.trangchu_layout);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        drawerLayout = findViewById(R.id.drawerLayout);
        expandableListView = findViewById(R.id.epMenu);
        appBarLayout = findViewById(R.id.appbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        btnTimKiem = findViewById(R.id.btnTimKiem);
        magic = findViewById(R.id.magic);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
        presenterLogicXyLyMenu = new PresenterLogicXyLyMenu(this);
        modelDangNhap = new ModelDangNhap();

        presenterLogicXyLyMenu.LayDanhSachMenu();

        //?????t id cho 2 fragment
        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(this, 0, this);
        if (mGoogleApiClient == null && !mGoogleApiClient.isConnected()) {
            mGoogleApiClient = modelDangNhap.LayGoogleApiClient(this, 1, this);
        }

        appBarLayout.addOnOffsetChangedListener(this);

        btnTimKiem.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        magic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "B???n v???a nh???n ???????c m???t ????n ?????t h??ng m???i";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(TrangChuActivity.this, "My Notification");

                builder.setSmallIcon(R.drawable.ic_baseline_message_24);
                builder.setContentTitle("Th??ng b??o m???i");
                builder.setContentText(message);
                builder.setAutoCancel(true);

//                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(TrangChuActivity.this);
//                notificationManager.notify(0, builder.build());
                managerCompat.notify(1, builder.build());
            }
        });

    }

    //Kh???i t???o menu
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        this.menu = menu;

        MenuItem iGioHang = menu.findItem(R.id.itGioHang);//L???y item gi??? h??ng
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);//G???i ?????n giao di???n custom gi??? h??ng
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);//G??n id v??o cho textView

        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));//set s??? l?????ng sp gi??? h??ng

        //X??? l?? nh???n v??o gi??? h??ng, giaoDienjCustomGioHang l??c n??y l?? c??i layout gi??? h??ng, ch??? kh??ng c??n l?? itGioHang n???a, video 41.
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(TrangChuActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        itemDangNhap = menu.findItem(R.id.itDangNhap);
        menuItemDangXuat = menu.findItem(R.id.itDangXuat);

        //Set name FB
        accessToken = presenterLogicXyLyMenu.LayTokenNguoiDungFB();
        if (accessToken != null) {
            //B?????c 2 l???y tr?????ng name th??ng qua token v???a l???y ???????c
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        tennguoidung = object.getString("name");
                        //set ten tren menu
                        itemDangNhap.setTitle("Hi, " + tennguoidung);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

            Bundle parameter = new Bundle();
            //??ang mu???n l???y name ng?????i d??ng
            parameter.putString("fields", "name");

            graphRequest.setParameters(parameter);
            //g???i executeAsync m???i v??o onCompleted, m???i g???i th??nh c??ng
            graphRequest.executeAsync();
        }

        //Set name GG
        googleSignInResult = modelDangNhap.LayThongTinDangNhapGoogle(mGoogleApiClient);
        if (googleSignInResult != null) {
            itemDangNhap.setTitle(googleSignInResult.getSignInAccount().getDisplayName());
        }

        //Set name Login SharePreference
        String tennv = modelDangNhap.LayCacheDangNhap(this);
        if (!tennv.equals("")) {
            itemDangNhap.setTitle(tennv);
        }

        //Hi???n th??? ????ng xu???t
        if (accessToken != null || googleSignInResult != null || !tennv.equals("")) {
            menuItemDangXuat.setVisible(true);
        }

        return true;


    }

    //????? x??? l?? khi click trong menu ???????c x??? ra (menu lua chon dang nhap, dang ky)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();
        switch (id) {
            case R.id.itDangNhap:
                //n???u accessToken l?? null(t???c ch??a ????ng nh???p) th?? m???i v??o trang ????ng nh???p
                if (accessToken == null && googleSignInResult == null && modelDangNhap.LayCacheDangNhap(this).equals("")) {
                    Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(iDangNhap);
                } else {
                }
                break;

            case R.id.itDangXuat:
                if (accessToken != null) {
                    LoginManager.getInstance().logOut();
                    //clear menu c??
                    this.menu.clear();
                    //t???o menu m???i
                    this.onCreateOptionsMenu(this.menu);
                }

                if (googleSignInResult != null) {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                    //clear menu c??
                    this.menu.clear();
                    //t???o menu m???i
                    this.onCreateOptionsMenu(this.menu);
                }

                if (!modelDangNhap.LayCacheDangNhap(this).equals("")) {
                    modelDangNhap.CapNhatCacheDangNhap(this, "");
                    //clear menu c??
                    this.menu.clear();
                    //t???o menu m???i
                    this.onCreateOptionsMenu(this.menu);
                }
                break;

            case R.id.itSearch:
                Intent iTimKiem = new Intent(this, TimKiemActivity.class);
                startActivity(iTimKiem);
                break;
        }
        return true;
    }

    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
        ExpandAdapter expandAdapter = new ExpandAdapter(this, loaiSanPhamList);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
//        Log.d("kiemtra", loaiSanPhamList.get(0).getTENLOAISP());
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //X??? l?? khi vu???t l??n thanh m???t t??m ki???m
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //chi???u cao verticalOffset khi vu???t l??n (s??? ??m)
        if (collapsingToolbarLayout.getHeight() + verticalOffset <= 1.5 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)) {
            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(0).setDuration(200);//???n ??i sau 200ms

            MenuItem itSearch = menu.findItem(R.id.itSearch);
            itSearch.setVisible(true);
        } else {
            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(1).setDuration(200);

            try {
                MenuItem itSearch = menu.findItem(R.id.itSearch);
                itSearch.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Do ch???y v??o onResume khi ch???y l???n ?????u n??n txtGioHang s??? null -> l???i
        if (txtGioHang != null) {
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));//set s??? l?????ng sp gi??? h??ng
        }
//        Log.d("abss", String.valueOf(presenterLogicChiTietSanPham.DemSanPhamCoTrongGioHang(this)));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnTimKiem:
                Intent iTimKiem = new Intent(this, TimKiemActivity.class);
                startActivity(iTimKiem);
                break;

//            case R.id.magic:
//                String message = "B???n v???a c?? m???t ????n h??ng m???i";
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(TrangChuActivity.this, "My Notification");
//
//                builder.setSmallIcon(R.drawable.ic_baseline_message_24);
//                builder.setContentTitle("Th??ng b??o m???i");
//                builder.setContentText(message);
//                builder.setAutoCancel(true);
//
////                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(TrangChuActivity.this);
////                notificationManager.notify(0, builder.build());
//                managerCompat.notify(1, builder.build());
//
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//                    NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
//                    NotificationManager manager = getSystemService(NotificationManager.class);
//                    manager.createNotificationChannel(channel);
//                }
//
//                break;
        }
    }
}
