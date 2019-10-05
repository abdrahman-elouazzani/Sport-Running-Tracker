package ma.elouazzani.elouazzani.running.programmes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import ma.elouazzani.elouazzani.running.R;
import ma.elouazzani.elouazzani.running.run_activity.Run_activity_main;

/**
 * Created by elouazzani on 30/11/2016.
 */
public class Programme_activity extends AppCompatActivity {
    // admob
    private String TAG = Programme_activity.class.getSimpleName();
    InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpTo(Programme_activity.this, new Intent(Programme_activity.this, Run_activity_main.class));

            }
        });
        ImageView imageView=(ImageView)findViewById(R.id.backdrop);
        Glide.with(this).load(R.drawable.runing1).into(imageView);
        TextView fullText=(TextView)findViewById(R.id.fullText);
        fullText.setText(getResources().getString(R.string.fullText1).toString());
        TextView titel=(TextView)findViewById(R.id.titel);
        titel.setText(getResources().getString(R.string.title1).toString());



        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.admob_intestitial));

        AdRequest adRequestt = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequestt);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });

    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }





}
