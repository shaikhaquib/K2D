package com.abatechnology.kirana2door.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.abatechnology.kirana2door.R;

public class OrderSuccess extends AppCompatActivity {

    TextView orddet;
    Button continueshopping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_donestep);
        getSupportActionBar().hide();

        orddet = findViewById(R.id.orderidtime);
        continueshopping = findViewById(R.id.continueshoping);

        orddet.setText("Order ID is "+getIntent().getStringExtra("orderid")+" \nWe will Deliver your order within 2-3 hour \n Thank you for shopping with us");

        continueshopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderSuccess.this,Home.class);
                startActivity(intent);
            }
        });

    }
}
