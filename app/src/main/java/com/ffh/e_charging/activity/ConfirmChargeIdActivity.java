package com.ffh.e_charging.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ffh.e_charging.Listener.OnFetchDataListener;
import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.utils.API;
import com.ffh.e_charging.utils.Net;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import butterknife.Bind;


public class ConfirmChargeIdActivity extends BaseActivity {

    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.iv_erweima)
    ImageView ivErweima;
    @Bind(R.id.tv_charge_id)
    TextView tvChargeId;
    @Bind(R.id.btn_confirm)
    Button btnConfirm;
    @Bind(R.id.et_input_id)
    EditText etInputId;
    private String chargeId;

    @Override
    public int initView() {
        return R.layout.activity_confirm_charge_id;
    }

    @Override
    public void init() {
        Intent intent = getIntent();

        chargeId = intent.getStringExtra("chargeId");
        if (TextUtils.isEmpty(chargeId)) {
            //do nothing
        } else {

            try {
                BitMatrix encode = new MultiFormatWriter().encode(chargeId, BarcodeFormat.QR_CODE, 200, 200);

                Bitmap bitmap = toBitmap(encode);

                ivErweima.setImageBitmap(bitmap);

            } catch (WriterException e) {
                e.printStackTrace();
            }

            tvChargeId.setText("ID: " + chargeId);
            etInputId.setText(chargeId);
        }
    }


    public void onClick(View v) {
        String s = etInputId.getText().toString();
        if (TextUtils.isEmpty(s)) {
            st("请输入内容！");
            return;
        }
        //TODO 预约
        String format = String.format(API.SCDN_SCAN_GET, s);
        Net.get(format, new OnFetchDataListener() {
            @Override
            public void onSuccess(String result) {
                st(result);

                startActivity(new Intent(ConfirmChargeIdActivity.this, StatusActivity.class));
            }

            @Override
            public void onFail(int respCode, String data) {
                st_e2(data);
            }
        });
    }

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    private Bitmap toBitmap(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    //模拟网络请求
    public void onBackClick(View v) {
        onBackPressed();
    }


}
