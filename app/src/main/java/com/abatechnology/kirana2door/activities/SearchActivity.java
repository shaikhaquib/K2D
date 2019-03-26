package com.abatechnology.kirana2door.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.abatechnology.kirana2door.Global;
import com.abatechnology.kirana2door.R;
import com.abatechnology.kirana2door.ViewDialog;
import com.abatechnology.kirana2door.adapters.ProductList;
import com.abatechnology.kirana2door.adapters.ProductListItem;
import com.abatechnology.kirana2door.api.AppController;
import com.abatechnology.kirana2door.api.RetrofitClient;
import com.abatechnology.kirana2door.other.DataVar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class SearchActivity extends AppCompatActivity {

    RequestQueue queue;
    List<DataVar> data=new ArrayList<>();
    ImageView Back,clear;
    EditText query;
    RecyclerView rvSearch;
    Context context;
    private Timer timer;
    boolean login =true ;
    ProgressBar progressBar;
    //   ProductAdapter mAdapter;
    List<ProductListItem> prodlist = new ArrayList<>();
    String s,sp,swt;
    int cal2,cal3;
    int offset = 0;
    ViewDialog progressDialog;
    //private EndlessRecyclerOnScrollListener mScrollListener = null;
    private SwipeRefreshLayout mSwipeRefreshLayout = null;

    private TextWatcher searchTextWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(final Editable arg0) {
            // user typed: start the timer
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // do your actual work here
                    SearchActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            String searchquery=arg0.toString();
                            // mAdapter.getFilter().filter(arg0);
                            getData(searchquery);

                        }
                    });

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    // hide keyboard as well?
                    // InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    // in.hideSoftInputFromWindow(searchText.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }, 900); // 600ms delay before the timer executes the "run" method from TimerTask
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // nothing to do here
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // user is typing: reset already started timer (if existing)

            if(s.toString().trim().length()==0){
                clear.setVisibility(View.GONE);
            } else {
                clear.setVisibility(View.VISIBLE);
            }

            if (timer != null) {
                timer.cancel();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();

        context = SearchActivity.this ;
        progressDialog = new ViewDialog(this);


        rvSearch=findViewById(R.id.rvSearch);
        rvSearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        Back= (ImageView) findViewById(R.id.searchClose);
        clear= (ImageView) findViewById(R.id.srchClear);
        query= (EditText) findViewById(R.id.SearchBox);
        progressBar=  findViewById(R.id.searchProgress);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.setText("");
            }
        });
        query.addTextChangedListener(searchTextWatcher );

        rvSearch.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view= LayoutInflater.from(SearchActivity.this).inflate(R.layout.product_list, viewGroup,false);
                Holder holder=new Holder(view);
                return holder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final Holder myHolder= (Holder) viewHolder;
                final ProductListItem model = prodlist.get(i);
                Glide.with(SearchActivity.this).load(model.getProductThambImage()).into(myHolder.img);
                myHolder.prdname.setText(model.getProductName());
                myHolder.prddesc.setText(model.getProductDiscription1());
                myHolder.prdprice.setText("₹. "+model.getProductPrice());
                myHolder.prdwtunit.setText(model.getProductWeight()+" "+model.getUnits());
                myHolder.prodqty.setText(model.getMinimumQuantity());
                final int calwt=Integer.parseInt(model.getProductWeight());
                final int calprice=Integer.parseInt(model.getProductPrice());
                final int[] wt = {Integer.parseInt(model.getProductWeight())};
                model.setFinalqty("1");
                model.setPpri(model.getProductPrice());

                myHolder.plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                /*int qt = Integer.parseInt(current.minimum_quantity);
                wt[0] = wt[0] + qt;

                 s = Float.toString(wt[0]);
                myHolder.quantity.setText(s);*/


                        if(Integer.parseInt(model.getMinimumQuantity())<99) {
                            model.setMinimumQuantity(Integer.toString(Integer.parseInt(model.getMinimumQuantity()) + 1));

                            cal2 = calprice * Integer.parseInt(model.getMinimumQuantity());
                            cal3 = calwt * Integer.parseInt(model.getMinimumQuantity());
                            sp =String.valueOf(cal2);
                            model.setPpri(sp);
                            swt =String.valueOf(cal3);
                            String mn =String.valueOf(Integer.parseInt(model.getMinimumQuantity()));
                            model.setMinteger(Integer.parseInt(model.getMinimumQuantity()));
                            myHolder.prdprice.setText("₹. "+sp);
                            myHolder.prdwtunit.setText(swt+" "+model.getUnits());
                            myHolder.prodqty.setText(mn);
                            model.setFinalqty(mn);

                        }
                    }
                });

                myHolder.minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                /*int qt = Integer.parseInt(current.minimum_quantity);
                if(wt[0] >0){
                    wt[0] = wt[0] - qt;
                }

                 s = Float.toString(wt[0]);
                myHolder.quantity.setText(s);*/
                        if(model.getMinteger()>1) {
                            model.setMinteger(model.getMinteger() - 1);
                            cal2 = calprice * model.getMinteger();
                            cal3 = calwt * model.getMinteger();
                            sp =String.valueOf(cal2);
                            model.setPpri(sp);
                            swt =String.valueOf(cal3);
                            String mn =String.valueOf(model.getMinteger());
                            model.setMinimumQuantity(Integer.toString(model.getMinteger()));
                            myHolder.prdprice.setText("₹. "+sp);
                            myHolder.prdwtunit.setText(swt+" "+model.getUnits());
                            myHolder.prodqty.setText(mn);
                            model.setFinalqty(mn);
                        }
                        else {
                            myHolder.prodqty.setText("1");
                        }
                    }
                });

                myHolder.addcart.setOnClickListener(new View.OnClickListener() {
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
                        Home.AddToCart innerObject = outerObject.new AddToCart();
                        //MainActivity.new AddToCart().execute(current.product_id,current.product_weight,"qwerty@gmail.com");

                /*if(myHolder.quantity.getText().toString().trim() == "1"){
                    innerObject.execute(current.product_id,"1",email);
                }else{*/
                        innerObject.execute(model.getProductId(),model.getFinalqty(),model.getShopId(),Global.customer_id);
                        //}
                        Snackbar snackbar = Snackbar.make(v, "Product Successfully Added To Cart !", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                });

                myHolder.itemView.setTag(i);
                myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SearchActivity.this, ProductDetail.class);
                        intent.putExtra("product_id",model.getProductId());
                        intent.putExtra("shop_id",model.getShopId());
                        intent.putExtra("qty",model.getFinalqty());
                        startActivity(intent);
                    }
                });
                /*Glide.with(Product_page.this).load(prd_img[i]).into(myHolder.img);
                myHolder.prdname.setText(prd_names[i]);
                myHolder.prddesc.setText(prd_names[i]);
                myHolder.prdwtunit.setText(prd_qnt[i]);*/
                //    myHolder.prdprice.setText(prd_price[i]);


            }

            @Override
            public int getItemCount() {
                return prodlist.size();
            }
            class Holder extends RecyclerView.ViewHolder {
                ImageView img,plus,minus;
                TextView prdname , prddesc, prdwtunit , prdprice,prodqty;
                CardView addcart;
                public Holder(@NonNull View itemView) {
                    super(itemView);


                    img = itemView.findViewById(R.id.product_img);
                    plus = itemView.findViewById(R.id.product_img);
                    minus = itemView.findViewById(R.id.product_img);
                    prdname = itemView.findViewById(R.id.product_name);
                    prddesc = itemView.findViewById(R.id.product_desc);
                    prdwtunit = itemView.findViewById(R.id.product_qnt);
                    prodqty = itemView.findViewById(R.id.qty);
                    prdprice = itemView.findViewById(R.id.price);
                    plus = itemView.findViewById(R.id.plus);
                    minus = itemView.findViewById(R.id.minus);
                    addcart = itemView.findViewById(R.id.addcart);
                    //  prdprice = itemView.findViewById(R.id.product_price);


                }
            } }
        );


    }

    private void getData(final String searchquery) {
        //data variables call
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, RetrofitClient.BASE_URL+"searchproductofshop", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                Gson gson = new Gson();
                ProductList res = gson.fromJson(response, ProductList.class);
                prodlist = res.getProductList();
                rvSearch.getAdapter().notifyDataSetChanged();

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
                param.put("querystr", searchquery);
                param.put("shopid", Global.selshopid);
                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

}
