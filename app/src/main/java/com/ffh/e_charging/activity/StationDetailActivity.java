package com.ffh.e_charging.activity;

import android.content.Intent;
import android.widget.Gallery;
import android.widget.TextView;

import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.model.Stations;

import butterknife.Bind;

public class StationDetailActivity extends BaseActivity {

    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.sd_name)
    TextView sdName;
    @Bind(R.id.sd_address)
    TextView sdAddress;
    @Bind(R.id.sd_address_detail)
    TextView sdAddressDetail;
    @Bind(R.id.st_count)
    TextView stCount;
    @Bind(R.id.sd_charge_price)
    TextView sdChargePrice;
    @Bind(R.id.sd_stop_price)
    TextView sdStopPrice;
    @Bind(R.id.sd_phone)
    TextView sdPhone;
    @Bind(R.id.sd_feedback)
    TextView sdFeedback;
    @Bind(R.id.gallery_detail)
    Gallery galleryDetail;
    private Stations.ContentEntity entity;


    //TODO 获取网络数据
    @Override
    public int initView() {
        return R.layout.activity_station_detail;
    }

    @Override
    public void init() {


        Intent intent = getIntent();
        entity = (Stations.ContentEntity) intent.getSerializableExtra("station");


        // Net.get(API.STATION_DETAIL , this);

    }
}
