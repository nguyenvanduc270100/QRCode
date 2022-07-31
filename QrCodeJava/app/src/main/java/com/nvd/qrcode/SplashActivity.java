package com.nvd.qrcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.nvd.qrcode.activity.ActivityLanguage;

public class SplashActivity extends AppCompatActivity {
    private Boolean checkFirst, checkInternet;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences spr = getSharedPreferences("myLanguage", MODE_PRIVATE);
        checkFirst = spr.getBoolean("flagFirst", false);

        // check internet
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo()!=null)
        {
            //thông báo có internet
            checkInternet = true;
        }
        else
        {
            //thông báo mất internet
            checkInternet = false;
        }
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checkFirst == false && checkInternet == false) {
                    Intent intent = new Intent(SplashActivity.this, ActivityLanguage.class);
                    startActivity(intent);
                } else if (checkInternet == true && checkFirst == true){
                    AdRequest adRequest = new AdRequest.Builder().build();

                    InterstitialAd.load(getApplicationContext(), "ca-app-pub-3940256099942544/1033173712", adRequest,
                            new InterstitialAdLoadCallback() {
                                @Override
                                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                    // The mInterstitialAd reference will be null until
                                    // an ad is loaded.
                                    mInterstitialAd = interstitialAd;

                                    mInterstitialAd.show(SplashActivity.this);


                                    // FullScreenContentCallback
                                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                        @Override
                                        public void onAdDismissedFullScreenContent() {
                                            // Called when fullscreen content is dismissed.
                                            Intent i = new Intent(SplashActivity.this, MainActivity.class);
                                            startActivity(i);

                                        }

                                        @Override
                                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                                            // Called when fullscreen content failed to show.
                                            Log.d("TAG", "The ad failed to show.");
                                        }

                                        @Override
                                        public void onAdShowedFullScreenContent() {
                                            // Called when fullscreen content is shown.
                                            // Make sure to set your reference to null so you don't
                                            // show it a second time.
                                            mInterstitialAd = null;
                                            Log.d("TAG", "The ad was shown.");
                                        }
                                    });

                                }

                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    // Handle the error

                                    mInterstitialAd = null;
                                }
                            });
                } else if(checkInternet == false && checkFirst == true){
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                } else if (checkFirst == false && checkInternet == true){
                    Intent intent = new Intent(SplashActivity.this, ActivityLanguage.class);
                    startActivity(intent);
                }
            }
        }, 4000);
    }
}