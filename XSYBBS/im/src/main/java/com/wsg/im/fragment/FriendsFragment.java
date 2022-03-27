package com.wsg.im.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.constants.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.interfaces.OnItemLongClickListener;
import com.hyphenate.easeui.modules.contact.EaseContactListFragment;
import com.hyphenate.exceptions.HyphenateException;
import com.wsg.base.view.CustomDialog;
import com.wsg.im.R;
import com.wsg.im.activity.ChatActivity;

import es.dmoral.toasty.Toasty;


/**
 * Created by wsg
 * on         2018/6/28.
 * function:好友列表Fragment
 */
public class FriendsFragment extends EaseContactListFragment implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    private CustomDialog mDialog;
    private Button btDelete;
    private Button btCancel;
    private EaseUser easeUser;

    @Override
    public void onItemClick(View view, int position) {
        super.onItemClick(view, position);
        EaseUser user = contactLayout.getContactList().getItem(position);

        Intent intent = new Intent(getContext(), ChatActivity.class);
        intent.putExtra(EaseConstant.EXTRA_CONVERSATION_ID, user.getNickname());
        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        startActivity(intent);
        Log.d(TAG, "start chat id:" + user.getNickname());
    }

    @Override
    public void onResume() {
        super.onResume();
        contactLayout.loadDefaultData();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        //初始化dialog
        mDialog = new CustomDialog(getActivity(), 0, 0,
                R.layout.dialog_delete_friend, R.style.pop_anim_style, Gravity.BOTTOM, 0);
        //提示框以外点击无效
        mDialog.setCancelable(false);
        btDelete = (Button) mDialog.findViewById(R.id.btn_delete);
        btDelete.setOnClickListener(this);


        btCancel = (Button) mDialog.findViewById(R.id.btn_delete_cancle);
        btCancel.setOnClickListener(this);

        contactLayout.getContactList().setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                easeUser = contactLayout.getContactList().getItem(position);
                mDialog.show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_delete) {
            try {
                EMClient.getInstance().contactManager().deleteContact(easeUser.getUsername());
                Toasty.success(getActivity(), "删除好友成功", Toast.LENGTH_SHORT).show();

                contactLayout.loadDefaultData();
            } catch (HyphenateException e) {
                e.printStackTrace();
                Toasty.error(getActivity(), "删除好友失败", Toast.LENGTH_SHORT).show();
            }

            mDialog.dismiss();
            contactLayout.loadDefaultData();
        } else if (id == R.id.btn_delete_cancle) {
            mDialog.dismiss();
        }
    }
}
