package com.project.isell_java.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;

import android.view.View;
import android.view.ViewTreeObserver;
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


    private int display_cnt = 0;
    private EditText edt1, edt2;
    private br.com.simplepass.loadingbutton.customViews.CircularProgressButton btn1;
    private ConstraintLayout clmain;
    private CardView card1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        init();






        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideSoftKeyboard(LoginActivity.this,v);
                login();





            }
        });




        clmain.getViewTreeObserver().addOnGlobalLayoutListener(new
                                                                       ViewTreeObserver.OnGlobalLayoutListener() {
                                                                           @Override
                                                                           public void onGlobalLayout() {
                                                                               display_cnt = display_cnt + 1;


                                                                               if (display_cnt > 2) {

                                                                                   Rect r = new Rect();
                                                                                   clmain.getWindowVisibleDisplayFrame(r);
                                                                                   int screenHeight = clmain.getRootView().getHeight();
                                                                                   int keypadHeight = screenHeight - r.bottom;
                                                                                   if (keypadHeight > screenHeight * 0.15) {

                                                                                       ObjectAnimator animator = ObjectAnimator.ofFloat(card1, "translationY", -500f);
                                                                                       animator.setDuration(2000);
                                                                                       animator.start();

                                                                                       //   Toast.makeText(LoginActivity.this, "Keyboard is showing", Toast.LENGTH_LONG).show();
                                                                                   } else {


                                                                                       ObjectAnimator animator = ObjectAnimator.ofFloat(card1, "translationY", 100f);
                                                                                       animator.setDuration(2000);
                                                                                       animator.start();


                                                                                       //Toast.makeText(LoginActivity.this, "keyboard closed"+ display_cnt, Toast.LENGTH_LONG).show();
                                                                                   }

                                                                               }
                                                                           }

                                                                       });

    }


    private void init() {

        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        btn1 = findViewById(R.id.btn1);
        clmain = findViewById(R.id.clmain);
        card1 = findViewById(R.id.card1);

    }

    private  void login()
    {

        String username = edt1.getText().toString().trim();
        String password = edt2.getText().toString().trim();


        if (!username.equals("") && !password.equals("")) {

            Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

            //  Call<Response> call = apiService.do_login("8943220888", "123456");
            Call<Response> call = apiService.do_login(edt1.getText().toString(), edt2.getText().toString());

            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


//                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().
//                                putString("pref_login",new Gson().toJson( response.body())).apply();
//
//
//                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                        String value = preferences.getString("pref_login", "");


                    if (response.body().getCode().equals("CLIENT_LOGIN_OK")) {


                        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);


                        mPrefs.edit().clear();
                        //   Response myObject = new Response();


                        Response MyObject = new Response();
                        MyObject.setCode(response.body().getCode());
                        MyObject.setMsg(response.body().getMsg());
                        MyObject.setData(response.body().getData());

                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(MyObject);
                        prefsEditor.putString("MyObject", json);
                        prefsEditor.commit();


                        Utils.setTocken(response.body().getData().getToken());

                        Intent in = new Intent(LoginActivity.this, ImportActivity.class);
                        startActivity(in);

                    } else {
                        showSnack_W(""+response.body().getMsg());
                    }

                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    showSnack_W("not ok");
                }
            });
        }
        else
        {

        }





    }



}
