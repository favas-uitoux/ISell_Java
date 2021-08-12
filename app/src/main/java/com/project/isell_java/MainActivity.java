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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;
import com.google.gson.Gson;
import com.project.isell_java.pojos.read_data.InventoriesItem;
import com.project.isell_java.pojos.read_data.Response;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BasicActivity {

    private Button btn_import;
    private List<InventoriesItem> list_inv;

    ProgressBar progressBarOne;
    private TextView textViewProgressOne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        btn_import.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {




                PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                        .setReadTimeout(30_000)
                        .setConnectTimeout(30_000)
                        .build();
                PRDownloader.initialize(getApplicationContext(), config);

                try {
                    String url = "http://www.milantex.in/test/data.json";
                    String file = "/downloads/data.json";

                    String fileName="1.json";
                    String dirPath=getApplicationContext().getFilesDir().getAbsolutePath();


                    int downloadId = PRDownloader.download(url, dirPath, fileName)
                            .build()
                            .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                                @Override
                                public void onStartOrResume() {

                                }
                            })
                            .setOnPauseListener(new OnPauseListener() {
                                @Override
                                public void onPause() {

                                }
                            })
                            .setOnCancelListener(new OnCancelListener() {
                                @Override
                                public void onCancel() {

                                }
                            })
                            .setOnProgressListener(new OnProgressListener() {
                                @Override
                                public void onProgress(Progress progress) {

                                    long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                    progressBarOne.setProgress((int) progressPercent);
                                    textViewProgressOne.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                                    progressBarOne.setIndeterminate(false);

                                }
                            })
                            .start(new OnDownloadListener() {
                                @Override
                                public void onDownloadComplete() {

                                }

                                @Override
                                public void onError(Error error) {

                                }


                            });





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
        progressBarOne=findViewById(R.id.progressBarOne);
        textViewProgressOne=findViewById(R.id.textViewProgressOne);

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