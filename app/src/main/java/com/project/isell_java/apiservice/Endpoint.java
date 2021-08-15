package com.project.isell_java.apiservice;




import com.project.isell_java.pojos.login.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Endpoint {



@FormUrlEncoded
@POST("client/login")
Call<Response> do_login(@Field("mobile") String mobile, @Field("password") String password);







}
