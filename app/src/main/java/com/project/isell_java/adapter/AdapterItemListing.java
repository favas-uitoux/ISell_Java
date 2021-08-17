package com.project.isell_java.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.project.isell_java.R;
import com.project.isell_java.database.entities.InvEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AdapterItemListing extends RecyclerView.Adapter<AdapterItemListing.ViewHolderClass> {


    Context context;
    List<InvEntity> list;

    public AdapterItemListing(Context context, List<InvEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_item, parent, false);
        //   view.findViewById(R.id.txt1).setMinimumHeight(560);
        // view.setMinimumHeight(160);
        //   View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {


        final InvEntity cpr = list.get(position);











        holder.txt1.setText(cpr.getName());







        //  holder.txtold.setBackgroundResource(R.color.color1);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolderClass extends RecyclerView.ViewHolder {



        TextView txt1;



        public ViewHolderClass(View itemView) {
            super(itemView);


            txt1 = itemView.findViewById(R.id.txt1);






        }
    }




}
