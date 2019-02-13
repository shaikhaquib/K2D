package com.kirana2door.kiranatodoor.Fragment;

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
import com.kirana2door.kiranatodoor.activities.CartProductList;
import com.kirana2door.kiranatodoor.adapters.StockistListInCart;
import com.kirana2door.kiranatodoor.adapters.StockistListInCartItem;
import com.kirana2door.kiranatodoor.api.AppController;
import com.kirana2door.kiranatodoor.api.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Frg_Cart extends Fragment {

    RecyclerView rv_cart;
    List<StockistListInCartItem> slci = new ArrayList<>();
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

        rv_cart = view.findViewById(R.id.rv_cart);
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

        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, RetrofitClient.BASE_URL+"getcartstklist", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                StockistListInCart res = gson.fromJson(response, StockistListInCart.class);
                slci = res.getStockistListInCart();
                rv_cart.getAdapter().notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
