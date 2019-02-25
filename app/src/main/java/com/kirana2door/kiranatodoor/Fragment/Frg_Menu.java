package com.kirana2door.kiranatodoor.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.kirana2door.kiranatodoor.Global;
import com.kirana2door.kiranatodoor.R;
import com.kirana2door.kiranatodoor.ViewDialog;
import com.kirana2door.kiranatodoor.activities.ChangePassword;
import com.kirana2door.kiranatodoor.activities.LoginActivity;
import com.kirana2door.kiranatodoor.activities.ManageAddress;
import com.kirana2door.kiranatodoor.activities.OTPVerification;
import com.kirana2door.kiranatodoor.activities.PersonalInfo;
import com.kirana2door.kiranatodoor.activities.UpdateContact;
import com.kirana2door.kiranatodoor.api.RetrofitClient;
import com.kirana2door.kiranatodoor.models.MenuResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class Frg_Menu extends Fragment {

    TextView mnAccount , mnChangePassword, mnPersonalInfo, mnManageAddress, mnUpdateContact;
    LinearLayout mnLinearlayout;
    View mnAccountView;
    Boolean mnAccountLytState = false;
    Button logout;
    ViewDialog progress;
    public String firstword;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.frg_menu, null);
        progress=new ViewDialog(getActivity());
        mnAccount = view.findViewById(R.id.mnAccount);
        mnChangePassword = view.findViewById(R.id.mnChangePassword);
        mnManageAddress = view.findViewById(R.id.mnManageAddress);
        mnPersonalInfo = view.findViewById(R.id.mnPersonalInfo);
        mnUpdateContact = view.findViewById(R.id.mnUpdateContact);
        mnLinearlayout = view.findViewById(R.id.mnAccountLayout);
        mnAccountView =view.findViewById(R.id.mnAccountView);
        logout =view.findViewById(R.id.logout);

        mnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChangePassword.class));
            }
        });

        mnPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PersonalInfo.class));
            }
        });

        mnManageAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ManageAddress.class));
            }
        });

        mnUpdateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UpdateContact.class));
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Kirana2Door");
                builder.setMessage("Are you sure want to logout ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().getSharedPreferences("personal_info_of_k2d_cust", MODE_PRIVATE).edit().clear().apply();
                        getActivity().getSharedPreferences("my_shared_preff", MODE_PRIVATE).edit().clear().apply();
                        Intent intent = new Intent(getActivity(),LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                });

                builder.show();
            }
        });


        final ImageView image = view.findViewById(R.id.MnDP);
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

        progress.show();
        Call<MenuResponse> call = RetrofitClient
                .getInstance().getApi().getCustAllDetails(Global.customer_id);
        call.enqueue(new Callback<MenuResponse>() {
            @Override
            public void onResponse(Call<MenuResponse> call, Response<MenuResponse> response) {
                MenuResponse mr = response.body();
                progress.dismiss();
                firstword = mr.getFirst_name().substring(0,1);

                TextDrawable drawable = TextDrawable.builder()
                        .buildRound(firstword.toUpperCase(), Color.GRAY);
                image.setImageDrawable(drawable);

                Objects.requireNonNull(getActivity()).getSharedPreferences("personal_info_of_k2d_cust", MODE_PRIVATE).edit().clear().apply();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("personal_info_of_k2d_cust", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("cust_id", mr.getCust_id());
                editor.putString("first_name", mr.getFirst_name());
                editor.putString("last_name", mr.getLast_name());
                editor.putString("address1", mr.getAddress1());
                editor.putString("address2", mr.getAddress2());
                editor.putString("address3", mr.getAddress3());
                editor.putString("city", mr.getCity());
                editor.putString("state", mr.getState());
                editor.putString("country", mr.getCountry());
                editor.putString("pincode", mr.getPincode());
                editor.putString("contact_no", mr.getContact_no());
                editor.putString("email_id", mr.getEmail_id());
                editor.putString("status", mr.getStatus());
                editor.apply();

            }

            @Override
            public void onFailure(Call<MenuResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getActivity(), "Failed to get customer details !", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }


}