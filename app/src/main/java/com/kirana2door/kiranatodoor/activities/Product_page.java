package com.kirana2door.kiranatodoor.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kirana2door.kiranatodoor.R;

public class Product_page extends AppCompatActivity {

    String[] prd_img = {
            "https://www.bigbasket.com/media/uploads/p/s/40112512_2-bb-royal-medjool-dates.jpg",
            "https://www.bigbasket.com/media/uploads/p/s/40072455_5-bb-royal-organic-kabuli-chanachanna.jpg",
            "https://www.bigbasket.com/media/uploads/p/s/40112523_2-bb-royal-dried-fruit-blueberries.jpg",
            "https://www.bigbasket.com/media/uploads/p/s/40072463_6-bb-royal-organic-besan-flour.jpg",
            "https://www.bigbasket.com/media/uploads/p/s/40072465_6-bb-royal-organic-brown-chanachanna-brown.jpg",
            "https://www.bigbasket.com/media/uploads/p/s/40026269_1-milkfood-pure-ghee.jpg"
    },
            prd_names = {
                    "Royal Dates",
                    "Kabuli chana",
                    "Blue Berries",
                    "Oraganic Besan",
                    "Royal Organic",
                    "Pure Ghee"
            },prd_price = {"₹ 200","₹ 35","₹ 106","₹ 56","₹ 1005","₹ 100"},
            prd_qnt= {"1 kg","250 gm","1 kg","1 kg","1 kg","1 kg"};


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.rv_product);
       ImageView prdback = findViewById(R.id.prd_back);
       prdback.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view= LayoutInflater.from(Product_page.this).inflate(R.layout.product_list, viewGroup,false);
                Holder holder=new Holder(view);
                return holder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final Holder myHolder= (Holder) viewHolder;
                Glide.with(Product_page.this).load(prd_img[i]).into(myHolder.img);
                myHolder.prdname.setText(prd_names[i]);
                myHolder.prddesc.setText(prd_names[i]);
                myHolder.prdqnt.setText(prd_qnt[i]);
            //    myHolder.prdprice.setText(prd_price[i]);


                myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),ProductDetail.class).putExtra("pid","id"));
                    }
                });


            }

            @Override
            public int getItemCount() {
                return prd_img.length;
            }
            class Holder extends RecyclerView.ViewHolder {
                ImageView img;
                TextView prdname , prddesc, prdqnt , prdprice;
                public Holder(@NonNull View itemView) {
                    super(itemView);


                    img = itemView.findViewById(R.id.product_img);
                    prdname = itemView.findViewById(R.id.product_name);
                    prddesc = itemView.findViewById(R.id.product_desc);
                    prdqnt = itemView.findViewById(R.id.product_qnt);
                  //  prdprice = itemView.findViewById(R.id.product_price);


                }
            } }
        );

    }
}
