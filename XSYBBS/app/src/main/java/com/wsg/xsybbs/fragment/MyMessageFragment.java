package com.wsg.xsybbs.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.wsg.xsybbs.R;
import com.wsg.xsybbs.ThreadPool.MyThreadPool;
import com.wsg.xsybbs.util.L;

/**
 * Created by wsg
 * on         2018/6/28.
 * function: 消息列表Fragment
 */
public class MyMessageFragment extends EaseConversationListFragment {
    @Override
    protected void initView() {
        super.initView();

        ////搜索框默认不能输入，防止键盘弹出，影响交互
        query.setEnabled(false);


//        hideTitleBar();
        initData();
    }
    // TODO: 2018/7/5 需要自己定制





    private void initData() {
        // run in a second
        final long timeInterval = 10000;
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    // ------- code for task to run
                    conversationListView.refresh();
                    // ------- ends here
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        //2019/2/5   试着解决内存泄漏问题
        MyThreadPool.getThreadPool().execute(runnable);
    }


}
