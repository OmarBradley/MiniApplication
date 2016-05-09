package com.example.tacademy.miniapplication;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.annimon.stream.Optional;
import com.example.tacademy.miniapplication.chatting.ChattingFragment;
import com.example.tacademy.miniapplication.facebook.FacebookFragment;
import com.example.tacademy.miniapplication.tstore.TStoreFragment;
import com.example.tacademy.miniapplication.youtube.YoutubeFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // 맨처음 구동된다면..
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.container, new TStoreFragment()).commit();
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_tstore) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new TStoreFragment()).commit();
        } else if (id == R.id.nav_facebook) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new FacebookFragment()).commit();
        } else if (id == R.id.nav_youtube) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new YoutubeFragment()).commit();
        } else if (id == R.id.nav_chatting) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ChattingFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
