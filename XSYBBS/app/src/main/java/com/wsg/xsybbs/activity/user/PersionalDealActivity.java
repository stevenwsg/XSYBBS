package com.wsg.xsybbs.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseConstant;
import com.wsg.xsybbs.R;
import com.wsg.xsybbs.activity.ChatActivity;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.User;
import com.wsg.xsybbs.util.L;
import com.wsg.xsybbs.util.UtilTools;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

/**
 * Created by wsg
 * on         2018/6/29.
 * function: 个人详细界面
 */
public class PersionalDealActivity extends BaseActivity implements View.OnClickListener {

    private CircleImageView profile;
    private TextView name;
    private ImageView sex;
    private TextView desc;
    private Button btadd;
    private Button btsend;

    private String id;

    private String n;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persional_deal);

        initView();
    }

    //初始化控件
    private void initView() {
        profile=(CircleImageView)findViewById(R.id.show_profile);
        name=(TextView)findViewById(R.id.show_name);
        sex=(ImageView)findViewById(R.id.show_sex);
        desc=(TextView)findViewById(R.id.show_desc);

        btadd=(Button)findViewById(R.id.btn_add_friend);
        btadd.setOnClickListener(this);
        btsend=(Button)findViewById(R.id.btn_send_message);
        btsend.setOnClickListener(this);

        //加载数据
        initdata();


    }

    private void initdata() {



        L.d("enter initdata");

        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        //获取用户的完整信息

        BmobQuery<User> bmobQuery = new BmobQuery<User>();
        bmobQuery.getObject(id, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e==null){
                    //开始设置数据
                    if(user.getImage()!=null){
                        UtilTools.getImage(PersionalDealActivity.this,profile,user.getImage());
                    }else{
                        profile.setImageResource(R.mipmap.logo);
                    }

                    name.setText(user.getUsername());
                    n=user.getUsername();
                    if (user.isSex()==true){
                        sex.setImageResource(R.drawable.male);
                    }else{
                        sex.setImageResource(R.drawable.female);
                    }

                    desc.setText(user.getDesc());


                }else {
                    L.d(e.toString()+e.getErrorCode()+e.getMessage());
                    Toasty.error(PersionalDealActivity.this,"数据获取失败，请检查网络", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_friend:

                //参数为要添加的好友的username和添加理由
                try{
                    //参数为要添加的好友的username和添加理由
                    EMClient.getInstance().contactManager().addContact(n,"你好，很高兴认识你");

                    Toasty.success(PersionalDealActivity.this,"添加成功",Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    e.printStackTrace();
                    L.d(e.getMessage()+e.toString());
                    Toasty.success(PersionalDealActivity.this,"添加失败，你和对方已经是好友",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_send_message:


                String name =n;

                Intent chat = new Intent(PersionalDealActivity.this,ChatActivity.class);
                chat.putExtra(EaseConstant.EXTRA_USER_ID,name);  //对方账号
                chat.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE); //单聊模式


                startActivity(chat);



                break;
        }
    }
}
