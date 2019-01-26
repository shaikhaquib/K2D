package com.kirana2door.kiranatodoor.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.kirana2door.kiranatodoor.Fragment.Frg_Cart;
import com.kirana2door.kiranatodoor.Fragment.Frg_Home;
import com.kirana2door.kiranatodoor.Fragment.Frg_Menu;
import com.kirana2door.kiranatodoor.Fragment.Frg_Notification;
import com.kirana2door.kiranatodoor.Fragment.Frg_Store;
import com.kirana2door.kiranatodoor.R;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment fragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        fragment = new Frg_Home();
        loadFragment(fragment);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        fragment = new Frg_Home();
                        break;

                    case R.id.notification:
                        fragment = new Frg_Notification();
                        break;

                    case R.id.cart:
                        fragment = new Frg_Cart();
                        break;

                    case R.id.store:
                        fragment = new Frg_Store();
                        break;

                    case R.id.menu:
                        fragment = new Frg_Menu();
                        break;
                }
                return loadFragment(fragment);
            }

        });
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
