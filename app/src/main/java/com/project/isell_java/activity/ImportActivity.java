package com.project.isell_java.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.isell_java.BasicActivity;
import com.project.isell_java.R;
import com.project.isell_java.adapter.AdapterItemListing;
import com.project.isell_java.apiservice.ApiClient;
import com.project.isell_java.apiservice.Endpoint;
import com.project.isell_java.database.appdb.Appdb;
import com.project.isell_java.database.entities.InvEntity;
import com.project.isell_java.pojos.import_data.InventoriesItem;
import com.project.isell_java.pojos.import_data.Response;
import com.project.isell_java.pojos.import_data.RetailersItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ImportActivity extends BasicActivity {

    private com.ldoublem.loadingviewlib.LVChromeLogo chrome1;

    private LinearLayout llchrome;

    private Appdb db;
    private RecyclerView recv1;
    private androidx.swiperefreshlayout.widget.SwipeRefreshLayout refresh1;
    private AdapterItemListing adp;
    private List<InvEntity> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);


        init();


        download();


    }


    private void init() {

        db = Appdb.getDb_instance(getApplicationContext());
        recv1 = findViewById(R.id.recv1);
        llchrome = findViewById(R.id.llchrome);
        chrome1 = findViewById(R.id.chrome1);
        refresh1 = findViewById(R.id.refresh1);

    }

    private void show_the_list() {

        list = new ArrayList<>();
        list.addAll(db.getInvEntityDao().get_all_datas());

        adp = new AdapterItemListing(getApplicationContext(), list);


        LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recv1.setLayoutManager(lm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recv1.getContext(), lm.getOrientation());
        recv1.addItemDecoration(dividerItemDecoration);


        recv1.setAdapter(adp);


        LayoutAnimationController anim_cont = AnimationUtils.loadLayoutAnimation(ImportActivity.this, R.anim.layout_animation_from_bottom);

        recv1.setLayoutAnimation(anim_cont);
        adp.notifyDataSetChanged();
        recv1.scheduleLayoutAnimation();


    }

    private void download() {
        refresh1.setVisibility(View.GONE);
        llchrome.setVisibility(View.VISIBLE);

        chrome1.startAnim();


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<Response> call = apiService.importData();

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {




                if(response.body() !=null) {
                    if (response.body().getData() != null) {

                        int pricing_level=0;

                        List<RetailersItem> list_Retailers=   response.body().getData().getRetailers();

                        for( RetailersItem row:list_Retailers)
                        {
                            if(row.getContact().equals(db.getChartcodeEntityDao().get_user_cred()))
                            {
                                pricing_level= row.getPricingLevel();

                            }



                        }
                        if(pricing_level==0)
                        {
                            pricing_level=1;
                        }



                        db.getInvEntityDao().del_all();

                        for (InventoriesItem row : response.body().getData().getInventories()) {


                            db.getInvEntityDao().insert_inv_item(new InvEntity(0, "" + row.getUid(), "" + row.getName(), "" + row.getHsncode(), "" + row.getGroup(),
                                    row.getRate1(), row.getRate2(), row.getRate3(), row.getRate4(), pricing_level));

                        }





                        showSnack_W("Downloaded " + db.getInvEntityDao().get_count() + " items");
                        chrome1.stopAnim();
                        llchrome.setVisibility(View.GONE);
                        refresh1.setVisibility(View.VISIBLE);
                        show_the_list();


                    } else {
                        showSnack_W("No items found");
                        chrome1.stopAnim();
                        llchrome.setVisibility(View.GONE);
                        refresh1.setVisibility(View.VISIBLE);
                    }

                }
                else
                {
                    showSnack_W("No items found");
                    chrome1.stopAnim();
                    llchrome.setVisibility(View.GONE);
                    refresh1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                chrome1.stopAnim();
                llchrome.setVisibility(View.GONE);
                refresh1.setVisibility(View.VISIBLE);
            }
        });


    }


}
