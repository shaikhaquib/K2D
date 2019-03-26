package com.abatechnology.kirana2door.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.abatechnology.kirana2door.R;
import com.abatechnology.kirana2door.ViewDialog;
import com.abatechnology.kirana2door.api.RetrofitClient;
import com.abatechnology.kirana2door.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassVerification extends AppCompatActivity {

    private PinEntryEditText otp;
    private Button subotpbtn;
    private String emailid;
    private String contact;
    ViewDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass_verification);
        getSupportActionBar().hide();

        otp = findViewById(R.id.pinView);
        subotpbtn = findViewById(R.id.chkotp);
        progress=new ViewDialog(this);
        emailid = getIntent().getStringExtra("emailid");
        contact = getIntent().getStringExtra("contact");
    }
    public void CheckOTP(View arg) {
        String OTPPASS = otp.getText().toString().trim();

        if (OTPPASS.isEmpty()) {
            otp.setError("OTP required");
            otp.requestFocus();
            return;
        }

        if (OTPPASS.length() < 4) {
            otp.setError("Please enter OTP which we have sent to you !");
            otp.requestFocus();
            return;
        }
        progress.show();
        Call<DefaultResponse> call = RetrofitClient
                .getInstance().getApi().checkForgotPassOTP(OTPPASS);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                progress.dismiss();
                if (!defaultResponse.isError()) {

                    Intent intent = new Intent(ForgotPassVerification.this, SetNewPassword.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("emailid",emailid);
                    intent.putExtra("contact",contact);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ForgotPassVerification.this, defaultResponse.getErrormsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(ForgotPassVerification.this, "Wrong OTP !", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ReSendOTP(View arg){
        progress.show();
        Call<DefaultResponse> call = RetrofitClient
                .getInstance().getApi().resendOTP(emailid.trim());
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                progress.dismiss();
                if (!defaultResponse.isError()) {

                    Toast.makeText(ForgotPassVerification.this, defaultResponse.getErrormsg(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(ForgotPassVerification.this, defaultResponse.getErrormsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(ForgotPassVerification.this, "Failed to process your request !", Toast.LENGTH_LONG).show();
            }
        });
    }
}
