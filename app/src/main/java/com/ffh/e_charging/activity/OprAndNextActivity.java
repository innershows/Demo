package com.ffh.e_charging.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffh.e_charging.Listener.OnFetchDataListener;
import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.model.ChargeList;
import com.ffh.e_charging.model.SyncOrder;
import com.ffh.e_charging.utils.API;
import com.ffh.e_charging.utils.Net;

import java.util.HashMap;

import butterknife.Bind;

public class OprAndNextActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_station_name)
    TextView tvStationName;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.radio_group)
    RadioGroup radioGroup;
    @Bind(R.id.iv_bg)
    ImageView ivBg;
    @Bind(R.id.iv_fore)
    ImageView ivFore;
    @Bind(R.id.iv_ani)
    RelativeLayout ivAni;
    @Bind(R.id.ll_first)
    LinearLayout llFirst;
    @Bind(R.id.park_time)
    TextView parkTime;
    @Bind(R.id.park_price)
    TextView parkPrice;
    @Bind(R.id.ll_second)
    RelativeLayout llSecond;
    private RotateAnimation animation;
    private ChargeList entity;
    private SyncOrder order;

    private String stationName;
    private String chargeName;
    private String chargeId;

    @Override
    public int initView() {
        return R.layout.activity_opr_and_next;
    }

    @Override
    public void init() {


        getDataFromIntent();


        initViewPageAndRadioGroup();
    }

    private void getDataFromIntent() {


        Intent intent = getIntent();
        entity = (ChargeList) intent.getSerializableExtra("entity");
        order = (SyncOrder) intent.getSerializableExtra("entity2");


        if (entity != null) {
            stationName = entity.getChargerName();
            chargeName = entity.getChargerName();
            chargeId = entity.getChargerId();
        } else if (order != null) {
            stationName = order.getStationName();
            chargeName = order.getAlias();
            chargeId = order.getCsno();
        }
    }


    private void initViewPageAndRadioGroup() {

        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                ((RadioButton) radioGroup.getChildAt(position)).setChecked(true);
                if (position == 0) {
                    tvType.setText("停车");
                } else {
                    tvType.setText("充电");
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(this);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                ImageView iv = new ImageView(getApplicationContext());

                if (position == 0) {
                    iv.setImageResource(R.drawable.stop_start);
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HashMap params = new HashMap();
                            params.put("token", token);
                            params.put("chargerId", chargeId);

                            //TODO 开始停车
                            //startChargeRequest(params, position);

                        }
                    });

                } else {
                    iv.setImageResource(R.drawable.start_charge);
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HashMap params = new HashMap();
                            params.put("token", token);
                            params.put("chargerId", chargeId);

                            startChargeRequest(params, position);

                        }
                    });

                }


                iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                iv.setTag(position);


                container.addView(iv);

                return iv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }

    private void startChargeRequest(HashMap params, final int position) {
        Net.post(API.START_CHARGE, new OnFetchDataListener() {
            @Override
            public void onSuccess(String result) {

                //  st(result);

                viewPager.setVisibility(View.GONE);
                radioGroup.setVisibility(View.INVISIBLE);

                llFirst.setVisibility(View.GONE);
                llSecond.setVisibility(View.VISIBLE);

                ivAni.setVisibility(View.VISIBLE);

                if (position == 0) {
                    ivBg.setImageResource(R.drawable.stop_empty);
                } else {
                    ivBg.setImageResource(R.drawable.start_charge);
                }

                if (animation == null) {
                    animation = new RotateAnimation(0f, 359f, Animation.RELATIVE_TO_SELF,
                            0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

                    animation.setDuration(3000);//设置动画持续时间
                    animation.setInterpolator(new LinearInterpolator());//不停顿
                    animation.setRepeatCount(-1);//重复次数
                }
                ivFore.setAnimation(animation);
                ivFore.startAnimation(animation);


            }

            @Override
            public void onFail(int respCode, String data) {
                st_e(data);
            }
        }, params);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < group.getChildCount(); i++) {
            if (((RadioButton) group.getChildAt(i)).isChecked()) {
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        llFirst.setVisibility(View.VISIBLE);
        llSecond.setVisibility(View.GONE);

    }

    //模拟网络请求
    public void onBackClick(View v) {
        onBackPressed();
    }

}
