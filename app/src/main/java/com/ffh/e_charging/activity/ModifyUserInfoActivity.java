package com.ffh.e_charging.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffh.e_charging.Listener.OnFetchDataListener;
import com.ffh.e_charging.R;
import com.ffh.e_charging.base.BaseActivity;
import com.ffh.e_charging.model.User;
import com.ffh.e_charging.utils.API;
import com.ffh.e_charging.utils.FileUtils;
import com.ffh.e_charging.utils.Net;
import com.ffh.e_charging.utils.PreferenceUtils;
import com.ffh.e_charging.utils.Tools;

import java.io.File;
import java.util.HashMap;

import butterknife.Bind;

public class ModifyUserInfoActivity extends BaseActivity {


    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_nickname)
    EditText tvNickname;
    @Bind(R.id.tv_girl)
    TextView tvGirl;
    @Bind(R.id.choose_sex)
    RelativeLayout chooseSex;
    @Bind(R.id.tv_boy)
    TextView tvBoy;
    @Bind(R.id.tg_sex)
    RelativeLayout tgSex;
    @Bind(R.id.tvv_car_pinpai)
    TextView tvvCarPinpai;
    @Bind(R.id.tvv_car_type)
    TextView tvvCarType;
    @Bind(R.id.et_car_number)
    EditText etCarNumber;
    @Bind(R.id.btn_commit)
    Button btnCommit;
    private File avatarFile;

    @Override
    public int initView() {
        return R.layout.mine_info;
    }

    @Override
    public void init() {
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("info");

        if (user == null) {
            return;
        }

        setData(user);


    }


    //设置数据
    private void setData(User user) {
        tvNickname.setHint(user.getNickname() + "");
    }

    //性别:0 默认,1 男,2 女
    private int gender = 1;

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.choose_sex:
                // choose gender.
                st("fuck");
                if (gender != 2) {
                    tvBoy.setBackgroundResource(R.drawable.switch_hot_circle);
                    tvGirl.setBackgroundColor(Color.TRANSPARENT);
                    gender = 2;
                } else {
                    tvGirl.setBackgroundResource(R.drawable.switch_hot_circle);
                    tvBoy.setBackgroundColor(Color.TRANSPARENT);
                    gender = 1;
                }

                break;

            case R.id.btn_commit:
                // commit modify.

                commit();
                break;


            case R.id.iv_avatar:
                // upload avatar
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 1);

                break;
            case R.id.tvv_car_pinpai:
                //choose car paizi
                break;

            case R.id.tvv_car_type:
                //choose car type
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String realPathFromURI = Tools.getRealPathFromURI(uri, this);
            if (TextUtils.isEmpty(realPathFromURI)) {
                avatarFile = null;
            } else {
                avatarFile = new File(Environment.getExternalStorageDirectory()
                        + "/head.jpg");
                File temFile = new File(realPathFromURI);

                FileUtils.copyFile(temFile, avatarFile);
            }

            if (avatarFile == null) {
                return;
            }

            Net.uploadFile(String.format(API.UPLOAD_AVATAR, token), new OnFetchDataListener() {
                @Override
                public void onSuccess(String result) {
                    st("上传成功");
                }

                @Override
                public void onFail(int respCode, String data) {
                    st("上传失败");
                }
            }, avatarFile);

        }
    }

    /**
     * commit data
     */
    private void commit() {


        HashMap params = new HashMap();
//        params.put("password", "");
        params.put("nickname", tvNickname.getText().toString());
        params.put("sex", gender + "");
//        params.put("email", );
        Net.post(String.format(API.UPDATE_USER_INFO, token), new OnFetchDataListener() {
            @Override
            public void onSuccess(String result) {
                finish();
                PreferenceUtils.put("nickname", tvNickname.getText().toString());
                PreferenceUtils.put("sex", gender + "");
                st("修改成功");
            }

            @Override
            public void onFail(int respCode, String data) {
                st_e(data);
            }
        }, params);


    }

}
