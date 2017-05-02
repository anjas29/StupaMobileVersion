package com.exercise.stupa;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Fragment dashboardFragment, historyFragment, scheduleFragment, currentFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    dashboardFragment = new DashboardFragment();
                    currentFragment = dashboardFragment;
                    fragmentManager.beginTransaction().replace(R.id.content, currentFragment).commit();
                    return true;
                case R.id.navigation_history:
                    historyFragment = new HistoryFragment();
                    currentFragment = historyFragment;
                    fragmentManager.beginTransaction().replace(R.id.content, currentFragment).commit();
                    return true;
                case R.id.navigation_schedule:
                    scheduleFragment = new ScheduleFragment();
                    currentFragment = scheduleFragment;
                    fragmentManager.beginTransaction().replace(R.id.content, currentFragment).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(savedInstanceState != null){
            currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, "lastFragment");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content, currentFragment).commit();
        }else{
            dashboardFragment = new DashboardFragment();
            historyFragment = new HistoryFragment();

            currentFragment = dashboardFragment;
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content, currentFragment).commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "lastFragment", currentFragment);
    }
}
