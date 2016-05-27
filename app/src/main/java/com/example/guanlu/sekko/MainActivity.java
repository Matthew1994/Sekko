package com.example.guanlu.sekko;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,FragmentIndicator.OnIndicateListener{

    private int mark = 0;
    public static Fragment[] mFragments;
    FragmentIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setFragmentIndicator(0);




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
        getMenuInflater().inflate(R.menu.main, menu);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            showLoginDialog(this);
        } else if (id == R.id.nav_list) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,OrderActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //fragment
    private void setFragmentIndicator(int whichIsDefault) {
        mark = whichIsDefault;
        mFragments = new Fragment[3];
        mFragments[0] = getSupportFragmentManager().findFragmentById(R.id.movie_fragment);
        mFragments[1] = getSupportFragmentManager().findFragmentById(R.id.cimema_fragment);
        mFragments[2] = getSupportFragmentManager().findFragmentById(R.id.dating_fragment);

        switchFragment(whichIsDefault, false);

        mIndicator=(FragmentIndicator) findViewById(R.id.indicator);
        mIndicator.setOnIndicateListener(this);
    }


    @Override
    public void OnIndicate(View v, int which) {
        switchFragment(which, false);
    }


    private void switchFragment(int which, boolean animate) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (animate) {
            if (which > mark)
                ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
            else
                ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
        }
        for (int i = 0; i < mFragments.length; ++i)
            ft.hide(mFragments[i]);
        ft.show(mFragments[which]);
        ft.commit();
        /*if (mFragments[which] instanceof CallLogFragment)
            mFragments[which].onResume();*/
        FragmentIndicator.setIndicator(which);
        mark = which;
    }


    public void showLoginDialog(Context context) {

        final LoginDialog loginDialog = new LoginDialog(context,"login");
        loginDialog.init(1);
        loginDialog.show();
        loginDialog.setClickListener(new LoginDialog.ClickListenerInterface(){

            @Override
            public void doLogin() {

                loginDialog.dismiss();

                /*sharedPreferences = getSharedPreferences("login",Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "NULL");
                String password = sharedPreferences.getString("password","NULL");

                if(username.equals(loginDialog.getUserName()) && password.equals(loginDialog.getPassword())) {
                    Toast.makeText(getBaseContext(),"success",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(),"Wrong userName or Password",Toast.LENGTH_SHORT).show();
                    loginDialog.init(1);*/
            }
            @Override
            public void doCancel() {
                loginDialog.dismiss();
            }

            @Override
            public void doRegister() {
                /*String name = loginDialog.getRigisterUserName();
                String password = loginDialog.getRegisterPassword();
                sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(name==null || password==null) {
                    Toast.makeText(getBaseContext(),"Error! UserName or Password is valid!",Toast.LENGTH_SHORT).show();
                } else {

                    editor.putString("username", name);
                    editor.putString("password", password);
                    editor.commit();
                    Toast.makeText(getBaseContext(), "Rigister Success!\n "+name.toString()+sharedPreferences.getString("name","").toString()
                            +password.toString()+sharedPreferences.getString("password","").toString(), Toast.LENGTH_SHORT).show();*/
                    loginDialog.init(1);

            }

            @Override
            public void doTurn() {
                loginDialog.init(2);
            }

        });

}
}
