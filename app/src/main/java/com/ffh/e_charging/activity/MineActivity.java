package com.ffh.e_charging.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.view.ButtomTabView;

import butterknife.Bind;


public class MineActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.img_userIcon)
    ImageView imgUserIcon;
    @Bind(R.id.dengluzhuce)
    TextView dengluzhuce;
    @Bind(R.id.zhanghu)
    TextView zhanghu;
    @Bind(R.id.xiugaimima)
    TextView xiugaimima;
    @Bind(R.id.zhanghuyue)
    TextView zhanghuyue;
    @Bind(R.id.rl_zhanghuyue)
    RelativeLayout rlZhanghuyue;
    @Bind(R.id.youhuiquan)
    TextView youhuiquan;
    @Bind(R.id.zhanghujilu)
    TextView zhanghujilu;
    @Bind(R.id.gongxiangdianzhuang)
    TextView gongxiangdianzhuang;
    @Bind(R.id.tucao)
    TextView tucao;
    @Bind(R.id.guanyu)
    TextView guanyu;
    @Bind(R.id.tuichuzhanghu)
    Button tuichuzhanghu;
    @Bind(R.id.tab_buttom)
    ButtomTabView tabButtom;

    @Override
    public int initView() {
        return R.layout.mine;
    }

    @Override
    public void init() {
        dengluzhuce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dengluzhuce:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        for (Activity a : activities) {
            if (a != null) {
                a.finish();
            }
        }
        super.onBackPressed();
    }

}
