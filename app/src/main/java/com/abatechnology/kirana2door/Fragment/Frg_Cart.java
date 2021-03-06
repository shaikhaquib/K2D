package com.abatechnology.kirana2door.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.abatechnology.kirana2door.Global;
import com.abatechnology.kirana2door.R;
import com.abatechnology.kirana2door.ViewDialog;
import com.abatechnology.kirana2door.activities.CartProductList;
import com.abatechnology.kirana2door.activities.Home;
import com.abatechnology.kirana2door.adapters.StockistListInCart;
import com.abatechnology.kirana2door.adapters.StockistListInCartItem;
import com.abatechnology.kirana2door.api.AppController;
import com.abatechnology.kirana2door.api.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Frg_Cart extends Fragment {

    RecyclerView rv_cart;
    List<StockistListInCartItem> slci = new ArrayList<>();
    LinearLayout CartView , ErrorView;
    Button additem,addmore;
    ViewDialog progressDialog;
    /*String[] prd_img = {
        "https://www.bigbasket.com/media/uploads/p/s/40112512_2-bb-royal-medjool-dates.jpg",
        "https://www.bigbasket.com/media/uploads/p/s/40072455_5-bb-royal-organic-kabuli-chanachanna.jpg",
        "https://www.bigbasket.com/media/uploads/p/s/40112523_2-bb-royal-dried-fruit-blueberries.jpg"
        },
        prd_names = {
                "STOCK-LIST NAME 1",
                "STOCK-LIST NAME 2",
                "STOCK-LIST NAME 3"
        },prd_Count = {"9","15","6"};*/


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frg_cart, null);
        progressDialog=new ViewDialog(getActivity());
        rv_cart = view.findViewById(R.id.rv_cart);
        CartView = view.findViewById(R.id.cartView);
        ErrorView = view.findViewById(R.id.cartError);
        additem = view.findViewById(R.id.additem);
        addmore = view.findViewById(R.id.addmore);

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Home.class);
                startActivity(intent);
            }
        });
        addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Home.class);
                startActivity(intent);
            }
        });
        rv_cart.setNestedScrollingEnabled(false);
        rv_cart.setLayoutManager(new LinearLayoutManager(getActivity()));

        rv_cart.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view=LayoutInflater.from(getActivity()).inflate(R.layout.layout_category, viewGroup,false);
                Holder holder=new Holder(view);
                return holder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final Holder myHolder= (Holder) viewHolder;
                final StockistListInCartItem model = slci.get(i);
                Glide.with(getActivity()).load(model.getLogoImg()).into(myHolder.img);
                myHolder.prdname.setText(model.getShopName());
                myHolder.count.setText("Item In Cart : "+model.getItemCnt());
                myHolder.itemView.setTag(i);
                myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), CartProductList.class).putExtra("shopid",model.getShopId()));
                    }
                });

            }

            @Override
            public int getItemCount() {
                return slci.size();
            }
            class Holder extends RecyclerView.ViewHolder {
                ImageView img;
                TextView prdname,count;
                public Holder(@NonNull View itemView) {
                    super(itemView);


                    img = itemView.findViewById(R.id.cat_img);
                    prdname = itemView.findViewById(R.id.cat_name);
                    count = itemView.findViewById(R.id.cat_count);


                }
            } }
        );
        getData();
        return view;
    }

    private void getData() {
        //data variables call
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, RetrofitClient.BASE_URL+"getcartstklist", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                Gson gson = new Gson();
                StockistListInCart res = gson.fromJson(response, StockistListInCart.class);
                slci = res.getStockistListInCart();
                rv_cart.getAdapter().notifyDataSetChanged();

                if (slci.size() == 0) {
                    CartView.setVisibility(View.GONE);
                    ErrorView.setVisibility(View.VISIBLE);
                }else {
                    CartView.setVisibility(View.VISIBLE);
                    ErrorView.setVisibility(View.GONE);
                }

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
                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
