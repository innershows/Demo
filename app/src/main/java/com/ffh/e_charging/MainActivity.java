package com.ffh.e_charging;

import android.widget.TextView;

import com.ffh.e_charging.base.BaseActivity;

import butterknife.Bind;

public class MainActivity extends BaseActivity {



    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.textVie2w)
    TextView textVie2w;
    @Bind(R.id.textV3iew)
    TextView textV3iew;
    @Bind(R.id.text4View)
    TextView text4View;

    @Override
    public int initView() {

        System.out.println("---->");
        return R.layout.activity_main;
    }

}
