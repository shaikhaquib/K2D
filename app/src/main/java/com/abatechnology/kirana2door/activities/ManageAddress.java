package com.abatechnology.kirana2door.activities;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.abatechnology.kirana2door.R;
import com.abatechnology.kirana2door.ViewDialog;
import com.abatechnology.kirana2door.api.RetrofitClient;
import com.abatechnology.kirana2door.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageAddress extends AppCompatActivity {

    public EditText add1,add2,add3,pincode;
    public Button update;
    String custid;
    RelativeLayout rl;
    ViewDialog progress;
    SharedPreferences myPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_address);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progress=new ViewDialog(this);
        add1 = findViewById(R.id.add1);
        add2 = findViewById(R.id.add2);
        add3 = findViewById(R.id.add3);
        pincode = findViewById(R.id.pincode);
        update = findViewById(R.id.update);
        rl = findViewById(R.id.rl);

        myPrefs = getSharedPreferences("personal_info_of_k2d_cust", MODE_PRIVATE);
        add1.setText(myPrefs.getString("address1", ""));
        add2.setText(myPrefs.getString("address2", ""));
        add3.setText(myPrefs.getString("address3", ""));
        pincode.setText(myPrefs.getString("pincode", ""));
        custid = myPrefs.getString("cust_id", "");
    }

    public void UpdateData(View view) {

        String sadd1 = add1.getText().toString().trim();
        String sadd2 = add2.getText().toString().trim();
        String sadd3 = add3.getText().toString().trim();
        String spincode = pincode.getText().toString().trim();

        if (sadd1.isEmpty()) {
            add1.setError("Address is required");
            add1.requestFocus();
            return;
        }

        if (sadd2.isEmpty()) {
            add2.setError("Address is required");
            add2.requestFocus();
            return;
        }

        if (sadd3.isEmpty()) {
            add3.setError("Address is required");
            add3.requestFocus();
            return;
        }

        if (spincode.isEmpty()) {
            pincode.setError("Pincode is required");
            pincode.requestFocus();
            return;
        }

        if (spincode.length()!=6) {
            pincode.setError("Enter valid pincode");
            pincode.requestFocus();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Kirana2Door");
        builder.setMessage("Are you sure want to update ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progress.show();
                Call<DefaultResponse> call = RetrofitClient
                        .getInstance().getApi().manageAddressOfCust(add1.getText().toString().trim(), add2.getText().toString().trim(), add3.getText().toString().trim(), pincode.getText().toString().trim(), custid.trim());
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        DefaultResponse defaultResponse = response.body();
                        progress.dismiss();
                        if (!defaultResponse.isError()) {
                            myPrefs.edit().putString("address1", add1.getText().toString().trim()).apply();
                            myPrefs.edit().putString("address2", add2.getText().toString().trim()).apply();
                            myPrefs.edit().putString("address3", add3.getText().toString().trim()).apply();
                            myPrefs.edit().putString("pincode", pincode.getText().toString().trim()).apply();
                            Snackbar snackbar = Snackbar.make(rl, defaultResponse.getErrormsg(), Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        } else {
                            Snackbar snackbar = Snackbar.make(rl, defaultResponse.getErrormsg(), Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {
                        progress.dismiss();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        } return true;
    }
}
