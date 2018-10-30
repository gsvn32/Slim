package com.example.lenovo.slim;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private  DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new login_act()).commit();
            navigationView.setCheckedItem(R.id.nav_ls);
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        SharedPrefManager.getInstance(this).logout();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new login_act()).commit();
        return  true;

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ls) {
            // Handle the camera action
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new login_act()).commit();


        } else if (id == R.id.nav_profile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new profile()).commit();

        } else if (id == R.id.nav_exercise) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new content_act()).commit();
        } else if (id == R.id.nav_gym) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new my_Gym_act()).commit();

        } else if (id == R.id.nav_progress) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new data_act()).commit();

        } else if (id == R.id.nav_send) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new login_act()).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





}
