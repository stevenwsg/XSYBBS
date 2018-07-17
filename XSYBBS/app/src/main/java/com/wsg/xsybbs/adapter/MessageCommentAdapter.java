package com.wsg.xsybbs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.bean.Comment;

import java.util.List;


/**
 * Created by wsg
 * on         2018/7/9.
 * function: MessageCommentAdapter
 */
public class MessageCommentAdapter extends BaseAdapter implements View.OnClickListener {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Comment> mList;
    private Comment comment;
    private Callback mCallback;


    public MessageCommentAdapter(Context mContext, List<Comment> mList, Callback mCallback) {
        this.mContext = mContext;
        this.mList = mList;
        this.mCallback = mCallback;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }


    public interface Callback {
        public void click(View v);
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder viewHolder=null;
        //如果是第一次加载
        if(view==null){
            viewHolder=new ViewHolder();

            view=inflater.inflate(R.layout.item_message_comment,null);
            viewHolder.name=(TextView)view.findViewById(R.id.item_message_comment_tvname);
            viewHolder.content=(TextView) view.findViewById(R.id.item_message_comment_tvcontent);
            //设置缓存
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)view.getTag();
        }


        //设置数据
        comment=mList.get(position);


        viewHolder.name.setText(comment.getUsername());
        viewHolder.content.setText(comment.getContent());


        viewHolder.name.setTag(position);
        viewHolder.name.setOnClickListener(this);




        return view;

    }


    class ViewHolder{
        private TextView name;
        private TextView content;
    }
}
