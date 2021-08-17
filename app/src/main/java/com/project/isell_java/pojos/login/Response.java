package com.project.isell_java.pojos.login;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private String code;

	@SerializedName("data")
	private Data data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}