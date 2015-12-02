package com.ffh.e_charging.model;

/**
 * Created by innershows on 15/12/2.
 */
public class User extends Entity {

    /**
     * token : a2f34cf1f3724e44a3a265854292e4ec
     * email : null
     * mobile : 18683674575
     * nickname : null
     * headimgurl : null
     * sex : 0
     * balance : 0
     * registerDate : 20151130121846
     */

    private String token;
    private Object email;
    private String mobile;
    private Object nickname;
    private Object headimgurl;
    private int sex;
    private int balance;
    private String registerDate;

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setNickname(Object nickname) {
        this.nickname = nickname;
    }

    public void setHeadimgurl(Object headimgurl) {
        this.headimgurl = headimgurl;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getToken() {
        return token;
    }

    public Object getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public Object getNickname() {
        return nickname;
    }

    public Object getHeadimgurl() {
        return headimgurl;
    }

    public int getSex() {
        return sex;
    }

    public int getBalance() {
        return balance;
    }

    public String getRegisterDate() {
        return registerDate;
    }
}
