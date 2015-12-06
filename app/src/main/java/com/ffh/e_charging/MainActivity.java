package com.ffh.e_charging;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.ffh.e_charging.Listener.OnFetchDataListener;
import com.ffh.e_charging.activity.AppointStationActivity;
import com.ffh.e_charging.activity.ChargeListActivity;
import com.ffh.e_charging.activity.NavActivity;
import com.ffh.e_charging.activity.StationDetailActivity;
import com.ffh.e_charging.adapter.GalleryAdapter;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.model.AppointEntity;
import com.ffh.e_charging.model.Stations;
import com.ffh.e_charging.utils.API;
import com.ffh.e_charging.utils.Net;
import com.ffh.e_charging.utils.PreferenceUtils;
import com.ffh.e_charging.view.ButtomTabView;
import com.ffh.e_charging.zxing.activity.CaptureActivity;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;


public class MainActivity extends BaseActivity implements LocationSource, AMapLocationListener, AMap.OnMarkerClickListener, View.OnClickListener {


    @Bind(R.id.location)
    ImageView location;
    @Bind(R.id.map_view)
    MapView mapView;
    @Bind(R.id.fl_map_container)
    RelativeLayout flMapContainer;
    @Bind(R.id.fl_container)
    FrameLayout flContainer;
    @Bind(R.id.tab_buttom)
    ButtomTabView tabButtom;
    @Bind(R.id.scan)
    ImageView scan;
    @Bind(R.id.ll_top)
    LinearLayout llTop;
    @Bind(R.id.bigger)
    ImageView bigger;
    @Bind(R.id.smaller)
    ImageView smaller;
    @Bind(R.id.line_divider)
    View lineDivider;
    @Bind(R.id.gallery)
    Gallery gallery;
    @Bind(R.id.et_search)
    EditText etSearch;
    private AMap aMap;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;


    private Map<String, ArrayList<Stations.ContentEntity>> latLngEntity = new HashMap<String, ArrayList<Stations.ContentEntity>>();

    //数据
    private Stations stations;
    private AMapLocation aLocation;


    public static LatLng latLng;
    private GalleryAdapter adapter;


    @Override
    public int initView() {
        return R.layout.activity_main;
    }

    @Override
    public void initMap(Bundle bundle) {
        mapView.onCreate(bundle);
        aMap = mapView.getMap();
        //设置不显示按钮
        aMap.getUiSettings().setZoomControlsEnabled(false);

        aMap.getUiSettings().setMyLocationButtonEnabled(false);

        aMap.setOnMarkerClickListener(this);

        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false

        downloadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();


        if (gallery.getVisibility() == View.VISIBLE) {
            tabButtom.setVisibility(View.VISIBLE);
            gallery.setVisibility(View.GONE);
        }

    }

