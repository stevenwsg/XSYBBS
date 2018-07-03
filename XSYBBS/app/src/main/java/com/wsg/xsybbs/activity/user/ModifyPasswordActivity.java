package com.wsg.xsybbs.activity.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

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

                String now = et_old.getText().toString().trim();
                String news = et_new.getText().toString().trim();
                String new_password = et_new_again.getText().toString();

                //判断是否为空
                if (!TextUtils.isEmpty(now) & !TextUtils.isEmpty(news) & !TextUtils.isEmpty(new_password)) {
                    //判断两次新密码是否一致
                    if (news.equals(new_password)) {
                        //重置密码
                        User.updateCurrentUserPassword(now, news, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(ModifyPasswordActivity.this,
                                            R.string.reset_successfully, Toast.LENGTH_SHORT).show();
                                    finish();

                                }else{
                                    Toast.makeText(ModifyPasswordActivity.this, R.string.reset_failed, Toast.LENGTH_SHORT).show();

                                }

                            }
                        });


                    } else {
                        Toast.makeText(ModifyPasswordActivity.this, R.string.text_two_input_not_consistent, Toast.LENGTH_SHORT).show();
                    }





                }
                else{
                        Toast.makeText(ModifyPasswordActivity.this, R.string.text_tost_empty, Toast.LENGTH_SHORT).show();
                    }

                    break;

                }
        }

}

