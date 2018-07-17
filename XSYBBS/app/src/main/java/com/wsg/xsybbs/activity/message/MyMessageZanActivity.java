package com.wsg.xsybbs.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.wsg.xsybbs.R;
import com.wsg.xsybbs.activity.user.PersionalDealActivity;
import com.wsg.xsybbs.adapter.MessageZanAdapter;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.Note;
import com.wsg.xsybbs.bean.User;
import com.wsg.xsybbs.bean.Zan;
import com.wsg.xsybbs.util.L;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * Created by wsg
 * on         2018/7/9.
 * function: 我收到的赞
 */
public class MyMessageZanActivity extends BaseActivity implements MessageZanAdapter.Callback {

    private TextView textView;
    private ListView listView;
    private List<Zan> mlist = new ArrayList<>();
    private MessageZanAdapter adapter;
    private List<String> nid = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message_zan);

        initView();

        initdata();

    }

    private void initView() {
        textView = (TextView) findViewById(R.id.zan_tv);
        listView = (ListView) findViewById(R.id.zan_lv);
        adapter = new MessageZanAdapter(this, mlist, this);
        listView.setAdapter(adapter);
    }

    //为listview准备数据
    private void initdata() {
        //设置具体的值
        User user = BmobUser.getCurrentUser(User.class);
        BmobQuery<Note> query = new BmobQuery<Note>();
        query.addWhereEqualTo("userid", user.getObjectId());
        query.setLimit(50);
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if (e == null) {
                    if (list.size() != 0) {
                        for (int i = 0; i < list.size(); i++) {
                            nid.add(list.get(i).getObjectId());
                        }
                        //根据noteid查找zan
                        BmobQuery<Zan> q = new BmobQuery<Zan>();
                        q.addWhereContainedIn("noteid", nid);
                        q.addWhereEqualTo("status", true);
                        q.setLimit(50);

                        q.findObjects(new FindListener<Zan>() {
                            @Override
                            public void done(List<Zan> list, BmobException e) {
                                if (e == null) {
                                    if (list.size() != 0) {
                                        textView.setVisibility(View.GONE);
                                        mlist.addAll(list);
                                        adapter.notifyDataSetChanged();

                                    } else {
                                        textView.setText("你暂时未收到赞");
                                    }


                                } else {
                                    L.d("zan");
                                    textView.setText("数据获取失败，请检查网络");
                                }
                            }
                        });


                    } else {
                        textView.setText("你还没有发帖，暂无赞");
                    }

                } else {
                    L.d("nid" + e.getErrorCode() + e.getMessage() + e.toString());
                    textView.setText("数据获取失败，请检查网络");
                }
            }
        });
    }


    //listview   item  点击事件
    @Override
    public void click(View v) {


        int i = (Integer) v.getTag();
        Intent intent = null;

        switch (v.getId()) {
            case R.id.item_message_zan_tvname:
                intent = new Intent(this, PersionalDealActivity.class);
                intent.putExtra("id", mlist.get(i).getUserid());
                startActivity(intent);

                break;

        }
    }
}
