package ma.elouazzani.elouazzani.running.history;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

import ma.elouazzani.elouazzani.running.Database.Data;
import ma.elouazzani.elouazzani.running.Database.DataBase;
import ma.elouazzani.elouazzani.running.R;
import ma.elouazzani.elouazzani.running.run_activity.Run_activity_main;

/**
 * Created by elouazzani on 09/12/2016.
 */
public class History extends AppCompatActivity {
    private TextView total_run;
    List<Data> dataList;
    // admob
    private String TAG = History.class.getSimpleName();
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);
        ImageView imageView=(ImageView)findViewById(R.id.background);
        Glide.with(this).load(R.drawable.history_back).into(imageView);
        total_run=(TextView)findViewById(R.id.total_run);
        customfont();
        // toolbar
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpTo(History.this, new Intent(History.this, Run_activity_main.class));
                overridePendingTransition(R.animator.slide_right, R.animator.slide_right_out);

            }
        });

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        // linear show fo the item
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);
        addData();
        final Recycler_Adapter recycler_adapter=new Recycler_Adapter(this,dataList);
        recyclerView.setAdapter(recycler_adapter);

        total_run.setText(""+recycler_adapter.getItemCount());
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



    private  void customfont(){
        TextView titel_run=(TextView)findViewById(R.id.title_run);
        Typeface typeface=Typeface.createFromAsset(getAssets(),"Arial Black.ttf");
         total_run.setTypeface(typeface);
         titel_run.setTypeface(typeface);


    }
    private void addData()
    {
       dataList=new ArrayList<>();
        DataBase dataBase=new DataBase(this);
        dataList=dataBase.getAllData();

    }
}
