package com.kirana2door.kiranatodoor.activities;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.kirana2door.kiranatodoor.api.RetrofitClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton fab;
    Fragment fragment = null;
    ViewDialog progressDialoge;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        progressDialoge=new ViewDialog(this);
        SharedPreferences mPrefs = getSharedPreferences("my_shared_preff",0);
        Global.customer_id = mPrefs.getString("id", "");
        Global.email = mPrefs.getString("email", "");

        if(Global.selshopid == null) {
            Global.selshopid = "0";
        }
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

    public class AddToCart extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL(RetrofitClient.BASE_URL+"addtocart");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("product_id",params[0])
                        .appendQueryParameter("qty",params[1])
                        .appendQueryParameter("shop_id",params[2])
                        .appendQueryParameter("cust_id",params[3]);

                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return(result.toString());

                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            if(result.equalsIgnoreCase("successfully added")){
                //Snackbar.make(getWindow().getDecorView().getRootView(), "Product Successfully Added To Cart !", Snackbar.LENGTH_LONG).show();
                //Snackbar.make(MainActivity.this, "Product Successfully Added To Cart !", Snackbar.LENGTH_LONG).show();
                //Home fragment = (Home) getFragmentManager().findFragmentById(R.id.home);
                //fragment.success();
            } else if (result.equalsIgnoreCase("oops! Please try again!")){
                //Home fragment = (Home) getFragmentManager().findFragmentById(R.id.home);
                //fragment.tryagain();
            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {
                //Home fragment = (Home) getFragmentManager().findFragmentById(R.id.home);
                //fragment.error();
            }
        }

    }
    public class RemoveFromCart extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL(RetrofitClient.BASE_URL+"deletecartitem");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("cart_id",params[0]);

                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return(result.toString());

                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            if(result.equalsIgnoreCase("successfully removed")){
                //Snackbar.make(getWindow().getDecorView().getRootView(), "Product Successfully Added To Cart !", Snackbar.LENGTH_LONG).show();
                //Snackbar.make(MainActivity.this, "Product Successfully Added To Cart !", Snackbar.LENGTH_LONG).show();
                //Home fragment = (Home) getFragmentManager().findFragmentById(R.id.home);
                //fragment.success();
            } else if (result.equalsIgnoreCase("oops! Please try again!")){
                //Home fragment = (Home) getFragmentManager().findFragmentById(R.id.home);
                //fragment.tryagain();
            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {
                //Home fragment = (Home) getFragmentManager().findFragmentById(R.id.home);
                //fragment.error();
            }
        }

    }
}
