package com.ffh.e_charging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    private CallBack callBack;


    public interface CallBack {
        void onFetch(int count);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        callBack.onFetch(intent.getIntExtra("count", 0));

    }
}
