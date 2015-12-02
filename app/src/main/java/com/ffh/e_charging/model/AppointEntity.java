package com.ffh.e_charging.model;

/**
 * 预约成功之后 返回的实体
 * Created by innershows on 15/12/2.
 */
public class AppointEntity extends Entity {

    /**
     * status : 2
     * retain : 20
     * csno : EN10101159070001
     * alias : A011
     * type : 1
     * desc : EN+智能充电桩，单项交流，7KW
     * stationName : 驿普乐氏自用A电站
     * addr : 深圳市南山区科技园北朗山路28号
     */

    private int status;
    private int retain;
    private String csno;
    private String alias;
    private int type;
    private String desc;
    private String stationName;
    private String addr;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setRetain(int retain) {
        this.retain = retain;
    }

    public void setCsno(String csno) {
        this.csno = csno;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getStatus() {
        return status;
    }

    public int getRetain() {
        return retain;
    }

    public String getCsno() {
        return csno;
    }

    public String getAlias() {
        return alias;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getStationName() {
        return stationName;
    }

    public String getAddr() {
        return addr;
    }
}
