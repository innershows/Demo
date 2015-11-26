package com.ffh.e_charging;

import android.app.Application;

import org.xutils.x;

/**
 * Created by innershows on 15/11/26.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志

    }
}
