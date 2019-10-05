package ma.elouazzani.elouazzani.running.run_activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import ma.elouazzani.elouazzani.running.R;

/**
 * Created by elouazzani on 26/11/2016.
 */
public class Run_activity_main extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<Fragment> fragments;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.run_activity_main_layout);
        //toolbar
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // tabLayout
        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Activity"));
      //  tabLayout.addTab(tabLayout.newTab().setText("Location"));
        tabLayout.addTab(tabLayout.newTab().setText("Programme"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        // drawerLayout and set drawertoolgle to listener to it
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);
        // view pager
        addFragment();
        viewPager=(ViewPager)findViewById(R.id.pager);
        ViewpagerAdapter viewpagerAdapter=new ViewpagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(viewpagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        onTabChangeListener(tabLayout);


    }


    private void addFragment()
    {
        fragments=new ArrayList<>();
        fragments.add(new Activity_Fragment());
     //   fragments.add(new Location_Fragment());
        fragments.add(new Programme_Fragment());


    }
    private void onTabChangeListener(TabLayout tabLayout)
    {

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
                    viewPager.setCurrentItem(tab.getPosition());
                }catch (Exception e){e.printStackTrace();}
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
