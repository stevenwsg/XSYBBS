package com.wsg.xsybbs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.constants.EaseConstant;
import com.hyphenate.easeui.modules.conversation.EaseConversationListFragment;
import com.hyphenate.easeui.modules.conversation.model.EaseConversationInfo;
import com.hyphenate.easeui.modules.conversation.model.EaseConversationSetStyle;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.widget.EaseImageView;
import com.wsg.xsybbs.activity.ChatActivity;


/**
 * Created by wsg
 * on         2018/6/28.
 * function: 消息列表Fragment
 */
public class MyMessageFragment extends EaseConversationListFragment {

    private final String TAG = getClass().getSimpleName();


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //设置头像尺寸
        conversationListLayout.setAvatarSize(EaseCommonUtils.dip2px(mContext, 50));
        //设置头像样式：0为默认，1为圆形，2为方形(设置方形时，需要配合设置avatarRadius，默认的avatarRadius为50dp)
        conversationListLayout.setAvatarShapeType(EaseImageView.ShapeType.RECTANGLE);
        //设置圆角半径
        conversationListLayout.setAvatarRadius((int) EaseCommonUtils.dip2px(mContext, 5));
        //设置是否隐藏未读消息数，默认为不隐藏
        conversationListLayout.hideUnreadDot(false);
        //设置未读消息数展示位置，默认为左侧
        conversationListLayout.showUnreadDotPosition(EaseConversationSetStyle.UnreadDotPosition.LEFT);
    }

    @Override
    public void onItemClick(View view, int position) {
        super.onItemClick(view, position);
        EaseConversationInfo info = conversationListLayout.getItem(position);
        EMConversation conversation = (EMConversation) info.getInfo();

        Intent intent = new Intent(getContext(), ChatActivity.class);
        intent.putExtra(EaseConstant.EXTRA_CONVERSATION_ID, conversation.conversationId());
        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        startActivity(intent);

        Log.d(TAG, "start chat id:" + conversation.conversationId());

    }

    @Override
    public void onResume() {
        super.onResume();
        conversationListLayout.loadDefaultData();
    }
}
