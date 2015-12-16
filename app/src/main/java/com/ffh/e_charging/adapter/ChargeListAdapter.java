package com.ffh.e_charging.adapter;

import android.content.Context;
import android.graphics.Paint;
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

        holder1.chargerName.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        /**
         * 0x01
         * 标准 AC 单项智能充电桩
         * 0x02
         * 标准 AC 三项智能充电桩
         * 0x03
         * 标准 DC 智能充电桩
         * 0x11
         * 高级 AC 单项智能充电桩(with Lock)
         * 0x12
         * 高级 AC 三项智能充电桩(with Lock)
         * 0x13
         * 高级 DC 智能充电桩(with Lock)
         */
        String type = "";
        switch (chargeLists.get(position).getType()) {
            case 1:
                type = "AC 单项智能充电桩";
                break;
            case 2:
                type = "AC 三项智能充电桩";
                break;
            case 3:
                type = "DC 智能充电桩";
                break;
            case 11:
                type = "AC 单项智能充电桩(with Lock)";
                break;
            case 12:
                type = "AC 三项智能充电桩(with Lock)";
                break;
            case 13:
                type = "DC 智能充电桩(with Lock)";
                break;
            default:
                break;

        }

        /**
         * 0x01
         故障状态
         0x02
         空闲状态
         0x03
         充电状态
         0x04
         停车状态
         0x05*
         预约状态
         0x06

         维护状态
         */

        String status = "";
        switch (chargeLists.get(position).getStatus()) {
            case 1:
                status = "故障";
                break;
            case 2:
                status = "空闲";
                break;
            case 3:
                status = "充电";

                break;
            case 4:
                status = "停车";

                break;
            case 5:
                status = "预约";
                break;
            case 6:
                status = "维护";

                break;
            default:
                break;
        }

        holder1.chargerStatus.setText("状态：" + status);
        holder1.chargerType.setText("类型：" + type);
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
