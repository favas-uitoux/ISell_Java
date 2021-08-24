package com.project.isell_java.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.isell_java.BasicActivity;
import com.project.isell_java.R;
import com.project.isell_java.adapter.AdapterCart;
import com.project.isell_java.adapter.AdapterNewOrder;
import com.project.isell_java.database.appdb.Appdb;
import com.project.isell_java.database.entities.CartEntity;
import com.project.isell_java.database.entities.ChartcodeEntity;
import com.project.isell_java.database.entities.InvEntity;
import com.project.isell_java.database.entities.OrderDetailsEntity;
import com.project.isell_java.pojos.model_order_list.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewOrderActivity extends BasicActivity {

    private List<InvEntity> list_inv = new ArrayList<>();
    private List<Model> list = new ArrayList<>();
    private br.com.simplepass.loadingbutton.customViews.CircularProgressButton btn_submit;
    private RecyclerView recv1;
    private Appdb db;
    private AdapterNewOrder adp;
    private EditText edt_ser;

    private AdapterCart adp_cart_confirm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_new_order);


        init();
        //  db.getCartEntityDao().del_all();
        show_items(1);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (db.getCartEntityDao().get_count() > 0) {
                    show_popup();
                }


            }
        });


        edt_ser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                recv1.setAdapter(null);
                List<InvEntity> list_ser = db.getInvEntityDao().get_all_similar_datas("%" + s + "%");

                list = new ArrayList<>();
                for (InvEntity row : list_ser) {

                    double rate = row.getRate1();


                    list.add(new Model(row.getUid(), row.getName(), row.getGroup(), rate));
                }


                show_items(2);


            }
        });

    }


    private void init() {
        db = Appdb.getDb_instance(getApplicationContext());
        db.getCartEntityDao().del_all();
        btn_submit = findViewById(R.id.btn_submit);
        recv1 = findViewById(R.id.recv1);
        edt_ser = findViewById(R.id.edt_ser);


    }

    private void show_items(int type) {

        if (type == 1) {
            list_inv.addAll(db.getInvEntityDao().get_all_datas());
            for (InvEntity row : list_inv) {


                //chk price level and save price level in db
                if()

                double rate = row.getRate1();




                list.add(new Model(row.getUid(), row.getName(), row.getGroup(), rate));
            }
        }

        if (list.size() > 0) {

            adp = new AdapterNewOrder(getApplicationContext(), list);
            LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            recv1.setLayoutManager(lm);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recv1.getContext(), lm.getOrientation());
            recv1.addItemDecoration(dividerItemDecoration);


            recv1.setAdapter(adp);


            LayoutAnimationController anim_cont = AnimationUtils.loadLayoutAnimation(NewOrderActivity.this, R.anim.layout_animation_from_bottom_slow);

            recv1.setLayoutAnimation(anim_cont);
            adp.notifyDataSetChanged();
            recv1.scheduleLayoutAnimation();
        }


    }

    private void show_popup() {
        final Dialog dialog = new Dialog(NewOrderActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.cust_dialog2);
        dialog.setCancelable(true);
        dialog.show();

        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);


        RecyclerView recv1 = dialog.findViewById(R.id.recv1);
        Button btn_cnfm = dialog.findViewById(R.id.btn_cnfm);

        List<CartEntity> list_confirm = new ArrayList<>();
        list_confirm.addAll(db.getCartEntityDao().get_all_datas());

        adp_cart_confirm=new AdapterCart(getApplicationContext(),list_confirm);

        LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recv1.setLayoutManager(lm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recv1.getContext(), lm.getOrientation());
        recv1.addItemDecoration(dividerItemDecoration);


        recv1.setAdapter(adp_cart_confirm);


        LayoutAnimationController anim_cont = AnimationUtils.loadLayoutAnimation(NewOrderActivity.this, R.anim.layout_animation_from_bottom_slow);

        recv1.setLayoutAnimation(anim_cont);
        adp_cart_confirm.notifyDataSetChanged();
        recv1.scheduleLayoutAnimation();

        btn_cnfm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // insert cart into order details

                if( isTimeAutomatic(getApplicationContext()))
                {

                    //order date
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String oDate = df.format(c.getTime());


                    //order no
                    List<ChartcodeEntity> list_order_series_details=   db.getChartcodeEntityDao().get_series_details();
                    String series= list_order_series_details.get(0).getVal1();
                    int no=list_order_series_details.get(0).getVal3();
                    String order_no=series +"-"+no;

                    String client_id=""+db.getChartcodeEntityDao().get_stored_client_id();
                    String distro_id=""+db.getChartcodeEntityDao().get_stored_distro_id();


                    for(CartEntity row:list_confirm)
                    {

                        row.getItem_id();
                        row.getItemname();

                        row.getQty();
                        row.getSel_rate();

                        // calculate total qty*rate

                    }



                 //   showSnack_W(series +"-"+no);
                    Toast.makeText(getApplicationContext(),""+series +"-"+no,Toast.LENGTH_LONG).show();







                }
                else
                {
                    showSnack_W("Please enable automatic date");
                }



            }
        });


  //      List<OrderDetailsEntity> list_orders=new ArrayList<>();

//        for (CartEntity row : list)
//        {
//            list_orders.add(new OrderDetailsEntity(0,))
//        }
//        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
//        Toast.makeText(getApplicationContext(),currentDateTimeString,Toast.LENGTH_LONG).show();

//
//
//
//
//
//        // formattedDate have current date/time
//        Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();


    }


}
