package com.abatechnology.kirana2door.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.abatechnology.kirana2door.R;

public class StockistDetail extends AppCompatActivity {

    public ImageView shoplogo;
    public TextView stname, ownername, timing, compadd;
    public EditText mobono, tele, email;
    public TextView mcall, tcall, sendemail;
    public String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sockist_detail);
        getSupportActionBar().hide();
        shoplogo = findViewById(R.id.stockislogo);
        email = findViewById(R.id.email);
        /*tele = findViewById(R.id.telephone);
        mobono = findViewById(R.id.mobile);*/
        stname = findViewById(R.id.shopname);
        ownername = findViewById(R.id.ownername);
        timing = findViewById(R.id.timing);
        compadd = findViewById(R.id.comadd);
        /*mcall = findViewById(R.id.mcall);
        tcall = findViewById(R.id.tcall);*/
        sendemail = findViewById(R.id.sendmail);

        Glide.with(StockistDetail.this).load(getIntent().getStringExtra("logo_img")).into(shoplogo);
        stname.setText(getIntent().getStringExtra("stname"));
        email.setText(getIntent().getStringExtra("email"));
        /*tele.setText(getIntent().getStringExtra("landline_no"));
        mobono.setText(getIntent().getStringExtra("mobile_no"));*/
        ownername.setText(getIntent().getStringExtra("ownername"));
        compadd.setText(getIntent().getStringExtra("address"));
        ownername.setText(getIntent().getStringExtra("ownername"));
        status = getIntent().getStringExtra("status");

        if (status.equalsIgnoreCase("active")) {
            timing.setText(getIntent().getStringExtra("timing"));
        } else {
            timing.setText(getIntent().getStringExtra("status"));
        }

        /*mcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + mobono.getText().toString().trim()));
                if (ActivityCompat.checkSelfPermission(StockistDetail.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });

        tcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + tele.getText().toString().trim()));
                if (ActivityCompat.checkSelfPermission(StockistDetail.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });*/

        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
    }

    private void sendMail() {
        /*Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, email.getText().toString().trim());
        intent.putExtra(Intent.EXTRA_SUBJECT, "Write Subject of mail here");
        intent.putExtra(Intent.EXTRA_TEXT, "Respected "+ownername+" Sir,");

        startActivity(Intent.createChooser(intent, "Choose an email client..."));*/
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        String[] recipients={"info@kiran2door.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(intent, "Send mail via..."));
    }
}
