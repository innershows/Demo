package com.ffh.e_charging.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ffh.e_charging.MyIntentService;
import com.ffh.e_charging.MyReceiver;
import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.model.AppointEntity;
import com.ffh.e_charging.model.Stations;

import butterknife.Bind;

public class AppointStationActivity extends BaseActivity {

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
    private int stationId;
    private Stations.ContentEntity entity;
    private AppointEntity appointEntity;

    @Override
    public int initView() {
        return R.layout.activity_appoint_station;
    }

    private boolean hasData = false;

    @Override
    public void init() {
        Intent intent = getIntent();
        try {
            entity = (Stations.ContentEntity) intent.getSerializableExtra("station");
            appointEntity = (AppointEntity) intent.getSerializableExtra("station_detail");
            appointContent.setText(Html.fromHtml("已成功为你在" + entity.getStationName() + "预约了<font color='#FF6600'>" + appointEntity.getStationName() + "</font>，将为你保留60分钟" +
                    "请在预定时间内到达并使用。"));

            receiver.setCallBack(new MyReceiver.CallBack() {
                @Override
                public void onFetch(int count) {
                    numberCountDown.setText(MyIntentService.currentMinute + "");
                }
            });
        } catch (Exception e) {

        }


    }


    private MyReceiver receiver = new MyReceiver();

    @Override
    protected void onResume() {
        super.onResume();


        if (hasData) {
            if (MyIntentService.isStarted) {
                numberCountDown.setText(MyIntentService.currentMinute + "");
            } else {
                Intent service = new Intent(this, MyIntentService.class);
                service.putExtra("retain", appointEntity.getRetain());
                startService(service);
            }

            registerReceiver(receiver, new IntentFilter());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (hasData) {
            unregisterReceiver(receiver);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:


                break;
            case R.id.btn_arrived:


                break;


        }
    }

}
