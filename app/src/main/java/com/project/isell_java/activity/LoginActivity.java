package com.project.isell_java.activity;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;

import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.isell_java.BasicActivity;
import com.project.isell_java.R;
import com.project.isell_java.Utils;
import com.project.isell_java.apiservice.ApiClient;
import com.project.isell_java.apiservice.Endpoint;
import com.project.isell_java.database.appdb.Appdb;
import com.project.isell_java.database.entities.ChartcodeEntity;
import com.project.isell_java.pojos.login.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends BasicActivity {


    private int display_cnt = 0;
    private EditText edt1, edt2;
    private br.com.simplepass.loadingbutton.customViews.CircularProgressButton btn1;
    private ConstraintLayout clmain;
    private CardView card1;
    private Appdb db;

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

        db = Appdb.getDb_instance(getApplicationContext());
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

            btn1.setEnabled(false);
            btn1.startAnimation();

            Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

            //  Call<Response> call = apiService.do_login("8943220888", "123456");
            Call<Response> call = apiService.do_login(username, password);

            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


//                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().
//                                putString("pref_login",new Gson().toJson( response.body())).apply();
//
//
//                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                        String value = preferences.getString("pref_login", "");

                    if(response.body()!=null)
                    {
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


                            //save token to db
                            db.getChartcodeEntityDao().del_token();

                            db.getChartcodeEntityDao().insert_chartcode_item(new ChartcodeEntity(0,"token",response.body().getData().getToken(),""+response.body().getData().getUser().getId(),response.body().getData().getUser().getDistroId()));



                            //check serie no set or not?

                        int count=    db.getChartcodeEntityDao().get_count_of_stored_series_no();

                        if(count==1)
                        {
                            go_to_dashboard();
                        }
                        else
                        {
                            db.getChartcodeEntityDao().del_series();

                            show_dialog();


                        }





                        } else {
                            showSnack_W(""+response.body().getMsg());
                        }
                    }




                    btn1.stopAnimation();
                    btn1.revertAnimation();
                    btn1.setEnabled(true);

                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    showSnack_W("Something went wrong");

                    btn1.stopAnimation();
                    btn1.revertAnimation();
                    btn1.setEnabled(true);
                }
            });
        }
        else
        {

        }





    }

private  void go_to_dashboard()
{
    Intent in = new Intent(LoginActivity.this, DashboardActivity.class);
    startActivity(in);
    finish();

}


private void show_dialog()
{
    final Dialog dialog = new Dialog(LoginActivity.this);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

    dialog.setContentView(R.layout.cust_dialog1);
    dialog.setCancelable(false);
    dialog.show();


    EditText edt_series=    dialog.findViewById(R.id.edt_series);
    Button btn_save=    dialog.findViewById(R.id.btn_save);

    btn_save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(edt_series.getText().toString().trim().length()>0)
            {
                //save the series in web  , then in localdb , then go to dashboard
              //  hideSoftKeyboard(LoginActivity.this,v);


                Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
                Call<com.project.isell_java.pojos.save_series.Response> call = apiService.save_series(edt_series.getText().toString().trim());

                call.enqueue(new Callback<com.project.isell_java.pojos.save_series.Response>() {
                    @Override
                    public void onResponse(Call<com.project.isell_java.pojos.save_series.Response> call, retrofit2.Response<com.project.isell_java.pojos.save_series.Response> response) {
                      if(response.body() !=null)
                      {
                          if(response.body().getResult().equals("1"))
                          {
                              db.getChartcodeEntityDao().insert_chartcode_item(new ChartcodeEntity
                                      (0,"series_no",edt_series.getText().toString().trim(),"",1));

                              int count=    db.getChartcodeEntityDao().get_count_of_stored_series_no();

                              if(count==1)
                              {
                                  go_to_dashboard();
                              }

                          }
                          else
                          {


                              Toast toast = Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_LONG);
                              toast.setGravity(Gravity.CENTER, 0, 400);
                              toast.show();




                          }
                      }




                    }

                    @Override
                    public void onFailure(Call<com.project.isell_java.pojos.save_series.Response> call, Throwable t) {

                    }
                });
            }

        }
    });














}






}
