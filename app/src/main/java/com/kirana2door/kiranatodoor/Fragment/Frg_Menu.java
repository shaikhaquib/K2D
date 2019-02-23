package com.kirana2door.kiranatodoor.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.activities.ChangePassword;

public class Frg_Menu extends Fragment {

    TextView mnAccount , mnChangePassword;
    LinearLayout mnLinearlayout;
    View mnAccountView;
    Boolean mnAccountLytState = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_menu, null);

        mnAccount = view.findViewById(R.id.mnAccount);
        mnChangePassword = view.findViewById(R.id.mnChangePassword);
        mnLinearlayout = view.findViewById(R.id.mnAccountLayout);
        mnAccountView =view.findViewById(R.id.mnAccountView);

        mnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChangePassword.class));
            }
        });

        ImageView image = view.findViewById(R.id.MnDP);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound("A", Color.GRAY);

        mnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mnAccountLytState){
                    mnAccountLytState =  true;
                    mnAccountView.setVisibility(View.GONE);
                    mnLinearlayout.setVisibility(View.VISIBLE);
                }else {
                    mnAccountLytState =  false;
                    mnAccountView.setVisibility(View.VISIBLE);
                    mnLinearlayout.setVisibility(View.GONE);
                }
            }
        });

        image.setImageDrawable(drawable);

        return view;
    }
}