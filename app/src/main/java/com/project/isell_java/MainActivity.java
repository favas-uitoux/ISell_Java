package com.project.isell_java;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.isell_java.pojos.read_data.InventoriesItem;
import com.project.isell_java.pojos.read_data.Response;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BasicActivity {

    private Button btn_import;
    private List<InventoriesItem> list_inv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        btn_import.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//                Uri uri = Uri.parse("http://www.milantex.in/test/data.json");
//                DownloadManager.Request request = new DownloadManager.Request(uri);
//                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
//                long reference = manager.enqueue(request);


                try {
                    String url = "http://www.milantex.in/test/data.json";
                    String file = "/downloads/data.json";






                }
                catch ( Exception e)
                {
                    Toast.makeText(getApplicationContext(),"catch error,",Toast.LENGTH_SHORT).show();
                }


            }
        });








    }



    private void init()
    {
        btn_import=findViewById(R.id.btn_import);

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