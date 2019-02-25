package com.kirana2door.kiranatodoor.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.ViewDialog;
import com.kirana2door.kiranatodoor.api.RetrofitClient;
import com.kirana2door.kiranatodoor.models.LoginResponse;
import com.kirana2door.kiranatodoor.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button loginButton;
    ViewDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progress = new ViewDialog(this);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        loginButton= findViewById(R.id.login);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void DoLogin(View arg){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 8) {
            editTextPassword.setError("Password should be atleast 8 character long");
            editTextPassword.requestFocus();
            return;
        }
        progress.show();
        Call<LoginResponse> call = RetrofitClient
                .getInstance().getApi().userLogin(email, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                progress.dismiss();

                if (!loginResponse.isError()) {
                    // Log.d("message",loginResponse.getMessage());
                    if(loginResponse.getMessage().equalsIgnoreCase("SUCCESS")) {
                        SharedPrefManager.getInstance(LoginActivity.this)
                                .saveUser(loginResponse.getUser());

                        Intent intent = new Intent(LoginActivity.this, Home.class);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else if(loginResponse.getMessage().equalsIgnoreCase("UPDATED")){
                        Intent intent = new Intent(LoginActivity.this, ContactVerification.class);
                        intent.putExtra("emailid",loginResponse.getUser().getEmail());
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(LoginActivity.this, OTPVerification.class);
                        intent.putExtra("emailid",loginResponse.getUser().getEmail());
                        startActivity(intent);
                    }

                } else {
                    Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(LoginActivity.this, "Failed to process your request !", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void newUser(View view) {
        startActivity(new Intent(getApplicationContext(),CustomerRegistration.class));
    }

    public void forgotPassword(View view) {
        startActivity(new Intent(getApplicationContext(),ForgotPassword.class));
    }

    public void onBackPressed(){

            finish();

    }
}
