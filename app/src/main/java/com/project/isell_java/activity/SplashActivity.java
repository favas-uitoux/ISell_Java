package com.project.isell_java.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import androidx.core.app.ActivityCompat;

import com.project.isell_java.BasicActivity;
import com.project.isell_java.R;

import static maes.tech.intentanim.CustomIntent.customType;


public class SplashActivity extends BasicActivity {


    String action = "";
  //  private Appdb db;

    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




      //  db = Appdb.getDb_instance(getApplicationContext());





        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override

            public void run() {

                check_perm();

            }

        }, 4000);


    }


    public void check_perm() {


        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) + ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) + ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            //     showSnack_W("app does not have permission now");


            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(
                    SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(
                    SplashActivity.this, Manifest.permission.RECORD_AUDIO)) {


                Request_Perm();
            } else {

                Request_Perm();

            }


        } else {

            goto_next_screen();

//            }

        }


    }


    public void Request_Perm() {


        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setCancelable(false);
        builder.setMessage(" Read and Write External" +
                " Storage permissions are required to do the task.");
        builder.setTitle("Please grant these permissions");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCompat.requestPermissions(
                        SplashActivity.this,
                        new String[]{


                                Manifest.permission.WRITE_EXTERNAL_STORAGE,

                                Manifest.permission.READ_EXTERNAL_STORAGE,

                                Manifest.permission.RECORD_AUDIO

                        },
                        MY_PERMISSIONS_REQUEST_CODE
                );
            }
        });


        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // lmain.setVisibility(View.GONE);
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CODE: {
                // When request is cancelled, the results array are empty
                if (
                        (grantResults.length > 0) &&
                                (grantResults[0] + grantResults[1] + grantResults[2] == PackageManager.PERMISSION_GRANTED)
                ) {
                    // Permissions are granted

                    showSnack_W(getString(R.string.permission_granted));
                    goto_next_screen();
                } else {

                    //lmain.setVisibility(View.GONE);
                    // Permissions are denied
                    showSnack_W(getString(R.string.permission_denied));
                }
                return;
            }
        }
    }


    private void goto_next_screen() {


            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            customType(SplashActivity.this, "fadein-to-fadeout");
            finish();



    }

}


