package com.wsg.xsybbs.activity.user;


import android.graphics.Color;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.wsg.xsybbs.R;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.User;
import com.wsg.xsybbs.threadpool.MyThreadPool;
import com.wsg.xsybbs.util.L;
import com.wsg.xsybbs.util.UtilTools;

import com.wyp.avatarstudio.AvatarStudio;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

/**
 * Created by wsg
 * on         2018/7/2.
 * function: 修改个人信息
 */

public class ModifyPersionalInformationActivity extends BaseActivity implements View.OnClickListener {

    //圆形头像
    private CircleImageView profile_image;


    //四个个人输入信息框
    private EditText et_username;
    private EditText et_sex;
    private EditText et_age;
    private EditText et_desc;

    //更新按钮
    private Button btn_update_ok;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_persional_information);

        initView();

    }


    //初始化控件
    private void initView() {

        //按照布局依次进行初始化
        profile_image=(CircleImageView) findViewById(R.id.modify_profile_image);
        profile_image.setOnClickListener(this);

        et_username=(EditText) findViewById(R.id.et_username);
        et_sex=(EditText) findViewById(R.id.et_sex);
        et_age=(EditText) findViewById(R.id.et_age);
        et_desc=(EditText) findViewById(R.id.et_desc);


        btn_update_ok=(Button)findViewById(R.id.btn_update_ok);
        btn_update_ok.setOnClickListener(this);



        User user = BmobUser.getCurrentUser(User.class);
        et_username.setText(user.getUsername());
        et_age.setText(user.getAge() + "");
        et_sex.setText(user.isSex() ? getString(R.string.text_boy) : getString(R.string.text_girl_f));
        et_desc.setText(user.getDesc());


        if(user.getImage()!=null){
            UtilTools.getImage(ModifyPersionalInformationActivity.this,profile_image,user.getImage());
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.modify_profile_image:
                new AvatarStudio.Builder(ModifyPersionalInformationActivity.this)
                        .needCrop(true)//是否裁剪，默认裁剪
                        .setTextColor(Color.BLUE)
                        .dimEnabled(true)//背景是否dim 默认true
                        .setAspect(1, 1)//裁剪比例 默认1：1
                        .setOutput(200, 200)//裁剪大小 默认200*200
                        .setText("打开相机", "从相册中选取", "取消")
                        .show(new AvatarStudio.CallBack() {
                            @Override
                            public void callback(String uri) {
                                //uri为图片路径
                                Glide.with(ModifyPersionalInformationActivity.this).load(new File(uri))
                                        .asBitmap().into(profile_image);
                            }
                        });

                break;
            case R.id.btn_update_ok:

                //1.拿到输入框的值
                String username = et_username.getText().toString();
                String age = et_age.getText().toString();
                String sex = et_sex.getText().toString();
                String desc = et_desc.getText().toString();

                //2.判断是否为空
                if (!TextUtils.isEmpty(username) & !TextUtils.isEmpty(age) & !TextUtils.isEmpty(sex)) {
                    //3.更新属性
                    final User user = new User();
                    user.setUsername(username);
                    user.setAge(Integer.parseInt(age));
                    //性别
                    if (sex.equals(getString(R.string.text_boy))) {
                        user.setSex(true);
                    } else {
                        user.setSex(false);
                    }
                    //简介
                    if (!TextUtils.isEmpty(desc)) {
                        user.setDesc(desc);
                    } else {
                        user.setDesc(getString(R.string.text_nothing));
                    }
                    final BmobUser bmobUser = BmobUser.getCurrentUser();

                    MyThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            user.update(bmobUser.getObjectId(), new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        //修改成功
                                        UtilTools.putImageToShare(ModifyPersionalInformationActivity.this,profile_image);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toasty.success(ModifyPersionalInformationActivity.this, getString(R.string.text_editor_success), Toast.LENGTH_SHORT, true).show();
                                                finish();
                                            }
                                        });
                                    } else {
                                        L.d(e.getMessage()+e.getErrorCode()+e.toString());
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toasty.error(ModifyPersionalInformationActivity.this, getString(R.string.text_editor_failure), Toast.LENGTH_SHORT, true).show();
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    });
                } else {
                    Toasty.info(ModifyPersionalInformationActivity.this, getString(R.string.text_tost_empty), Toast.LENGTH_SHORT, true).show();
                }


                break;



        }

    }



}

