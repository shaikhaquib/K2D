package com.kirana2door.kiranatodoor.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kirana2door.kiranatodoor.Global;
import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.ViewDialog;
import com.kirana2door.kiranatodoor.adapters.SubOrderHistoryProductListItem;
import com.kirana2door.kiranatodoor.adapters.SubOrderList;
import com.kirana2door.kiranatodoor.api.AppController;
import com.kirana2door.kiranatodoor.api.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import params.com.stepview.StatusView;
import params.com.stepview.StatusViewScroller;

public class SubOrder extends AppCompatActivity {

    ViewDialog progressDialog;
    RecyclerView recyclerView;
    List<SubOrderHistoryProductListItem> suborderprodlist = new ArrayList<>();
    public String oid,stkname,logopic,amt,tamt,shipcst,ostatus;
    public int totalitem=0;
    public ImageView logo_img;
    public TextView stname,ttlitem,famt,totl,ship,orderid;
    StatusViewScroller indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.rvSubhist);
        logo_img = findViewById(R.id.logo_img);
        stname = findViewById(R.id.stname);
        ttlitem = findViewById(R.id.ttlitem);
        totl = findViewById(R.id.totl);
        ship = findViewById(R.id.ship);
        famt = findViewById(R.id.famt);
        orderid = findViewById(R.id.oid);
        indicator = findViewById(R.id.status);
        progressDialog=new ViewDialog(SubOrder.this);
        orderid.setText(getIntent().getStringExtra("oid"));
        stname.setText(getIntent().getStringExtra("stname"));
        famt.setText(getIntent().getStringExtra("famt"));
        ship.setText(getIntent().getStringExtra("ship"));
        totl.setText(getIntent().getStringExtra("totl"));
        Glide.with(SubOrder.this).load(getIntent().getStringExtra("logopic")).into(logo_img);
        ostatus = getIntent().getStringExtra("status");
        indicator.setTag(0);
        StatusView Status = indicator.getStatusView();
        Status.setCurrentCount(Integer.parseInt(ostatus));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view= LayoutInflater.from(SubOrder.this).inflate(R.layout.suborder, viewGroup,false);
                Holder holder=new Holder(view);
                return holder;
                //View view  = LayoutInflater.from(SubOrder.this).inflate(R.layout.suborder,viewGroup,false);
                //return new RecyclerView.ViewHolder(view) {};
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final Holder myHolder= (Holder) viewHolder;
                final SubOrderHistoryProductListItem model = suborderprodlist.get(i);
                Glide.with(SubOrder.this).load(model.getProductThambImage()).into(myHolder.img);
                myHolder.prdname.setText(model.getProductName());
                myHolder.prddesc.setText(model.getProductDiscription1());
                myHolder.prdprice.setText("₹. "+model.getOrderProductPrice());
                int qty = Integer.parseInt(model.getCurrentProductWeight()) * Integer.parseInt(model.getQuantity());
                myHolder.prodqty.setText(Integer.toString(qty)+" "+model.getCurrentProductUnit());

            }

            @Override
            public int getItemCount() {
                return suborderprodlist.size();
            }
            class Holder extends RecyclerView.ViewHolder {
                ImageView img;
                TextView prdname , prddesc, prdprice,prodqty;

                public Holder(@NonNull View itemView) {
                    super(itemView);
                    img = itemView.findViewById(R.id.product_img);
                    prdname = itemView.findViewById(R.id.product_name);
                    prddesc = itemView.findViewById(R.id.product_desc);
                    prodqty = itemView.findViewById(R.id.qty);
                    prdprice = itemView.findViewById(R.id.price);
                }
            }
        });

        getData();
    }

    private void getData() {
        //data variables call
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, RetrofitClient.BASE_URL+"suborderhistory", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                Gson gson = new Gson();
                SubOrderList res = gson.fromJson(response, SubOrderList.class);
                suborderprodlist = res.getSubOrderHistoryProductList();
                recyclerView.getAdapter().notifyDataSetChanged();
                ttlitem.setText("Total Items : "+suborderprodlist.size());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> param = new HashMap<String,String>();
                param.put("cid", Global.customer_id);
                param.put("oid", orderid.getText().toString().trim());
                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        } return true;
    }
}
