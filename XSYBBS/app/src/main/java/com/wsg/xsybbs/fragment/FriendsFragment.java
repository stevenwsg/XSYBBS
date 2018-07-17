package com.wsg.xsybbs.fragment;


import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.exceptions.HyphenateException;
import com.wsg.xsybbs.R;
import com.wsg.xsybbs.view.CustomDialog;

import es.dmoral.toasty.Toasty;


/**
 * Created by wsg
 * on         2018/6/28.
 * function:好友列表Fragment
 */
public class FriendsFragment extends EaseContactListFragment implements View.OnClickListener {


    private CustomDialog dialog;
    private Button btndelete;
    private Button btncancel;
    private EaseUser easeUser;


    @Override
    protected void initView() {
        super.initView();


        //搜索框默认不能输入，防止键盘弹出，影响交互
        query.setEnabled(false);

        //初始化dialog
        dialog = new CustomDialog(getActivity(), 0, 0,
               R.layout.dialog_delete_friend, R.style.pop_anim_style, Gravity.BOTTOM, 0);
        //提示框以外点击无效
        dialog.setCancelable(false);
        btndelete = (Button) dialog.findViewById(R.id.btn_delete);
        btndelete.setOnClickListener(this);


        btncancel = (Button) dialog.findViewById(R.id.btn_delete_cancle);
        btncancel.setOnClickListener(this);

    }

    //重写父类的方法，添加长按事件
    @Override
    protected void setUpView() {
        super.setUpView();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                easeUser=(EaseUser)listView.getItemAtPosition(position);
                dialog.show();
                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_delete:
                try {
                    EMClient.getInstance().contactManager().deleteContact(easeUser.getUsername());
                    Toasty.success(getActivity(),"删除好友成功",Toast.LENGTH_SHORT).show();

                    //刷新页面
                    contactsMap.remove(easeUser.getUsername());
                    refresh();



                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Toasty.error(getActivity(),"删除好友失败",Toast.LENGTH_SHORT).show();
                }

                dialog.dismiss();
                refresh();
                break;
            case R.id.btn_delete_cancle:
                dialog.dismiss();
                break;
        }
    }
}
