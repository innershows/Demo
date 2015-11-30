package com.ffh.e_charging.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by innershows on 15/11/26.
 */
public class FirstFragment extends BaseFragment {
    @Bind(R.id.mapView)
    MapView mapView;
    private AMap aMap;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        return view;
    }
    
    @Override
    protected void init() {

        mapView.onCreate(null);
        if (aMap == null) {
            aMap = mapView.getMap();
        } else if (mapView.getParent() != null) {
            ((ViewGroup) mapView.getParent()).removeView(mapView);
        }

    }
}