package com.ffh.e_charging.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffh.e_charging.Listener.OnFetchDataListener;
import com.ffh.e_charging.MainActivity;
import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.model.User;
import com.ffh.e_charging.utils.API;
import com.ffh.e_charging.utils.Net;
import com.ffh.e_charging.utils.PreferenceUtils;
import com.ffh.e_charging.view.ButtomTabView;
import com.google.gson.Gson;

import org.xutils.x;

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
    private User user;

    @Override
    public int initView() {
        return R.layout.mine;
    }

    @Override
    public void init() {
        dengluzhuce.setOnClickListener(this);
        updateInfo();

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (!TextUtils.isEmpty(token)) {
            setData();
        }
    }

    private void updateInfo() {

        if (!TextUtils.isEmpty(token)) {
            Net.get(String.format(API.USER_INFO_DETAIL, token), new OnFetchDataListener() {
                @Override
                public void onSuccess(String result) {

                    Gson gson = new Gson();
                    user = gson.fromJson(result, User.class);

                    if (user == null) {
                        return;
                    }
                    PreferenceUtils.put("token", user.getToken() + "");
                    PreferenceUtils.put("email", user.getEmail() + "");
                    PreferenceUtils.put("mobile", user.getMobile() + "");
                    PreferenceUtils.put("nickname", user.getNickname() + "");
                    PreferenceUtils.put("headimgurl", user.getHeadimgurl() + "");
                    PreferenceUtils.put("registerDate", user.getRegisterDate());
                    PreferenceUtils.put("balance", user.getBalance() + "");
                    PreferenceUtils.put("sex", user.getSex() + "");
                }

                @Override
                public void onFail(int respCode, String data) {

                }
            });

        }

    }


    private void setData() {
        String headimgurl = PreferenceUtils.getString("headimgurl", "");
        if (!TextUtils.isEmpty(headimgurl)) {
            //x.image().bind(imgUserIcon, headimgurl);
        }
        x.image().bind(imgUserIcon, "https://pic1.zhimg.com/014802cb91c37fd91aa1e340174e66e4_m.jpg");
        dengluzhuce.setText(PreferenceUtils.getString("nickname", ""));
        dengluzhuce.setEnabled(false);

        zhanghu.setText(PreferenceUtils.getString("mobile", ""));

        zhanghuyue.setText(Html.fromHtml("账户余额  <font color='#FF6600'>" + PreferenceUtils.getString("balance", "") + "元</font>"));
        tuichuzhanghu.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.dengluzhuce:
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.zhanghu:
                //修改用户信息

                if (TextUtils.isEmpty(token)) {
                    return;
                }
                Intent intent = new Intent(this, ModifyUserInfoActivity.class);
                if (user != null) {
                    intent.putExtra("info", user);
                }
                startActivity(intent);
                break;

            case R.id.xiugaimima:
                //修改密码

                if (TextUtils.isEmpty(token)) {
                    return;
                }

                startActivity(new Intent(this, ModifyPwdActivity.class));
                break;

            case R.id.rl_zhanghuyue:

                if (TextUtils.isEmpty(token)) {
                    return;
                }
                //充值
                break;
            case R.id.youhuiquan:
                //优惠券
                //TODO 优惠券
                break;
            case R.id.zhanghujilu:
                //user record

                if (TextUtils.isEmpty(token)) {
                    return;
                }

                startActivity(new Intent(this, UserRecodeActivity.class));
                break;

            case R.id.gongxiangdianzhuang:
                //share elc_point

                if (TextUtils.isEmpty(token)) {
                    return;
                }
                break;
            case R.id.tucao:
                //feed back

                if (TextUtils.isEmpty(token)) {
                    return;
                }

                startActivity(new Intent());




                break;

            case R.id.tuichuzhanghu:
                //logout

                if (TextUtils.isEmpty(token)) {
                    return;
                }
                PreferenceUtils.reset(this);
                startActivity(new Intent(this, MainActivity.class));

                break;

            default:
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
        System.exit(0);
        super.onBackPressed();
    }

    //模拟网络请求
    public void onBackClick(View v) {
        onBackPressed();
    }


}
