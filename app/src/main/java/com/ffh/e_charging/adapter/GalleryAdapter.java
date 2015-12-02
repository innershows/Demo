package com.ffh.e_charging.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ffh.e_charging.R;
import com.ffh.e_charging.base.EBaseAdapter;
import com.ffh.e_charging.model.Stations;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by innershows on 15/12/1.
 */
public class GalleryAdapter extends EBaseAdapter<Stations.ContentEntity> {


    public GalleryAdapter(Context context) {
        super(context);
    }


    private View.OnClickListener navListener;
    private View.OnClickListener bookListener;
    private View.OnClickListener useListener;

    public void setNavListener(View.OnClickListener navListener) {
        this.navListener = navListener;
    }

    public void setBookListener(View.OnClickListener bookListener) {
        this.bookListener = bookListener;
    }

    public void setUseListener(View.OnClickListener useListener) {
        this.useListener = useListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gallery, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        Stations.ContentEntity item = getItem(position);

        holder.stationName.setText(item.getStationName());
        holder.stationDistance.setText(item.getStationDist() + "");
        holder.stationAddress.setText(item.getAddr());
        holder.stationCount.setText(item.getRapidChargers() + "");

//        holder.stationRating.setRating(());

        holder.stationBook.setTag(position);
        holder.stationGps.setTag(position);
        holder.stationUse.setTag(position);

        holder.stationBook.setOnClickListener(this.bookListener);
        holder.stationGps.setOnClickListener(this.navListener);
        holder.stationUse.setOnClickListener(this.useListener);

        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_gallery.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.station_name)
        TextView stationName;
        @Bind(R.id.station_distance)
        TextView stationDistance;
        @Bind(R.id.station_address)
        TextView stationAddress;
        @Bind(R.id.station_count)
        TextView stationCount;
        @Bind(R.id.station_rating)
        RatingBar stationRating;
        @Bind(R.id.station_gps)
        TextView stationGps;
        @Bind(R.id.station_book)
        TextView stationBook;
        @Bind(R.id.station_use)
        TextView stationUse;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
