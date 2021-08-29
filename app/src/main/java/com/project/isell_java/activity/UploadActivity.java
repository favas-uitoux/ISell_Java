package com.project.isell_java.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.project.isell_java.BasicActivity;
import com.project.isell_java.R;
import com.project.isell_java.Utils;
import com.project.isell_java.apiservice.ApiClient;
import com.project.isell_java.apiservice.Endpoint;
import com.project.isell_java.database.appdb.Appdb;
import com.project.isell_java.database.entities.OrderDetailsEntity;
import com.project.isell_java.pojos.base.Pojomodelbase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends BasicActivity {

    private Appdb db;
    JSONObject jsonObject, wholejsonObject;
    private LinearLayout llchrome;
    private com.ldoublem.loadingviewlib.LVChromeLogo chrome1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        init();

        do_upload_now();



    }


private void init()
{
    db = Appdb.getDb_instance(getApplicationContext());
    llchrome=findViewById(R.id.llchrome);
    chrome1=findViewById(R.id.chrome1);


}


    private void do_upload_now()
    {

        List<OrderDetailsEntity> list_send= db.getOrderDetailsEntityDao().get_all_datas();
        int send_size = 0;
        wholejsonObject = new JSONObject();

        int k=-1;
        for (int j = 0; j < list_send.size(); j++) {


            try {

                if (!list_send.get(j).getOno().trim().equals("") && !list_send.get(j).getIid().trim().equals("")   && list_send.get(j).getQty() >0   && !list_send.get(j).getDid().trim().equals("")  && !list_send.get(j).getCid().trim().equals("")     ) {
                    jsonObject = new JSONObject();


                    jsonObject.put("did", list_send.get(j).getDid());
                    jsonObject.put("cid", list_send.get(j).getCid());

                    jsonObject.put("iid", list_send.get(j).getId());
                    jsonObject.put("qty", list_send.get(j).getQty());

                    jsonObject.put("inam", Utils.GVCOT(list_send.get(j).getInam()));
                    jsonObject.put("tot", list_send.get(j).getTotal());


                    jsonObject.put("ono", list_send.get(j).getOno());
                    jsonObject.put("odate", list_send.get(j).getOdate());


                    send_size = send_size + 1;

                    k=k+1;
                    wholejsonObject.put("rawdata" + k, jsonObject);
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        if(send_size>0)
        {
//            btn2.setEnabled(false);
//            btn2.startAnimation();
            llchrome.setVisibility(View.VISIBLE);
            chrome1.startAnim();

            Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

            Call<Pojomodelbase> call = apiService.save_orders(wholejsonObject.toString(), send_size);

            call.enqueue(new Callback<Pojomodelbase>() {
                @Override
                public void onResponse(Call<Pojomodelbase> call, Response<Pojomodelbase> response) {


                    chrome1.stopAnim();
                    llchrome.setVisibility(View.GONE);

                    if(response.body().getResult().equals("1"))
                    {
                        showSnack_W(response.body().getMessage());

                        db.getOrderDetailsEntityDao().del_all();

                        finish();
                    }
                    else
                    {
                        showSnack_W(response.body().getMessage());
                    }





//                    btn2.stopAnimation();
//                    btn2.revertAnimation();
//                    btn2.setEnabled(true);
                }

                @Override
                public void onFailure(Call<Pojomodelbase> call, Throwable t) {

                    showSnack_W(t.getLocalizedMessage());
//                    btn2.stopAnimation();
//                    btn2.revertAnimation();
//                    btn2.setEnabled(true);

                    chrome1.stopAnim();
                    llchrome.setVisibility(View.GONE);
                }
            });
        }
        else
        {

            showSnack_W("Nothing toi upload");
        }


    }

}
