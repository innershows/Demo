package com.ffh.e_charging.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffh.e_charging.R;
import com.ffh.e_charging.activity.LoginActivity;
import com.ffh.e_charging.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by innershows on 15/11/26.
 */
public class ThirdFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.img_userIcon)
    ImageView imgUserIcon;
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
    @Bind(R.id.dengluzhuce)
    TextView dengluzhuce;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.mine, container, false);
        return inflate;
    }


    @Override
    protected void init() {
        dengluzhuce.setOnClickListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dengluzhuce:
                startActivity(new Intent(mActivity, LoginActivity.class));
                break;
        }
    }
}
