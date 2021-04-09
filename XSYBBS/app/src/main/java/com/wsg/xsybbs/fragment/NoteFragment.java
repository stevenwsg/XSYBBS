package com.wsg.xsybbs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.adapter.NoteAdapterV2;
import com.wsg.xsybbs.bean.BanneData;
import com.wsg.xsybbs.module.addnote.view.AddNoteActivity;
import com.wsg.xsybbs.module.searchnote.view.SearchNoteActivity;

import com.wsg.xsybbs.bean.Banne;
import com.wsg.xsybbs.bean.Note;
import com.wsg.xsybbs.util.L;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import es.dmoral.toasty.Toasty;

/**
 * Created by wsg
 * on         2018/6/28.
 * function:  贴吧Fragment
 */
public class NoteFragment extends Fragment implements View.OnClickListener {

    private EditText etSearch;
    private ImageView ivSearch;
    private ImageView ivPost;

    private RecyclerView rvNote;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Object> dataList = new ArrayList<>();

    private NoteAdapterV2 noteAdapterV2;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, null);

        initView(view);
        return view;
    }

    private void initView(View view) {
        etSearch = (EditText) view.findViewById(R.id.et_note);
        etSearch.setInputType(InputType.TYPE_NULL);
        etSearch.setEnabled(false);
        etSearch.setOnClickListener(this);
        ivSearch = (ImageView) view.findViewById(R.id.iv_search);
        ivSearch.setOnClickListener(this);
        ivPost = (ImageView) view.findViewById(R.id.iv_post);
        ivPost.setOnClickListener(this);


        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);   //设置下拉刷新进度条的颜色
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();   //进行刷新操作
                swipeRefreshLayout.setRefreshing(false);
            }


        });

        rvNote = view.findViewById(R.id.rv_note);
        rvNote.setLayoutManager(new LinearLayoutManager(getActivity()));
        noteAdapterV2 = new NoteAdapterV2();
        rvNote.setAdapter(noteAdapterV2);
        initBanner();
        initData();
    }


    //初始化banner
    private void initBanner() {
        BmobQuery<Banne> query = new BmobQuery<Banne>();
        query.setLimit(5);
        //按时间降序
        query.order("-createdAt");
        query.findObjects(new FindListener<Banne>() {
            @Override
            public void done(List<Banne> list, BmobException e) {
                if (e == null) {
                    dataList.add(0, new BanneData(list));
                    noteAdapterV2.setItems(dataList);
                    noteAdapterV2.notifyItemInserted(0);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.et_note:
            case R.id.iv_search:
                intent = new Intent(getActivity(), SearchNoteActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_post:
                intent = new Intent(getActivity(), AddNoteActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //初始化数据
    private void initData() {
        //获取数据
        BmobQuery<Note> query = new BmobQuery<Note>();
        query.setLimit(10);
        //按时间降序
        query.order("-updatedAt");
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if (e == null) {
                    for (int i = 1; i < dataList.size(); i++) {
                        dataList.remove(i);
                    }
                    dataList.addAll(list);
                    noteAdapterV2.setItems(dataList);
                    noteAdapterV2.notifyDataSetChanged();

                } else {
                    L.d(e.toString() + e.getErrorCode() + e.getMessage());
                    Toasty.error(getActivity(), "请求数据失败，请检查网络", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
