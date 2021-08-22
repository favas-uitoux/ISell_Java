package com.project.isell_java.apiservice;


import com.project.isell_java.Utils;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{

    public  static String BASE_URL="https://ibill-sales-order-api.erekha.in/";
    public   static Retrofit retrofit=null;





    public static Retrofit getClient() {
        if (retrofit==null) {

            Interceptor interceptor = chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization","Bearer " + Utils.getTocken())
                        .build();


                return chain.proceed(request);
            };



            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();



            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(logging)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }







}
