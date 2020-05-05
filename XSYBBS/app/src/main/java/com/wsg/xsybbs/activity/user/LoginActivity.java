package com.wsg.xsybbs.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.wsg.xsybbs.MainActivity;
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
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

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


    //进度条
    private CustomDialog dialog;

    //圆形头像
    private CircleImageView profile_image;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case StaticClass.LOGIN_SUCESS:
                    dialog.dismiss();
                    //跳转到主页
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case StaticClass.LOGIN_FAILED:
                    dialog.dismiss();
                    Toasty.error(LoginActivity.this, "登录失败，请检查用户名或密码错误", Toast.LENGTH_SHORT, true).show();
                    break;

                case StaticClass.Login_BAN:
                    dialog.dismiss();
                    Toasty.info(LoginActivity.this, "你的账号已被禁止登陆，请联系管理员", Toast.LENGTH_SHORT, true).show();
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

    public void initView() {
        etName = (EditText) findViewById(R.id.et_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.bt_login);
        btnRegister = (Button) findViewById(R.id.bt_registered);
        tvTouristLogin = (TextView) findViewById(R.id.tv_tourist);
        tvForgetPassword = (TextView) findViewById(R.id.tv_forget);


        //设置用户信息
        etName.setText(SPUtils.getString(this, StaticClass.USER_NAME, ""));
        etPassword.setText(SPUtils.getString(this, StaticClass.PASSWORD, ""));


        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_loding_login, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        dialog.setCancelable(false);


        //添加点击事件
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tvTouristLogin.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);



    }

    //点击事件处理
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            //登录的逻辑
            case R.id.bt_login:
                //获取输入框的值
                final String name = etName.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();
                //判断是否为空
                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)) {
                    dialog.show();
                    //填充数据
                    final User user = new User();
                    user.setUsername(name);
                    user.setPassword(password);

                    //使用线程池
                    MyThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            user.login(new SaveListener<User>() {
                                @Override
                                public void done(User user, BmobException e) {
                                    if (e == null) {


                                        if (user.getBan() == false) {


                                            EMClient.getInstance().login(name, user.getObjectId(), new EMCallBack() {//回调
                                                @Override
                                                public void onSuccess() {
                                                    EMClient.getInstance().groupManager().loadAllGroups();
                                                    EMClient.getInstance().chatManager().loadAllConversations();
                                                    L.d("登录聊天服务器成功！");


                                                    //如果用户记得自己的密码，没有进行注册，还需要在这块保存用户信息
                                                    if (SPUtils.getString(LoginActivity.this, StaticClass.USER_NAME, "").equals("")) {
                                                        //保存用户信息
                                                        SPUtils.putString(LoginActivity.this, StaticClass.USER_NAME, name);
                                                        SPUtils.putString(LoginActivity.this, StaticClass.PASSWORD, password);
                                                    }


                                                    //如果第一次登录成功，则以后每次闪屏页直接跳转到 主页
                                                    if (SPUtils.getBoolean(LoginActivity.this, StaticClass.SHARE_IS_LOGIN, true) == true) {
                                                        SPUtils.putBoolean(LoginActivity.this, StaticClass.SHARE_IS_LOGIN, false);
                                                    }


                                                    handler.sendEmptyMessage(StaticClass.LOGIN_SUCESS);
                                                }

                                                @Override
                                                public void onProgress(int progress, String status) {

                                                }

                                                @Override
                                                public void onError(int code, String message) {
                                                    handler.sendEmptyMessage(StaticClass.LOGIN_FAILED);
                                                    L.d("登录聊天服务器失败！");
                                                }
                                            });


                                        } else {
                                            handler.sendEmptyMessage(StaticClass.Login_BAN);

                                        }


                                    } else {
                                        L.d(e.toString() + e.getErrorCode());
                                        handler.sendEmptyMessage(StaticClass.LOGIN_FAILED);
                                    }
                                }
                            });
                        }
                    });


                } else {
                    Toasty.info(LoginActivity.this, "输入框不能为空", Toast.LENGTH_SHORT, true).show();
                }


                break;
            //跳转注册页面
            case R.id.bt_registered:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            //跳转游客登录页面
            case R.id.tv_tourist:
                intent = new Intent(LoginActivity.this, TouristLoginActivity.class);
                startActivity(intent);
                break;
            //跳转忘记密码页面
            case R.id.tv_forget:
                intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
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
