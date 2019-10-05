package ma.elouazzani.elouazzani.running.run_activity;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import ma.elouazzani.elouazzani.running.R;
import ma.elouazzani.elouazzani.running.runing.Runing;

/**
 * A simple {@link Fragment} subclass.
 */
public class Activity_Fragment extends Fragment {

private Context context;
    static final Integer LOCATION = 0x1;
    ImageView image;
    public Activity_Fragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_activity_, container, false);
          image=(ImageView)view.findViewById(R.id.imageView);
          Glide.with(this).load(R.drawable.runing_now).into(image);
          ImageView start_btn=(ImageView)view.findViewById(R.id.start_btn);
          start_btn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  askForPermission(Manifest.permission.ACCESS_FINE_LOCATION, LOCATION);
              }
          });
        return view;
}

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) getContext(), permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions((Activity) getContext(), new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
            }
        } else {
            startActivity(new Intent(getContext(), Runing.class));
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(ActivityCompat.checkSelfPermission(getContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED){
            switch (requestCode) {
                //Location
                case 1:
                   startActivity(new Intent(getContext(), Runing.class));
                    break;

            }

            Toast.makeText(getContext(), "Permission granted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

}
