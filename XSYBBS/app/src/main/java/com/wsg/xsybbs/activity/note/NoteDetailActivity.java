package com.wsg.xsybbs.activity.note;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.activity.user.PersionalDealActivity;
import com.wsg.xsybbs.adapter.CommentAdapter;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.Comment;
import com.wsg.xsybbs.bean.Note;
import com.wsg.xsybbs.bean.User;
import com.wsg.xsybbs.bean.Zan;
import com.wsg.xsybbs.util.L;
import com.wsg.xsybbs.util.UtilTools;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;


/**
 * Created by wsg
 * on         2018/7/8.
 * function: 帖子详情界面
 */
public class NoteDetailActivity extends BaseActivity implements View.OnClickListener, CommentAdapter.Callback {

    private CircleImageView note_profile;
    private TextView title;
    private TextView type;
    private TextView time;
    private TextView content;
    private ImageView zan;
    private TextView zancount;
    private ImageView replay;
    private TextView replaycount;

    private EditText editText;
    private Button button;
    private ListView lvnotedetail;

    private Note note;
    private List<Comment> mlist=new ArrayList<>();
    private CommentAdapter commentAdapter;

    //默认没有点赞
    private Boolean iszan=false;
    //回复数量
    private int count=0;

    private Boolean tag=false;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        initdata();
        initCommentdata();
        initView();

    }




    //初始化数据
    private void initdata() {
        Intent intent=getIntent();
        note= (Note) intent.getSerializableExtra("note");
    }

    private void initView() {
        //寻找控件

        note_profile=(CircleImageView)findViewById(R.id.note_detail_profile);
        title=(TextView)findViewById(R.id.note_detail_title);
        type=(TextView)findViewById(R.id.note_detail_type);
        time=(TextView)findViewById(R.id.note_detail_time);
        content=(TextView)findViewById(R.id.note_detail_content);
        zan=(ImageView)findViewById(R.id.note_detail_zan);
        replay=(ImageView)findViewById(R.id.note_detail_replay);
        zancount=(TextView)findViewById(R.id.note_detail_zancount);
        replaycount=(TextView)findViewById(R.id.note_detail_replaycount);


        editText=(EditText)findViewById(R.id.et_note_detail);
        button=(Button)findViewById(R.id.bt_note_detail);




        //设置数据
        if(note.getImage()!=null){
            UtilTools.getImage(this,note_profile,note.getImage());
        }else{
            note_profile.setImageResource(R.mipmap.logo);
        }

        title.setText(note.getTitle());
        type.setText(note.getTypeid());
        time.setText(note.getUpdatedAt().substring(0,10));
        content.setText(note.getContent());
        zancount.setText(""+note.getZancount());

        count=note.getReplaycount();
        replaycount.setText(""+note.getReplaycount());

        initZanState();


        //点击事件
        note_profile.setOnClickListener(this);
        zan.setOnClickListener(this);
        replay.setOnClickListener(this);
        button.setOnClickListener(this);



        lvnotedetail=(ListView)findViewById(R.id.lv_note_detail);
        commentAdapter=new CommentAdapter(this,mlist,this);
        lvnotedetail.setAdapter(commentAdapter);


    }



    //设置赞初始状态
    private void initZanState() {
        User user = BmobUser.getCurrentUser(User.class);


        BmobQuery<Zan> query = new BmobQuery<Zan>();
        query.addWhereEqualTo("userid",user.getObjectId());
        query.addWhereEqualTo("noteid",note.getObjectId());
        query.findObjects(new FindListener<Zan>() {
            @Override
            public void done(List<Zan> list, BmobException e) {
                if(e==null){
                    if(list.size()!=0){//有数据
                        L.d(list.size()+"");
                        if(list.get(0).getStatus()==true){//有效点赞
                            L.d("有效点赞");
                            zan.setImageResource(R.drawable.zan_pressed);
                            iszan=true;
                            tag=true;
                        }else{
                            L.d("无效赞");
                        }


                    }else{
                        L.d("没有请求到数据 list.size()=0");
                    }


                }
                else{
                    L.d("获取点赞状态失败"+e.getErrorCode()+e.getMessage()+e.toString());
                }

            }
        });


    }

    private void initCommentdata() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                BmobQuery<Comment> query = new BmobQuery<Comment>();
                query.addWhereEqualTo("noteid",note.getObjectId());
                query.setLimit(50);
                query.findObjects(new FindListener<Comment>() {
                    @Override
                    public void done(List<Comment> list, BmobException e) {
                        mlist.addAll(list);
                        commentAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();

    }










    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.note_detail_profile:
                Intent intent=new Intent(NoteDetailActivity.this, PersionalDealActivity.class);
                intent.putExtra("id",note.getUserid());
                startActivity(intent);
                break;
            case R.id.note_detail_zan:

                if(iszan==false){
                    //点赞
                    makesureZan();
                }else{
                    //取消点赞
                    cancelZan();

                }




                break;


            case R.id.bt_note_detail:

                String s=editText.getText().toString().trim();
                if(!TextUtils.isEmpty(s)){
                    final Comment comment=new Comment();
                    comment.setNoteid(note.getObjectId());
                    User user = BmobUser.getCurrentUser(User.class);
                    comment.setContent(s);
                    comment.setUserid(user.getObjectId());
                    comment.setUsername(user.getUsername());

                    comment.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                Toasty.success(NoteDetailActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
                                mlist.add(comment);
                                commentAdapter.notifyDataSetChanged();

                                editText.setText("");
                                //评论数的处理，显示+1，数据库加1
                                replaycount.setText((++count)+"" );




                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {

                                        note.setReplaycount(count);

                                        note.update(note.getObjectId(), new UpdateListener() {
                                            @Override
                                            public void done(BmobException e) {
                                                if(e==null){
                                                    L.d("commentcount更新成功");

                                                }else {
                                                    L.d("commentcount"+e.toString()+e.getMessage()+e.getErrorCode());
                                                }

                                            }
                                        });
                                    }
                                }).start();






                            }else{
                                L.d("commentreason"+e.getErrorCode()+e.toString()+e.getMessage());
                                Toasty.error(NoteDetailActivity.this,"评论失败，请检查网络",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }else{
                    Toasty.info(this, "亲，输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }


    //点赞
    private void makesureZan() {

        User user = BmobUser.getCurrentUser(User.class);

        //无数据创建数据，有数据修改数据




        if(tag==true){

            zan.setImageResource(R.drawable.zan_pressed);
            zancount.setText(""+(Integer.valueOf(zancount.getText().toString().trim())+1));


            BmobQuery<Zan> query = new BmobQuery<Zan>();
            query.addWhereEqualTo("userid",user.getObjectId());
            query.addWhereNotEqualTo("noteid", note.getObjectId());
            query.setLimit(1);

            query.findObjects(new FindListener<Zan>() {
                @Override
                public void done(List<Zan> list, BmobException e) {
                    if(e==null){
                        Zan z=list.get(0);
                        z.setStatus(true);
                        z.update(z.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e==null){
                                    note.setZancount(Integer.valueOf(note.getZancount()+1));
                                    note.update(note.getObjectId(), new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if (e==null){

                                                iszan=true;
                                            }else{
                                                L.d("帖子状态更新失败");
                                            }
                                        }
                                    });

                                }else{
                                    L.d("赞状态更新失败");

                                }
                            }
                        });


                    }else{
                        L.d(e.toString()+e.getErrorCode()+e.getMessage());
                        L.d("查找失败");

                    }

                }
            });





        }else {

            zan.setImageResource(R.drawable.zan_pressed);
            zancount.setText("" + (Integer.valueOf(zancount.getText().toString().trim()) + 1));


            Zan z = new Zan();
            z.setUserid(user.getObjectId());
            z.setUsername(user.getUsername());
            z.setNoteid(note.getObjectId());
            z.setStatus(true);
            z.setNotename(note.getTitle());

            z.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {

                        note.setZancount(note.getZancount() + 1);
                        note.update(note.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {

                                if (e == null) {

                                    iszan = true;
                                    tag = true;


                                } else {
                                    Toasty.error(NoteDetailActivity.this, "点赞失败，请检查网络", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toasty.error(NoteDetailActivity.this, "点赞失败，请检查网络", Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }

    }

    //取消点赞
    private void cancelZan() {


        zan.setImageResource(R.drawable.zan);
        zancount.setText(""+(Integer.valueOf(zancount.getText().toString().trim())-1));
        iszan=false;
        User user = BmobUser.getCurrentUser(User.class);

        BmobQuery<Zan> query = new BmobQuery<Zan>();
        query.addWhereEqualTo("userid",user.getObjectId());
        query.addWhereNotEqualTo("noteid", note.getObjectId());
        query.setLimit(1);

        query.findObjects(new FindListener<Zan>() {
            @Override
            public void done(List<Zan> list, BmobException e) {
                if(e==null){
                    Zan z=list.get(0);
                    z.setStatus(false);
                    z.update(z.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null){
                                note.setZancount(Integer.valueOf(note.getZancount()-1));
                                note.update(note.getObjectId(), new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if (e==null){
                                            iszan=false;
                                        }
                                    }
                                });

                            }else{

                            }
                        }
                    });


                }else{

                }

            }
        });

    }




    //item 的点击事件
    @Override
    public void click(View v) {
        int i=(Integer) v.getTag();
        switch(v.getId()){
            case R.id.item_comment_name:
                Intent intent=new Intent(this,PersionalDealActivity.class);
                intent.putExtra("id",mlist.get(i).getUserid());
                startActivity(intent);
                break;
        }

    }
}
