package com.project.isell_java.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.isell_java.BasicActivity;
import com.project.isell_java.R;
import com.project.isell_java.apiservice.ApiClient;
import com.project.isell_java.apiservice.Endpoint;
import com.project.isell_java.database.appdb.Appdb;
import com.project.isell_java.database.entities.InvEntity;
import com.project.isell_java.pojos.import_data.InventoriesItem;
import com.project.isell_java.pojos.import_data.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class ImportActivity  extends BasicActivity {

    private Button btn_import;
    private Appdb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        btn_import=findViewById(R.id.btn_import);
        db = Appdb.getDb_instance(getApplicationContext());

        btn_import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                Call<Response> call = apiService.importData();

                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                        showSnack_W("ok");

                        db.getInvEntityDao().del_all();

                  for(InventoriesItem row:response.body().getData().getInventories())
                  {

                      db.getInvEntityDao().insert_inv_item(new InvEntity(0,"",""+row.getName(),""+row.getHsncode(),""+row.getGroup(),
                              row.getRate1(),row.getRate2(),row.getRate3(),row.getRate4(),1));

                  }

                  showSnack_W(""+db.getInvEntityDao().get_count());








                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });


            }
        });

    }

}
