package com.ffh.e_charging;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.ffh.e_charging.Listener.OnFetchDataListener;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.fragment.SecondFragment;
import com.ffh.e_charging.fragment.ThirdFragment;
import com.ffh.e_charging.model.Stations;
import com.ffh.e_charging.utils.API;
import com.ffh.e_charging.utils.Net;
import com.ffh.e_charging.view.ButtomTabView;
import com.ffh.e_charging.zxing.activity.CaptureActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends BaseActivity {


    @Bind(R.id.location)
    ImageView location;
    @Bind(R.id.map_view)
    MapView mapView;
    @Bind(R.id.fl_map_container)
    RelativeLayout flMapContainer;
    @Bind(R.id.fl_container)
    FrameLayout flContainer;
    @Bind(R.id.tab_buttom)
    ButtomTabView tabButtom;
    private AMap aMap;


    @Override
    public int initView() {
        return R.layout.activity_main;
    }

    @Override
    public void initMap(Bundle bundle) {
        mapView.onCreate(bundle);
        aMap = mapView.getMap();
        //设置不显示按钮
        aMap.getUiSettings().setZoomControlsEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();

        downloadData();
    }

    private void downloadData() {


        Net.get(API.STATIONS_GET, new OnFetchDataListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Stations stations = gson.fromJson(result, Stations.class);
                int page = stations.getPage();
                st(page + "==  " + stations.getContent().get(2).getAddr());
            }

            @Override
            public void onFail(int respCode, String data) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.location:
                //定位
                break;
            case R.id.bigger:
                //放大
                break;
            case R.id.smaller:
                //缩小
                break;
            case R.id.scan:
                //二维码扫描
                startActivity(new Intent(this, CaptureActivity.class));
                break;
        }

    }


    @Override
    public void init() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(null);
        fragments.add(new SecondFragment());
        fragments.add(new ThirdFragment());
        tabButtom.setMapView(flMapContainer);
        tabButtom.setFragments(getFragmentManager(), fragments, R.id.fl_container);

    }


}
