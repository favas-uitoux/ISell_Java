package com.project.isell_java.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.project.isell_java.BasicActivity;
import com.project.isell_java.R;
import com.project.isell_java.adapter.AdapterDash;
import com.project.isell_java.database.appdb.Appdb;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends BasicActivity {

    private GridView gridview;
    private List<String> list;
    private Appdb db;
    private TextView txt_clientid,txt_distroid;
    private com.google.android.material.floatingactionbutton.FloatingActionButton fab1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);


        init();


        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.getChartcodeEntityDao().del_token();

                finish();

                
                
            }
        });

    }

    private void init()
    {

        db = Appdb.getDb_instance(getApplicationContext());
        fab1=findViewById(R.id.fab1);
        gridview=findViewById(R.id.gridview);
        txt_clientid=findViewById(R.id.txt_clientid);
        txt_distroid=findViewById(R.id.txt_distroid);

        list = new ArrayList<String>();
        list.add("New Order");
        list.add("Import");
        list.add("Sync");
        gridview.setAdapter(new AdapterDash(this, list, "Client"));


        txt_clientid.setText(""+db.getChartcodeEntityDao().get_stored_client_id());
        txt_distroid.setText(""+db.getChartcodeEntityDao().get_stored_distro_id());




    }

}
