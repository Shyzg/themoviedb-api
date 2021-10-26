package com.mobileapplicationdevelopment.themoviedb.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mobileapplicationdevelopment.themoviedb.R;

public class MainMenuActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    NavHostFragment navHostFragment;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        bnv = findViewById(R.id.bottom_navigation_view_main_menu);
        toolbar = findViewById(R.id.toolbar_main_menu);
        setSupportActionBar(toolbar);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment_main_menu);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.nowPlayingFragment, R.id.upcomingFragment).build();
        NavigationUI.setupActionBarWithNavController(MainMenuActivity.this, navHostFragment.getNavController(), appBarConfiguration);
        NavigationUI.setupWithNavController(bnv, navHostFragment.getNavController());
    }
}