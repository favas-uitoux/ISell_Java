package com.project.isell_java.pojos.model_order_list;

public class Model {

    private String uid;
    private String itemname;
    private String group;
    private double rate;





    public Model(String uid, String itemname, String group, double rate) {
        this.uid = uid;
        this.itemname = itemname;
        this.group = group;
        this.rate = rate;

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }


}
