package com.ffh.e_charging.model;

/**
 * Created by innershows on 15/12/2.
 */
public class ChargeList extends Entity {


    /**
     * stationId : 332
     * chargerName : K001
     * status : 2
     * type : 1
     * chargerId : EN1020115B060100
     */


    private int stationId;
    private String chargerName;
    private int status;
    private int type;
    private String chargerId;

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public void setChargerName(String chargerName) {
        this.chargerName = chargerName;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setChargerId(String chargerId) {
        this.chargerId = chargerId;
    }

    public int getStationId() {
        return stationId;
    }

    public String getChargerName() {
        return chargerName;
    }

    public int getStatus() {
        return status;
    }

    public int getType() {
        return type;
    }

    public String getChargerId() {
        return chargerId;
    }
}
