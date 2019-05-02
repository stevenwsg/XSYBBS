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
import com.wsg.xsybbs.threadpool.MyThreadPool;
import com.wsg.xsybbs.util.L;
import com.wsg.xsybbs.util.SPUtils;
import com.wsg.xsybbs.util.StaticClass;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import es.dmoral.toasty.Toasty;

/**
 * Created by wsg
 * on         2018/7/1.
 * function:  修改密码
 */
public class ModifyPasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_old;
    private EditText et_new;
    private EditText et_new_again;
    private Button bt_modify;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.MODIFYPASSWORD_SUCESS:
                    Toasty.success(ModifyPasswordActivity.this, getString(R.string.reset_successfully), Toast.LENGTH_SHORT, true).show();
                    finish();
                    break;

                case StaticClass.FORGETPASSWORD_FAILED:
                    Toasty.error(ModifyPasswordActivity.this, getString(R.string.reset_failed), Toast.LENGTH_SHORT, true).show();
                    break;

            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);

        initView();

    }

    //初始化控件
    private void initView() {
        et_old = (EditText) findViewById(R.id.et_now);
        et_new = (EditText) findViewById(R.id.et_new);
        et_new_again = (EditText) findViewById(R.id.et_new_password);
        bt_modify = (Button) findViewById(R.id.btn_update_password);

        bt_modify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update_password:

                final String now = et_old.getText().toString().trim();
                final String news = et_new.getText().toString().trim();
                final String new_password = et_new_again.getText().toString();

                //判断是否为空
                if (!TextUtils.isEmpty(now) & !TextUtils.isEmpty(news) & !TextUtils.isEmpty(new_password)) {
                    //判断两次新密码是否一致
                    if (news.equals(new_password)) {


                        //2017/12/29因为要进行网络操作 所以需要修改。开启线程进行网络操作  。可能之前是因为 项目赶的急，没有开线程
                        //2019/5/2 使用全局线程池重构
                        MyThreadPool.execute(new Runnable() {
                            @Override
                            public void run() {
                                //重置密码
                                User.updateCurrentUserPassword(now, news, new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if (e == null) {
                                            //保存用户信息
                                            SPUtils.putString(ModifyPasswordActivity.this, StaticClass.PASSWORD, new_password);
                                            handler.sendEmptyMessage(StaticClass.MODIFYPASSWORD_SUCESS);
                                        } else {
                                            // TODO: 2018/12/29     errorCode:211,errorMsg:用户请先登录，或者用户登录已过期需要重新登录用户请先登录，或者用户登录已过期需要重新登录211
                                            L.e(e.toString()+e.getMessage()+e.getErrorCode());
                                            handler.sendEmptyMessage(StaticClass.MODIFYPASSWORD_FAILED);
                                        }

                                    }
                                });
                            }
                        });
                    } else {
                        Toasty.info(ModifyPasswordActivity.this, getString(R.string.text_two_input_not_consistent), Toast.LENGTH_SHORT, true).show();
                    }


                } else {
                    Toasty.info(ModifyPasswordActivity.this, getString(R.string.text_tost_empty), Toast.LENGTH_SHORT, true).show();
                }
                break;
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}

