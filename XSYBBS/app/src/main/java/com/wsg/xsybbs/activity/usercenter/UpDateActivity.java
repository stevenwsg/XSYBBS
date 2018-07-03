package com.wsg.xsybbs.activity.usercenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.util.UtilTools;

import cn.bmob.v3.update.BmobUpdateAgent;

/**
 * Created by wsg
 * on         2018/6/29.
 * function:
 */
public class UpDateActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvVersion;
    private TextView btUpdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        initView();
    }


    //初始化控件
    private void initView() {

        tvVersion=(TextView)findViewById(R.id.update_tv);
        btUpdate=(Button)findViewById(R.id.update_bt);

        tvVersion.setText("目前版本"+ UtilTools.getVersion(this));
        btUpdate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update_bt:
                //检查更新
                BmobUpdateAgent.setUpdateOnlyWifi(false);
                BmobUpdateAgent.update(this);
                Toast.makeText(this,"暂时没新版本，敬请期待哦~",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
