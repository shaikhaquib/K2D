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
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.abatechnology.kirana2door.activities.Home;
import com.abatechnology.kirana2door.activities.SubOrder;
import com.abatechnology.kirana2door.adapters.OrderHistoryList;
import com.abatechnology.kirana2door.adapters.OrderHistoryListItem;
import com.abatechnology.kirana2door.api.AppController;
import com.abatechnology.kirana2door.api.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import params.com.stepview.StatusView;
import params.com.stepview.StatusViewScroller;

public class Frg_Notification extends Fragment {


    List<OrderHistoryListItem> ordlist = new ArrayList<>();
    ViewDialog progressDialog;
    RecyclerView rvHist;
    LinkedHashMap map;
    RelativeLayout orderview,orderErrorView;
    TextView ordernow;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frg_notification, null);
        progressDialog=new ViewDialog(getActivity());
        map = new LinkedHashMap<>();
        map.put("Received",1);
        map.put("Packed",2);
        map.put("Shipped",3);
        map.put("Delivered",4);
        rvHist = view.findViewById(R.id.rvrHist);
        orderview = view.findViewById(R.id.orderview);
        orderErrorView = view.findViewById(R.id.nohistoru);
        ordernow = view.findViewById(R.id.ordernow);

        ordernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Home.class);
                startActivity(intent);
            }
        });
        rvHist.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHist.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view= LayoutInflater.from(getActivity()).inflate(R.layout.order_hist, viewGroup,false);
                Holder holder=new Holder(view);
                return holder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final Holder myHolder= (Holder) viewHolder;
                final OrderHistoryListItem model = ordlist.get(i);
                Glide.with(getActivity()).load(model.getLogoImg()).into(myHolder.img);
                myHolder.prdname.setText(model.getShopName());
                myHolder.prdqnt.setText(model.getOrderId());
                myHolder.indicator.setTag(i);
                StatusView Status = myHolder.indicator.getStatusView();
                Status.setCurrentCount(Integer.parseInt(String.valueOf(map.get(model.getOrderStatus()))));

                myHolder.itemView.setTag(i);
                myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), SubOrder.class);
                        intent.putExtra("oid",model.getOrderId());
                        intent.putExtra("stname",model.getShopName());
                        intent.putExtra("famt",model.getFinalAmmount());
                        intent.putExtra("ship",model.getShippingCharges());
                        intent.putExtra("totl",model.getTotalAmmount());
                        intent.putExtra("logopic",model.getLogoImg());
                        intent.putExtra("status",String.valueOf(map.get(model.getOrderStatus())));
                        startActivity(intent);
                    }
                });

                /*Glide.with(getActivity()).load(prd_img[i]).into(myHolder.img);
                myHolder.prdname.setText(prd_names[i]);
                myHolder.prdqnt.setText("Total Item Ordered : "+prd_qnt[i]);
                myHolder.indicator.setTag(i);
                StatusView Status = myHolder.indicator.getStatusView();
                Status.setCurrentCount(Integer.parseInt(prd_status[i]));*/



            }

            @Override
            public int getItemCount() {
                return ordlist.size();
            }
            class Holder extends RecyclerView.ViewHolder {
                ImageView img;
                TextView prdname , prdqnt ;
                StatusViewScroller indicator;
                public Holder(@NonNull View itemView) {
                    super(itemView);


                    img = itemView.findViewById(R.id.product_img);
                    prdname = itemView.findViewById(R.id.histname);
                    prdqnt = itemView.findViewById(R.id.histQuntity);
                    indicator = itemView.findViewById(R.id.status);


                }
            } }
        );

        getData();
        return view;
    }

    private void getData() {
        //data variables call
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, RetrofitClient.BASE_URL+"orderhistory", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                Gson gson = new Gson();
                OrderHistoryList res = gson.fromJson(response, OrderHistoryList.class);
                ordlist = res.getOrderHistoryList();
                rvHist.getAdapter().notifyDataSetChanged();

                if (ordlist.size() == 0) {
                    orderview.setVisibility(View.GONE);
                    orderErrorView.setVisibility(View.VISIBLE);
                }else {
                    orderview.setVisibility(View.VISIBLE);
                    orderErrorView.setVisibility(View.GONE);
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
