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

import butterknife.Bind;

//TODO 页面逻辑和接口不符
public class ModifyPwdActivity extends BaseActivity {


    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.et_number)
    EditText etNumber;
    @Bind(R.id.et_sure)
    EditText etSure;
    @Bind(R.id.btn_send_sms)
    Button btnSendSms;
    @Bind(R.id.ver_tishiyu)
    TextView verTishiyu;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.et_password2)
    EditText etPassword2;
    @Bind(R.id.btn_ensure)
    Button btnEnsure;

    @Override
    public int initView() {
        return R.layout.mine_modify_password;
    }

    @Override
    public void init() {

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_sms:
                //发送验证码
                if (checkUserName()) {
                    String trim = etNumber.getText().toString().trim();
                    Net.get(API.SMS_GET + "mobile=" + trim, new OnFetchDataListener() {
                        @Override
                        public void onSuccess(String result) {
                            st(result);
                        }

                        @Override
                        public void onFail(int respCode, String data) {
                            st_e(data);
                        }
                    });
                }

                break;

            case R.id.btn_ensure:
                //提交修改
                break;
        }
    }

    private boolean checkUserName() {
        String phone = etNumber.getText().toString().trim();
        return !TextUtils.isEmpty(phone);
    }
}
