package com.kirana2door.kiranatodoor.activities;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.ViewPagerAdapter;
import com.kirana2door.kiranatodoor.models.CatbanItem;

import java.util.ArrayList;
import java.util.List;

public class ProductDetail extends AppCompatActivity {
String[] prd_img = {
            "https://www.bigbasket.com/media/uploads/p/s/40112512_2-bb-royal-medjool-dates.jpg",
            "https://www.bigbasket.com/media/uploads/p/s/40072455_5-bb-royal-organic-kabuli-chanachanna.jpg",
            "https://www.bigbasket.com/media/uploads/p/s/40112523_2-bb-royal-dried-fruit-blueberries.jpg"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ViewPager pager =   findViewById(R.id.photos_viewpager);
        VPagerAdapter adapter = new VPagerAdapter(getApplicationContext());
        pager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager, true);

        for(int i=0; i < tabLayout.getTabCount(); i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(0, 0, 15, 0);
            tab.requestLayout();
        }
    }

    class VPagerAdapter extends PagerAdapter {

        private Context context;
        private LayoutInflater layoutInflater;int type ;

        public VPagerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return prd_img.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = null;


                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.custom_layout, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
                //imageView.setImageResource(images[position]);


                Glide.with(context).load(prd_img[position]).into(imageView);


                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                    }
                });

                ViewPager vp = (ViewPager) container;
                vp.addView(view, 0);

            return view;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            ViewPager vp = (ViewPager) container;
            View view = (View) object;
            vp.removeView(view);

        }
    }

}
