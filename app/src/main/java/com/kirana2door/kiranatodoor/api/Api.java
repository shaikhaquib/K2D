package com.kirana2door.kiranatodoor.api;

import com.kirana2door.kiranatodoor.activities.CustomerRegistration;
import com.kirana2door.kiranatodoor.models.DefaultResponse;
import com.kirana2door.kiranatodoor.models.LoginResponse;
import com.kirana2door.kiranatodoor.models.MainPageResponse;

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

    @FormUrlEncoded
    @POST("registerUser")
    Call<DefaultResponse> registerUser(
            @Field("fname") String sfname,
            @Field("lname") String slname,
            @Field("add1") String sadd1,
            @Field("add2") String sadd2,
            @Field("add3") String sadd3,
            @Field("email") String semail,
            @Field("pincode") String spincode,
            @Field("phno") String sphno,
            @Field("password") String sconfpass
    );

    @FormUrlEncoded
    @POST("verify_customer")
    Call<DefaultResponse> checkOTP(
            @Field("otp") String OTPPASS
    );

    @FormUrlEncoded
    @POST("forgotpassotpverify")
    Call<DefaultResponse> checkForgotPassOTP(
            @Field("otp") String OTPPASS
    );
    @FormUrlEncoded
    @POST("resendotp")
    Call<DefaultResponse> resendOTP(
            @Field("email") String emailid,
            @Field("contact") String contact
    );

    @FormUrlEncoded
    @POST("forgotpassemailncontverify")
    Call<DefaultResponse> forgotPassReq(
            @Field("email") String emailid,
            @Field("contact") String contact
    );

    @FormUrlEncoded
    @POST("forgotpasschange")
    Call<DefaultResponse> forgotChangePass(
            @Field("password") String snewpass,
            @Field("email") String email,
            @Field("contact") String phno
    );

    @FormUrlEncoded
    @POST("mainpagealldata")
    Call<MainPageResponse> mainPageAllData(
            @Field("custid") String custid,
            @Field("stcode") String shopid
    );
}
