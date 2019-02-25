package com.kirana2door.kiranatodoor.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.api.RetrofitClient;
import com.kirana2door.kiranatodoor.models.DefaultResponse;
import com.kirana2door.kiranatodoor.models.LoginResponse;
import com.kirana2door.kiranatodoor.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerRegistration extends AppCompatActivity {

    private EditText fname,lname,email,add1,add2,add3,pin,phno,pass,confpass;
    private Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        getSupportActionBar().hide();

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        add1 = findViewById(R.id.add1);
        add2 = findViewById(R.id.add2);
        add3 = findViewById(R.id.add3);
        pin = findViewById(R.id.pincode);
        phno = findViewById(R.id.phno);
        pass = findViewById(R.id.pass);
        confpass = findViewById(R.id.confpass);
        registerButton = findViewById(R.id.regbtn);
    }

    public void DoRegister(View arg){
        String sfname = fname.getText().toString().trim();
        String slname = lname.getText().toString().trim();
        final String semail = email.getText().toString().trim();
        String sadd1 = add1.getText().toString().trim();
        String sadd2 = add2.getText().toString().trim();
        String sadd3 = add3.getText().toString().trim();
        String spincode = pin.getText().toString().trim();
        final String sphno = phno.getText().toString().trim();
        String spass = pass.getText().toString().trim();
        String sconfpass = confpass.getText().toString().trim();

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

        if (semail.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if (sadd1.isEmpty()) {
            add1.setError("Address1 is required");
            add1.requestFocus();
            return;
        }

        if (sadd2.isEmpty()) {
            add2.setError("Add2 is required");
            add2.requestFocus();
            return;
        }

        if (sadd3.isEmpty()) {
            add3.setError("Add3 is required");
            add3.requestFocus();
            return;
        }

        if (spincode.isEmpty()) {
            pin.setError("Pincode is required");
            pin.requestFocus();
            return;
        }

        if (sphno.isEmpty()) {
            phno.setError("Contact number is required");
            phno.requestFocus();
            return;
        }

        if (spass.isEmpty()) {
            pass.setError("Password is required");
            pass.requestFocus();
            return;
        }

        if (sconfpass.isEmpty()) {
            confpass.setError("Please confirm password");
            confpass.requestFocus();
            return;
        }




        Call<DefaultResponse> call = RetrofitClient
                .getInstance().getApi().registerUser(sfname,slname,sadd1,sadd2,sadd3,semail,spincode,sphno,sconfpass);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();

                if (!defaultResponse.isError()) {

                    Intent intent = new Intent(CustomerRegistration.this, OTPVerification.class);
                    intent.putExtra("emailid",semail);
                    intent.putExtra("contact",sphno);
                    startActivity(intent);


                } else {
                    Toast.makeText(CustomerRegistration.this, defaultResponse.getErrormsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(CustomerRegistration.this, "Failed to process your request !", Toast.LENGTH_LONG).show();
            }
        });
    }
}
