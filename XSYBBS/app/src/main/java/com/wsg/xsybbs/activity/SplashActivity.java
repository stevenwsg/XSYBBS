package com.wsg.xsybbs.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import android.widget.TextView;

import com.wsg.xsybbs.MainActivity;
import com.wsg.xsybbs.R;
import com.wsg.xsybbs.activity.user.LoginActivity;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.util.L;
import com.wsg.xsybbs.util.SPUtils;
import com.wsg.xsybbs.util.StaticClass;
import com.wsg.xsybbs.util.UtilTools;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

import static com.wsg.xsybbs.util.StaticClass.SHARE_IS_LOGIN;


/**
 * Created by wsg
 * on         2018/6/28.
 * function:    闪屏页
 *
 */

public class SplashActivity extends BaseActivity{

    private TextView textView;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case StaticClass.HANDLER_SPLASH:
                    if(isFirst()){
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    }
                    else{
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    }
            }
            finish();

        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //解决Android点击图标重新启动问题
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        setContentView(R.layout.activity_splash);
        initView();
        
    }

    private void initView() {

        //设置字体
        textView=(TextView)findViewById(R.id.splash_tv);
        UtilTools.setFont(this,textView);





        //动态申请权限

        /**
         * 请求所有必要的权限----原理就是获取清单文件中申请的权限
         */


        List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
        permissionItems.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "读取手机状态", R.drawable.permission_ic_phone));
        permissionItems.add(new PermissionItem(Manifest.permission.RECORD_AUDIO, "录音", R.drawable.permission_ic_micro_phone));

        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "位置", R.drawable.permission_ic_location));

        permissionItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取文件", R.drawable.permission_ic_storage));
        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入文件", R.drawable.permission_ic_storage));

        HiPermission.create(SplashActivity.this)
                .title("亲爱的用户")
                .permissions(permissionItems)
                .filterColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()))//图标的颜色
                .animStyle(R.style.PermissionAnimScale)//设置动画
                .msg("此应用需要获取以下权限")
                .style(R.style.PermissionBlueStyle)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                        L.d("close");

                    }

                    @Override
                    public void onFinish() {
                        //"所有权限申请完成"
                        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH,2000);
                    }

                    @Override
                    public void onDeny(String permission, int position) {
                        //"onDeny"
                    }

                    @Override
                    public void onGuarantee(String permission, int position) {
                        // "onGuarantee"
                    }
                });



    }

    //判断程序是否第一次运行
    private boolean isFirst() {
        boolean isFirst = SPUtils.getBoolean(this,SHARE_IS_LOGIN,true);
        if(isFirst){
            return true;
        }else {
            return false;
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
