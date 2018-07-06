package com.example.ranggarizky.tugas_akhir.mainpage;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.ranggarizky.tugas_akhir.R;
import com.example.ranggarizky.tugas_akhir.keywordpage.KeywordFragment;
import com.example.ranggarizky.tugas_akhir.settingpage.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final DashBoardFragment fragment = new DashBoardFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_container, fragment)
                    .commit();
        }

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home" ,R.drawable.ic_baseline_home_24px);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Kata Kunci", R.drawable.ic_baseline_vpn_key_24px);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Setting", R.drawable.ic_baseline_settings_20px);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.light_grey));
        bottomNavigation.setAccentColor(getResources().getColor(R.color.colorAccent));
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.grey));

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                Fragment fragment = null;

                switch (position) {
                    case 0:
                        fragment = new DashBoardFragment();
                        break;
                    case 1:
                        fragment = new KeywordFragment();
                        break;
                    case 2:
                        fragment = new SettingFragment();
                        break;
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .commit();
                return true;
            }
        });
    }



}
