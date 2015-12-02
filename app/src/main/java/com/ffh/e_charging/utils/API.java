package com.ffh.e_charging.utils;

/**
 * Created by innershows on 15/11/30.
 */
public class API {
    private static final String DOMAIN = "https://api.e-charging.cn/v1/";


    public static final String ACCESS_TOKEN_GET = DOMAIN + "accessToken?apiKey=" + Constant.API_KEY;

    public static final String STATIONS_GET = DOMAIN + "stations";

    public static final String LOGIN_POST = DOMAIN + "signin";
    public static final String REGISTER_POST = DOMAIN + "signup";
    public static final String SMS_GET = DOMAIN + "sms?";
    public static final String USER_INFO_DETAIL = DOMAIN + "users/?";
    public static final String APPOINT_STATION = DOMAIN + "chargers/appoint";
    public static final String APPOINT_CANCEL = DOMAIN + "chargers/appointment/cancel";
    public static final String APPOINT_ARRIVED = DOMAIN + "chargers/park";
    public static final String CHARGES_ONE_STATION = DOMAIN + "stations/%s/chargers";
}
