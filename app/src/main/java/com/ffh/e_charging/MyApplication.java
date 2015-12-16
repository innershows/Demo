package com.ffh.e_charging;

import android.app.Application;
import android.text.TextUtils;

import com.ffh.e_charging.utils.Net;
import com.ffh.e_charging.utils.PreferenceUtils;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by innershows on 15/11/26.
 */
public class MyApplication extends Application {

    public static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志
        Net.init(this);

        app = this;


        if (TextUtils.isEmpty(PreferenceUtils.getString("accessToken", ""))) {
            Net.getAccessKey();
        }
    }


}
