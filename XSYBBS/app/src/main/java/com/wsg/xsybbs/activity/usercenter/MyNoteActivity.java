package com.wsg.xsybbs.activity.usercenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.module.modifynote.view.ModifyMyNoteActivity;
import com.wsg.xsybbs.adapter.MyNoteAdapter;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.Note;
import com.wsg.xsybbs.bean.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import es.dmoral.toasty.Toasty;

/**
 * Created by wsg
 * on         2018/6/30.
 * function:我的帖子
 */
public class MyNoteActivity extends BaseActivity implements MyNoteAdapter.Callback {

    private TextView tvmynote;
    private ListView lvmynote;
    private List<Note> mList=new ArrayList<>();
    private MyNoteAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynote);
        initview();

    }

    private void initview() {
        tvmynote=(TextView)findViewById(R.id.tv_mynote);
        tvmynote.setText("正在加载，请稍等...");
        lvmynote=(ListView)findViewById(R.id.lv_my_note);

        adapter=new MyNoteAdapter(MyNoteActivity.this,mList,this);
        lvmynote.setAdapter(adapter);

        initdata();

    }
    //初始化数据
    private void initdata() {

        //根据姓名获取内容
        User user = BmobUser.getCurrentUser(User.class);
        user.getUsername();

        //1、获取表中存放的数据
        BmobQuery<Note> query = new BmobQuery<Note>();
        query.addWhereEqualTo("userid",user.getObjectId());

        query.setLimit(50);
        //按照时间降序
        query.order("-createdAt");

        //开始查找
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if(e==null){
                    if(list.size()!=0){
                        tvmynote.setVisibility(View.GONE);
                        mList.addAll(list);
                        adapter.notifyDataSetChanged();
                    }else {
                        tvmynote.setText("亲，你暂时还没发布帖子");
                    }


                }else{
                    Toasty.error(MyNoteActivity.this,"亲，数据获取失败，请检查网络", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public void click(View v) {
        final int i=(Integer) v.getTag();
        switch(v.getId()){
            //修改帖子
            case R.id.item_my_note_modify:
                Intent intent=new Intent(this,ModifyMyNoteActivity.class);
                intent.putExtra("id",mList.get(i));
                startActivity(intent);
                break;

            //删除帖子
            case R.id.item_my_note_delete:
                Note  n=new Note();
                n.setObjectId(mList.get(i).getObjectId());
                n.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {

                        if(e==null){
                            Toasty.success(MyNoteActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                            mList.remove(i);
                            adapter.notifyDataSetChanged();
                            if(mList.size()==0){
                                tvmynote.setVisibility(View.VISIBLE);
                                tvmynote.setText("亲，你已经没有帖子了");
                            }


                        }else{
                            Toasty.error(MyNoteActivity.this,"删除失败，请检查网络",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                break;


        }

    }





}
