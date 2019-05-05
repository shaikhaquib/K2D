package com.abatechnology.kirana2door.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abatechnology.kirana2door.Global;
import com.abatechnology.kirana2door.R;
import com.abatechnology.kirana2door.ViewDialog;
import com.abatechnology.kirana2door.adapters.ShipmentCSTNTM;
import com.abatechnology.kirana2door.api.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    public static final int CONNECTION_TIMEOUT = 60000;
    public static final int READ_TIMEOUT = 60000;
    TextView titem,total,shopcst,subttl,timedet;
    Button placeorder;
    ImageView backbtn;
    RelativeLayout rl;
    ViewDialog progressDialog;
    String pincode,shopid,add1,add2,add3,state,city;
    int shipingcost = 0,finalamt = 0,dmin = 0;
    String timedetailsinhrmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payement_secondstep);
        getSupportActionBar().hide();
        backbtn = findViewById(R.id.prd_back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressDialog=new ViewDialog(PaymentActivity.this);
        titem = findViewById(R.id.titem);
        total = findViewById(R.id.total);
        shopcst = findViewById(R.id.shipcost);
        subttl = findViewById(R.id.stotal);
        timedet = findViewById(R.id.timedet);
        placeorder = findViewById(R.id.plord);
        rl = findViewById(R.id.rl);

        titem.setText(getIntent().getStringExtra("titem"));
        total.setText(getIntent().getStringExtra("tprice"));
        pincode = getIntent().getStringExtra("pincode");
        shopid = getIntent().getStringExtra("shopid");
        add1 = getIntent().getStringExtra("add1");
        add2 = getIntent().getStringExtra("add2");
        add3 = getIntent().getStringExtra("add3");
        state = getIntent().getStringExtra("state");
        city = getIntent().getStringExtra("city");
        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceedToPlaceOrder();
            }
        });
        getData();
    }

    private void getData() {
        progressDialog.show();
        Call<ShipmentCSTNTM> call = RetrofitClient
                .getInstance().getApi().getShippingCostNTime(pincode,shopid);
        call.enqueue(new Callback<ShipmentCSTNTM>() {
            @Override
            public void onResponse(Call<ShipmentCSTNTM> call, Response<ShipmentCSTNTM> response) {
                ShipmentCSTNTM mr = response.body();
                progressDialog.dismiss();

                if(Integer.parseInt(total.getText().toString().trim()) < 500 ){
                    shopcst.setText("+ "+mr.getShippingCharges()+" ₹");
                    shipingcost = Integer.parseInt(mr.getShippingCharges());
                }else{
                    shopcst.setText("+ 0 ₹");
                    shipingcost = 0;
                }
                finalamt = shipingcost + Integer.parseInt(total.getText().toString().trim());
                subttl.setText(finalamt + " ₹");
                dmin = Integer.parseInt(mr.getDeliveryTime());
                int hours = dmin / 60;
                int minutes = dmin % 60;
                timedetailsinhrmin = hours+":"+minutes;
                timedet.setText("Your order will be delivered within next "+hours+":"+minutes+" hours.");
            }

            @Override
            public void onFailure(Call<ShipmentCSTNTM> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PaymentActivity.this, "Failed to get customer details !", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void proceedToPlaceOrder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Kirana2Door");
        builder.setMessage("To confirm your order click on Yes");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.show();
                /*Call<DefaultResponse> call = RetrofitClient
                        .getInstance().getApi().placeOrder(Global.email,Global.customer_id,"Cash On Delivery",pincode,shopid,total.getText().toString().trim()
                                ,Integer.toString(shipingcost),Integer.toString(finalamt),add1,add2,add3,state,city);
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        DefaultResponse defaultResponse = response.body();
                        progressDialog.dismiss();
                        if (!defaultResponse.isError()) {
                            Intent intent = new Intent(PaymentActivity.this,OrderSuccess.class);
                            intent.putExtra("orderid",defaultResponse.getErrormsg());
                            startActivity(intent);
                        }else{
                            Snackbar snackbar = Snackbar.make(rl, defaultResponse.getErrormsg(), Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Snackbar snackbar = Snackbar.make(rl, "Failed to process your request !", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                });*/
                new PaymentActivity.placeOrder().execute(Global.email,Global.customer_id,"Cash On Delivery",pincode,shopid,total.getText().toString().trim()
                        ,Integer.toString(shipingcost),Integer.toString(finalamt),add1,add2,add3,state,city);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing
            }
        });

        builder.show();
    }


    private class placeOrder extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.show();

        }
        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL(RetrofitClient.BASE_URL+"placeorder");

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
                        .appendQueryParameter("email",params[0])
                        .appendQueryParameter("cid",params[1])
                        .appendQueryParameter("payment_type",params[2])
                        .appendQueryParameter("pincode",params[3])
                        .appendQueryParameter("shopid",params[4])
                        .appendQueryParameter("totalprice",params[5])
                        .appendQueryParameter("shippingcost",params[6])
                        .appendQueryParameter("finalprice",params[7])
                        .appendQueryParameter("add1",params[8])
                        .appendQueryParameter("add2",params[9])
                        .appendQueryParameter("add3",params[10])
                        .appendQueryParameter("state",params[11])
                        .appendQueryParameter("city",params[12]);

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

            progressDialog.dismiss();
            try {
                JSONObject jobj = new JSONObject(result);

                if(!jobj.getBoolean("error"))
                {
                    Intent intent = new Intent(PaymentActivity.this,OrderSuccess.class);
                    intent.putExtra("orderid",jobj.getString("errormsg"));
                    intent.putExtra("timedet",timedetailsinhrmin);
                    startActivity(intent);

                }else if (result.equalsIgnoreCase("oops! Please try again!")){
                    Snackbar snackbar = Snackbar.make(rl, "Failed to process your request !", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}