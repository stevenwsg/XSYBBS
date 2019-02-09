package com.wsg.xsybbs.fragment;



import android.os.AsyncTask;

import com.hyphenate.easeui.ui.EaseConversationListFragment;


/**
 * Created by wsg
 * on         2018/6/28.
 * function: 消息列表Fragment
 */
public class MyMessageFragment extends EaseConversationListFragment {

    private MyTask myTask;


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
        myTask=new MyTask();
        myTask.execute();
    }


    class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            // run in a second
            final long timeInterval = 10000;
            while (true) {
                // ------- code for task to run
                conversationListView.refresh();
                // ------- ends here
                try {
                    Thread.sleep(timeInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
    }


    @Override
    public void onDestroy() {

        //如果异步任务不为空 并且状态是 运行时  ，就把他取消这个加载任务
        if(myTask !=null && myTask.getStatus() != AsyncTask.Status.FINISHED){
            myTask.cancel(true);

        }
        super.onDestroy();
    }
}