    private void downloadData() {


        Net.get(API.STATIONS_GET, new OnFetchDataListener() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                stations = gson.fromJson(result, Stations.class);


                for (Stations.ContentEntity entity : stations.getContent()) {
                    String lonLat = entity.getLonLat();
                    if (latLngEntity.containsKey(lonLat)) {
                        ArrayList<Stations.ContentEntity> contentEntities = latLngEntity.get(lonLat);
                        contentEntities.add(entity);
                        continue;
                    }


                    ArrayList<Stations.ContentEntity> value = new ArrayList<>();
                    value.add(entity);
                    latLngEntity.put(lonLat, value);
                }


                if (stations != null && stations.getContent() != null) {
                    addMarker();
                }
            }

            @Override
            public void onFail(int respCode, String data) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.location:
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                //定位
                break;
            case R.id.bigger:

                aMap.moveCamera(CameraUpdateFactory.zoomBy(1));

                //放大
                break;
            case R.id.smaller:
                aMap.moveCamera(CameraUpdateFactory.zoomBy(-1));
                //缩小
                break;
            case R.id.scan:
                //二维码扫描
                startActivity(new Intent(this, CaptureActivity.class));
                break;

            case R.id.station_book:


                if (!PreferenceUtils.getBoolean("isLogin", false)) {
                    st("请先登陆");
                    return;
                }


                final Stations.ContentEntity value = stations.getContent().get(Integer.parseInt(v.getTag() + ""));

                final Dialog dialog = new Dialog(this, R.style.NobackDialog);


                View view = dialog.getLayoutInflater().inflate(R.layout.dialog_appoint, null);

                dialog.setContentView(view);


                TextView tvContent = (TextView) view.findViewById(R.id.dialog_content);


                tvContent.setText(Html.fromHtml("您确定需要预约<font color='#FF6600'>" + value.getStationName() + "</font>的充电桩吗?"));
                view.findViewById(R.id.dialog_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });
                view.findViewById(R.id.dialog_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (dialog != null) {
                            dialog.dismiss();
                        }


                        HashMap params = new HashMap();

                        /**)
                         * token
                         String
                         是
                         用户 token
                         2
                         type
                         Int
                         否
                         电桩类型:0 默认,1 直流,2 交流
                         3
                         stationId
                         Int
                         是
                         电站 ID
                         */
                        params.put("token", PreferenceUtils.getString("token", ""));
                        params.put("type", value.getStationType());
                        params.put("stationId", value.getStationId());

                        Net.post(API.APPOINT_STATION, new OnFetchDataListener() {
                            @Override
                            public void onSuccess(String result) {
                                System.out.println("=====>" + result);
                                Gson gson = new Gson();
                                AppointEntity appointEntity = gson.fromJson(result, AppointEntity.class);

                                Intent intent1 = new Intent(MainActivity.this, AppointStationActivity.class);

                                intent1.putExtra("station", value);
                                intent1.putExtra("station_detail", appointEntity);

                                startActivity(intent1);

                            }

                            @Override
                            public void onFail(int respCode, String data) {
                                System.out.println("=====>" + respCode);
                                System.out.println("=====>" + data);
                                st_e(data);
                            }
                        }, params);

//
//                        Intent intent1 = new Intent(MainActivity.this, AppointStationActivity.class);
//
//                        intent1.putExtra("station", value);
//
//                        startActivity(intent1);
                    }
                });


                Window window = dialog.getWindow();

                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.gravity = Gravity.BOTTOM;
                attributes.width = getResources().getDisplayMetrics().widthPixels;

                window.setAttributes(attributes);
                dialog.show();


                //预订
                break;
            case R.id.station_gps:
                Intent intent = new Intent(this, NavActivity.class);
                intent.putExtra("start", this.latLng);
                Stations.ContentEntity entity = stations.getContent().get(Integer.parseInt(v.getTag() + ""));
                String[] split = entity.getLonLat().split(",");


                LatLng end = new LatLng(
                        Double.parseDouble(split[1]),
                        Double.parseDouble(split[0]));

                intent.putExtra("end", end);


                startActivity(intent);

                //导航
                break;
            case R.id.station_use:
                //使用

                intent = new Intent(this, ChargeListActivity.class);
                intent.putExtra("station", stations.getContent().get(Integer.parseInt(v.getTag() + "")));
                startActivity(intent);
                break;

            case R.id.station_name:
                intent = new Intent(this, StationDetailActivity.class);

                intent.putExtra("station", stations.getContent().get(Integer.parseInt(v.getTag() + "")));

                startActivity(intent);

                break;
        }


    }


    @Override
    public void init() {

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                String s = v.getText().toString();

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    try {
                        Net.get(API.STATIONS_GET + "?name=" + URLEncoder.encode(s, "UTF-8"), new OnFetchDataListener() {
                            @Override
                            public void onSuccess(String result) {
                                st(result);
                                stations = new Gson().fromJson(result, Stations.class);
                                if (stations == null || stations.getContent().isEmpty()) {
                                    st("没有结果");
                                    return;
                                }
                                aMap.clear();
                                addMarker();
                            }

                            @Override
                            public void onFail(int respCode, String data) {
                                st_e(data);
                            }
                        });
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

//        ArrayList<Fragment> fragments = new ArrayList<>();
//        fragments.add(null);
//        fragments.add(new SecondFragment());
//        fragments.add(new ThirdFragment());
//        tabButtom.setMapView(flMapContainer);
//        //tabButtom.setFragments(getFragmentManager(), fragments, R.id.fl_container);

    }


    //////////////定位接口方法
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        System.out.println("-----activate");
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);

            mLocationOption.setNeedAddress(true);

            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            //设置定位间隔,单位毫秒,默认为2000ms
            mLocationOption.setInterval(60000);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        System.out.println("-----deactivate");
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    ////定位回调
    @Override
    public void onLocationChanged(AMapLocation aLocation) {
        System.out.println("-----onLocationChanged");
        if (mListener != null && aLocation != null) {
            this.aLocation = aLocation;
            mListener.onLocationChanged(this.aLocation);// 显示系统小蓝点
            System.out.println("----" + aLocation.getLatitude() + "---" + aLocation.getLongitude());

            latLng = new LatLng(aLocation.getLatitude(), aLocation.getLongitude());
        }
    }


    private void addMarker() {
        List<Stations.ContentEntity> entities = stations.getContent();

        //aMap.clear();

        for (int i = 0; i < entities.size(); i++) {
            Stations.ContentEntity entity = entities.get(i);
            String lonLat = entity.getLonLat();

            String[] split = lonLat.split(",");

            MarkerOptions options = new MarkerOptions();

            options.title(entity.getStationName());

            LatLng latLng = new LatLng(Double.parseDouble(split[1]),
                    Double.parseDouble(split[0]));
            options.position(
                    latLng);

            options.title(i + "");
            options.icon(BitmapDescriptorFactory.fromResource(R.drawable.location_test));

            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

            aMap.addMarker(options);

        }
    }


    //marker 点击事件
    @Override
    public boolean onMarkerClick(Marker marker) {


        tabButtom.setVisibility(View.GONE);
        lineDivider.setVisibility(View.GONE);


        int position = Integer.parseInt(marker.getTitle());


        if (adapter == null) {
            initAdapter(position);
        }

        gallery.setSelection(position);

//        System.out.println("===marker latitude==>>" + marker.getPosition().latitude);
//        System.out.println("===marker longitude==>>" + marker.getPosition().longitude);
//        for (int i = 0; i < stations.getContent().size(); i++) {
//
//            Stations.ContentEntity entity = stations.getContent().get(i);
//            String[] split = entity.getLonLat().split(",");
//
//            LatLng latLng = new LatLng(
//                    Double.parseDouble(split[1]),
//                    Double.parseDouble(split[0]));
//
//
//            System.out.println("===point name==>>" + entity.getStationName());
//
//            System.out.println("===point latitude==>>" + latLng.latitude);
//            System.out.println("===point longitude==>>" + latLng.longitude);
//
//            if (((latLng.latitude + latLng.longitude) - (marker.getPosition().latitude
//                    + marker.getPosition().longitude)) < 0.001) {
//                //如果相等
//                st("相等");
//                break;
//            }
//
//        }

        return true;
    }

    private void initAdapter(int position) {
        adapter = new GalleryAdapter(this);
        adapter.setData(stations.getContent());


        adapter.setBookListener(this);
        adapter.setNavListener(this);
        adapter.setUseListener(this);

        gallery.setAdapter(adapter);

        gallery.setVisibility(View.VISIBLE);


        gallery.setSpacing(25);

        gallery.setSelection(position, true);


//        gallery.setOnItemClickListener(this);

    }

    @Override
    public void onBackPressed() {
        for (Activity a : activities) {
            if (a != null) {
                a.finish();
            }
        }
        super.onBackPressed();
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent(this, ChargeListActivity.class);
//        intent.putExtra("station", stations.getContent().get(position));
//        startActivity(intent);
//    }
}
