package com.project.isell_java.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.project.isell_java.R;
import com.project.isell_java.activity.ImportActivity;
import com.project.isell_java.activity.NewOrderActivity;

import java.util.List;

public class AdapterDash extends BaseAdapter {


    Context context;
    List<String> list;
    String user_role = "";

    public AdapterDash(Context context, List<String> list, String user_role) {
        this.context = context;
        this.list = list;
        this.user_role = user_role;
    }

    private class ViewHolder {
        CardView card1;
        ImageView iv1;
        TextView txt1;
        ConstraintLayout cl1;


    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView;
        final ViewHolder holder;
        if (view == null) {



      /*      imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);


       */

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.model_adminb_details_style, null);


            holder = new ViewHolder();

            holder.card1 = view.findViewById(R.id.card1);
            holder.iv1 = view.findViewById(R.id.iv1);
            holder.txt1 = view.findViewById(R.id.txt1);
            holder.cl1 = view.findViewById(R.id.cl1);

            view.setTag(holder);
        } else {
            //  imageView = (ImageView) view;

            holder = (ViewHolder) view.getTag();
        }
        //    imageView.setImageResource(mThumbIds[position]);


        final String cpr = list.get(position);
        holder.txt1.setText(cpr);

        if (cpr.equals("New Order")) {
            holder.iv1.setImageResource(R.drawable.new1);
        } else if (cpr.equals("Import")) {
            holder.iv1.setImageResource(R.drawable.import1);
        } else if (cpr.equals("Sync")) {
            holder.iv1.setImageResource(R.drawable.sync);
        }


        holder.cl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.txt1.getText().toString().trim().equals("Import")) {
                    // Toast.makeText(context,"add teacher",Toast.LENGTH_LONG).show();
                    Intent in = new Intent(context, ImportActivity.class);

                    context.startActivity(in);

                }
                else if (holder.txt1.getText().toString().trim().equals("New Order")) {
                    // Toast.makeText(context,"add teacher",Toast.LENGTH_LONG).show();
                    Intent in = new Intent(context, NewOrderActivity.class);
                   // in.putExtra("user_role", user_role);


                    context.startActivity(in);

                }
                //else if (holder.txt1.getText().toString().trim().equals(ChangePassword)) {
//                    // Toast.makeText(context,"add teacher",Toast.LENGTH_LONG).show();
//                    Intent in = new Intent(context, ChangePasswordActivity.class);
//
//                    context.startActivity(in);
//
//                }

            }
        });


        return view;
    }


//    public Integer[] mThumbIds = {
//            R.drawable.ic_teacher,
//            R.drawable.ic_student,
//            R.drawable.ic_report,
//            R.drawable.ic_report_std
//
//
//    };

}
