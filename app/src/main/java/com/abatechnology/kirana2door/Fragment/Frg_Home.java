package com.abatechnology.kirana2door.Fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.abatechnology.kirana2door.BannerSlider;
import com.abatechnology.kirana2door.Global;
import com.abatechnology.kirana2door.R;
import com.abatechnology.kirana2door.ViewDialog;
import com.abatechnology.kirana2door.ViewPagerAdapter;
import com.abatechnology.kirana2door.activities.Home;
import com.abatechnology.kirana2door.activities.ProductDetail;
import com.abatechnology.kirana2door.activities.Product_page;
import com.abatechnology.kirana2door.activities.SearchActivity;
import com.abatechnology.kirana2door.api.AppController;
import com.abatechnology.kirana2door.api.RetrofitClient;
import com.abatechnology.kirana2door.models.BannerItem;
import com.abatechnology.kirana2door.models.CatItem;
import com.abatechnology.kirana2door.models.CatbanItem;
import com.abatechnology.kirana2door.models.MainResponse;
import com.abatechnology.kirana2door.models.ProdbanItem;
import com.abatechnology.kirana2door.models.StockistItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Frg_Home extends Fragment {
    int currentIndex=0;
    ViewPager viewPager,manualSlider;
    LinearLayout sliderDotspanel;
    RecyclerView rvOfferproduct,rvCat,rvManualSlider;
    private int dotscount;
    RequestQueue queue;
    String currentversion;
    private ImageView[] dots;
    View parentLayout;
    List<BannerItem> bannerItems = new ArrayList<>();
    List<CatbanItem> catbanItems = new ArrayList<>();
    List<CatItem> catItems = new ArrayList<>();
    List<ProdbanItem> prodbanItems = new ArrayList<>();
    List<StockistItem> stockistItems = new ArrayList<>();
    ViewDialog progressDialoge;
    Home activity;
    TextView stockistname,stockisttime;
    ImageView searchimg;


    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frg_home, null);
        activity = (Home)getActivity();
        queue= Volley.newRequestQueue(activity);
        update();
        progressDialoge=new ViewDialog(getActivity());
        sliderDotspanel =  view.findViewById(R.id.catSliderDots);
        viewPager = view.findViewById(R.id.catviewPager);
        manualSlider = view.findViewById(R.id.manualSlider);
        rvOfferproduct = view.findViewById(R.id.rvOfferproduct);
        stockistname = view.findViewById(R.id.stockistname);
        stockisttime = view.findViewById(R.id.shoptiming);
        searchimg = view.findViewById(R.id.search);
        searchimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
        rvOfferproduct.setNestedScrollingEnabled(false);
        rvCat = view.findViewById(R.id.rvCat);
        rvManualSlider = view.findViewById(R.id.rvManualSlider);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvManualSlider);
        rvManualSlider.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rvManualSlider.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.manual_slide,viewGroup,false);
                return new Holder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Holder holder = (Holder)viewHolder;
                final CatbanItem model = catbanItems.get(i);
                Glide.with(getActivity()).load(model.getOffpicPath()).into(holder.Slide);
                holder.itemView.setTag(i);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), Product_page.class).putExtra("CatID",model.getOffcategoryid()));
                    }
                });
            }

            @Override
            public int getItemCount() {
                return catbanItems.size();
            }
            class Holder extends RecyclerView.ViewHolder {
                ImageView Slide;
                public Holder(@NonNull View itemView) {
                    super(itemView);
                    Slide = itemView.findViewById(R.id.imageView);
                }
            }
        });

        rvCat.setNestedScrollingEnabled(false);
        rvCat.setLayoutManager(new LinearLayoutManager(getActivity()));
        manualSlider.setPageMargin(-20);


        rvOfferproduct.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayout.HORIZONTAL,false));
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
                myHolder.prdname.setText(model.getProduct_name());
                myHolder.itemView.setTag(i);
                myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ProductDetail.class);
                        intent.putExtra("product_id",model.getOffproductid());
                        intent.putExtra("shop_id",model.getShopId());
                        intent.putExtra("qty","1");
                        startActivity(intent);
                        //startActivity(new Intent(getActivity(), Product_page.class).putExtra("CatID",model.getOffcategoryid()));
                    }
                });

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
                myHolder.count.setText(model.getCnt());
                myHolder.itemView.setTag(i);
                myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),Product_page.class);
                        intent.putExtra("CatID",model.getCategoryId());
                        intent.putExtra("itmcnt",model.getCnt());
                        startActivity(intent);
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

        progressDialoge.show();
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, RetrofitClient.BASE_URL+"mainpagealldata", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialoge.dismiss();
                Gson gson = new Gson();
                MainResponse ReMainResponse = gson.fromJson(response, MainResponse.class);
                bannerItems=ReMainResponse.getBanner();
                catbanItems=ReMainResponse.getCatban();
                catItems=ReMainResponse.getCat();
                prodbanItems=ReMainResponse.getProdban();
                stockistItems=ReMainResponse.getStockist();
                Global.selshopid = stockistItems.get(0).getId();
                stockistname.setText(stockistItems.get(0).getShopName());
                if(!stockistItems.get(0).getStatus().equalsIgnoreCase("offline")){
                    stockisttime.setText("Opens on : "+stockistItems.get(0).getOpeningtime().substring(0,5)+" "+stockistItems.get(0).getOampm()+" - "+stockistItems.get(0).getClosingtime().substring(0,5)+" "+stockistItems.get(0).getCampm());
                }else{
                    stockisttime.setText("Offline");
                }
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity(),catbanItems,0);
                manualSlider.setAdapter(viewPagerAdapter);
                slider();
                rvOfferproduct.getAdapter().notifyDataSetChanged();
                rvCat.getAdapter().notifyDataSetChanged();
                rvManualSlider.getAdapter().notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialoge.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> param = new HashMap<String,String>();
                param.put("custid",Global.customer_id);
                //param.put("stcode",activity.getIntent().getStringExtra("id"));
                param.put("stcode",Global.selshopid);
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
                    if((currentIndex+1)>bannerItems.size() ){
                        currentIndex=0;
                    }else{
                        currentIndex++;
                    }
                    viewPager.setCurrentItem(currentIndex);
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

    private void update() {
        final StringRequest request = new StringRequest(StringRequest.Method.POST, RetrofitClient.BASE_URL + "isupdaterequire", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    PackageInfo pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
                    currentversion = pInfo.versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

                try {

                    JSONObject jsonObject =new JSONObject(response);
                    double newverison = Double.parseDouble(jsonObject.getString("version"));
                    double verison = Double.parseDouble(currentversion);
                    final String link = jsonObject.getString("link");

                    if (verison < newverison){
                        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
                        View mView = layoutInflaterAndroid.inflate(R.layout.update, null);
                        android.app.AlertDialog.Builder alertDialogBuilderUserInput = new android.app.AlertDialog.Builder(getContext());
                        alertDialogBuilderUserInput.setView(mView);
                        TextView button=mView.findViewById(R.id.updateappbtn);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(link));
                                startActivity(intent);
                            }
                        });
                        final android.app.AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                        alertDialogAndroid.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        alertDialogAndroid.setCancelable(false);
                        alertDialogAndroid.show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Unable to check updates !", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String ,String> map = new HashMap<>();
                map.put("product_id","2");
                return map;
            }
        };
        queue.add(request);
    }

}