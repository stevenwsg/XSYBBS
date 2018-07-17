package com.wsg.xsybbs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.bean.Note;
import com.wsg.xsybbs.bean.Zan;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;


/**
 * Created by wsg
 * on         2018/7/9.
 * function:MessageZanAdapter
 */
public class MessageZanAdapter extends BaseAdapter implements View.OnClickListener {
    private Context mContext;
    private LayoutInflater inflater;
    private List<Zan> mList;
    private Zan zan;
    @Override
    public void onClick(View v) {
        mCallback.click(v);
    }

    public interface Callback {
        public void click(View v);
    }

    private Callback mCallback;

    public MessageZanAdapter(Context mContext, List<Zan> mList, Callback mCallback) {
        this.mContext = mContext;
        this.mList = mList;
        this.mCallback = mCallback;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_message_zan, null);
            viewHolder.name=(TextView)convertView.findViewById(R.id.item_message_zan_tvname);
            viewHolder.content=(TextView) convertView.findViewById(R.id.item_message_zan_title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        zan=mList.get(position);

        viewHolder.name.setText(zan.getUsername());
        viewHolder.content.setText(zan.getNotename());



        viewHolder.name.setTag(position);
        viewHolder.name.setOnClickListener(this);


        return convertView;
    }

    class ViewHolder{
        private TextView name;
        private TextView content;
    }
}
