package com.ffh.e_charging.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ffh.e_charging.Listener.OnFetchDataListener;
import com.ffh.e_charging.R;
import com.ffh.e_charging.adapter.RecordAdapter;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.model.UserRecord;
import com.ffh.e_charging.utils.API;
import com.ffh.e_charging.utils.Net;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.Bind;


/**
 * 用户记录
 */
public class UserRecodeActivity extends BaseActivity {


    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_use_record)
    TextView tvUseRecord;
    @Bind(R.id.tv_chongzhi_record)
    TextView tvChongzhiRecord;
    @Bind(R.id.lv)
    ListView lv;
    private UserRecord record;

    @Override
    public int initView() {
        return R.layout.activity_user_recode;
    }

    @Override
    public void init() {
        HashMap params = new HashMap();

        /**
         * No
         参数
         类型
         是否必要
         描述
         1
         token
         String
         是
         用户 token
         2
         startTime
         String
         否
         开始时间
         3
         endTime
         String
         否
         结束时间
         4
         page
         Int
         否
         当前页码
         5
         pageSize
         Int
         否
         每页记录条数,默认 20 条
         6
         serviceType
         Int
         否
         类型
         */
        params.put("token", token);

        String api = String.format(API.EXPENSES_RECODES_POST, token);

        Net.get(api, new OnFetchDataListener() {
            @Override
            public void onSuccess(String result) {
                //TODO 获取json 数据

                record = new Gson().fromJson(result, UserRecord.class);

                lv.setAdapter(new
                        RecordAdapter(UserRecodeActivity.this, record.getContent()));

                lv.setDividerHeight(20);
            }

            @Override
            public void onFail(int respCode, String data) {
                st_e(data);
            }
        });
    }

    //模拟网络请求
    public void onBackClick(View v) {
        onBackPressed();
    }


}
