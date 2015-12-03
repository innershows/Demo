package com.ffh.e_charging.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by innershows on 15/12/3.
 */
public class CalenderUtils {

    static Calendar calendar = Calendar.getInstance();

    /**
     * 获取毫秒数 ： 格式为： 20151101120355  yyyyMMddHHmmss
     *
     * @param str
     */
    public static long getMills(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date date = format.parse(str);

            calendar.setTime(date);

            return calendar.getTimeInMillis();
        } catch (ParseException e) {
            System.out.println("====>" + e.toString());
            e.printStackTrace();
        }
        return 0;
    }

    public static int culateMinute(String str1, String str2) {
        long abs = Math.abs(getMills(str1) - getMills(str2));

        System.out.println("====>" + abs);
        return (int) (abs / 60 / 1000);
    }
}
