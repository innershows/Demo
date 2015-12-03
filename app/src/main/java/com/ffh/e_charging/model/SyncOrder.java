package com.ffh.e_charging.model;

/**
 * Created by innershows on 15/12/3.
 */
public class SyncOrder extends Entity {


    /**
     * status : 5
     * retain : 20
     * csno : EN10101159070001
     * alias : A011
     * type : 1
     * desc : EN+智能充电桩，单项交流，7KW
     * stationName : 驿普乐氏自用A电站
     * addr : 深圳市南山区科技园北朗山路28号
     * startTime : 20151203104639
     * nowTime : 20151203104714
     * chargeEnergy : 0
     * chargeCost : 0
     * parkTimeLen : 0
     * chargeTimeLen : 0
     * lockTimeLen : 120
     * gunStatus : 1
     * lockStatus : 2
     * chargeEnergy:
     */

    private int status;
    private int retain;
    private String csno;
    private String alias;
    private int type;
    private String desc;
    private String stationName;
    private String addr;
    private String startTime;
    private String nowTime;
    private int chargeEnergy;
    private int chargeCost;
    private int parkTimeLen;
    private int chargeTimeLen;
    private int lockTimeLen;
    private int gunStatus;
    private int lockStatus;

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

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }

    public void setChargeEnergy(int chargeEnergy) {
        this.chargeEnergy = chargeEnergy;
    }

    public void setChargeCost(int chargeCost) {
        this.chargeCost = chargeCost;
    }

    public void setParkTimeLen(int parkTimeLen) {
        this.parkTimeLen = parkTimeLen;
    }

    public void setChargeTimeLen(int chargeTimeLen) {
        this.chargeTimeLen = chargeTimeLen;
    }

    public void setLockTimeLen(int lockTimeLen) {
        this.lockTimeLen = lockTimeLen;
    }

    public void setGunStatus(int gunStatus) {
        this.gunStatus = gunStatus;
    }

    public void setLockStatus(int lockStatus) {
        this.lockStatus = lockStatus;
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

    public String getStartTime() {
        return startTime;
    }

    public String getNowTime() {
        return nowTime;
    }

    public int getChargeEnergy() {
        return chargeEnergy;
    }

    public int getChargeCost() {
        return chargeCost;
    }

    public int getParkTimeLen() {
        return parkTimeLen;
    }

    public int getChargeTimeLen() {
        return chargeTimeLen;
    }

    public int getLockTimeLen() {
        return lockTimeLen;
    }

    public int getGunStatus() {
        return gunStatus;
    }

    public int getLockStatus() {
        return lockStatus;
    }
}
