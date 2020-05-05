package com.wsg.xsybbs.activity.user;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.adapter.GlideImageLoader;
import com.wsg.xsybbs.adapter.TlNoteAdapter;
import com.wsg.xsybbs.base.BaseActivity;
import com.wsg.xsybbs.bean.Banne;
import com.wsg.xsybbs.bean.Note;
import com.wsg.xsybbs.threadpool.MyThreadPool;
import com.wsg.xsybbs.util.L;
import com.wsg.xsybbs.util.StaticClass;
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

    private List<String> listp = new ArrayList<>();
    private Banner banner;
    private ListView lvTLNote;
    private TlNoteAdapter tlnoteadapter;
    private List<Note> mlist = new ArrayList<>();


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.TOURIST_NOTE_SUCESS:

                    tlnoteadapter = new TlNoteAdapter(TouristLoginActivity.this, mlist);
                    lvTLNote.setAdapter(tlnoteadapter);

                    break;
                case StaticClass.TOURIST_NOTE_FAILED:
                    Toasty.error(TouristLoginActivity.this, "请求数据失败，请检查网络", Toast.LENGTH_LONG).show();
                    break;
                case StaticClass.TOURIST_BANNER_SUCESS:
                    banner.start();
                    break;
                case StaticClass.TOURIST_BANNER_FAILED:
                    break;
            }
        }
    };


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

        lvTLNote = (ListView) findViewById(R.id.lv_tl_note);


        initData();

        initBanner();

    }

    //初始化banner
    private void initBanner() {





        new Thread(new Runnable() {
            @Override
            public void run() {

                BmobQuery<Banne> query = new BmobQuery<Banne>();
                query.setLimit(50);
                //按时间降序
                query.order("-createdAt");
                query.findObjects(new FindListener<Banne>() {
                    @Override
                    public void done(List<Banne> list, BmobException e) {
                        if (e == null) {
                            listp.clear();
                            for (int i = 0; i < list.size(); i++) {
                                listp.add(list.get(i).getPhoto());
                            }
                            //重新设置图片集合
                            banner.setImages(listp);
                            //banner设置方法全部调用完毕时最后调用
                            handler.sendEmptyMessage(StaticClass.TOURIST_BANNER_SUCESS);
                        } else {

                        }
                    }
                });

            }
        }).start();




    }

    //初始化数据
    private void initData() {


        //2018/12/29  开启线程
        //2019/5/2 全局线程池重构
        MyThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                BmobQuery<Note> query = new BmobQuery<Note>();
                query.setLimit(10);
                //按时间降序
                query.order("-top");
                query.findObjects(new FindListener<Note>() {
                    @Override
                    public void done(List<Note> list, BmobException e) {
                        if (e == null) {
                            //设置适配器
                            mlist.addAll(list);
                            handler.sendEmptyMessage(StaticClass.TOURIST_NOTE_SUCESS);
                        } else {
                            L.d(e.toString() + e.getErrorCode() + e.getMessage());
                            handler.sendEmptyMessage(StaticClass.TOURIST_NOTE_FAILED);

                        }
                    }
                });
            }
        });
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
