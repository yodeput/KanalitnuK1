package com.car.insurance.online.ca;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.car.insurance.online.R;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;

import static com.car.insurance.online.ca.Preferences.BANNER_ID1;
import static com.car.insurance.online.ca.Preferences.BANNER_ID2;


public class LimitDialog  extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes;
    private AdView adView1;
    private AdView adView2;

    public LimitDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_limit);
        yes = (Button) findViewById(R.id.btn_yes);
        yes.setOnClickListener(this);

        adView1 = findViewById(R.id.adView1);
        adView2 = findViewById(R.id.adView2);
        adView1.setAdUnitId(BANNER_ID1);
        adView2.setAdUnitId(BANNER_ID2);
        Builder adRequest = new Builder();

        try {
            this.adView1.loadAd(adRequest.build());
            this.adView2.loadAd(adRequest.build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}