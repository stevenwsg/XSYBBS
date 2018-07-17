package com.wsg.xsybbs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.bean.Comment;

import java.util.List;


/**
 * Created by wsg
 * on         2018/7/8.
 * function:
 */
public class CommentAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private LayoutInflater inflater;
    private List<Comment> mList;
    private Comment comment;
    private Callback mCallback;

    public CommentAdapter(Context mContext, List<Comment> mList, Callback mCallback) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){

            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_comment, null);
            viewHolder.commentname=(TextView)convertView.findViewById(R.id.item_comment_name);
            viewHolder.commentcontent=(TextView)convertView.findViewById(R.id.item_comment_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        comment=mList.get(position);

        viewHolder.commentname.setText(comment.getUsername()+":");
        viewHolder.commentcontent.setText(comment.getContent());

        viewHolder.commentname.setTag(position);
        viewHolder.commentname.setOnClickListener(this);


        return convertView;
    }




    class ViewHolder {
        private TextView commentname;
        private TextView commentcontent;
    }
}
