package com.mahasiswa.nawank.datamahasiswa;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by kenzie on 4/12/2017.
 */

public interface RegisterAPI {

        @FormUrlEncoded
        @POST("/insert.php")
        Call<Value> simpan(@Field("nim") String nim,
                           @Field("nama") String nama,
                           @Field("kelas") String kelas,
                           @Field("gender") String gender);

    @GET("view.php")
    Call<Value> view();

    @FormUrlEncoded
    @POST("update.php")
    Call<Value> ubah(@Field("nim") String nim,
                     @Field("nama") String nama,
                     @Field("kelas") String kelas,
                     @Field("gender") String gender);

    @FormUrlEncoded
    @POST("delete.php")
    Call<Value> hapus(@Field("nim") String nim);

    @FormUrlEncoded
    @POST("search.php")
    Call<Value> search(@Field("search") String search);
    }
