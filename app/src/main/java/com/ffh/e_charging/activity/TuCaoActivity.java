package com.ffh.e_charging.activity;

import android.view.View;

import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseActivity;

public class TuCaoActivity extends BaseActivity {


    @Override
    public int initView() {
        return R.layout.activity_tu_cao;
    }

    @Override
    public void init() {

    }

    public void onBackClick(View v) {
        onBackPressed();
    }
}
