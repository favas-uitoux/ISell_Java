package com.project.isell_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.isell_java.R;
import com.project.isell_java.database.appdb.Appdb;
import com.project.isell_java.database.entities.CartEntity;
import com.project.isell_java.pojos.order_not_uploaded.Model;


import java.util.List;

public class AdapterNotuploadedOrder extends RecyclerView.Adapter<AdapterNotuploadedOrder.ViewHolderClass> {



    Context context;
    List<com.project.isell_java.pojos.order_not_uploaded.Model> list;


    //  ViewHolderClass holder2;


    LinearLayout lll1;
    String date;


    public AdapterNotuploadedOrder(Context context, List<com.project.isell_java.pojos.order_not_uploaded.Model> list) {

        this.context = context;
        this.list = list;



    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;

        view = inflater.inflate(R.layout.model_not_uploaded, parent, false);


        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {


        final Model cpr = list.get(position);

        holder.txtname.setText("Order No:" + cpr.getOno()+"   Total:"+cpr.getTot()+"/-");








    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    public class ViewHolderClass extends RecyclerView.ViewHolder {


        TextView txtname;


        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);



            txtname = itemView.findViewById(R.id.txtname);


        }
    }


}
