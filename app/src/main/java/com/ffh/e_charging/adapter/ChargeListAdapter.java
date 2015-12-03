package com.ffh.e_charging.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ffh.e_charging.R;
import com.ffh.e_charging.model.ChargeList;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by innershows on 15/12/2.
 */
public class ChargeListAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ChargeList> chargeLists;


    LayoutInflater inflater;

    public ChargeListAdapter(Context context, List<ChargeList> chargeLists) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.chargeLists = chargeLists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(inflater.inflate(R.layout.item_c_l, parent, false));
    }

    private View.OnClickListener listener;

    public void setOnItemClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.chargerName.setText(chargeLists.get(position).getChargerName());
        holder1.chargerStatus.setText(chargeLists.get(position).getStatus() + "");
        holder1.chargerType.setText(chargeLists.get(position).getType() + "");
        holder1.chargerUseGonglv.setText(chargeLists.get(position).getChargerId());
        holder1.flFather.setTag(chargeLists.get(position));
        holder1.flFather.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return chargeLists.size();
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_c_l.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.charger_name)
        TextView chargerName;
        @Bind(R.id.charger_status)
        TextView chargerStatus;
        @Bind(R.id.charger_type)
        TextView chargerType;
        @Bind(R.id.charger_use_gonglv)
        TextView chargerUseGonglv;

        @Bind(R.id.rl_father)
        View flFather;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
