package com.wsg.xsybbs.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wsg.xsybbs.MainActivity;
import com.wsg.xsybbs.R;
import com.wsg.xsybbs.activity.TouristLogin;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.User;
import com.wsg.xsybbs.util.L;
import com.wsg.xsybbs.util.StaticClass;
import com.wsg.xsybbs.view.CustomDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by wsg
 * on         2018/6/28.
 * function: 登录页面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    //控件声明
    private EditText etName;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;
    private TextView tvTouristLogin;
    private TextView tvForgetPassword;
    private TextView tvModifyPassword;

    //进度条
    private CustomDialog dialog;



    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case StaticClass.LOGIN_SUCESS:
                    dialog.dismiss();
                    //跳转到主页
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case StaticClass.LOGIN_FAILED:
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this,"登录失败，请检查用户名或密码错误",Toast.LENGTH_SHORT).show();
                    break;
            }


        }
    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化控件
        initView();
    }
    public void initView(){
        etName=(EditText)findViewById(R.id.et_name);
        etPassword=(EditText)findViewById(R.id.et_password);
        btnLogin=(Button)findViewById(R.id.bt_login);
        btnRegister=(Button)findViewById(R.id.bt_registered);
        tvTouristLogin=(TextView) findViewById(R.id.tv_tourist);
        tvForgetPassword=(TextView) findViewById(R.id.tv_forget);
        tvModifyPassword=(TextView)findViewById(R.id.tv_modify);

        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_loding_login, R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);
        dialog.setCancelable(false);


        //添加点击事件
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tvTouristLogin.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);
        tvModifyPassword.setOnClickListener(this);



    }

    //点击事件处理
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            //登录的逻辑
            case R.id.bt_login:
                //获取输入框的值
                String name = etName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                //判断是否为空
                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)){
                    dialog.show();
                    //填充数据
                    final User user=new User();
                    user.setUsername(name);
                    user.setPassword(password);

                    //登录需要连接网络，开新线程

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            user.login(new SaveListener<User>() {
                                @Override
                                public void done(User user, BmobException e) {
                                    if(e==null){
                                        // TODO: 2018/6/30 别忘了登录环信，并设置自动登录，环信登陆成功之后才能说登陆成功，并跳转
                                        handler.sendEmptyMessage(StaticClass.LOGIN_SUCESS);
                                    }else{
                                        L.d(e.toString()+e.getErrorCode());
                                        handler.sendEmptyMessage(StaticClass.LOGIN_FAILED);
                                    }
                                }
                            });

                        }
                    }).start();


                }else{
                    Toast.makeText(LoginActivity.this,"输入框不能为空",Toast.LENGTH_SHORT);
                }











                    break;
             //跳转注册页面
            case R.id.bt_registered:
                intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
             //跳转游客登录页面
            case R.id.tv_tourist:
                intent=new Intent(LoginActivity.this,TouristLogin.class);
                startActivity(intent);
                break;
             //跳转忘记密码页面
            case R.id.tv_forget:
                intent=new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_modify:
                intent=new Intent(LoginActivity.this,ModifyPasswordActivity.class);
                startActivity(intent);
                break;
        }

    }
}
