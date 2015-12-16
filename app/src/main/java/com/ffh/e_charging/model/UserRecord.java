package com.ffh.e_charging.model;

import java.util.List;

/**
 * Created by innershows on 15/12/15.
 */
public class UserRecord extends Entity {


    /**
     * hasNext : false
     * hasPrevious : false
     * page : 1
     * totalPages : 1
     * content : [{"id":19,"uuid":"1001020151202700221","startTime":"20151202120951","endTime":"20151202123549","allTimeLen":0,"stationName":"驿普乐氏自用A电站","serviceType":0,"allCost":0,"chargerName":"A011","chargeTimeLen":0,"chargeEnergy":0,"chargeCost":0,"parkTimeLen":0,"parkCost":0},{"id":21,"uuid":"1001020151202754482","startTime":"20151202143133","endTime":"20151202145349","allTimeLen":0,"stationName":"驿普乐氏自用A电站","serviceType":0,"allCost":0,"chargerName":"A011","chargeTimeLen":0,"chargeEnergy":0,"chargeCost":0,"parkTimeLen":0,"parkCost":0},{"id":23,"uuid":"1001020151202218754","startTime":"20151202153746","endTime":"20151202153757","allTimeLen":0,"stationName":"驿普乐氏自用A电站","serviceType":1,"allCost":0,"chargerName":"A011","chargeTimeLen":0,"chargeEnergy":0,"chargeCost":0,"parkTimeLen":0,"parkCost":0},{"id":29,"uuid":"1001020151203425951","startTime":"20151203101419","endTime":"20151203101612","allTimeLen":0,"stationName":"通产新材产业园北区停车场充电站","serviceType":0,"allCost":0,"chargerName":null,"chargeTimeLen":0,"chargeEnergy":0,"chargeCost":0,"parkTimeLen":0,"parkCost":0},{"id":30,"uuid":"1001020151203458962","startTime":"20151203103910","endTime":"20151203104625","allTimeLen":0,"stationName":"通产新材产业园北区停车场充电站","serviceType":0,"allCost":0,"chargerName":null,"chargeTimeLen":0,"chargeEnergy":0,"chargeCost":0,"parkTimeLen":0,"parkCost":0},{"id":31,"uuid":"1001020151203498313","startTime":"20151203104451","endTime":"20151203104749","allTimeLen":0,"stationName":"通产新材产业园北区停车场充电站","serviceType":0,"allCost":0,"chargerName":null,"chargeTimeLen":0,"chargeEnergy":0,"chargeCost":0,"parkTimeLen":0,"parkCost":0},{"id":32,"uuid":"1001020151203477424","startTime":"20151203104639","endTime":"20151203110146","allTimeLen":0,"stationName":"驿普乐氏自用A电站","serviceType":0,"allCost":0,"chargerName":"A011","chargeTimeLen":0,"chargeEnergy":0,"chargeCost":0,"parkTimeLen":0,"parkCost":0},{"id":33,"uuid":"1001020151203795685","startTime":"20151203110232","endTime":"20151203112451","allTimeLen":0,"stationName":"驿普乐氏自用A电站","serviceType":0,"allCost":0,"chargerName":"A011","chargeTimeLen":0,"chargeEnergy":0,"chargeCost":0,"parkTimeLen":0,"parkCost":0},{"id":34,"uuid":"1001020151203319156","startTime":"20151203112948","endTime":"20151203113149","allTimeLen":0,"stationName":"通产新材产业园北区停车场充电站","serviceType":0,"allCost":0,"chargerName":null,"chargeTimeLen":0,"chargeEnergy":0,"chargeCost":0,"parkTimeLen":0,"parkCost":0},{"id":35,"uuid":"1001020151203334797","startTime":"20151203113002","endTime":"20151203113159","allTimeLen":0,"stationName":"驿普乐氏自用A电站","serviceType":0,"allCost":0,"chargerName":"A011","chargeTimeLen":0,"chargeEnergy":0,"chargeCost":0,"parkTimeLen":0,"parkCost":0},{"id":36,"uuid":"1001020151203160468","startTime":"20151203113140","endTime":"20151203114013","allTimeLen":0,"stationName":"通产新材产业园北区停车场充电站","serviceType":0,"allCost":0,"chargerName":null,"chargeTimeLen":0,"chargeEnergy":0,"chargeCost":0,"parkTimeLen":0,"parkCost":0},{"id":37,"uuid":"1001020151203566741","startTime":"20151203143815","endTime":"20151203144018","allTimeLen":0,"stationName":"通产新材产业园北区停车场充电站","serviceType":0,"allCost":0,"chargerName":null,"chargeTimeLen":0,"chargeEnergy":0,"chargeCost":0,"parkTimeLen":0,"parkCost":0},{"id":38,"uuid":"1001020151203751332","startTime":"20151203151926","endTime":"20151203152141","allTimeLen":0,"stationName":"通产新材产业园北区停车场充电站","serviceType":0,"allCost":0,"chargerName":null,"chargeTimeLen":0,"chargeEnergy":0,"chargeCost":0,"parkTimeLen":0,"parkCost":0}]
     */

    private boolean hasNext;
    private boolean hasPrevious;
    private int page;
    private int totalPages;
    /**
     * id : 19
     * uuid : 1001020151202700221
     * startTime : 20151202120951
     * endTime : 20151202123549
     * allTimeLen : 0
     * stationName : 驿普乐氏自用A电站
     * serviceType : 0
     * allCost : 0
     * chargerName : A011
     * chargeTimeLen : 0
     * chargeEnergy : 0
     * chargeCost : 0
     * parkTimeLen : 0
     * parkCost : 0
     */

    private List<ContentEntity> content;

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setContent(List<ContentEntity> content) {
        this.content = content;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<ContentEntity> getContent() {
        return content;
    }

    public static class ContentEntity extends Entity {
        private int id;
        private String uuid;
        private String startTime;
        private String endTime;
        private int allTimeLen;
        private String stationName;
        private int serviceType;
        private int allCost;
        private String chargerName;
        private int chargeTimeLen;
        private int chargeEnergy;
        private int chargeCost;
        private int parkTimeLen;
        private int parkCost;

        public void setId(int id) {
            this.id = id;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public void setAllTimeLen(int allTimeLen) {
            this.allTimeLen = allTimeLen;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public void setServiceType(int serviceType) {
            this.serviceType = serviceType;
        }

        public void setAllCost(int allCost) {
            this.allCost = allCost;
        }

        public void setChargerName(String chargerName) {
            this.chargerName = chargerName;
        }

        public void setChargeTimeLen(int chargeTimeLen) {
            this.chargeTimeLen = chargeTimeLen;
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

        public void setParkCost(int parkCost) {
            this.parkCost = parkCost;
        }

        public int getId() {
            return id;
        }

        public String getUuid() {
            return uuid;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public int getAllTimeLen() {
            return allTimeLen;
        }

        public String getStationName() {
            return stationName;
        }

        public int getServiceType() {
            return serviceType;
        }

        public int getAllCost() {
            return allCost;
        }

        public String getChargerName() {
            return chargerName;
        }

        public int getChargeTimeLen() {
            return chargeTimeLen;
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

        public int getParkCost() {
            return parkCost;
        }
    }
}
