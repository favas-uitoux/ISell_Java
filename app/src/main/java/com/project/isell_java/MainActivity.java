package com.project.isell_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.isell_java.pojos.read_data.InventoriesItem;
import com.project.isell_java.pojos.read_data.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BasicActivity {

    private RecyclerView rcv1;
    private List<InventoriesItem> list_inv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        show_inventory();




    }

    private void init()
    {
        rcv1=findViewById(R.id.rcv1);

    }

    private void show_inventory()
    {
        //read  re

        try {
            String jsonString=      getJsonFromAssets(getApplicationContext(),"data.json");

            Response pojo =new Response() ;
            Gson gson = new Gson();
            pojo= gson.fromJson(jsonString,Response.class);

            list_inv = new ArrayList<>();
            list_inv.addAll(pojo.getData().getInventories());

            Toast.makeText(getApplicationContext(),""+list_inv.size(),Toast.LENGTH_LONG).show();


        }
        catch(Exception e)
        {

        }


    }




}