package ma.elouazzani.elouazzani.running.run_activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ma.elouazzani.elouazzani.running.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Setting_Fragment extends Fragment {


    public Setting_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting_, container, false);
    }


}
