package com.kirana2door.kiranatodoor.activities;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.ViewDialog;
import com.kirana2door.kiranatodoor.api.RetrofitClient;
import com.kirana2door.kiranatodoor.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalInfo extends AppCompatActivity {

    public EditText fname,lname,email;
    public Button update;
    String custid;
    RelativeLayout rl;
    ViewDialog progress;
    SharedPreferences myPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progress=new ViewDialog(this);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        update = findViewById(R.id.update);
        rl = findViewById(R.id.rl);

        myPrefs = getSharedPreferences("personal_info_of_k2d_cust", MODE_PRIVATE);
        fname.setText(myPrefs.getString("first_name", ""));
        lname.setText(myPrefs.getString("last_name", ""));
        email.setText(myPrefs.getString("email_id", ""));
        custid = myPrefs.getString("cust_id", "");
        email.setEnabled(false);
    }


    public void UpdateData(View view) {
        String sfname = fname.getText().toString().trim();
        String slname = lname.getText().toString().trim();

        if (sfname.isEmpty()) {
            fname.setError("First name is required");
            fname.requestFocus();
            return;
        }

        if (slname.isEmpty()) {
            lname.setError("Last name is required");
            lname.requestFocus();
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
                .getInstance().getApi().updatePersonalInfo(fname.getText().toString().trim(),lname.getText().toString().trim(),custid.trim());
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                progress.dismiss();
                if (!defaultResponse.isError()) {
                    myPrefs.edit().putString("first_name" , fname.getText().toString().trim()).apply();
                    myPrefs.edit().putString("last_name" , lname.getText().toString().trim()).apply();
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
