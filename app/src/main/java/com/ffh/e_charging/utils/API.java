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
    public static final String USER_INFO_DETAIL = DOMAIN + "users/%s";
    public static final String APPOINT_STATION = DOMAIN + "chargers/appoint";
    public static final String APPOINT_CANCEL = DOMAIN + "chargers/appointment/cancel";
    public static final String APPOINT_ARRIVED = DOMAIN + "chargers/park";
    public static final String CHARGES_ONE_STATION = DOMAIN + "stations/%s/chargers";
    public static final String SYNC_ORDER_GET = DOMAIN + "users/%s/sync";
    public static final String START_CHARGE = DOMAIN + "chargers/start";
    public static final String STOP_CHARGE = DOMAIN + "chargers/stop";
    public static final String UPDATE_USER_INFO = DOMAIN + "users/%s";
    public static final String UPLOAD_AVATAR = DOMAIN + "users/%s/avatar";
    public static final String SCDN_SCAN_GET = DOMAIN + "chargers/%s";
    public static final String EXPENSES_RECODES_POST = DOMAIN + "expenses?token=%s";
    public static final String FEED_BACK_POST = DOMAIN + "feedback";
}
