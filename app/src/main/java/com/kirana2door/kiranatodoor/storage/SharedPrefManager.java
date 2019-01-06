package com.kirana2door.kiranatodoor.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.kirana2door.kiranatodoor.models.User;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_preff";

    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }


    public static synchronized SharedPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }


    public void saveUser(User user) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("id", user.getId());
        editor.putString("email", user.getEmail());
        editor.putString("name", user.getName());
        editor.putString("pincode", user.getPincode());
        editor.putString("contact", user.getContact());
        editor.putString("address", user.getAddress());
        editor.putString("city", user.getCity());
        editor.putString("state", user.getState());
        editor.putString("country", user.getCountry());
        editor.apply();

    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("id", null) != null;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString("id", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("pincode", null),
                sharedPreferences.getString("contact", null),
                sharedPreferences.getString("address", null),
                sharedPreferences.getString("city", null),
                sharedPreferences.getString("state", null),
                sharedPreferences.getString("country", null)
        );
    }

    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
