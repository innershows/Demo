package com.ffh.e_charging.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ffh.e_charging.Listener.OnFetchDataListener;
import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.utils.API;
import com.ffh.e_charging.utils.Net;

import java.util.HashMap;

import butterknife.Bind;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.et_number)
    EditText etNumber;
    @Bind(R.id.et_sure)
    EditText etSure;
    @Bind(R.id.yanzhengma)
    Button yanzhengma;
    @Bind(R.id.tishiyu1)
    TextView tishiyu1;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.et_password2)
    EditText etPassword2;
    @Bind(R.id.tishiyu2)
    TextView tishiyu2;
    @Bind(R.id.zhuce)
    Button zhuce;

    @Override
    public int initView() {
        return R.layout.mine_register;
    }

    @Override
    public void init() {

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.yanzhengma:
                if (checkUserName()) {
                    String trim = etNumber.getText().toString().trim();
                    Net.get(API.SMS_GET + "mobile=" + trim, new OnFetchDataListener() {
                        @Override
                        public void onSuccess(String result) {
                            st(result);
                        }

                        @Override
                        public void onFail(int respCode, String data) {
                            st(data);
                        }
                    });
                }

                break;

            case R.id.zhuce:
                HashMap map = new HashMap<String, String>();
                if (checkInfo(map)) {
                    Net.post(API.REGISTER_POST, new OnFetchDataListener() {
                        @Override
                        public void onSuccess(String result) {
                            st(result);
                        }

                        @Override
                        public void onFail(int respCode, String data) {
                            st(data);
                        }
                    }, map);
                }
                break;

        }
    }

    private boolean checkInfo(HashMap map) {
        boolean ret = false;

        String number = etNumber.getText().toString();

        String pwd = etPassword.getText().toString();
        String s = etSure.getText().toString();

        if (TextUtils.isEmpty(number) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(s)) {
            st("请输入完整信息");
            return ret;
        }

        map.put("mobile", number);
        map.put("password", pwd);
        map.put("verifyCode", s);

        ret = true;

        return ret;
    }

    private boolean checkUserName() {

        String s = etNumber.getText().toString();
        if (TextUtils.isEmpty(s)) {

            st("请先输入电话号码");
            return false;
        }

        return true;
    }
}
