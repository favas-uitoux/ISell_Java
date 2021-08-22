package com.project.isell_java.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chartcode")
public class ChartcodeEntity {

    @PrimaryKey(autoGenerate = true)
    private   long id;





    private String type;
    private String val1;
    private String val2;

    private int val3;




    public ChartcodeEntity(long id, String type, String val1, String val2, int val3) {
        this.id = id;
        this.type = type;
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVal1() {
        return val1;
    }

    public void setVal1(String val1) {
        this.val1 = val1;
    }

    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }

    public int getVal3() {
        return val3;
    }

    public void setVal3(int val3) {
        this.val3 = val3;
    }



}
