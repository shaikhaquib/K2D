package com.kirana2door.kiranatodoor.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.kirana2door.kiranatodoor.R;

public class Frg_Menu extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_menu, null);


        ImageView image = view.findViewById(R.id.MnDP);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound("A", Color.GRAY);

        image.setImageDrawable(drawable);

        return view;
    }
}