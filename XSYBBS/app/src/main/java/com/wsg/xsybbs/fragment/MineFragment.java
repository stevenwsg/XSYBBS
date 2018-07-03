package com.wsg.xsybbs.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.activity.usercenter.AboutActivity;
import com.wsg.xsybbs.activity.usercenter.ChooseProfileActivity;
import com.wsg.xsybbs.activity.usercenter.FeedBackActivity;
import com.wsg.xsybbs.activity.user.LoginActivity;
import com.wsg.xsybbs.activity.usercenter.ModifyPersionalInformationActivity;
import com.wsg.xsybbs.activity.usercenter.MyMessageActivity;
import com.wsg.xsybbs.activity.usercenter.MyNoteActivity;
import com.wsg.xsybbs.activity.usercenter.UpDateActivity;
import com.wsg.xsybbs.bean.User;
import com.wsg.xsybbs.view.CustomDialog;

import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wsg
 * on         2018/6/28.
 * function: 个人中心Fragment
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    //圆形头像
    private CircleImageView profile_image;

    private TextView edit_user;


    private TextView tv_username;
    private TextView tv_sex;
    private TextView tv_age;
    private TextView tv_desc;




    private TextView tv_mynote;
    private TextView tv_mymessage;
    private TextView tv_about;
    private TextView tv_feedback;
    private TextView tv_update;
    private TextView tv_signout;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        //按照布局依次进行初始化
        profile_image=(CircleImageView) view.findViewById(R.id.profile_image);
        profile_image.setOnClickListener(this);

        edit_user=(TextView)view.findViewById(R.id.edit_user);
        edit_user.setOnClickListener(this);


        tv_username=(TextView) view.findViewById(R.id.tv_username);
        tv_sex=(TextView) view.findViewById(R.id.tv_sex);
        tv_age=(TextView) view.findViewById(R.id.tv_age);
        tv_desc=(TextView) view.findViewById(R.id.tv_desc);





        tv_mynote=(TextView)view.findViewById(R.id.tv_sell);
        tv_mynote.setOnClickListener(this);
        tv_mymessage=(TextView)view.findViewById(R.id.tv_message);
        tv_mymessage.setOnClickListener(this);
        tv_about=(TextView)view.findViewById(R.id.tv_about);
        tv_about.setOnClickListener(this);
        tv_feedback=(TextView)view.findViewById(R.id.tv_back);
        tv_feedback.setOnClickListener(this);
        tv_update=(TextView)view.findViewById(R.id.tv_update);
        tv_update.setOnClickListener(this);
        tv_signout=(TextView)view.findViewById(R.id.tv_sign_out);
        tv_signout.setOnClickListener(this);









        //设置具体的值

        User user = BmobUser.getCurrentUser(User.class);
        tv_username.setText(user.getUsername());
        tv_age.setText(user.getAge() + "");
        tv_sex.setText(user.isSex() ? getString(R.string.text_boy) : getString(R.string.text_girl_f));
        tv_desc.setText(user.getDesc());

    }





    //所有控件的点击事件在这里处理

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.profile_image:
                startActivity(new Intent(getActivity(), ModifyPersionalInformationActivity.class));
                break;
            case R.id.edit_user:
                startActivity(new Intent(getActivity(), ModifyPersionalInformationActivity.class));
                break;
            case R.id.tv_sell:
                startActivity(new Intent(getActivity(), MyNoteActivity.class));
                break;
            case R.id.tv_message:
                startActivity(new Intent(getActivity(), MyMessageActivity.class));
                break;
            case R.id.tv_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.tv_back:
                startActivity(new Intent(getActivity(), FeedBackActivity.class));
                break;
            case R.id.tv_update:
                startActivity(new Intent(getActivity(), UpDateActivity.class));
                break;
            case R.id.tv_sign_out:
                // TODO: 2018/7/1 保存用户信息
                startActivity(new Intent(getActivity(), LoginActivity.class));
                //将主页关掉
                getActivity().finish();
                break;

        }


    }
}
