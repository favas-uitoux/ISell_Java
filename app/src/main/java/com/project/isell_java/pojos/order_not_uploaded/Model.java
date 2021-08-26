package com.project.isell_java.pojos.order_not_uploaded;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model implements Serializable {


    @SerializedName("ono")
    private String ono;

    @SerializedName("tot")
    private double tot;

    public Model(String ono, double tot) {
        this.ono = ono;
        this.tot = tot;
    }


    public String getOno() {
        return ono;
    }

    public void setOno(String ono) {
        this.ono = ono;
    }

    public double getTot() {
        return tot;
    }

    public void setTot(double tot) {
        this.tot = tot;
    }
}
