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
import com.project.isell_java.pojos.model_order_list.Model;

import java.util.List;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolderClass> {


    private Appdb db;
    Context context;
    List<CartEntity> list;


    //  ViewHolderClass holder2;


    LinearLayout lll1;
    String date;


    public AdapterCart(Context context, List<CartEntity> list) {

        this.context = context;
        this.list = list;
        this.date = date;
        db = Appdb.getDb_instance(context);

    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;

        view = inflater.inflate(R.layout.model_cart_confirm, parent, false);


        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {


        final CartEntity cpr = list.get(position);
//
//        if (db.getCartEntityDao().get_count_of_item(cpr.getUid()) > 0) {
//            holder.edt_qty.setText(""+db.getCartEntityDao().get_qty_of_item(cpr.getUid()));
//            holder.edt_qty.setEnabled(false);
//
//
//            holder.txtname.setBackgroundResource(R.color.green);
//            holder.chk1.setChecked(true);
//
//        } else {
//            holder.edt_qty.setEnabled(true);
//            holder.edt_qty.setText("");
//            holder.txtname.setBackgroundResource(R.color.blue);
//            holder.chk1.setChecked(false);
//
//        }


        holder.txtname.setText("" + cpr.getItemname());
        holder.txtqty.setText("" + cpr.getQty());








    }

    @Override
    public int getItemCount() {
        return list.size();
    }





    public class ViewHolderClass extends RecyclerView.ViewHolder {


        TextView txtname,txtqty;



        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);



            txtname = itemView.findViewById(R.id.txtname);
            txtqty = itemView.findViewById(R.id.txtqty);



        }
    }


}
