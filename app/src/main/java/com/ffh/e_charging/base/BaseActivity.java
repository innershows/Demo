package com.ffh.e_charging.base;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by innershows on 15/11/26.
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    /**
     * 显示Toast
     *
     * @param content
     */
    protected void st(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}
