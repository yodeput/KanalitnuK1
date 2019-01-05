package jabar.bdg.pajakkita;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


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

        MobileAds.initialize(this, getString(R.string.APP_ID));
        final EditText edit_ban = findViewById(R.id.edit_ban);
        final EditText edit_inter = findViewById(R.id.edit_inter);
        final EditText edit_id_ban = findViewById(R.id.edit_id_ban);
        final EditText edit_id_inter = findViewById(R.id.edit_id_inter);
        final EditText edit_limit = findViewById(R.id.edit_limit);
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


        adView1 = findViewById(R.id.adView1);
        adView2 = findViewById(R.id.adView2);
        Builder adRequest = new Builder();
        try {
            this.adView1.loadAd(adRequest.build());
            this.adView2.loadAd(adRequest.build());
        } catch (Exception ee) {
            ee.printStackTrace();
        }


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

    private void show_dialog_limit() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Limit Reached");
        alertDialog.setMessage("Impression Limit Reached");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


}
