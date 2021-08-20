package com.project.isell_java.activity;

import android.os.Bundle;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.project.isell_java.R;
import com.project.isell_java.adapter.AdapterDash;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private GridView gridview;
    private List<String> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);


        init();


    }

    private void init()
    {
        gridview=findViewById(R.id.gridview);

        list = new ArrayList<String>();
        list.add("New Order");
        list.add("Import");
        list.add("Sync");
        gridview.setAdapter(new AdapterDash(this, list, "Client"));


    }

}
