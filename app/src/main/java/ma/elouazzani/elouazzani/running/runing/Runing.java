package ma.elouazzani.elouazzani.running.runing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.text.SimpleDateFormat;
import java.util.Date;

import ma.elouazzani.elouazzani.running.Database.Data;
import ma.elouazzani.elouazzani.running.Database.DataBase;
import ma.elouazzani.elouazzani.running.R;
import ma.elouazzani.elouazzani.running.run_activity.Run_activity_main;

/**
 * Created by elouazzani on 02/12/2016.
 */
public class Runing extends Activity implements LocationListener {

    LocationManager locationManager;
    private double distance = 0, index_stop = 0;
    private double speed = 0;
    private String time;
    private TextView distanceView, speedView;
    private Chronometer chronometer;
    private ImageView pause_btn, save_btn, go_music;
    boolean stop = false;
    boolean stat = true;
    private long timeWhenStopped = 0;
    // flag for GPS status
    boolean isGPSEnabled = false;
    // flag for network status
    boolean isNetworkEnabled = false;
    //
    boolean isFragment_Gps = false;
    // admob
    private String TAG = Runing.class.getSimpleName();
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runing_layout);
        // show map

        ImageButton show_map = (ImageButton) findViewById(R.id.show_map);
        show_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Gps_Activity.class));
                overridePendingTransition(R.animator.slide_left, R.animator.slide_left_out);
            }
        });
        // find the View into layout
        distanceView = (TextView) findViewById(R.id.distance);
        speedView = (TextView) findViewById(R.id.speed);
        chronometer = (Chronometer) findViewById(R.id.time);
        pause_btn = (ImageView) findViewById(R.id.pause_btn);
        save_btn = (ImageView) findViewById(R.id.save_btn);
        go_music = (ImageView) findViewById(R.id.go_music);
        // Set style font text
        Cm_Font();
        chronometer.start();
        // GPS_PROVIDER
        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        // getting GPS status
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // getting network status
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        try {
            if (isGPSEnabled) {
                locationManager.getProvider(LocationManager.GPS_PROVIDER);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

            } else if (isNetworkEnabled) {
                locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//
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

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            if (stop) index_stop = location.getSpeed();
            else index_stop = 0;
            distance += location.getSpeed() - index_stop;
            speed = location.getSpeed() * 3.6;
            distanceView.setText("" + String.format("%.02f", distance * 0.001));
            speedView.setText("" + String.format("%.01f", speed));

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, provider + " enable", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, provider + " disable", Toast.LENGTH_LONG).show();
    }

    // custom font TextView
    private void Cm_Font() {
        // get the font from the assets
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Langdon.otf");
        //
        distanceView.setTypeface(typeface);
        speedView.setTypeface(typeface);
        chronometer.setTypeface(typeface);

    }

    // click pause_btn
    public void pause(View v) {

        if (v == pause_btn) {

            if (stat) {
                pause_btn.setImageResource(R.mipmap.pause_btn_prs);
                stop = true;
                timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                chronometer.stop();
                Location location = new Location("");
                location.setSpeed(0);
                onLocationChanged(location);
            } else {
                pause_btn.setImageResource(R.mipmap.pause_btn);
                stop = false;
                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chronometer.start();
            }
            stat = !stat;
        }
    }

    // click save_btn
    public void save(View v) {
        if (v == save_btn) {
            if (stat)
                pause(pause_btn);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Message");
            builder.setMessage("are you sure you want to save ?");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    time = chronometer.getText().toString();
                    // date format
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    String currentDateandTime = sdf.format(new Date());
                    Data data = new Data(currentDateandTime, distance * 0.001, time);
                    DataBase dataBase = new DataBase(getApplicationContext());
                    dataBase.putData(data);
                    //
                    Toast.makeText(getApplicationContext(), "successfully saved", Toast.LENGTH_LONG).show();
                    stat = false;
                    pause(pause_btn);
                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    stat = false;
                    pause(pause_btn);

                }
            });
            builder.show();
        }
    }

    public void go_music(View v) {
        if (v == go_music) {
            Intent intent = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
            startActivity(intent);
            overridePendingTransition(R.animator.slider_up, R.animator.no_change);
        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("your progress will not be saved ");
        builder.setPositiveButton("End", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NavUtils.navigateUpTo(Runing.this, new Intent(getApplicationContext(), Run_activity_main.class));
                overridePendingTransition(R.animator.slide_right, R.animator.slide_right_out);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}