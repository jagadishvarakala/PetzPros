package com.petz.pros.data.network;


import com.petz.pros.data.network.pojo.LoginRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    /*
  Retrofit get annotation with our URL
  And our method that will return us the List of User List
  */

    @FormUrlEncoded
    @POST("addUser.php")
    public Call<ResponseBody> registerUser(@Field("name") String name,
                                           @Field("email") String password,
                                           @Field("password") String email, @Field("image") String image);


    @POST("login")
    public Call<ResponseBody> userLogin(@Body LoginRequest loginRequest);


    @FormUrlEncoded
    @POST("updateUser.php")
    public Call<ResponseBody> updateUser(@Field("id") Integer id,
                                         @Field("name") String password,
                                         @Field("image") String image);

    @FormUrlEncoded
    @POST("updatePasswordUser.php")
    public Call<ResponseBody> updatePasswordUser(@Field("id") Integer id,
                                                 @Field("password") String password
    );

    @GET("deleteUser.php")
    Call<ResponseBody> deleteUser(@Query("id") Integer id);

}
