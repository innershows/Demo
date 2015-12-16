package com.ffh.e_charging.model;

/**
 * Created by innershows on 15/12/3.
 */
public class ErrorMsg2 extends Entity {


    /**
     * code : 1400
     * data : 请求的数据不存在
     */

    private int code;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
