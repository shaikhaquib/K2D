package com.kirana2door.kiranatodoor.Fragment;

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

import com.bumptech.glide.Glide;
import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.ViewPagerAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class Frg_Home extends Fragment {

    ViewPager viewPager,manualSlider;
    LinearLayout sliderDotspanel;
    RecyclerView rvOfferproduct,rvCat;
    private int dotscount;
    private ImageView[] dots;
    View parentLayout;

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
    },prd_Count = {"20","35","16","56","05","10"};


    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;


    String[] imagearray = {
            "https://www.bigbasket.com/media/customPage/b01eee88-e6bc-410e-993c-dedd012cf04b/a0c46e89-2c57-47ee-9b4b-5e5d7b102bdc/b4ab9e83-a7e2-4e26-9547-3dfe310c212a/All_70p_Deal_Paytm_offer_DT_4_1130x400_19thJan.jpg",
            "https://www.bigbasket.com/media/customPage/b01eee88-e6bc-410e-993c-dedd012cf04b/a0c46e89-2c57-47ee-9b4b-5e5d7b102bdc/b4ab9e83-a7e2-4e26-9547-3dfe310c212a/T1_All_GoodDiet_DT_2_1130x400_12thJan.jpg",
            "https://www.bigbasket.com/media/customPage/b01eee88-e6bc-410e-993c-dedd012cf04b/a0c46e89-2c57-47ee-9b4b-5e5d7b102bdc/b4ab9e83-a7e2-4e26-9547-3dfe310c212a/T1_All_GoodDiet_DT_2_1130x400_12thJan.jpg",
            "https://www.bigbasket.com/media/customPage/b01eee88-e6bc-410e-993c-dedd012cf04b/a0c46e89-2c57-47ee-9b4b-5e5d7b102bdc/b4ab9e83-a7e2-4e26-9547-3dfe310c212a/All_bbStarbigsave_DT_1_1130x400_12thJan.jpg",
            "https://www.bigbasket.com/media/customPage/b01eee88-e6bc-410e-993c-dedd012cf04b/a0c46e89-2c57-47ee-9b4b-5e5d7b102bdc/4a1760bc-0ec8-4300-a250-4993235350ab/T1_All_HealthStore_DT_1_560x378_16thJan.jpg",
            "https://www.bigbasket.com/media/uploads/banner_images/T1_All_RefernEarn_DT_1_1130x400_12thjan.jpg"} ;

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
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity(),imagearray,0);

        manualSlider.setAdapter(viewPagerAdapter);
        slider();

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
                Glide.with(getActivity()).load(prd_img[i]).into(myHolder.img);
                myHolder.prdname.setText(prd_names[i]);


            }

            @Override
            public int getItemCount() {
                return prd_img.length;
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
                Glide.with(getActivity()).load(prd_img[i]).into(myHolder.img);
                myHolder.prdname.setText(prd_names[i]);
                myHolder.count.setText(prd_Count[i]);


            }

            @Override
            public int getItemCount() {
                return prd_img.length;
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

        return view ;
    }


    public void slider(){


        if (imagearray.length<2){

            sliderDotspanel.setVisibility(View.GONE );
        }

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity(),imagearray,1);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

//        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

             /*   for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));
*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
