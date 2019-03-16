package com.kirana2door.kiranatodoor.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
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
import com.kirana2door.kiranatodoor.ViewDialog;
import com.kirana2door.kiranatodoor.activities.Home;
import com.kirana2door.kiranatodoor.activities.LoginActivity;
import com.kirana2door.kiranatodoor.activities.StockistDetail;
import com.kirana2door.kiranatodoor.adapters.ShopListItem;
import com.kirana2door.kiranatodoor.adapters.ShopsInPincode;
import com.kirana2door.kiranatodoor.api.AppController;
import com.kirana2door.kiranatodoor.api.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Frg_Store extends Fragment {

    List<ShopListItem> shoplist = new ArrayList<>();
    ViewDialog progressDialog;
    RecyclerView recyclerView;
    SharedPreferences sp;
    String pincode;
    String SHARED_PREF_NAME = "my_shared_preff";
    Home activity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.frg_stockist, null);
        progressDialog=new ViewDialog(getActivity());
        activity = (Home) getActivity();


        sp = getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        pincode = (sp.getString("pincode", ""));
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.stckistitem,viewGroup,false);
                return new Holder(view1);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final Holder myHolder= (Holder) viewHolder;
                final ShopListItem model = shoplist.get(i);
                Glide.with(getActivity()).load(model.getLogoImg()).into(myHolder.img);
                myHolder.name.setText(model.getShopName());
                myHolder.pin.setText(model.getCityName()+"-"+model.getPincode());
                myHolder.itemView.setTag(i);
                myHolder.dmenu.setTag(i);
                myHolder.card.setTag(i);


                if (activity.getIntent().getStringExtra("id").equals(model.getId())){
                    myHolder.card.setCardBackgroundColor(Color.parseColor("#ccffcc"));
                //    myHolder.img.setColorFilter(ContextCompat.getColor(getActivity(), R.color.gtin), android.graphics.PorterDuff.Mode.SRC_IN);

                }


                myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), Home.class);
                        intent.putExtra("id",model.getId());
                        startActivity(intent);

                    }
                });

                myHolder.dmenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), StockistDetail.class);

                        intent.putExtra("stname",model.getShopName());
                        intent.putExtra("mobile_no",model.getMobileNo());
                        intent.putExtra("landline_no",model.getLandlineNo());
                        intent.putExtra("logo_img",model.getLogoImg());
                        intent.putExtra("status",model.getStatus());
                        intent.putExtra("email",model.getEmail());
                        intent.putExtra("address",model.getAddress()+" "+model.getCityName()+" "+model.getStateName()+" "+model.getCountry());
                        intent.putExtra("timing",model.getOpeningtime()+" "+model.getOampm()+" to "+model.getClosingtime()+" "+model.getCampm());
                        intent.putExtra("ownername",model.getFirstName()+" "+model.getLastName());

                        startActivity(intent);
                    }
                });
                /*Holder holder =(Holder) viewHolder;
                holder.itemView.setTag(i);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), StockistDetail.class));
                    }
                });*/

            }

            @Override
            public int getItemCount() {
                return shoplist.size();
            }

            class Holder extends RecyclerView.ViewHolder {
                ImageView img,dmenu;
                TextView name , pin ;
                CardView card;
                public Holder(@NonNull View itemView) {
                    super(itemView);


                    card = itemView.findViewById(R.id.card);
                    img = itemView.findViewById(R.id.stockislogo);
                    dmenu = itemView.findViewById(R.id.dmenu);
                    name = itemView.findViewById(R.id.stname);
                    pin = itemView.findViewById(R.id.pin);
                }
            }

        });
        getData();
        return view;
    }
    private void getData() {
        //data variables call
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, RetrofitClient.BASE_URL+"stockistlistpincodewise", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                Gson gson = new Gson();
                ShopsInPincode res = gson.fromJson(response, ShopsInPincode.class);
                shoplist = res.getShopList();
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
                param.put("pincode", pincode.trim());
                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
