package com.wsg.xsybbs;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.User;
import com.wsg.xsybbs.fragment.FriendsFragment;
import com.wsg.xsybbs.fragment.MessageFragment;
import com.wsg.xsybbs.fragment.MineFragment;
import com.wsg.xsybbs.fragment.NoteFragment;

/*
主界面
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {


    //Fragment
    private FragmentManager fm;
    private NoteFragment noteFragment;
    private FriendsFragment friendsFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
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

                hideFragment(friendsFragment, fragmentTransaction);
                hideFragment(messageFragment, fragmentTransaction);
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
                hideFragment(messageFragment, fragmentTransaction);
                hideFragment(mineFragment, fragmentTransaction);


                if (friendsFragment == null) {
                    friendsFragment = new FriendsFragment();
                    fragmentTransaction.add(R.id.content_layout, friendsFragment);
                } else {
                    fragmentTransaction.show(friendsFragment);
                }

                break;

            case R.id.message_layout_view:
                mNoteView.setBackgroundResource(R.drawable.note);
                mFriendsView.setBackgroundResource(R.drawable.friends);
                mMessageView.setBackgroundResource(R.drawable.message_pressed);
                mMineView.setBackgroundResource(R.drawable.mine);

                hideFragment(noteFragment, fragmentTransaction);
                hideFragment(friendsFragment, fragmentTransaction);
                hideFragment(mineFragment, fragmentTransaction);


                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    fragmentTransaction.add(R.id.content_layout, messageFragment);
                } else {
                    fragmentTransaction.show(messageFragment);
                }


                break;

            case R.id.mine_layout_view:
                mNoteView.setBackgroundResource(R.drawable.note);
                mFriendsView.setBackgroundResource(R.drawable.friends);
                mMessageView.setBackgroundResource(R.drawable.message);
                mMineView.setBackgroundResource(R.drawable.mine_pressed);

                hideFragment(noteFragment, fragmentTransaction);
                hideFragment(friendsFragment, fragmentTransaction);
                hideFragment(messageFragment, fragmentTransaction);


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
}
