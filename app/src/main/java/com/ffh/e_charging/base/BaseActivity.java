package com.ffh.e_charging.base;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.ffh.e_charging.model.ErrorMsg;
import com.ffh.e_charging.model.ErrorMsg2;
import com.ffh.e_charging.utils.PreferenceUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by innershows on 15/11/26.
 */
public abstract class BaseActivity extends Activity {


    protected static List<Activity> activities = new ArrayList<>();


    protected String token;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        activities.add(this);
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        token = PreferenceUtils.getString("token", "");

        setContentView(initView());
        ButterKnife.bind(this);
        initMap(savedInstanceState);
        init();

    }

    public void initMap(Bundle bundle) {

    }

    public abstract int initView();

    public abstract void init();

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        activities.remove(this);
    }

    /**
     * 显示Toast
     *
     * @param content
     */
    protected void st(String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }


        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示Toast
     *
     * @param result
     */
    protected void st_e(String result) {
        ErrorMsg errorMsg = new Gson().fromJson(result, ErrorMsg.class);
        if (errorMsg != null && !TextUtils.isEmpty(errorMsg.getErrorMsg())) {
            Toast.makeText(this, errorMsg.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }


    }

    protected void st_e2(String result) {
        ErrorMsg2 errorMsg = new Gson().fromJson(result, ErrorMsg2.class);
        if (errorMsg != null && !TextUtils.isEmpty(errorMsg.getData())) {
            Toast.makeText(this, errorMsg.getData(), Toast.LENGTH_SHORT).show();
        }
    }
}
