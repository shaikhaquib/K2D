package com.abatechnology.kirana2door.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abatechnology.kirana2door.R;
import com.abatechnology.kirana2door.ViewDialog;
import com.abatechnology.kirana2door.api.RetrofitClient;
import com.abatechnology.kirana2door.models.DefaultResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    private EditText email,phno;
    private Button nextButton;
    ViewDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();
        progress=new ViewDialog(this);
        email = findViewById(R.id.email);
        phno = findViewById(R.id.phno);
    }

    public void ForgetpassCheck(View arg){

        final String semail = email.getText().toString().trim();
        final String sphno = phno.getText().toString().trim();

        Pattern pattern2 = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher2 = pattern2.matcher(semail);

        if (semail.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if (!matcher2.matches()) {
            email.setError("Please enter valid email");
            email.requestFocus();
            return;
        }
        if (sphno.isEmpty()) {
            phno.setError("Contact number is required");
            phno.requestFocus();
            return;
        }

        if (sphno.length()!=10) {
            phno.setError("Enter valid contact number");
            phno.requestFocus();
            return;
        }

        progress.show();
        Call<DefaultResponse> call = RetrofitClient
                .getInstance().getApi().forgotPassReq(semail,sphno);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                progress.dismiss();
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
                progress.dismiss();
                Toast.makeText(ForgotPassword.this, "Failed to process your request !", Toast.LENGTH_LONG).show();
            }
        });
    }
}
