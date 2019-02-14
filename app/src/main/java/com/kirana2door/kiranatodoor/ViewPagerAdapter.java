package com.kirana2door.kiranatodoor;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.kirana2door.kiranatodoor.models.CatbanItem;

import java.util.List;


/**
 * Created by Sanket on 27-Feb-17.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<CatbanItem>  images ;
    int type ;

    public ViewPagerAdapter(Context context, List<CatbanItem> img_array, int i) {
        this.context = context;
        this.images=img_array;
        this.type = i;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = null;

        if (type == 0){

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.manual_slide, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            //imageView.setImageResource(images[position]);

            Glide.with(context).load(images.get(position).getOffpicPath()).into(imageView);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

            ViewPager vp = (ViewPager) container;
            vp.addView(view, 0);
            int pagerPadding = 16;
            vp.setClipToPadding(false);
            vp.setPadding(pagerPadding, 0, pagerPadding, 0);
        }else if (type == 1) {

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             view = layoutInflater.inflate(R.layout.custom_layout, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            //imageView.setImageResource(images[position]);
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(20));

            Glide.with(context).load(images.get(position).getOffpicPath()).apply(requestOptions).into(imageView);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

            ViewPager vp = (ViewPager) container;
            vp.addView(view, 0);
        }

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
