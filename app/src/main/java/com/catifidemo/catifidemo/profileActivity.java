package com.catifidemo.catifidemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class profileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment frag= null;
    users active_u;
    TextView user_name,user_mail;
    View headerView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("myapp","profileActivity start");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, new MainFragment()); // replace a Fragment with Frame Layout
        transaction.commit(); // commit the changes

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        headerView = navigationView.getHeaderView(0);


        user_name=(TextView)headerView.findViewById(R.id.user_name);
        user_mail=(TextView)headerView.findViewById(R.id.user_mail);

        active_u=  (users) getIntent().getSerializableExtra("users");

        Log.d("myapp", "name: " + active_u.getName() );
        Log.d("myapp", "mail: " + active_u.getEmail() );

        Log.d("myapp "," before ");

        user_name.setText(active_u.getName() );
        user_mail.setText(active_u.getEmail() );

        Log.d("myapp "," after ");

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment frag = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_wifi) {
            // Handle the camera action
            frag = new WifiFragment();

        } else if (id == R.id.nav_edit) {

            frag = new EditFragment();

        } else if (id == R.id.nav_advertise) {

            frag = new AdvertiseFragment();

        } else if (id == R.id.nav_coupon) {

            frag = new CouponFragment();

        } else if (id == R.id.nav_manage) {

            frag = new ManageFragment();

        } else if (id == R.id.nav_share) {

            frag = new MainFragment();

        } else if (id == R.id.nav_feedback) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (frag != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, frag); // replace a Fragment with Frame Layout
            transaction.commit(); // commit the changes
//            drawer.closeDrawers(); // close the all open Drawer Views
            return true;
        }else{
            Log.d("myapp","frag == Null");
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    protected void adClick(View view){
        Log.d("myapp","adClick");
        startActivity(new Intent(this, adActivity.class));
    }


    protected void get_Wifi_status(View view){
        String status="";
        Log.d("myapp","get_Wifi_status function");
//        Connect_to_wifi(Context);
//        return status;
    }



    protected void Connect_to_wifi(){
        String networkSSID = "N&C.ltd";
        String networkPass = "049896970adi";

        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"" + networkSSID + "\"";   // Please note the quotes. String should contain ssid in quotes

        conf.wepKeys[0] = "\"" + networkPass + "\"";
        conf.wepTxKeyIndex = 0;
        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);

        conf.preSharedKey = "\""+ networkPass +"\"";

        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);

        WifiManager wifiManager = (WifiManager) getApplication().getSystemService(Context.WIFI_SERVICE);
        wifiManager.addNetwork(conf);

        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
        for( WifiConfiguration i : list ) {
            Log.d("myapp","wifi connection: "+i);

            if(i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
                wifiManager.disconnect();
                wifiManager.enableNetwork(i.networkId, true);
                wifiManager.reconnect();
                break;
            }
        }

        Log.d("myapp","finish ");
    }

}
