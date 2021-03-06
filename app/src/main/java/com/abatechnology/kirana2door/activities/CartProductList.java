package com.abatechnology.kirana2door.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.abatechnology.kirana2door.Global;
import com.abatechnology.kirana2door.R;
import com.abatechnology.kirana2door.ViewDialog;
import com.abatechnology.kirana2door.adapters.ProductListInCart;
import com.abatechnology.kirana2door.adapters.ProductListInCartItem;
import com.abatechnology.kirana2door.api.AppController;
import com.abatechnology.kirana2door.api.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartProductList extends AppCompatActivity {

    ViewDialog progressDialog;
    List<ProductListInCartItem> prodlist = new ArrayList<>();
    RecyclerView recyclerView;
    int wt,price,tprice=0;
    TextView totalamt;
    public Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_product_list);

        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.rv_cartproduct);
        progressDialog=new ViewDialog(CartProductList.this);
        ImageView prdback = findViewById(R.id.crtprd_back);
        totalamt = findViewById(R.id.totalamt);
        checkout = findViewById(R.id.checkout);
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
                View view= LayoutInflater.from(CartProductList.this).inflate(R.layout.product, viewGroup,false);
                Holder holder=new Holder(view);
                return holder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final Holder myHolder= (Holder) viewHolder;
                final ProductListInCartItem model = prodlist.get(i);
                Glide.with(CartProductList.this).load(model.getProductThambImage()).into(myHolder.img);
                myHolder.prdname.setText(model.getProductName());
                myHolder.prddesc.setText(model.getProductDiscription1());
                price = Integer.parseInt(model.getProductPrice()) * Integer.parseInt(model.getProductQty());
                myHolder.prdprice.setText("₹. "+price);
                //tprice = tprice + price;
                //totalamt.setText("₹."+Integer.toString(tprice));

                wt = Integer.parseInt(model.getProductWeight()) * Integer.parseInt(model.getProductQty());
                myHolder.prdwtut.setText(wt+" "+model.getUnits());
                myHolder.prdqnt.setText("Qty - "+model.getProductQty());

                myHolder.removefromcart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        /*if (!login){  AlertDialog.Builder builder;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                builder = new AlertDialog.Builder(context);
                            } else {
                                builder = new AlertDialog.Builder(context);
                            }
                            builder.setTitle("Sorry ! please login first")
                                    .setMessage("This feature not available for guest user")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                            //SessionManager
                                            //context.startActivity(new Intent(context,LoginActivity.class));
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            //  startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                        }
                                    })
                                    .show();}else {*/

                        Home outerObject = new Home();
                        Home.RemoveFromCart innerObject = outerObject.new RemoveFromCart();
                        //MainActivity.new AddToCart().execute(current.product_id,current.product_weight,"qwerty@gmail.com");

                /*if(myHolder.quantity.getText().toString().trim() == "1"){
                    innerObject.execute(current.product_id,"1",email);
                }else{*/
                        innerObject.execute(model.getCartId());
                        //}
                        Snackbar snackbar = Snackbar.make(v, "Product Successfully Removed From Cart !", Snackbar.LENGTH_LONG);
                        snackbar.show();

                        getData();
                    }
                });
                /*Glide.with(CartProductList.this).load(prd_img[i]).into(myHolder.img);
                myHolder.prdname.setText(prd_names[i]);
                myHolder.prddesc.setText(prd_names[i]);
                myHolder.prdqnt.setText(prd_qnt[i]);
                myHolder.prdprice.setText(prd_price[i]);*/

            }

            @Override
            public int getItemCount() {
                return prodlist.size();
            }
            class Holder extends RecyclerView.ViewHolder {
                ImageView img;
                TextView prdname , prddesc, prdqnt , prdprice,prdwtut;
                CardView removefromcart;
                public Holder(@NonNull View itemView) {
                    super(itemView);

                    img = itemView.findViewById(R.id.product_img);
                    prdname = itemView.findViewById(R.id.product_name);
                    prddesc = itemView.findViewById(R.id.product_desc);
                    prdqnt = itemView.findViewById(R.id.qty);
                    prdwtut = itemView.findViewById(R.id.product_qnt);
                    prdprice = itemView.findViewById(R.id.product_price);
                    removefromcart = itemView.findViewById(R.id.removefromcart);

                }
            } }
        );
        getData();

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tprice>0) {
                    Intent intent = new Intent(CartProductList.this, BillingAddActivity.class);
                    intent.putExtra("shopid", getIntent().getStringExtra("shopid"));
                    intent.putExtra("totalp", Integer.toString(tprice));
                    intent.putExtra("totalitems", prodlist.size());
                    startActivity(intent);
                }else{
                    Toast.makeText(CartProductList.this,"No product in list to buy ?",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getData() {
        //data variables call
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, RetrofitClient.BASE_URL+"getcartitemofstk", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tprice=0;

                int price1=0;
                progressDialog.dismiss();
                Gson gson = new Gson();
                ProductListInCart res = gson.fromJson(response, ProductListInCart.class);
                prodlist = res.getProductListInCart();
                for(int i=0;i<prodlist.size();i++) {
                    ProductListInCartItem modelx = prodlist.get(i);
                    price1 = Integer.parseInt(modelx.getProductPrice()) * Integer.parseInt(modelx.getProductQty());
                    tprice = tprice + price1;
                }
                totalamt.setText("₹."+Integer.toString(tprice));
                recyclerView.getAdapter().notifyDataSetChanged();

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
                param.put("custid", Global.customer_id);
                param.put("shopid", getIntent().getStringExtra("shopid"));
                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
