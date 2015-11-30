package com.ffh.e_charging.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffh.e_charging.base.BaseFragment;

/**
 * Created by innershows on 15/11/26.
 */
public class SecondFragment extends BaseFragment {
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(mActivity);
        textView.setText("22222222");
        return textView;
    }
}
