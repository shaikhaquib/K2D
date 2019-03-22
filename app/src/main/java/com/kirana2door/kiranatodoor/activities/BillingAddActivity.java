package com.kirana2door.kiranatodoor.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.kirana2door.kiranatodoor.Global;
import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.ViewDialog;
import com.kirana2door.kiranatodoor.api.RetrofitClient;
import com.kirana2door.kiranatodoor.models.MenuResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillingAddActivity extends AppCompatActivity {

    public EditText name,add1,add2,add3,phno,city,state,pin,email;
    ImageView backbtn;
    public Button next;
    ViewDialog progressDialog;
    public boolean flgallow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_firststep);
        getSupportActionBar().hide();

        backbtn = findViewById(R.id.prd_back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressDialog=new ViewDialog(BillingAddActivity.this);
        name = findViewById(R.id.fullname);
        phno = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        add1 = findViewById(R.id.comadd);
        add2 = findViewById(R.id.add2);
        add3 = findViewById(R.id.add3);
        city = findViewById(R.id.city);
        pin = findViewById(R.id.pincode);
        state = findViewById(R.id.state);
        next = findViewById(R.id.nextbtn);
        getDate();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flgallow) {
                    Intent intent = new Intent(BillingAddActivity.this, PaymentActivity.class);
                    intent.putExtra("shopid", getIntent().getStringExtra("shopid"));
                    intent.putExtra("titem", getIntent().getStringExtra("totalitems"));
                    intent.putExtra("tprice", getIntent().getStringExtra("totalp"));
                    intent.putExtra("pincode", pin.getText().toString().trim());
                    intent.putExtra("add1", add1.getText().toString().trim());
                    intent.putExtra("add2", add2.getText().toString().trim());
                    intent.putExtra("add3", add3.getText().toString().trim());
                    intent.putExtra("state", state.getText().toString().trim());
                    intent.putExtra("city", city.getText().toString().trim());
                    startActivity(intent);
                }else{
                    Toast.makeText(BillingAddActivity.this, "Unable to get billing information ! Please Try again ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getDate() {
        progressDialog.show();
        Call<MenuResponse> call = RetrofitClient
                .getInstance().getApi().getCustAllDetails(Global.customer_id);
        call.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                MenuResponse mr = response.body();
                progressDialog.dismiss();
                name.setText(mr.getFirst_name()+" "+mr.getLast_name());
                add1.setText(mr.getAddress1());
                add2.setText(mr.getAddress2());
                add3.setText(mr.getAddress3());
                city.setText(mr.getCity());
                state.setText(mr.getState());
                pin.setText(mr.getPincode());
                phno.setText(mr.getContact_no());
                email.setText(mr.getEmail_id());
                flgallow = true;
            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(BillingAddActivity.this, "Failed to get customer details !", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void back(View view) {
        finish();
    }
}
