package com.project.isell_java.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.isell_java.BasicActivity;
import com.project.isell_java.R;
import com.project.isell_java.Utils;
import com.project.isell_java.apiservice.ApiClient;
import com.project.isell_java.apiservice.Endpoint;
import com.project.isell_java.pojos.login.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends BasicActivity {


    private EditText edt1,edt2;
    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        init();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                Call<Response> call = apiService.do_login("8943220888","123456");
                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {



//                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().
//                                putString("pref_login",new Gson().toJson( response.body())).apply();
//
//
//                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                        String value = preferences.getString("pref_login", "");


                        SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);


                        mPrefs.edit().clear();
                     //   Response myObject = new Response();


                       Response MyObject= new Response();
                        MyObject.setCode(response.body().getCode());
                        MyObject.setMsg(response.body().getMsg());
                        MyObject.setData(response.body().getData());

                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(MyObject);
                        prefsEditor.putString("MyObject", json);
                        prefsEditor.commit();


                        Utils.setTocken(response.body().getData().getToken());


                       // Gson gson1 = new Gson();
//                        String json1 = mPrefs.getString("MyObject", "");
//                        Response obj = gson.fromJson(json1, Response.class);
//
//                        Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_LONG).show();

                        Intent in=new Intent(LoginActivity.this,ImportActivity.class);
                        startActivity(in);


                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        showSnack_W("not ok");
                    }
                });


            }
        });



    }


    private void init()
    {

        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        btn1=findViewById(R.id.btn1);

    }


}
