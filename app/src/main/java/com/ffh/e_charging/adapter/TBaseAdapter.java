package com.ffh.e_charging.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by innershows on 15/12/15.
 */
public abstract class TBaseAdapter<T> extends BaseAdapter {


    protected List<T> entities;


    protected Context context;

    public TBaseAdapter(Context context, List<T> entities) {
        this.entities = entities;
        this.context = context;
    }

    @Override
    public int getCount() {
        return entities.size();
    }

    @Override
    public T getItem(int position) {
        return entities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}