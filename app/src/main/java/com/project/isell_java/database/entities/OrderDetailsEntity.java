package com.project.isell_java.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "order_details")
public class OrderDetailsEntity {

    @PrimaryKey(autoGenerate = true)
    private   long id;





    private String odate;
    private String ono;

    private String cid;
    private String did;

    private  String iid;
   private  String inam ;

   private double qty;
   private double total;


    public OrderDetailsEntity(long id, String odate, String ono, String cid, String did, String iid, String inam, double qty, double total) {
        this.id = id;
        this.odate = odate;
        this.ono = ono;
        this.cid = cid;
        this.did = did;
        this.iid = iid;
        this.inam = inam;
        this.qty = qty;
        this.total = total;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    public String getOno() {
        return ono;
    }

    public void setOno(String ono) {
        this.ono = ono;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getInam() {
        return inam;
    }

    public void setInam(String inam) {
        this.inam = inam;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
