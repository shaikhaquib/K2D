package com.kirana2door.kiranatodoor.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.activities.StockistDetail;

public class Frg_Store extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.frg_stockist, null);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
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

                Holder holder =(Holder) viewHolder;
                holder.itemView.setTag(i);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), StockistDetail.class));
                    }
                });

            }

            @Override
            public int getItemCount() {
                return 10;
            }

            class Holder extends RecyclerView.ViewHolder {
                public Holder(@NonNull View itemView) {
                    super(itemView);
                }
            }

        });

        return view;
    }
}
