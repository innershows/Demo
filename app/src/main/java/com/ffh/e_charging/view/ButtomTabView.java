package com.ffh.e_charging.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ffh.e_charging.MainActivity;
import com.ffh.e_charging.R;
import com.ffh.e_charging.activity.AppointStationActivity;
import com.ffh.e_charging.activity.MineActivity;

import java.util.List;

/**
 * 底部的Tab
 * Created by innershows on 15/11/26.
 */
public class ButtomTabView extends LinearLayout implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg;
    private View mapView;

    public ButtomTabView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ButtomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ButtomTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private List<Fragment> fragments;
    private FragmentManager manager;
    private int resId;

    public void setFragments(FragmentManager manager, List<Fragment> fragments, int resId) {
        this.fragments = fragments;
        this.manager = manager;
        this.resId = resId;
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        rg = (RadioGroup) LayoutInflater.from(context).inflate(R.layout.item_buttom, this, false);
        addView(rg);
        rg.setOnCheckedChangeListener(this);
    }


    public void setChecked(int index) {
        ((RadioButton) (rg.getChildAt(index))).setChecked(true);
    }

    private static int lastChosen = 0;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int index = 0; index < group.getChildCount(); index++) {
            RadioButton rb = (RadioButton) group.getChildAt(index);
            if (rb.isChecked() && index != lastChosen) {

                lastChosen = index;

                switch (index) {
                    case 0:
                        getContext().startActivity(new Intent(getContext(), MainActivity.class));
                        break;
                    case 1:
                        getContext().startActivity(new Intent(getContext(), AppointStationActivity.class));
                        break;
                    case 2:
                        getContext().startActivity(new Intent(getContext(), MineActivity.class));
                        break;
                }

//                Fragment fragment = fragments.get(index);
//                if (fragment == null) {
//                    if (mapView != null && mapView.getVisibility() == GONE) {
//                        mapView.setVisibility(VISIBLE);
//                    }
//                    try {
//
//                        manager.beginTransaction().
//
//                                remove(fragments.get(2)).commit();
//                    } catch (Exception e) {
//
//                    }
//                    try {
//
//                        manager.beginTransaction().
//                                remove(fragments.get(1)).
//                                commit();
//                    } catch (Exception e) {
//
//                    }
//
//                    continue;
//                }
//
//                if (mapView != null && mapView.getVisibility() == VISIBLE) {
//                    mapView.setVisibility(GONE);
//                }
//                manager.
//                        beginTransaction().
//                        replace(resId, fragment, fragment.getClass().
//                                getSimpleName()).
//                        commit();

                break;
            }

        }
    }

    public void setMapView(View mapView) {
        this.mapView = mapView;
    }

    public void setIndex(int index) {
        lastChosen = index;
    }
}
