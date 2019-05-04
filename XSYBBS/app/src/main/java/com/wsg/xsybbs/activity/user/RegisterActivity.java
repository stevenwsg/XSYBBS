package com.wsg.xsybbs.activity.user;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.wsg.xsybbs.R;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.User;
import com.wsg.xsybbs.threadpool.MyThreadPool;
import com.wsg.xsybbs.util.L;
import com.wsg.xsybbs.util.SPUtils;
import com.wsg.xsybbs.util.StaticClass;
import com.wsg.xsybbs.view.CustomDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import es.dmoral.toasty.Toasty;

/**
 * Created by wsg
 * on         2018/6/28.
 * function:  注册用户
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_user;
    private EditText et_age;
    private EditText et_desc;
    private RadioGroup mRadioGroup;
    private EditText et_pass;
    private EditText et_password;
    private EditText et_email;
    private Button btnRegistered;
    //进度条
    private CustomDialog dialog;

    //性别
    private boolean isGender = true;



    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case StaticClass.REGISTER_SUCESS:
                    dialog.dismiss();
                    Toasty.success(RegisterActivity.this, "注册成功,请登录", Toast.LENGTH_SHORT, true).show();
                    finish();
                    break;
                case StaticClass.REGISTER_FAILED:
                    dialog.dismiss();
                    Toasty.error(RegisterActivity.this, "注册失败，请修改用户名重试或检查邮箱格式是否正确", Toast.LENGTH_SHORT, true).show();
                    break;
            }


        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        et_user = (EditText) findViewById(R.id.et_user);
        et_age = (EditText) findViewById(R.id.et_age);
        et_desc = (EditText) findViewById(R.id.et_desc);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_password = (EditText) findViewById(R.id.et_password);
        et_email = (EditText) findViewById(R.id.et_email);
        btnRegistered = (Button) findViewById(R.id.btnRegistered);
        btnRegistered.setOnClickListener(this);

        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_loding_register, R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);
        dialog.setCancelable(false);

        //先把性别判断一下
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_boy) {
                    isGender = true;
                } else if (checkedId == R.id.rb_girl) {
                    isGender = false;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnRegistered:
                //开始写注册逻辑


                //获取到输入框的值
                final String name = et_user.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();
                String pass = et_pass.getText().toString().trim();
                final String password = et_password.getText().toString().trim();
                String email = et_email.getText().toString().trim();


                //判断是否为空
                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(age) &
                        !TextUtils.isEmpty(pass) &
                        !TextUtils.isEmpty(password) &
                        !TextUtils.isEmpty(email)){

                    //判断两次输入的密码是否一致
                    if (pass.equals(password)){
                        dialog.show();
                        //判断简介是否为空，如果为空填为默认
                        if (TextUtils.isEmpty(desc)) {
                            desc = getString(R.string.text_nothing);
                        }

                        //开始填充数据
                        final User user=new User();

                        user.setUsername(name);
                        user.setPassword(password);
                        user.setSex(isGender);
                        user.setBan(false);
                        user.setDesc(desc);
                        user.setAge(Integer.parseInt(age));
                        user.setEmail(email);


                        //注册，开个线程吧，毕竟需要请求网络
                        //2019/5/2 使用全局线程池
                        MyThreadPool.execute(new Runnable() {
                            @Override
                            public void run() {
                                user.signUp(new SaveListener<User>() {
                                    @Override
                                    public void done(User user, BmobException e) {
                                        if(e==null){
                                            //开始注册环信
                                            regresterChat(user.getUsername(),user.getObjectId(),name,password);
                                        }else{
                                            L.d(e.toString()+e.getErrorCode()+e.getMessage());
                                            handler.sendEmptyMessage(StaticClass.REGISTER_FAILED);
                                        }
                                    }
                                });
                            }
                        });
                    }else{
                        Toasty.info(this,  getString(R.string.text_two_input_not_consistent), Toast.LENGTH_SHORT, true).show();
                    }


                    
                }else{
                    Toasty.info(this,  getString(R.string.text_tost_empty), Toast.LENGTH_SHORT, true).show();

                }


                break;
        }
    }

    public void regresterChat(final String name, final String password,final String n,final String p){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(name, password);//同步方法

                    //保存用户信息
                    SPUtils.putString(RegisterActivity.this,StaticClass.USER_NAME,n);
                    SPUtils.putString(RegisterActivity.this,StaticClass.PASSWORD,p);


                    handler.sendEmptyMessage(StaticClass.REGISTER_SUCESS);
                } catch (HyphenateException e1) {
                    e1.printStackTrace();
                    L.e("HyphenateException"+e1.getDescription()+e1.getMessage()+e1.getErrorCode());
                    handler.sendEmptyMessage(StaticClass.REGISTER_FAILED);
                }



            }
        }).start();
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
