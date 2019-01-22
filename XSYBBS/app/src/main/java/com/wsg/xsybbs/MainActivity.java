package com.wsg.xsybbs;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.exceptions.HyphenateException;
import com.wsg.xsybbs.activity.ChatActivity;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.fragment.FriendsFragment;
import com.wsg.xsybbs.fragment.MyMessageFragment;
import com.wsg.xsybbs.fragment.MineFragment;
import com.wsg.xsybbs.fragment.NoteFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
主界面
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {
    //Fragment
    private FragmentManager fm;


    public NoteFragment noteFragment;
    public FriendsFragment contactListFragment ;
    public MyMessageFragment myMessageFragment;
    public MineFragment mineFragment;
    private Fragment mCurrent;
    //RelativeLatout
    private RelativeLayout mNoteLayout;
    private RelativeLayout mFriendsLayout;
    private RelativeLayout mMessageLayout;
    private RelativeLayout mMineLayout;
    //TextView
    private TextView mNoteView;
    private TextView mFriendsView;
    private TextView mMessageView;
    private TextView mMineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //初始化界面

        initView();



        //默认显示第一个
        noteFragment = new NoteFragment();
        fm = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_layout, noteFragment);
        fragmentTransaction.commit();
    }


    public void initView() {


        //relativelayout
        mNoteLayout = (RelativeLayout) findViewById(R.id.note_layout_view);
        mNoteLayout.setOnClickListener(this);
        mFriendsLayout = (RelativeLayout) findViewById(R.id.friend_layout_view);
        mFriendsLayout.setOnClickListener(this);
        mMessageLayout = (RelativeLayout) findViewById(R.id.message_layout_view);
        mMessageLayout.setOnClickListener(this);
        mMineLayout = (RelativeLayout) findViewById(R.id.mine_layout_view);
        mMineLayout.setOnClickListener(this);



        //因为需要改背景，所以需要
        mNoteView = (TextView) findViewById(R.id.home_image_view);
        mFriendsView = (TextView) findViewById(R.id.fish_image_view);
        mMessageView = (TextView) findViewById(R.id.message_image_view);
        mMineView = (TextView) findViewById(R.id.mine_image_view);

        //默认第一个条目点击
        mNoteView.setBackgroundResource(R.drawable.note_pressed);





    }



    private void hideFragment(Fragment fragment, FragmentTransaction ft) {
        if (fragment != null) {
            ft.hide(fragment);
        }
    }



    //点击事件
    @Override
    public void onClick(View v) {
        //开启事务
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        switch (v.getId()){
            case R.id.note_layout_view:
                mNoteView.setBackgroundResource(R.drawable.note_pressed);
                mFriendsView.setBackgroundResource(R.drawable.friends);
                mMessageView.setBackgroundResource(R.drawable.message);
                mMineView.setBackgroundResource(R.drawable.mine);

                hideFragment(contactListFragment , fragmentTransaction);
                hideFragment(myMessageFragment, fragmentTransaction);
                hideFragment(mineFragment, fragmentTransaction);


                if (noteFragment == null) {
                    noteFragment = new NoteFragment();
                    fragmentTransaction.add(R.id.content_layout, noteFragment);
                } else {
                    fragmentTransaction.show(noteFragment);
                }


                break;

            case R.id.friend_layout_view:
                mNoteView.setBackgroundResource(R.drawable.note);
                mFriendsView.setBackgroundResource(R.drawable.friends_pressed);
                mMessageView.setBackgroundResource(R.drawable.message);
                mMineView.setBackgroundResource(R.drawable.mine);

                hideFragment(noteFragment, fragmentTransaction);
                hideFragment(myMessageFragment, fragmentTransaction);
                hideFragment(mineFragment, fragmentTransaction);


                if (contactListFragment == null) {
                    contactListFragment = new FriendsFragment();
                    new Thread() {//需要在子线程中调用
                        @Override
                        public void run() {
                            //需要设置联系人列表才能启动fragment
                            contactListFragment.setContactsMap(getContact());

                        }
                    }.start();



                    //设置item点击事件
                    contactListFragment.setContactListItemClickListener(new EaseContactListFragment.EaseContactListItemClickListener() {

                        @Override
                        public void onListItemClicked(EaseUser user) {
                            startActivity(new Intent(getApplicationContext(), ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, user.getUsername()));
                        }
                    });
                    
                    fragmentTransaction.add(R.id.content_layout, contactListFragment );
                } else {
                    fragmentTransaction.show(contactListFragment );
                }

                break;

            case R.id.message_layout_view:
                mNoteView.setBackgroundResource(R.drawable.note);
                mFriendsView.setBackgroundResource(R.drawable.friends);
                mMessageView.setBackgroundResource(R.drawable.message_pressed);
                mMineView.setBackgroundResource(R.drawable.mine);

                hideFragment(noteFragment, fragmentTransaction);
                hideFragment(contactListFragment , fragmentTransaction);
                hideFragment(mineFragment, fragmentTransaction);


                if (myMessageFragment == null) {
                    myMessageFragment = new MyMessageFragment();
                    myMessageFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {

                        //隐藏标题栏
                        //hideTitleBar();
                        @Override
                        public void onListItemClicked(EMConversation conversation) {
                            startActivity(new Intent(getApplicationContext(), ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId()));
                        }
                    });
                    fragmentTransaction.add(R.id.content_layout, myMessageFragment);
                } else {
                    fragmentTransaction.show(myMessageFragment);
                }


                break;

            case R.id.mine_layout_view:
                mNoteView.setBackgroundResource(R.drawable.note);
                mFriendsView.setBackgroundResource(R.drawable.friends);
                mMessageView.setBackgroundResource(R.drawable.message);
                mMineView.setBackgroundResource(R.drawable.mine_pressed);




                hideFragment(noteFragment, fragmentTransaction);
                hideFragment(contactListFragment , fragmentTransaction);
                hideFragment(myMessageFragment, fragmentTransaction);


                if (mineFragment== null) {
                    mineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.content_layout, mineFragment);
                } else {
                    fragmentTransaction.show(mineFragment);
                }


                break;
        }

        fragmentTransaction.commit();
    }



    //获取联系人
    private Map<String, EaseUser> getContact() {
        Map<String, EaseUser> map = new HashMap<>();
        try {
            List<String> userNames =                     EMClient.getInstance().contactManager().getAllContactsFromServer();
//            KLog.e("......有几个好友:" + userNames.size());
            for (String userId : userNames) {
//                KLog.e("好友列表中有 : " + userId);
                map.put(userId, new EaseUser(userId));
            }
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        return map;

    }



}
