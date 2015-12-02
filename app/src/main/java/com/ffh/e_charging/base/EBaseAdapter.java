package com.ffh.e_charging.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ffh.e_charging.model.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by innershows on 15/11/26.
 */
public abstract class EBaseAdapter<T extends Entity> extends BaseAdapter {

    private List<T> entities = new ArrayList<T>();


    protected LayoutInflater inflater;


    protected Context context;


    public EBaseAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    public void setData(List<T> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    public void resetData(List<T> entities) {
        this.entities.clear();
        this.entities.addAll(entities);
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
