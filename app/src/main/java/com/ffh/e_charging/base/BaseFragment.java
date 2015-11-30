package com.ffh.e_charging.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by innershows on 15/11/26.
 */
public abstract class BaseFragment extends Fragment {


    protected BaseActivity mActivity;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mActivity == null) {
            mActivity = (BaseActivity) getActivity();
        }
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View source = initView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, source);
        init();
        return source;
    }

    protected void init() {
    }

    ;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public final void onDetach() {
        super.onDetach();
        //保持fragment 一直拥有activity 的引用
        if (mActivity == null)
            mActivity = (BaseActivity) getActivity();
    }

    ////////////
    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
