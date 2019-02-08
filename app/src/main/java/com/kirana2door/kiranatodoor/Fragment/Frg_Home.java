package com.kirana2door.kiranatodoor.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kirana2door.kiranatodoor.BannerSlider;
import com.kirana2door.kiranatodoor.Global;
import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.ViewPagerAdapter;
import com.kirana2door.kiranatodoor.activities.Product_page;
import com.kirana2door.kiranatodoor.api.Api;
import com.kirana2door.kiranatodoor.api.AppController;
import com.kirana2door.kiranatodoor.api.RetrofitClient;
import com.kirana2door.kiranatodoor.models.BannerItem;
import com.kirana2door.kiranatodoor.models.CatItem;
import com.kirana2door.kiranatodoor.models.CatbanItem;
import com.kirana2door.kiranatodoor.models.MainPageResponse;
import com.kirana2door.kiranatodoor.models.MainResponse;
import com.kirana2door.kiranatodoor.models.ProdbanItem;
import com.kirana2door.kiranatodoor.models.StockistItem;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;

public class Frg_Home extends Fragment {

    ViewPager viewPager,manualSlider;
    LinearLayout sliderDotspanel;
    RecyclerView rvOfferproduct,rvCat;
    private int dotscount;
    private ImageView[] dots;
    View parentLayout;
    List<BannerItem> bannerItems = new ArrayList<>();
    List<CatbanItem> catbanItems = new ArrayList<>();
    List<CatItem> catItems = new ArrayList<>();
    List<ProdbanItem> prodbanItems = new ArrayList<>();
    List<StockistItem> stockistItems = new ArrayList<>();



    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frg_home, null);

        sliderDotspanel =  view.findViewById(R.id.catSliderDots);
        viewPager = view.findViewById(R.id.catviewPager);
        manualSlider = view.findViewById(R.id.manualSlider);
        rvOfferproduct = view.findViewById(R.id.rvOfferproduct);
        rvOfferproduct.setNestedScrollingEnabled(false);
        rvCat = view.findViewById(R.id.rvCat);
        rvCat.setNestedScrollingEnabled(false);
        rvCat.setLayoutManager(new LinearLayoutManager(getActivity()));
        manualSlider.setPageMargin(-20);


        rvOfferproduct.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayout.HORIZONTAL,true));
        rvOfferproduct.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view=LayoutInflater.from(getActivity()).inflate(R.layout.off_product, viewGroup,false);
                Holder holder=new Holder(view);
                return holder;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final Holder myHolder= (Holder) viewHolder;
                final ProdbanItem model = prodbanItems.get(i);
                Glide.with(getActivity()).load(model.getOffpicPath()).into(myHolder.img);
                myHolder.prdname.setText(model.getCreatedBy());


            }

            @Override
            public int getItemCount() {
                return prodbanItems.size();
            }
            class Holder extends RecyclerView.ViewHolder {
                ImageView img;
                TextView prdname;
                public Holder(@NonNull View itemView) {
                    super(itemView);


                    img = itemView.findViewById(R.id.prd_img);
                    prdname = itemView.findViewById(R.id.prd_name);


                }
            } }
        );
        rvCat.setAdapter(new RecyclerView.Adapter() {
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
                final CatItem model = catItems.get(i);
                Glide.with(getActivity()).load(model.getCategoryImg()).into(myHolder.img);
                myHolder.prdname.setText(model.getCategoryName());
                myHolder.count.setText(model.getCategoryId());
                myHolder.itemView.setTag(i);
                myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), Product_page.class).putExtra("CatID",model.getCategoryId()));
                    }
                });


            }

            @Override
            public int getItemCount() {
                return catItems.size();
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

        return view ;
    }

    private void getData() {
        //data variables call


        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, "https://dev.kirana2door.com/androidapi/mainpagealldata", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                MainResponse ReMainResponse = gson.fromJson(response, MainResponse.class);
                bannerItems=ReMainResponse.getBanner();
                catbanItems=ReMainResponse.getCatban();
                catItems=ReMainResponse.getCat();
                prodbanItems=ReMainResponse.getProdban();
                stockistItems=ReMainResponse.getStockist();

                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity(),catbanItems,0);
                manualSlider.setAdapter(viewPagerAdapter);
                slider();
                rvOfferproduct.getAdapter().notifyDataSetChanged();
                rvCat.getAdapter().notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> param = new HashMap<String,String>();
                param.put("custid",Global.customer_id);
                param.put("stcode","0");
                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);


//webapi call
   /*     Call<MainPageResponse> call = RetrofitClient
                .getInstance().getApi().mainPageAllData(Global.customer_id, "0");
        call.enqueue(new Callback<MainPageResponse>() {
            @Override
            public void onResponse(Call<MainPageResponse> call, Response<MainPageResponse> response) {
                MainPageResponse mpgResponse = response.body();

                if (!mpgResponse.isError()) {


                } else {
                    Toast.makeText(getActivity(), mpgResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MainPageResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to process your request !", Toast.LENGTH_LONG).show();
            }
        });*/
    }


    public void slider(){

              BannerSlider viewPagerAdapter = new BannerSlider(getActivity(),bannerItems,1);
              viewPager.setAdapter(viewPagerAdapter);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == dotscount) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }


}
