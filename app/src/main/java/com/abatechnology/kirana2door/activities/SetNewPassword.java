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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetNewPassword extends AppCompatActivity {

    private EditText newpass,confpass;
    private Button nextButton;
    private String email,phno;
    ViewDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
        getSupportActionBar().hide();
        progress=new ViewDialog(this);
        newpass = findViewById(R.id.npass);
        confpass = findViewById(R.id.cpass);
        email = getIntent().getStringExtra("emailid");
        phno = getIntent().getStringExtra("contact");
    }

    public void UpdateNwPass(View arg){

        final String snewpass = newpass.getText().toString().trim();
        final String sconfpass = confpass.getText().toString().trim();

        if (snewpass.isEmpty()) {
            newpass.setError("Password should not be empty !");
            newpass.requestFocus();
            return;
        }

        if (snewpass.length() < 8) {
            newpass.setError("Password length must 8 characters long");
            newpass.requestFocus();
            return;
        }

        if (sconfpass.isEmpty()) {
            confpass.setError("Password should not be empty !");
            confpass.requestFocus();
            return;
        }

        if (!snewpass.equalsIgnoreCase(sconfpass)) {
            confpass.setError("Password not matched !");
            confpass.requestFocus();
            return;
        }

        progress.show();
        Call<DefaultResponse> call = RetrofitClient
                .getInstance().getApi().forgotChangePass(sconfpass,email.trim(),phno.trim());
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                progress.dismiss();
                if (!defaultResponse.isError()) {

                    Intent intent = new Intent(SetNewPassword.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(SetNewPassword.this, defaultResponse.getErrormsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(SetNewPassword.this, "Failed to process your request !", Toast.LENGTH_LONG).show();
            }
        });
    }
}
