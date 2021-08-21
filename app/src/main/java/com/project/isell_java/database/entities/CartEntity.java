package com.project.isell_java.database.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart")
public class CartEntity {

    @PrimaryKey(autoGenerate = true)
    private   long id;





    private String itemname;
    private String item_id;
    private String group;

    private double qty;
   private  double sel_rate;
   private  double total;


    public CartEntity(long id, String itemname, String item_id, String group, double qty, double sel_rate, double total) {
        this.id = id;
        this.itemname = itemname;
        this.item_id = item_id;
        this.group = group;
        this.qty = qty;
        this.sel_rate = sel_rate;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getSel_rate() {
        return sel_rate;
    }

    public void setSel_rate(double sel_rate) {
        this.sel_rate = sel_rate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
