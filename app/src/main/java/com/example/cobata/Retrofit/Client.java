package com.example.cobata.Retrofit;

import com.example.cobata.Return.ResultHome;
import com.example.cobata.Return.ReturnCreate;
import com.example.cobata.Return.ReturnRead;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Client {
    @FormUrlEncoded
    @POST("create")
    Call<ReturnCreate> create(
            @Field("name") String nama,
            @Field("street") String street,
            @Field("city") String city,
            @Field("country_id") String country_id,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("website") String website,
            @Field("active") String active,
            @Field("customer") String customer,
            @Field("supplier") String supplier,
            @Field("employee") String employee,
            @Field("image") String image
            );

    @GET("read")
    Call<ResultHome> read();
}
