package com.kirana2door.kiranatodoor.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.api.RetrofitClient;
import com.kirana2door.kiranatodoor.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    private EditText email,phno;
    private Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();
        email = findViewById(R.id.email);
        phno = findViewById(R.id.phno);
    }

    public void ForgetpassCheck(View arg){

        final String semail = email.getText().toString().trim();
        final String sphno = phno.getText().toString().trim();

        if (semail.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if (sphno.isEmpty()) {
            phno.setError("Contact number is required");
            phno.requestFocus();
            return;
        }


        Call<DefaultResponse> call = RetrofitClient
                .getInstance().getApi().forgotPassReq(semail,sphno);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();

                if (!defaultResponse.isError()) {

                    Intent intent = new Intent(ForgotPassword.this, ForgotPassVerification.class);
                    intent.putExtra("emailid",semail);
                    intent.putExtra("contact",sphno);
                    startActivity(intent);

                } else {
                    Toast.makeText(ForgotPassword.this, defaultResponse.getErrormsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(ForgotPassword.this, "Failed to process your request !", Toast.LENGTH_LONG).show();
            }
        });
    }
}
