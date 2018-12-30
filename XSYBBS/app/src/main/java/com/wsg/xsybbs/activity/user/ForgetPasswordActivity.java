package com.wsg.xsybbs.activity.user;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.User;
import com.wsg.xsybbs.util.StaticClass;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import es.dmoral.toasty.Toasty;

/**
 * Created by wsg
 * on         2018/6/28.
 * function: 忘记密码页面
 */
public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_email;
    private Button bt_sure;
    private String  email;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.FORGETPASSWORD_SUCESS:
                    Toasty.success(ForgetPasswordActivity.this, "重置密码请求成功，请到" + email + "邮箱进行密码重置操作" + email, Toast.LENGTH_SHORT, true).show();
                    finish();
                    break;

                case StaticClass.FORGETPASSWORD_FAILED:
                    Toasty.error(ForgetPasswordActivity.this, getString(R.string.text_email_send_no), Toast.LENGTH_SHORT, true).show();
                    break;


            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initView();
    }

    //初始化控件
    private void initView() {
        et_email = (EditText) findViewById(R.id.et_email);
        bt_sure = (Button) findViewById(R.id.btn_forget_password);
        bt_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_forget_password:
                email = et_email.getText().toString().trim();
                //判断是否为空
                if (!TextUtils.isEmpty(email)) {
                    //发送邮件



                    //2017/12/29因为要进行网络操作 所以需要修改。开启线程进行网络操作  。可能之前是因为 项目赶的急，没有开线程

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            User.resetPasswordByEmail(email, new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        handler.sendEmptyMessage(StaticClass.FORGETPASSWORD_SUCESS);
                                    } else {
                                        handler.sendEmptyMessage(StaticClass.FORGETPASSWORD_FAILED);
                                    }
                                }
                            });

                        }
                    }).start();



                } else {
                    Toasty.info(ForgetPasswordActivity.this, getString(R.string.text_tost_empty), Toast.LENGTH_SHORT, true).show();
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler!=null){
            handler.removeCallbacksAndMessages(null);
            handler=null;
        }

    }
}
