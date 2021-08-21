package com.project.isell_java.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.isell_java.BasicActivity;
import com.project.isell_java.R;
import com.project.isell_java.adapter.AdapterNewOrder;
import com.project.isell_java.database.appdb.Appdb;
import com.project.isell_java.database.entities.InvEntity;
import com.project.isell_java.pojos.model_order_list.Model;

import java.util.ArrayList;
import java.util.List;

public class NewOrderActivity extends BasicActivity {

    private List<InvEntity> list_inv = new ArrayList<>();
    private List<Model> list = new ArrayList<>();
    private Button btn_submit;
    private RecyclerView recv1;
    private Appdb db;
    private AdapterNewOrder adp;
    private EditText edt_ser;


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

        if (type==1) {
            list_inv.addAll(db.getInvEntityDao().get_all_datas());
            for (InvEntity row : list_inv) {
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

}
