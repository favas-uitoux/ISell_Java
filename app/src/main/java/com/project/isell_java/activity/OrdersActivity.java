package com.project.isell_java.activity;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.isell_java.R;
import com.project.isell_java.adapter.AdapterNotuploadedOrder;
import com.project.isell_java.database.appdb.Appdb;
import com.project.isell_java.database.entities.OrderDetailsEntity;
import com.project.isell_java.pojos.order_not_uploaded.Model;

import java.util.List;

public class OrdersActivity  extends AppCompatActivity {

    private RecyclerView recv;

    private Appdb db;
    private AdapterNotuploadedOrder adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        init();
        show_orders();





    }


    private void init()
    {
        recv=findViewById(R.id.recv);
        db = Appdb.getDb_instance(getApplicationContext());
    }

    private void show_orders()
    {

      List<Model> list=  db.getOrderDetailsEntityDao().get_summary();

        adp = new AdapterNotuploadedOrder(getApplicationContext(), list);
        LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recv.setLayoutManager(lm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recv.getContext(), lm.getOrientation());
        recv.addItemDecoration(dividerItemDecoration);


        recv.setAdapter(adp);


        LayoutAnimationController anim_cont = AnimationUtils.loadLayoutAnimation(OrdersActivity.this, R.anim.layout_animation_from_bottom_slow);

        recv.setLayoutAnimation(anim_cont);
        adp.notifyDataSetChanged();
        recv.scheduleLayoutAnimation();







    }




}
