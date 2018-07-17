package com.wsg.xsybbs.activity.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.Toast;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.adapter.GlideImageLoader;
import com.wsg.xsybbs.adapter.TlNoteAdapter;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.Banne;
import com.wsg.xsybbs.bean.Note;
import com.wsg.xsybbs.util.L;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import es.dmoral.toasty.Toasty;

/**
 * Created by wsg
 * on         2018/6/28.
 * function:
 */
public class TouristLoginActivity extends BaseActivity {

    private List<String> listp=new ArrayList<>();
    private Banner banner;
    private ListView lvTLNote;
    private TlNoteAdapter tlnoteadapter;
    private List<Note> mlist=new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_login);
        initView();

    }

    private void initView() {
        listp.add("http://202.200.82.150/u/cms/www/201806/27104449b14a.jpg");
        listp.add("http://202.200.82.150/u/cms/www/201805/16111826e3zf.jpg");
        listp.add("http://202.200.82.150/u/cms/www/201710/30114208slub.jpg");
        listp.add("http://202.200.82.150/u/cms/www/201806/26174701kiz0.png");
        banner = (Banner) findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(listp);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        lvTLNote=(ListView)findViewById(R.id.lv_tl_note);
        tlnoteadapter=new TlNoteAdapter(TouristLoginActivity.this,mlist);
        lvTLNote.setAdapter(tlnoteadapter);

        initData();

        initBanner();

    }

    //初始化banner
    private void initBanner() {
        BmobQuery<Banne> query = new BmobQuery<Banne>();
        query.setLimit(10);
        //按时间降序
        query.order("-createdAt");
        query.findObjects(new FindListener<Banne>() {
            @Override
            public void done(List<Banne> list, BmobException e) {
                if(e==null){
                    listp.clear();
                    for (int i = 0; i <list.size() ; i++) {
                        listp.add(list.get(i).getPhoto());
                    }

                    //重新设置图片集合
                    banner.setImages(listp);
                    //banner设置方法全部调用完毕时最后调用
                    banner.start();


                }
            }
        });


    }

    //初始化数据
    private void initData() {
        //获取数据
        BmobQuery<Note> query = new BmobQuery<Note>();
        query.setLimit(50);
        //按时间降序
        query.order("-top");
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if(e==null){
                    //设置适配器
                    mlist.addAll(list);
                    tlnoteadapter.notifyDataSetChanged();

                }else{
                    L.d(e.toString()+e.getErrorCode()+e.getMessage());
                    Toasty.error(TouristLoginActivity.this,"请求数据失败，请检查网络", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

}
