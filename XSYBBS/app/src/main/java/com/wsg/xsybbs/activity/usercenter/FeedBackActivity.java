package com.wsg.xsybbs.activity.usercenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.Feedback;
import com.wsg.xsybbs.bean.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by wsg
 * on         2018/6/29.
 * function: 反馈界面
 */
public class FeedBackActivity extends BaseActivity implements View.OnClickListener {

    private EditText etback;
    private Button btback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
    }

    private void initView() {
        etback=(EditText)findViewById(R.id.et_back);
        btback=(Button)findViewById(R.id.bt_back);
        btback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_back:
                String s=etback.getText().toString().trim();
                if(!TextUtils.isEmpty(s)){

                    //开始反馈数据
                    Feedback feedback=new Feedback();
                    User user = BmobUser.getCurrentUser(User.class);
                    feedback.setUserid(user.getObjectId());
                    feedback.setContent(s);
                    feedback.setDeviceType("android");


                    feedback.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {

                            if(e==null){
                                Toast.makeText(getApplicationContext(),"反馈成功~~~",Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(FeedBackActivity.this,"反馈失败~~~",Toast.LENGTH_SHORT).show();
                            }

                        }

                    });



                }else{
                    Toast.makeText(FeedBackActivity.this,R.string.text_tost_empty,Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }
}
