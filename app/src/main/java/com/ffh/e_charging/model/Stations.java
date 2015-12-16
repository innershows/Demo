package com.ffh.e_charging.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by innershows on 15/11/30.
 */
public class Stations extends Entity {

    /**
     * hasNext : false
     * hasPrevious : false
     * page : 1
     * totalPages : 1
     * content : [{"stationId":332,"stationName":"北京展会演示充电站","lonLat":"116.4498770000,39.9679770000","stationDist":0,"addr":"中国国际展览中心","stationPhone":null,"stationType":0,"rapidChargers":0,"slowChargers":0,"stationImg":null},{"stationId":304,"stationName":"驿普乐氏自用A电站","lonLat":"113.9423800000,22.5629200000","stationDist":0,"addr":"深圳市南山区科技园北朗山路28号","stationPhone":null,"stationType":3,"rapidChargers":0,"slowChargers":2,"stationImg":null},{"stationId":305,"stationName":"深圳驿普乐氏自用B电站","lonLat":"113.9423800000,22.5629200000","stationDist":0,"addr":"深圳市南山区科技园北朗山路28号","stationPhone":null,"stationType":3,"rapidChargers":0,"slowChargers":0,"stationImg":null},{"stationId":306,"stationName":"深圳驿普乐氏公司自用C电站","lonLat":"113.9423800000,22.5629200000","stationDist":0,"addr":"深圳市南山区科技园北朗山路28号","stationPhone":null,"stationType":3,"rapidChargers":0,"slowChargers":0,"stationImg":null},{"stationId":334,"stationName":"通产新材产业园北区停车场充电站","lonLat":"113.9425640000,22.5635940000","stationDist":0,"addr":"深圳市南山区科技园北朗山2号路","stationPhone":null,"stationType":0,"rapidChargers":0,"slowChargers":3,"stationImg":null},{"stationId":339,"stationName":"功能测试电站","lonLat":"120.532386,31.281615","stationDist":0,"addr":"苏州新区玉山路","stationPhone":null,"stationType":0,"rapidChargers":0,"slowChargers":0,"stationImg":null},{"stationId":333,"stationName":"E-Charging测试电站","lonLat":"120.538939,31.287235","stationDist":0,"addr":null,"stationPhone":null,"stationType":0,"rapidChargers":0,"slowChargers":0,"stationImg":null},{"stationId":338,"stationName":"测试电站创建","lonLat":"120.540934,31.28901","stationDist":0,"addr":"玉山路嘉业阳光假日","stationPhone":null,"stationType":0,"rapidChargers":0,"slowChargers":0,"stationImg":null},{"stationId":308,"stationName":"易充电模拟电站GPRS-1","lonLat":"120.551082,31.290727","stationDist":0,"addr":"江苏省苏州市新区玉山路162号（水上世界）","stationPhone":null,"stationType":3,"rapidChargers":0,"slowChargers":0,"stationImg":null},{"stationId":307,"stationName":"易充电模拟电站1","lonLat":"120.551082,31.290727","stationDist":0,"addr":"江苏省苏州市新区玉山路162号（水上世界）","stationPhone":null,"stationType":3,"rapidChargers":0,"slowChargers":0,"stationImg":null}]
     */

    private boolean hasNext;
    private boolean hasPrevious;
    private int page;
    private int totalPages;
    /**
     * stationId : 332
     * stationName : 北京展会演示充电站
     * lonLat : 116.4498770000,39.9679770000
     * stationDist : 0
     * addr : 中国国际展览中心
     * stationPhone : null
     * stationType : 0
     * rapidChargers : 0
     * slowChargers : 0
     * stationImg : null
     */

    private ArrayList<ContentEntity> content;

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

    public void setContent(ArrayList<ContentEntity> content) {
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
        private int stationId;
        private String stationName;
        private String lonLat;
        private int stationDist;
        private String addr;
        private Object stationPhone;
        private int stationType;
        private int rapidChargers;
        private int slowChargers;
        private Object stationImg;
        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public void setLonLat(String lonLat) {
            this.lonLat = lonLat;
        }

        public void setStationDist(int stationDist) {
            this.stationDist = stationDist;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public void setStationPhone(Object stationPhone) {
            this.stationPhone = stationPhone;
        }

        public void setStationType(int stationType) {
            this.stationType = stationType;
        }

        public void setRapidChargers(int rapidChargers) {
            this.rapidChargers = rapidChargers;
        }

        public void setSlowChargers(int slowChargers) {
            this.slowChargers = slowChargers;
        }

        public void setStationImg(Object stationImg) {
            this.stationImg = stationImg;
        }

        public int getStationId() {
            return stationId;
        }

        public String getStationName() {
            return stationName;
        }

        public String getLonLat() {
            return lonLat;
        }

        public int getStationDist() {
            return stationDist;
        }

        public String getAddr() {
            return addr;
        }

        public Object getStationPhone() {
            return stationPhone;
        }

        public int getStationType() {
            return stationType;
        }

        public int getRapidChargers() {
            return rapidChargers;
        }

        public int getSlowChargers() {
            return slowChargers;
        }

        public Object getStationImg() {
            return stationImg;
        }
    }
}
