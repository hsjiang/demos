package com.rivent_chris.proguardtest.Model;

import java.io.Serializable;

/**
 * Created by riven_chris on 2017/8/22.
 */

public class Data implements Serializable{
    private int code;
    private String msg;
    private String data;

    public Data(int code, String msg) {
        this.code = code;
//        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
