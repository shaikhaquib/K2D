package com.kirana2door.kiranatodoor.api;

import com.kirana2door.kiranatodoor.models.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponse> userLogin(
            @Field("username") String email,
            @Field("password") String password
    );

}
