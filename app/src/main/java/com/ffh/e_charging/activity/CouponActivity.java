package com.ffh.e_charging.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ffh.e_charging.R;
import com.ffh.e_charging.adapter.CouponAdapter;
import com.ffh.e_charging.base.BaseActivity;

import java.util.ArrayList;

import butterknife.Bind;

public class CouponActivity extends BaseActivity {


    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.radio_group)
    RadioGroup radioGroup;
    @Bind(R.id.lv)
    ListView lv;

    @Override
    public int initView() {
        return R.layout.activity_coupon;
    }

    @Override
    public void init() {
        ArrayList entities = new ArrayList();

        for (int i = 0; i < 20; i++) {
            entities.add("");
        }
        lv.setAdapter(new CouponAdapter(this, entities));
    }

    //
    public void onBackClick(View v) {
        onBackPressed();
    }

}
