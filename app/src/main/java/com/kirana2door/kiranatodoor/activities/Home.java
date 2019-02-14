package com.kirana2door.kiranatodoor.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.kirana2door.kiranatodoor.Fragment.Frg_Cart;
import com.kirana2door.kiranatodoor.Fragment.Frg_Home;
import com.kirana2door.kiranatodoor.Fragment.Frg_Menu;
import com.kirana2door.kiranatodoor.Fragment.Frg_Notification;
import com.kirana2door.kiranatodoor.Fragment.Frg_Store;
import com.kirana2door.kiranatodoor.Global;
import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.ViewDialog;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton fab;
    Fragment fragment = null;
    ViewDialog progressDialoge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        progressDialoge=new ViewDialog(this);
        SharedPreferences mPrefs = getSharedPreferences("my_shared_preff",0);
        Global.customer_id = mPrefs.getString("id", "");
        bottomNavigationView = findViewById(R.id.navigation);
        fab = findViewById(R.id.floating_button);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_kirana2door_icons_cart_green));
                bottomNavigationView.setSelectedItemId(R.id.cart);
                fragment = new Frg_Cart();
                loadFragment(fragment);
            }
        });
        fragment = new Frg_Home();
        loadFragment(fragment);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_kirana2door_icons_cart_grey));
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                        fragment = new Frg_Home();
                        break;

                    case R.id.notification:
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_kirana2door_icons_cart_grey));
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                        fragment = new Frg_Notification();
                        break;

                    case R.id.cart:
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_kirana2door_icons_cart_green));
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                        fragment = new Frg_Cart();
                        break;

                    case R.id.store:
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_kirana2door_icons_cart_grey));
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                        fragment = new Frg_Store();
                        break;

                    case R.id.menu:
                        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_kirana2door_icons_cart_grey));
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
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
