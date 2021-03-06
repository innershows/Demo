package com.ffh.e_charging.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ffh.e_charging.MainActivity;
import com.ffh.e_charging.R;
import com.ffh.e_charging.activity.AppointStationActivity;
import com.ffh.e_charging.activity.MineActivity;

import java.util.List;

/**
 * 底部的Tab
 * Created by innershows on 15/11/26.
 */
public class ButtomTabView extends LinearLayout implements View.OnClickListener {

    private LinearLayout rg;
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

//    public void setFragments(FragmentManager manager, List<Fragment> fragments, int resId) {
//        this.fragments = fragments;
//        this.manager = manager;
//        this.resId = resId;
//    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        rg = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_buttom, this, false);
//        for (int i = 0; i < rg.getChildCount(); i++) {
//            ((RadioButton) rg.getChildAt(i)).setChecked(false);
//        }
        rg.findViewById(R.id.tab_find).setOnClickListener(this);
        rg.findViewById(R.id.tab_status).setOnClickListener(this);
        rg.findViewById(R.id.tab_mine).setOnClickListener(this);
        addView(rg);
//        rg.setOnCheckedChangeListener(this);
    }


//    public void setChecked(int index) {
//        ((RadioButton) (rg.getChildAt(index))).setChecked(true);
//    }

    private static int lastChosen = 0;

//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        System.out.println("====>lastChoose" + lastChosen);
//        Toast.makeText(getContext(), "hahaha", Toast.LENGTH_SHORT).show();
//        for (int index = 0; index < group.getChildCount(); index++) {
//            RadioButton rb = (RadioButton) group.getChildAt(index);
//            if (rb.isChecked() && index != lastChosen) {
//
//                lastChosen = index;
//
//                switch (index) {
//                    case 0:
//                        getContext().startActivity(new Intent(getContext(), MainActivity.class));
//                        break;
//                    case 1:
//                        getContext().startActivity(new Intent(getContext(), AppointStationActivity.class));
//                        break;
//                    case 2:
//                        getContext().startActivity(new Intent(getContext(), MineActivity.class));
//                        break;
//                }
//
////                Fragment fragment = fragments.get(index);
////                if (fragment == null) {
////                    if (mapView != null && mapView.getVisibility() == GONE) {
////                        mapView.setVisibility(VISIBLE);
////                    }
////                    try {
////
////                        manager.beginTransaction().
////
////                                remove(fragments.get(2)).commit();
////                    } catch (Exception e) {
////
////                    }
////                    try {
////
////                        manager.beginTransaction().
////                                remove(fragments.get(1)).
////                                commit();
////                    } catch (Exception e) {
////
////                    }
////
////                    continue;
////                }
////
////                if (mapView != null && mapView.getVisibility() == VISIBLE) {
////                    mapView.setVisibility(GONE);
////                }
////                manager.
////                        beginTransaction().
////                        replace(resId, fragment, fragment.getClass().
////                                getSimpleName()).
////                        commit();
//
//                break;
//            }
//
//        }
//    }

//    public void setMapView(View mapView) {
//        this.mapView = mapView;
//    }

    public void setIndex(int index) {
        lastChosen = index;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tab_find:
                if (lastChosen == 0) {
                    return;
                }
                lastChosen = 0;
                getContext().startActivity(new Intent(getContext(), MainActivity.class));
                break;
            case R.id.tab_status:
                if (lastChosen == 1) {
                    return;
                }
                lastChosen = 1;
                getContext().startActivity(new Intent(getContext(), AppointStationActivity.class));
                break;
            case R.id.tab_mine:
                if (lastChosen == 2) {
                    return;
                }
                lastChosen = 2;
                getContext().startActivity(new Intent(getContext(), MineActivity.class));
                break;
        }
    }
}
