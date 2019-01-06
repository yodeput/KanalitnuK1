package com.car.insurance.online.ca;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.car.insurance.online.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static com.car.insurance.online.ca.Preferences.AD_APP_ID;
import static com.car.insurance.online.ca.Preferences.BANNER_ID1;


public class MainActivity extends Activity {

    private String delay_banner, delay_inter;
    private AdView adView1;
    private AdView adView2;

    private Preferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = new Preferences(getApplicationContext());

        MobileAds.initialize(this, AD_APP_ID);
        final EditText edit_ban = findViewById(R.id.edit_ban);
        final EditText edit_inter = findViewById(R.id.edit_inter);
        final EditText edit_id_ban = findViewById(R.id.edit_id_ban);
        final EditText edit_id_inter = findViewById(R.id.edit_id_inter);
        final EditText edit_limit = findViewById(R.id.edit_limit);
        final View adContainer = findViewById(R.id.adMobView);
        String a = prefs.getBannerID();
        String b = prefs.getInterID();
        String c = prefs.getLimit();
        String d = prefs.getdelayban();
        String e = prefs.getdelayin();
        if (a.isEmpty() || a.equals("")) {
            edit_id_ban.setText(getString(R.string.BANNER_ID_1));
            edit_id_inter.setText(R.string.INTER_ID_2);
            edit_limit.setText(R.string.limit);
            edit_ban.setText(R.string.delay_ban);
            edit_inter.setText(R.string.delay_in);
        } else {
            edit_id_ban.setText(a);
            edit_id_inter.setText(b);
            edit_limit.setText(c);
            edit_ban.setText(d);
            edit_inter.setText(e);
        }

        Intent intent = getIntent();
        int aa = intent.getIntExtra("counter", 0);

        AdView mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.SMART_BANNER);
        mAdView.setAdUnitId(BANNER_ID1);
        ((RelativeLayout)adContainer).addView(mAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mAdView.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mAdView.setLayoutParams(params);

        AdView mAdView2 = new AdView(this);
        mAdView2.setAdSize(AdSize.SMART_BANNER);
        mAdView2.setAdUnitId(BANNER_ID1);
        ((RelativeLayout)adContainer).addView(mAdView2);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest2);

        RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams)mAdView2.getLayoutParams();
        params2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mAdView2.setLayoutParams(params2);


        LimitDialog limitDialog = new LimitDialog(MainActivity.this);

        String limit = prefs.getLimit();
        if (limit.equals("")) {
            limit = "0";
        }
        int l = Integer.parseInt(limit);
       if ( l>1 && ((l == aa) || (aa >= l))) {
            limitDialog.show();
        }

        Button but = findViewById(R.id.button_start);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.saveBannerID(edit_id_ban.getText().toString());
                prefs.saveInterID(edit_id_inter.getText().toString());
                prefs.saveLimit(edit_limit.getText().toString());
                prefs.saveDelay(edit_ban.getText().toString(), edit_inter.getText().toString());
                prefs.resetClickCounter();
                Intent i = new Intent(MainActivity.this, BannerActivity.class);
                delay_banner = edit_ban.getText().toString();
                delay_inter = edit_inter.getText().toString();
                i.putExtra("delay_banner", delay_banner);
                i.putExtra("delay_inter", delay_inter);
                startActivity(i);
            }
        });


    }

}
