package com.ffh.e_charging.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ffh.e_charging.Listener.OnFetchDataListener;
import com.ffh.e_charging.MyReceiver;
import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.model.SyncOrder;
import com.ffh.e_charging.utils.API;
import com.ffh.e_charging.utils.CalenderUtils;
import com.ffh.e_charging.utils.Net;
import com.ffh.e_charging.utils.PreferenceUtils;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.Bind;

/**
 * 0x01
 * 故障状态
 * 0x02
 * 空闲状态
 * 0x03
 * 充电状态
 * 0x04
 * 停车状态
 * 0x05*
 * 预约状态
 * 0x06
 * <p/>
 * 维护状态
 */
public class AppointStationActivity extends BaseActivity implements OnFetchDataListener {

    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.appoint_content)
    TextView appointContent;
    @Bind(R.id.iv_count_down)
    ImageView ivCountDown;
    @Bind(R.id.btn_cancel)
    Button btnCancel;
    @Bind(R.id.btn_arrived)
    Button btnArrived;
    @Bind(R.id.number_count_down)
    TextView numberCountDown;
    private SyncOrder syncOrder;


    private MyReceiver receiver = new MyReceiver();
    private int culateMinute;

    @Override
    public int initView() {
        return R.layout.activity_appoint_station;
    }

    private boolean hasData = false;

    @Override
    public void init() {


        //每一次进来都获取新的数据，保持统一性
        downloadData();

//        receiver.setCallBack(new MyReceiver.CallBack() {
//            @Override
//            public void onFetch(int count) {
//                System.out.println("====>count" + count);
//                numberCountDown.setText(count + "");
//            }
//        });

    }

    private void downloadData() {
        String url = String.format(API.SYNC_ORDER_GET, token);
        System.out.println("===>url" + url);
        Net.get(url, this);
    }

    //模拟网络请求
    public void onBackClick(View v) {
        onBackPressed();
    }


    @Override
    protected void onResume() {
        super.onResume();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("com.count_down");
//        registerReceiver(receiver, filter);


//        if (hasData) {
//            if (MyIntentService.isStarted) {
//                numberCountDown.setText(MyIntentService.currentMinute + "");
//            } else {
//                Intent service = new Intent(this, MyIntentService.class);
//                service.putExtra("retain", appointEntity.getRetain());
//                startService(service);
//            }
//
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        unregisterReceiver(receiver);
    }

    public void onClick(View v) {


        if (syncOrder == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.btn_cancel:


                HashMap params = new HashMap();
                params.put("token", PreferenceUtils.getString("token", ""));
                Net.post(API.APPOINT_CANCEL, new OnFetchDataListener() {
                    @Override
                    public void onSuccess(String result) {
                        st("取消成功");
                        finish();
                    }

                    @Override
                    public void onFail(int respCode, String data) {
                        st_e(data);
                    }
                }, params);

                break;
            case R.id.btn_arrived:

                HashMap params1 = new HashMap();
                params1.put("token", token);

                /**
                 * 服务类型:1 停车,2 充电
                 */
                params1.put("serviceType", syncOrder.getType());

                params1.put("chargerId", syncOrder.getCsno());

                Net.post(API.APPOINT_ARRIVED, new OnFetchDataListener() {
                    @Override
                    public void onSuccess(String result) {
                        st(result);

                        Intent intent = new Intent(AppointStationActivity.this, OprAndNextActivity.class);
                        intent.putExtra("entity2", syncOrder);
                        startActivity(intent);
                    }

                    @Override
                    public void onFail(int respCode, String data) {
                        st_e(data);
                    }
                }, params1);

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

    @Override
    public void onSuccess(String result) {
        System.out.println("====>result" + result);
        syncOrder = new Gson().fromJson(result, SyncOrder.class);

        //TODO 根据具体的状态，决定跳转那一个页面


        if (syncOrder != null) {
//            if (MyIntentService.isStarted) {
            culateMinute = syncOrder.getRetain() - CalenderUtils.culateMinute(syncOrder.getStartTime(), syncOrder.getNowTime());

            numberCountDown.setText(culateMinute + "");

            appointContent.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (culateMinute == 0) {
                        //TODO 预约时间已到
                        st("预约时间已过");
                        finish();
                        return;
                    }
                    culateMinute--;

                    if (appointContent != null) {

                        numberCountDown.setText(culateMinute + "");

                        appointContent.postDelayed(this, 1000 * 60);
                    }
                }
            }, 1000 * 60);
//            } else {
//                Intent service = new Intent(this, MyIntentService.class);
//                service.putExtra("retain", syncOrder.getRetain());
//                startService(service);
//            }

            appointContent.setText(Html.fromHtml("已成功为你在" + syncOrder.getStationName() + "预约了<font color='#FF6600'>" + syncOrder.getAlias() + "</font>，将为你保留" + syncOrder.getRetain() + "分钟" +
                    "请在预定时间内到达并使用。"));
        }

        // st(result);
    }

    @Override
    public void onFail(int respCode, String data) {
        st_e(data);
    }
}
