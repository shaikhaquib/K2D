package com.kirana2door.kiranatodoor.Fragment;

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

import com.bumptech.glide.Glide;
import com.kirana2door.kiranatodoor.R;

import params.com.stepview.StatusView;
import params.com.stepview.StatusViewScroller;

public class Frg_Notification extends Fragment {

    RecyclerView rvHist;
     String[] prd_img = {
        "https://www.bigbasket.com/media/uploads/p/s/40112512_2-bb-royal-medjool-dates.jpg",
        "https://www.bigbasket.com/media/uploads/p/s/40072455_5-bb-royal-organic-kabuli-chanachanna.jpg",
        "https://www.bigbasket.com/media/uploads/p/s/40112523_2-bb-royal-dried-fruit-blueberries.jpg"
        },
        prd_names = {
                "STOCK-LIST NAME 1",
                "STOCK-LIST NAME 2",
                "STOCK-LIST NAME 3"
        },prd_qnt = {"9","15","6"},prd_status = {"3","1","4"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frg_notification, null);

        rvHist = view.findViewById(R.id.rvrHist);
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
                Glide.with(getActivity()).load(prd_img[i]).into(myHolder.img);
                myHolder.prdname.setText(prd_names[i]);
                myHolder.prdqnt.setText("Total Item Ordered : "+prd_qnt[i]);
                myHolder.indicator.setTag(i);
                StatusView Status = myHolder.indicator.getStatusView();
                Status.setCurrentCount(Integer.parseInt(prd_status[i]));



            }

            @Override
            public int getItemCount() {
                return prd_img.length;
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


        return view;
    }
}
