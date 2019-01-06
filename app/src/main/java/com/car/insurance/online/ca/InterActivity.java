package com.car.insurance.online.ca;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.car.insurance.online.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class InterActivity extends Activity {
    private Preferences prefs;
    private InterstitialAd interstitial;
    private String delay_banner, delay_inter;
    public static Activity inter;
    private Handler handler = new Handler();
    private Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter);
        prefs = new Preferences(getApplicationContext());
        MobileAds.initialize(this, getString(R.string.APP_ID));
        inter = this;
        Intent intent = getIntent();
        delay_banner = intent.getStringExtra("delay_banner");
        delay_inter = intent.getStringExtra("delay_inter");

        prefs.setClickCounter();
        String ad_id = prefs.getBannerID();
        String limit = prefs.getLimit();
        final int l = Integer.parseInt(limit);
        final int counter = prefs.getClickCounter();

       runnable = new Runnable(){
            @Override
            public void run() {
                    Intent i = new Intent(InterActivity.this, BannerActivity.class);
                    i.putExtra("delay_banner", delay_banner);
                    i.putExtra("delay_inter", delay_inter);
                    startActivity(i);
                    InterActivity.this.finish();
                    InterActivity.this.finishAffinity();

            }
        };
        handler.postDelayed(runnable, (long) (Integer.valueOf(Integer.parseInt(delay_banner) * 900)));

        if ((l==counter)||(counter>=l)){
            Intent i = new Intent(InterActivity.this,MainActivity.class);
            i.putExtra("counter",counter);
            startActivity(i);
            BannerActivity.banner.finish();
            BannerActivity.banner.finishAffinity();
            InterActivity.this.finish();
            InterActivity.this.finishAffinity();
            prefs.resetClickCounter();
            handler.removeCallbacks(runnable);
        }

        Builder adRequest = new Builder();
        this.interstitial = new InterstitialAd(this);
        this.interstitial.setAdUnitId(ad_id);
        this.interstitial.loadAd(adRequest.build());
        this.interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                InterActivity.this.displayInterstitial();
            }
        });

    }

    public void displayInterstitial() {
        if (this.interstitial.isLoaded()) {
            this.interstitial.show();
        }
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacks(runnable);
        Intent i = new Intent(InterActivity.this,MainActivity.class);
        startActivity(i);
        InterActivity.this.finish();
        InterActivity.this.finishAffinity();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
