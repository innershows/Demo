package com.ffh.e_charging.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseActivity;

import butterknife.Bind;

public class ModifyPwdActivity extends BaseActivity {


    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.et_number)
    EditText etNumber;
    @Bind(R.id.et_sure)
    EditText etSure;
    @Bind(R.id.btn_send_sms)
    Button btnSendSms;
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

}
