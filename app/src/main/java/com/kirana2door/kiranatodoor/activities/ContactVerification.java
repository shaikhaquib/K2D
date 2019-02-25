package com.kirana2door.kiranatodoor.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.api.RetrofitClient;
import com.kirana2door.kiranatodoor.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactVerification extends AppCompatActivity {

    private PinEntryEditText otp;
    private Button subotpbtn;
    private String emailid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_verification);
        getSupportActionBar().hide();

        otp = findViewById(R.id.pinView);
        subotpbtn = findViewById(R.id.chkotp);

        emailid = getIntent().getStringExtra("emailid");
    }

    public void CheckOTP(View arg) {
        String OTPPASS = otp.getText().toString().trim();

        if (OTPPASS.isEmpty()) {
            otp.setError("OTP required");
            otp.requestFocus();
            return;
        }

        if (OTPPASS.length() < 4) {
            otp.setError("Please enter password which we have sent to you !");
            otp.requestFocus();
            return;
        }

        Call<DefaultResponse> call = RetrofitClient
                .getInstance().getApi().verifyContactUpdate(OTPPASS);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();

                if (!defaultResponse.isError()) {

                    Intent intent = new Intent(ContactVerification.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(ContactVerification.this, defaultResponse.getErrormsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(ContactVerification.this, "Failed to process your request !", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void ReSendOTP(View arg){

        Call<DefaultResponse> call = RetrofitClient
                .getInstance().getApi().resendOTP(emailid.trim(),"1234567890");
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();

                if (!defaultResponse.isError()) {

                    Toast.makeText(ContactVerification.this, defaultResponse.getErrormsg(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(ContactVerification.this, defaultResponse.getErrormsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(ContactVerification.this, "Failed to process your request !", Toast.LENGTH_LONG).show();
            }
        });
    }
}