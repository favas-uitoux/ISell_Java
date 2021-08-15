package com.project.isell_java.database.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "inv",indices = {@Index(value = {"uid"},
        unique = true)})
public class InvEntity {

    @PrimaryKey(autoGenerate = true)
    private   long id;



    @ColumnInfo(name = "uid")
   private String  uid;


    private String name;
    private String hsn;
    private String group;

   private  double rate1;
   private  double rate2;
   private  double rate3;
   private  double rate4;

   private int plevel;


    public InvEntity(long id, String uid, String name, String hsn, String group, double rate1, double rate2, double rate3, double rate4,int plevel) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.hsn = hsn;
        this.group = group;
        this.rate1 = rate1;
        this.rate2 = rate2;
        this.rate3 = rate3;
        this.rate4 = rate4;
        this.plevel=plevel;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHsn() {
        return hsn;
    }

    public void setHsn(String hsn) {
        this.hsn = hsn;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public double getRate1() {
        return rate1;
    }

    public double getRate2() {
        return rate2;
    }

    public double getRate3() {
        return rate3;
    }

    public double getRate4() {
        return rate4;
    }

    public int getPlevel() {
        return plevel;
    }
}
