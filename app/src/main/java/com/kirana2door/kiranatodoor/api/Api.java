package com.kirana2door.kiranatodoor.api;

import com.kirana2door.kiranatodoor.activities.CustomerRegistration;
import com.kirana2door.kiranatodoor.models.DefaultResponse;
import com.kirana2door.kiranatodoor.models.LoginResponse;
import com.kirana2door.kiranatodoor.models.MainPageResponse;
import com.kirana2door.kiranatodoor.models.MenuResponse;

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
    @POST("verifycontactupdate")
    Call<DefaultResponse> verifyContactUpdate(
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

    @FormUrlEncoded
    @POST("getCustAllDetails")
    Call<MenuResponse > getCustAllDetails(
            @Field("cust_id") String custid
    );

    @FormUrlEncoded
    @POST("updatecustomerpersonaldetails")
    Call<DefaultResponse> updatePersonalInfo(
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("cid") String cid
    );

    @FormUrlEncoded
    @POST("changepass")
    Call<DefaultResponse> changePass(
            @Field("oldpass") String oldpass,
            @Field("pass") String pass,
            @Field("cid") String cid
    );

    @FormUrlEncoded
    @POST("contactupdate")
    Call<DefaultResponse> contactUpdate(
            @Field("contact") String contact,
            @Field("cust_id") String custid
    );

    @FormUrlEncoded
    @POST("manageaddressofcust")
    Call<DefaultResponse> manageAddressOfCust(
            @Field("address1") String address1,
            @Field("address2") String address2,
            @Field("address3") String address3,
            @Field("pincode") String pincode,
            @Field("cid") String cid
    );
}
