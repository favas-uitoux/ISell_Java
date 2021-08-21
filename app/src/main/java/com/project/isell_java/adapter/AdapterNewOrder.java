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

public class AdapterNewOrder extends RecyclerView.Adapter<AdapterNewOrder.ViewHolderClass> {


    private Appdb db;
    Context context;
    List<Model> list;


    //  ViewHolderClass holder2;


    LinearLayout lll1;
    String date;


    public AdapterNewOrder(Context context, List<Model> list) {

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

        view = inflater.inflate(R.layout.model_new_order, parent, false);


        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {


        final Model cpr = list.get(position);

        if (db.getCartEntityDao().get_count_of_item(cpr.getUid()) > 0) {
            holder.edt_qty.setText(""+db.getCartEntityDao().get_qty_of_item(cpr.getUid()));
            holder.edt_qty.setEnabled(false);


            holder.txtname.setBackgroundResource(R.color.green);
            holder.chk1.setChecked(true);

        } else {
            holder.edt_qty.setEnabled(true);
            holder.edt_qty.setText("");
            holder.txtname.setBackgroundResource(R.color.blue);
            holder.chk1.setChecked(false);

        }


        holder.txtname.setText("" + cpr.getItemname());




        holder.chk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (holder.chk1.isChecked()) {







                    if (!holder.edt_qty.getText().toString().trim().equals("")) {
                        double qty = 0, total = 0;
                        try {
                            qty = Integer.parseInt(holder.edt_qty.getText().toString().trim());

                            total = qty * cpr.getRate();

                        } catch (Exception e) {
                            qty = 0;
                        }

                        if (qty > 0) {
                            db.getCartEntityDao().insert_cart_item(new CartEntity(0, cpr.getItemname(), cpr.getUid(), cpr.getGroup(), qty, cpr.getRate(), total));

                            notifyDataSetChanged();

                        } else {
                            holder.chk1.setChecked(false);
                            Toast.makeText(context, "please enter valuable quantity", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        holder.chk1.setChecked(false);
                        Toast.makeText(context, "please enter quantity", Toast.LENGTH_SHORT).show();
                    }



                } else {

                    db.getCartEntityDao().del_one(cpr.getUid());
                    notifyDataSetChanged();

                }


            }
        });


//        holder.edtstudied_juza_page.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    EditText et = (EditText) v.findViewById(R.id.edtstudied_juza_page);
//                  //      cpr.setNew_juza_page_no(et.getText().toString().trim());
//                   list_formated.get(position).setNew_juza_page_no(et.getText().toString().trim());
//                   // check_changes();
//         //           list_formated.get(position).s
//
//                    try
//                    {
//                        notifyDataSetChanged();
//                    }
//                    catch (Exception e)
//                    {
//
//                    }
//
//                }
//            }
//        });
//


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


//    public List<model_juza_detail_formated> get_data_tosend() {
//
//
//        holder2.edt_rough.setText(" ");
//        holder2.edt_rough.requestFocus();
//        holder2.edt_rough.setSelected(true);
//
//        holder2.edtstudied_juza_page.requestFocus();
//        // ll1.clearFocus();
//
//        //   edtr.setText("kgjgj");
//        //   Toast.makeText(context,"Bismillah....",Toast.LENGTH_LONG).show();
//
//        return list_formated;
//    }


    public class ViewHolderClass extends RecyclerView.ViewHolder {


        TextView txtname;
        EditText edt_qty;
        CheckBox chk1;


        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);


            //ll1 = itemView.findViewById(R.id.ll1);
            txtname = itemView.findViewById(R.id.txtname);
            chk1 = itemView.findViewById(R.id.chk1);
            edt_qty = itemView.findViewById(R.id.edt_qty);

//            txtjuzapage = itemView.findViewById(R.id.txtjuzapage);
//
//
//            edtstudied_juza_page = itemView.findViewById(R.id.edtstudied_juza_page);
//
//            edt_rough=itemView.findViewById(R.id.edtrough);
        }
    }


}
