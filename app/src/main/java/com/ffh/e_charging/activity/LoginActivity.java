package com.ffh.e_charging.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ffh.e_charging.Listener.OnFetchDataListener;
import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.model.User;
import com.ffh.e_charging.utils.API;
import com.ffh.e_charging.utils.Net;
import com.ffh.e_charging.utils.PreferenceUtils;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.Bind;

public class LoginActivity extends BaseActivity {


    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.et_number)
    EditText etNumber;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.tishiyu)
    TextView tishiyu;
    @Bind(R.id.login)
    Button login;
    @Bind(R.id.wangjimima)
    TextView wangjimima;
    @Bind(R.id.zhuce)
    TextView zhuce;

    @Override
    public int initView() {
        return R.layout.mine_login;
    }

    @Override
    public void init() {

    }

    /**
     * 处理点击事件
     *
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:

                final HashMap params = new HashMap();
                if (checkNameAndPwd(params)) {


                    Net.post(API.LOGIN_POST, new OnFetchDataListener() {
                        @Override
                        public void onSuccess(String result) {

                            Gson gson = new Gson();
                            User user = gson.fromJson(result, User.class);

                            PreferenceUtils.put("token", user.getToken());
                            PreferenceUtils.put("email", user.getEmail() + "");
                            PreferenceUtils.put("mobile", user.getMobile());
                            PreferenceUtils.put("nickname", user.getNickname() + "");
                            PreferenceUtils.put("headimgurl", user.getHeadimgurl() + "");
                            PreferenceUtils.put("registerDate", user.getRegisterDate());
                            PreferenceUtils.put("sex", user.getSex());
                            PreferenceUtils.put("password", params.get("password") + "");

                            finish();

                            PreferenceUtils.put("isLogin", true);


                            Net.get(API.USER_INFO_DETAIL, new OnFetchDataListener() {
                                @Override
                                public void onSuccess(String result) {

                                }

                                @Override
                                public void onFail(int respCode, String data) {

                                }
                            });
//                            Log.i("info", result);

                            st("登陆成功");
                        }

                        @Override
                        public void onFail(int respCode, String data) {
                            if (respCode == 400) {
                                tishiyu.setVisibility(View.VISIBLE);
                            }
                        }
                    }, params);
                }


                break;
            case R.id.wangjimima:
                break;
            case R.id.zhuce:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    private boolean checkNameAndPwd(HashMap map) {
        boolean ret = false;

        String number = etNumber.getText().toString();

        String pwd = etPassword.getText().toString();


        if (TextUtils.isEmpty(number) || TextUtils.isEmpty(pwd)) {
            st("请输入完整的用户名和密码");
            return ret;
        }

        map.put("mobile", number);
        map.put("password", pwd);

        ret = true;

        return ret;
    }
}
