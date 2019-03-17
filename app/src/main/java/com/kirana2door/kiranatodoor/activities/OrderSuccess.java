package com.kirana2door.kiranatodoor.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kirana2door.kiranatodoor.R;

public class OrderSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_donestep);
        getSupportActionBar().hide();

    }
}
