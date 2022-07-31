package com.nvd.qrcode;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.nvd.qrcode.fragment.CreateFragment;
import com.nvd.qrcode.fragment.HistoryFragment;
import com.nvd.qrcode.fragment.ScanFragment;
import com.nvd.qrcode.fragment.SettingFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    BottomNavigationView mBottomNavigation;
    private AdView mAdView;
    private Boolean checkInternet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdView = findViewById(R.id.adView);
        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.CALL_PHONE
                ).withListener(new MultiplePermissionsListener() {
                    @Override public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}
                    @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
                }).check();

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
// check internet
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo()!=null)
        {
            //thông báo có internet
            checkInternet = true;

            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }
        else
        {
            //thông báo mất internet
            checkInternet = false;

        }


        frameLayout = findViewById(R.id.framelayout);
        mBottomNavigation = findViewById(R.id.bottom_navigation);

        ScanFragment scanFragment = new ScanFragment();
        replaceFragment(scanFragment);
        mBottomNavigation.getMenu().findItem(R.id.bottom_scan).setChecked(true);
        mBottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.bottom_scan){
                    ScanFragment scanFragment = new ScanFragment();
                    replaceFragment(scanFragment);

                } else if(item.getItemId() == R.id.bottom_history){
                    HistoryFragment historyFragment = new HistoryFragment();
                    replaceFragment(historyFragment);

                } else if(item.getItemId() == R.id.bottom_create){
                    CreateFragment createFragment = new CreateFragment();
                    replaceFragment(createFragment);

                }else if(item.getItemId() == R.id.bottom_settings){
                    SettingFragment settingFragment = new SettingFragment();
                    replaceFragment(settingFragment);
                }
                return true;
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.add(R.id.framelayout, fragment, null);
        fragmentTransaction.replace(R.id.framelayout, fragment, null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}