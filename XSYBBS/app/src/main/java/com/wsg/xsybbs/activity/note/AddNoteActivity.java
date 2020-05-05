package com.wsg.xsybbs.activity.note;

import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hyphenate.util.DensityUtil;
import com.wsg.xsybbs.R;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.Note;
import com.wsg.xsybbs.bean.Type;
import com.wsg.xsybbs.bean.User;
import com.wsg.xsybbs.threadpool.MyThreadPool;
import com.wsg.xsybbs.util.L;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import es.dmoral.toasty.Toasty;


/**
 * Created by wsg
 * on         2018/7/6.
 * function:  发布帖子
 */
public class AddNoteActivity extends BaseActivity implements View.OnClickListener {


    private EditText etNewtitle;
    private EditText etNewcontent;
    private RadioGroup radioGroup;
    private Button btNewpost;



    private List<Type> mList=new ArrayList<>();
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        initview();

    }



    //初始化控件
    private void initview() {
        etNewtitle=(EditText)findViewById(R.id.newnote_title);
        etNewcontent=(EditText)findViewById(R.id.newnote_content);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        btNewpost=(Button)findViewById(R.id.newnote_post);
        btNewpost.setOnClickListener(this);

//        initdata();
//
//        //动态添加radiobutton
//        for (int i = 0; i <mList.size() ; i++) {
//            RadioButton radioButton = new RadioButton(this);
//            radioButton.setText(mList.get(i).getContent());
//            radioButton.setId(i);
//
//            RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            if (i != 0) {
//                lp.leftMargin = DensityUtil.dip2px(this,30);
//            }
//
//            radioButton.setChecked(i == 0);
//            type=mList.get(0).getContent();
//            radioGroup.addView(radioButton, lp);
//
//        }


        type="吐槽";

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton tempButton = (RadioButton)findViewById(checkedId);
                type=tempButton.getText().toString();
            }
        });



    }


    //获取数据
//    private void initdata() {
//        //获取数据
//        BmobQuery<Type> query = new BmobQuery<Type>();
//        query.setLimit(10);
//        //按时间降序
//        query.order("-createdAt");
//        query.findObjects(new FindListener<Type>() {
//            @Override
//            public void done(List<Type> list, BmobException e) {
//                if(e==null) {
//                    mList.addAll(list);
//                }else{
//                    L.d(e.toString()+e.getErrorCode()+e.getMessage());
//                    Toasty.error(AddNoteActivity.this,"板块获取失败，请检查网络", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });
//    }


    //处理点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newnote_post:
                String title=etNewtitle.getText().toString().trim();
                String content=etNewcontent.getText().toString().trim();

                if(!TextUtils.isEmpty(title)&!TextUtils.isEmpty(content)){
                    //获取用户信息
                    User user = BmobUser.getCurrentUser(User.class);
                    //开始填充信息
                    final Note note=new Note();
                    note.setUserid(user.getObjectId());
                    note.setImage(user.getImage());
                    note.setTypeid(type);
                    note.setTop(0);
                    note.setTitle(title);
                    note.setContent(content);
                    note.setZancount(0);
                    note.setReplaycount(0);

                    //使用全局线程池
                    MyThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            note.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if(e==null){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toasty.success(AddNoteActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        });
                                    }else{
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toasty.error(AddNoteActivity.this,"发布失败，请检查网络",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        L.d(e.toString()+e.getMessage()+e.getErrorCode());
                                    }
                                }
                            });
                        }
                    });

                }else{
                    Toasty.info(AddNoteActivity.this,"亲，输入框不能为空",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
