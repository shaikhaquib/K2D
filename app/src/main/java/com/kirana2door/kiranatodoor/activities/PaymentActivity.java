package com.kirana2door.kiranatodoor.activities;

import android.content.DialogInterface;
import android.content.Intent;
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

import com.kirana2door.kiranatodoor.Global;
import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.ViewDialog;
import com.kirana2door.kiranatodoor.adapters.ShipmentCSTNTM;
import com.kirana2door.kiranatodoor.api.RetrofitClient;
import com.kirana2door.kiranatodoor.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    TextView titem,total,shopcst,subttl,timedet;
    Button placeorder;
    ImageView backbtn;
    RelativeLayout rl;
    ViewDialog progressDialog;
    String pincode,shopid,add1,add2,add3,state,city;
    int shipingcost = 0,finalamt = 0,dmin = 0;

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
                Call<DefaultResponse> call = RetrofitClient
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
                });
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
}