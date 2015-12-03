package com.ffh.e_charging.model;

/**
 * Created by innershows on 15/12/3.
 */
public class ErrorMsg extends Entity {


    /**
     * errorCode : 1400
     * errorMsg : 请求的数据不存在
     */

    private int errorCode;
    private String errorMsg;

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
