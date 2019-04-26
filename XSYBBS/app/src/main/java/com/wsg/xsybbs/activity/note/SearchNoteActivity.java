package com.wsg.xsybbs.activity.note;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.adapter.NoteAdapter;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.Note;
import com.wsg.xsybbs.util.StaticClass;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import es.dmoral.toasty.Toasty;


/**
 * Created by wsg
 * on         2018/7/6.
 * function: 搜索帖子
 */
public class SearchNoteActivity extends BaseActivity implements View.OnClickListener {


    private EditText etSearcNote;
    private ImageView ivSearchNote;
    private TextView tvSearchNote;
    private ListView lvSearchNote;


    private NoteAdapter noteAdapter;
    private List<Note> listnote = new ArrayList<>();


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.SEARCH_NOTE_SUCCESS:

                    tvSearchNote.setVisibility(View.GONE);
                    noteAdapter.notifyDataSetChanged();

                    break;
                case StaticClass.SEARCH_NOTE_SUCCESS_NODATA:
                    tvSearchNote.setText("查找成功，暂无此类记录");

                    break;
                case StaticClass.SEARCH_NOTE_FAILED:
                    Toasty.error(SearchNoteActivity.this, "搜索失败，请检查网络", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_note);
        initView();
    }

    private void initView() {
        etSearcNote = (EditText) findViewById(R.id.et_searchnote);
        ivSearchNote = (ImageView) findViewById(R.id.iv_searchnote);
        ivSearchNote.setOnClickListener(this);

        tvSearchNote = (TextView) findViewById(R.id.tv_searchnote);
        lvSearchNote = (ListView) findViewById(R.id.lv_searchnote);


        noteAdapter = new NoteAdapter(SearchNoteActivity.this, listnote);
        lvSearchNote.setAdapter(noteAdapter);


        lvSearchNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchNoteActivity.this, NoteDetailActivity.class);
                intent.putExtra("note", listnote.get(position));
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_searchnote:


                final String title = etSearcNote.getText().toString().trim();
                listnote.clear();

                if (!TextUtils.isEmpty(title)) {
                    tvSearchNote.setVisibility(View.VISIBLE);

                    //2018/12/29 开启线程
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            BmobQuery<Note> query = new BmobQuery<Note>();
                            query.addWhereEqualTo("title", title);
                            query.setLimit(10);
                            query.findObjects(new FindListener<Note>() {
                                @Override
                                public void done(List<Note> list, BmobException e) {
                                    if (e == null) {
                                        if (list.size() != 0) {


                                            listnote.addAll(list);
                                            handler.sendEmptyMessage(StaticClass.SEARCH_NOTE_SUCCESS);

                                        } else {
                                            handler.sendEmptyMessage(StaticClass.SEARCH_NOTE_SUCCESS_NODATA);
                                        }
                                    } else {
                                        handler.sendEmptyMessage(StaticClass.SEARCH_NOTE_FAILED);
                                    }
                                }
                            });

                        }
                    }).start();

                } else {
                    Toasty.info(SearchNoteActivity.this, "亲，输入框不能为空", Toast.LENGTH_SHORT).show();
                }


                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}
