package com.wsg.xsybbs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wsg.xsybbs.R;
import com.wsg.xsybbs.bean.Note;
import com.wsg.xsybbs.util.UtilTools;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by wsg
 * on         2018/7/6.
 * function: noteadapter 帖子适配器
 */
public class NoteAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Note> mList;
    private Note note;

    public NoteAdapter(Context mContext, List<Note> mList) {
        this.mContext = mContext;
        this.mList = mList;
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
        ViewHolder viewHolder = null;
        if(convertView==null){
            viewHolder = new ViewHolder();


            convertView = inflater.inflate(R.layout.item_note, null);
            viewHolder.note_profile=(CircleImageView)convertView.findViewById(R.id.item_note_profile);
            viewHolder.title=(TextView)convertView.findViewById(R.id.item_note_title);
            viewHolder.type=(TextView)convertView.findViewById(R.id.item_note_type);
            viewHolder.time=(TextView)convertView.findViewById(R.id.item_note_time);
            viewHolder.content=(TextView)convertView.findViewById(R.id.item_note_content);
            viewHolder.zan=(ImageView) convertView.findViewById(R.id.item_note_zan);
            viewHolder.zancount=(TextView)convertView.findViewById(R.id.item_note_zancount);
            viewHolder.replay=(ImageView)convertView.findViewById(R.id.item_note_replay);
            viewHolder.replaycount=(TextView)convertView.findViewById(R.id.item_note_replaycount);


            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //开始设置数据
        note=mList.get(position);
        if(note.getImage()!=null){
            UtilTools.getImage(mContext,viewHolder.note_profile,note.getImage());
        }else{
            viewHolder.note_profile.setImageResource(R.mipmap.logo);
        }

        viewHolder.title.setText(note.getTitle());
        viewHolder.type.setText(note.getTypeid());
        viewHolder.time.setText(note.getUpdatedAt().substring(0,10));
        viewHolder.content.setText(note.getContent());

        viewHolder.zancount.setText(""+note.getZancount());
        viewHolder.replaycount.setText(""+note.getReplaycount());


        return convertView;
    }


    class ViewHolder {
        public CircleImageView note_profile;
        public TextView title;
        public TextView type;
        public TextView time;
        public TextView content;
        public ImageView zan;
        public TextView zancount;
        public ImageView replay;
        public TextView replaycount;
    }
}




