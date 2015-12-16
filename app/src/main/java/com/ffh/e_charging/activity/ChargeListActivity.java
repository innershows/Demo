package com.ffh.e_charging.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ffh.e_charging.Listener.OnFetchDataListener;
import com.ffh.e_charging.R;
import com.ffh.e_charging.adapter.ChargeListAdapter;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.model.ChargeList;
import com.ffh.e_charging.model.Stations;
import com.ffh.e_charging.utils.API;
import com.ffh.e_charging.utils.Net;
import com.ffh.e_charging.view.ButtomTabView;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;


public class ChargeListActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.recycler_charge)
    RecyclerView recyclerCharge;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.btn_tab)
    ButtomTabView btnTab;
    private List<ChargeList> chargeLists;
    private Stations.ContentEntity entity;

    @Override
    public int initView() {
        return R.layout.activity_charge_list;
    }


    @Override
    public void init() {

        btnTab.setIndex(-1);

        recyclerCharge.setLayoutManager(new LinearLayoutManager(this));


        recyclerCharge.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.getChildPosition(view) != 0)
                    outRect.top = 20;
            }
        });

        Intent intent = getIntent();
        entity = (Stations.ContentEntity) intent.getSerializableExtra("station");
        String format = String.format(API.CHARGES_ONE_STATION, entity.getStationId());
        Net.get(format, new OnFetchDataListener() {
            @Override
            public void onSuccess(String result) {
                ChargeList[] chargeListsA = new Gson().fromJson(result, ChargeList[].class);
                chargeLists = Arrays.asList(chargeListsA);

                System.out.println("====>" + chargeLists.size());


                ChargeListAdapter adapter = new ChargeListAdapter(ChargeListActivity.this, chargeLists);
                adapter.setOnItemClickListener(ChargeListActivity.this);
                recyclerCharge.setAdapter(adapter);


            }

            @Override
            public void onFail(int respCode, String data) {
                st_e(data);
            }
        });
    }

    @Override
    public void onClick(View v) {


        final Dialog dialog = new Dialog(this, R.style.NobackDialog);


        View view = dialog.getLayoutInflater().inflate(R.layout.dialog_appoint, null);

        dialog.setContentView(view);


        TextView tvContent = (TextView) view.findViewById(R.id.dialog_content);


        tvContent.setText("非常抱歉，您的账户余额不足，请充值后使用");
        view.findViewById(R.id.dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        ((TextView) view.findViewById(R.id.title)).setText("提示");
        View viewById = view.findViewById(R.id.dialog_confirm);
        ((Button) viewById).setText("充值");
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });


        if (TextUtils.isEmpty(token)) {
            st("请先登陆");
        }

        Intent intent = new Intent(this, OprAndNextActivity.class);
        intent.putExtra("entity", (Serializable) v.getTag());
        startActivity(intent);
    }

    //模拟网络请求
    public void onBackClick(View v) {
        onBackPressed();
    }

}
