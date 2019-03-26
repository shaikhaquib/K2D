package com.abatechnology.kirana2door.activities;

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

import com.abatechnology.kirana2door.Global;
import com.abatechnology.kirana2door.R;
import com.abatechnology.kirana2door.ViewDialog;
import com.abatechnology.kirana2door.api.RetrofitClient;
import com.abatechnology.kirana2door.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  UpdateContact extends AppCompatActivity {

    public EditText contact;
    public Button update;
    String custid;
    RelativeLayout rl;
    ViewDialog progress;
    SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progress=new ViewDialog(this);
        contact = findViewById(R.id.contact);
        update = findViewById(R.id.update);
        rl = findViewById(R.id.rl);
        myPrefs = getSharedPreferences("personal_info_of_k2d_cust", MODE_PRIVATE);
        contact.setText(myPrefs.getString("contact_no", ""));
        custid = myPrefs.getString("cust_id", "");
    }

    public void UpdateData(View view) {
        final String sphno = contact.getText().toString().trim();

        if (sphno.isEmpty()) {
            contact.setError("Contact number is required");
            contact.requestFocus();
            return;
        }

        if(sphno.length() != 10){
            contact.setError("Contact number is not valid");
            contact.requestFocus();
            return;
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure want to change contact no. ?");
        builder.setMessage("Note :- If your contact number changed successfully then your redirected to Login page. \n Then do login. after that OTP confirmation screen will appear. \n If verification successfully done then you again redirected to login screen. \n Do Re-login and done.");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progress.show();
                Call<DefaultResponse> call = RetrofitClient
                        .getInstance().getApi().contactUpdate(contact.getText().toString().trim(),custid.trim(), Global.email);
                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                        DefaultResponse defaultResponse = response.body();
                        progress.dismiss();
                        assert defaultResponse != null;
                        if (!defaultResponse.isError()) {
                            //SharedPreferences.Editor.clear().apply();
                            getSharedPreferences("personal_info_of_k2d_cust", MODE_PRIVATE).edit().clear().apply();
                            getSharedPreferences("my_shared_preff", MODE_PRIVATE).edit().clear().apply();
                            Intent intent = new Intent(UpdateContact.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
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
