package com.ffh.e_charging.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseFragment;

/**
 * Created by innershows on 15/11/26.
 */
public class FirstFragment extends BaseFragment {


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        return view;
    }
    
    @Override
    protected void init() {



    }
}