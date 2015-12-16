package com.ffh.e_charging.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ffh.e_charging.R;
import com.ffh.e_charging.model.UserRecord;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by innershows on 15/12/15.
 */
public class RecordAdapter extends TBaseAdapter<UserRecord.ContentEntity> {


    public RecordAdapter(Context context, List entities) {
        super(context, entities);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_record_01, parent, false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        UserRecord.ContentEntity item = getItem(position);

//        holder.ivType


        //#00BAFD
        holder.tvCostDetail.setText(Html.fromHtml("消费<font color='#00BAFD'>" + item.getAllCost() + "</font>" +
                        "     余额<font color='#FF6600'>" + 0 + "</font>元   " + item.getStationName()
        ));

        holder.tvTime.setText(item.getStartTime());

        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_record_01.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.iv_type)
        ImageView ivType;
        @Bind(R.id.tv_cost_detail)
        TextView tvCostDetail;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_comment)
        TextView tvComment;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
