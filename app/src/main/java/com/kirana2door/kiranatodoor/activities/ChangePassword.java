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

import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.ViewDialog;
import com.kirana2door.kiranatodoor.api.RetrofitClient;
import com.kirana2door.kiranatodoor.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {
    public EditText oldpass,newpass,confpass;
    public Button update;
    String custid;
    RelativeLayout rl;
    ViewDialog progress;
    SharedPreferences myPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        //getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progress=new ViewDialog(this);
        oldpass = findViewById(R.id.oldpass);
        newpass = findViewById(R.id.newpass);
        confpass = findViewById(R.id.confnewpass);
        update = findViewById(R.id.update);
        rl = findViewById(R.id.rl);

        myPrefs = getSharedPreferences("personal_info_of_k2d_cust", MODE_PRIVATE);
        custid = myPrefs.getString("cust_id", "");
    }

    public void UpdateData(View view) {

        String opass = oldpass.getText().toString().trim();
        String npass = newpass.getText().toString().trim();
        String nconfpass = confpass.getText().toString().trim();

        if (opass.isEmpty()) {
            oldpass.setError("Old password is required");
            oldpass.requestFocus();
            return;
        }

        if (npass.isEmpty()) {
            newpass.setError("New password is required");
            newpass.requestFocus();
            return;
        }

        if (npass.length() < 8) {
            newpass.setError("Password is too short");
            newpass.requestFocus();
            return;
        }

        if (nconfpass.isEmpty()) {
            confpass.setError("Password is required");
            confpass.requestFocus();
            return;
        }

        if (nconfpass.length() < 8) {
            confpass.setError("Password is too short");
            confpass.requestFocus();
            return;
        }

        if (!nconfpass.equalsIgnoreCase(npass)) {
            confpass.setError("Password not matched");
            confpass.requestFocus();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Kirana2Door");
        builder.setMessage("Are you sure want to update ? \n Note : If password changed successfully then you will redirected to login screen and you have to login back with your new password. ");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

        progress.show();
        Call<DefaultResponse> call = RetrofitClient
                .getInstance().getApi().changePass(oldpass.getText().toString().trim(),confpass.getText().toString().trim(),custid.trim());
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                progress.dismiss();
                if (!defaultResponse.isError()) {
                    getSharedPreferences("personal_info_of_k2d_cust", MODE_PRIVATE).edit().clear().apply();
                    getSharedPreferences("my_shared_preff", MODE_PRIVATE).edit().clear().apply();
                    Intent intent = new Intent(ChangePassword.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
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
