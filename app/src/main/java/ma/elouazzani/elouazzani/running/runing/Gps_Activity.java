package ma.elouazzani.elouazzani.running.runing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ma.elouazzani.elouazzani.running.R;

public class Gps_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.animator.slide_left,R.animator.slide_left_out);
    }
}

