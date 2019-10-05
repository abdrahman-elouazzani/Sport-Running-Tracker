package ma.elouazzani.elouazzani.running.run_activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by elouazzani on 26/11/2016.
 */
public class ViewpagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    public ViewpagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }
}
