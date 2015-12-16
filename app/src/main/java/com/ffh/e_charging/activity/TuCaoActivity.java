package com.ffh.e_charging.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ffh.e_charging.Listener.OnFetchDataListener;
import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.utils.API;
import com.ffh.e_charging.utils.Net;

import java.util.HashMap;

import butterknife.Bind;

public class TuCaoActivity extends BaseActivity {


    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.radio_group)
    RadioGroup radioGroup;
    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.btn_commit)
    Button btnCommit;

    @Override
    public int initView() {
        return R.layout.activity_tu_cao;
    }

    @Override
    public void init() {

    }


    public void btnClick(View v) {
        String content = etContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            return;
        }

        StringBuffer sb = new StringBuffer(content);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
            if (rb.isChecked()) {
                sb.append("," + rb.getText().toString());
            }
        }

        HashMap params = new HashMap();

        /**
         token
         String
         是
         用户 token

         content
         String
         是
         反馈内容
         *
         */
        params.put("token", token);
        params.put("type", 1);
        params.put("content", sb.toString());

        Net.post(API.FEED_BACK_POST, new OnFetchDataListener() {
            @Override
            public void onSuccess(String result) {
                st("感谢您的反馈!");
                finish();
            }

            @Override
            public void onFail(int respCode, String data) {
                st(data);
            }
        }, params);


    }

    public void onBackClick(View v) {
        onBackPressed();
    }
}
