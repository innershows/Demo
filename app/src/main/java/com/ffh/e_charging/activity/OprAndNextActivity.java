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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.model.ChargeList;

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
    private RotateAnimation animation;


    @Override
    public int initView() {
        return R.layout.activity_opr_and_next;
    }

    @Override
    public void init() {

        Intent intent = getIntent();

        ChargeList entity = (ChargeList) intent.getSerializableExtra("entity");

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                ((RadioButton) radioGroup.getChildAt(position)).setChecked(true);
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
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView iv = new ImageView(getApplicationContext());

                if (position == 0) {
                    iv.setImageResource(R.drawable.stop_start);
                } else {
                    iv.setImageResource(R.drawable.start_charge);
                }


                iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                iv.setTag(position);

                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setVisibility(View.GONE);
                        radioGroup.setVisibility(View.INVISIBLE);


                        ivAni.setVisibility(View.VISIBLE);

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
                });
                container.addView(iv);

                return iv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
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

}
