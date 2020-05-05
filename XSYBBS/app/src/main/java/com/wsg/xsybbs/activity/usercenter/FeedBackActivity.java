package com.wsg.xsybbs.activity.usercenter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.Feedback;
import com.wsg.xsybbs.bean.User;
import com.wsg.xsybbs.threadpool.MyThreadPool;
import com.wsg.xsybbs.util.StaticClass;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import es.dmoral.toasty.Toasty;

/**
 * Created by wsg
 * on         2018/6/29.
 * function: 反馈界面
 */
public class FeedBackActivity extends BaseActivity implements View.OnClickListener {

    private EditText etback;
    private Button btback;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.FEEDBACK_SUCESS:
                    Toasty.success(FeedBackActivity.this, "反馈成功~~~", Toast.LENGTH_SHORT, true).show();
                    finish();
                    break;
                case StaticClass.FEEDBACK_FAILED:
                    Toasty.error(FeedBackActivity.this, "反馈失败~~~,请检查网络", Toast.LENGTH_SHORT, true).show();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
    }

    private void initView() {
        etback = (EditText) findViewById(R.id.et_back);
        btback = (Button) findViewById(R.id.bt_back);
        btback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_back:
                String s = etback.getText().toString().trim();
                if (!TextUtils.isEmpty(s)) {

                    //开始反馈数据
                    final Feedback feedback = new Feedback();
                    User user = BmobUser.getCurrentUser(User.class);
                    feedback.setUserid(user.getObjectId());
                    feedback.setContent(s);
                    feedback.setDeviceType("android");



                    //2018/12/29   上传反馈开启线程
                    MyThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            feedback.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {

                                    if (e == null) {
                                        handler.sendEmptyMessage(StaticClass.FEEDBACK_SUCESS);
                                    } else {
                                        handler.sendEmptyMessage(StaticClass.FEEDBACK_SUCESS);
                                    }

                                }

                            });
                        }
                    });
                } else {
                    Toasty.info(FeedBackActivity.this, getString(R.string.text_tost_empty), Toast.LENGTH_SHORT, true).show();
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
