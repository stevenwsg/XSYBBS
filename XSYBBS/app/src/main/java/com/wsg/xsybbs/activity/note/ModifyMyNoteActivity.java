package com.wsg.xsybbs.activity.note;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import com.wsg.xsybbs.R;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.Note;
import com.wsg.xsybbs.threadpool.MyThreadPool;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import es.dmoral.toasty.Toasty;


/**
 * Created by wsg
 * on         2018/7/8.
 * function: 修改帖子
 */
public class ModifyMyNoteActivity extends BaseActivity implements View.OnClickListener {

    private EditText etModifytitle;
    private EditText etModifycontent;
    private RadioGroup radioGroup;
    private Button btModifypost;

    private Note note;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_my_note);
        initview();
    }

    //初始化控件
    private void initview() {
        etModifytitle=(EditText)findViewById(R.id.modify_note_title);
        etModifycontent=(EditText)findViewById(R.id.modify_note_content);
        radioGroup=(RadioGroup)findViewById(R.id.modify_radioGroup);

        btModifypost=(Button)findViewById(R.id.modify_note);
        btModifypost.setOnClickListener(this);

        initData();

        //设置数据
        etModifytitle.setText(note.getTitle());
        etModifycontent.setText(note.getContent());

        for (int i = 0; i <radioGroup.getChildCount() ; i++) {
            RadioButton rd = (RadioButton) radioGroup.getChildAt(i);
            if(rd.getText().toString().trim().equals(note.getTypeid().toString().trim())){
                rd.setChecked(true);
                break;
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton rbtn = (RadioButton) findViewById(checkedId);
                note.setTypeid(rbtn.getText().toString().trim());
            }
        });


    }

    //初始化数据
    private void initData() {
        Intent i=getIntent();
        note= (Note) i.getSerializableExtra("id");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.modify_note:

                String title=etModifytitle.getText().toString().trim();
                String content=etModifycontent.getText().toString().trim();

                if (!TextUtils.isEmpty(title)&!TextUtils.isEmpty(content)){
                    note.setTitle(title);
                    note.setContent(content);

                    MyThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            note.update(note.getObjectId(), new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if(e==null){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toasty.success(ModifyMyNoteActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        });
                                    }else{
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toasty.error(ModifyMyNoteActivity.this,"修改失败，请检查网络", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    });
                }else{
                    Toasty.info(ModifyMyNoteActivity.this,"亲，输入框怒能为空", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
