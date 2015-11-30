package com.ffh.e_charging.Listener;

/**
 * Created by innershows on 15/11/30.
 */
public interface OnFetchDataListener {
    void onSuccess(String result);

    void onFail(int respCode , String data);
}
