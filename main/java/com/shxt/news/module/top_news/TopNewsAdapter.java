package com.shxt.news.module.top_news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shxt.news.R;
import com.shxt.news.bean.News;

import java.util.List;
import java.util.Map;

/**
 * Created by 张国荣 on 2016/12/25.
 */

public class TopNewsAdapter extends BaseAdapter{
    private List<News> top_news;
    private LayoutInflater layoutInflater;
    private Context context;
    public TopNewsAdapter(Context context,List<News> top_news){
        this.context=context;
        this.top_news=top_news;
        this.layoutInflater=LayoutInflater.from(context);
    }
    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Zujian{
        public TextView title;
        public TextView author;
        public TextView time;
        public ImageView picture;
    }
    @Override
    public int getCount() {
        return top_news.size();
    }

    @Override
    public Object getItem(int position) {
        return top_news.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zujian zujian=null;
        if(convertView==null){
            zujian=new Zujian();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.top_news_item, null);
            zujian.title=(TextView)convertView.findViewById(R.id.title_item);
            zujian.time=(TextView)convertView.findViewById(R.id.time_item);
            zujian.author=(TextView)convertView.findViewById(R.id.author_item);
            zujian.picture=(ImageView)convertView.findViewById(R.id.pictue_item);
            convertView.setTag(zujian);
        }else{
            zujian=(Zujian)convertView.getTag();
        }
        //绑定数据
        zujian.title.setText((String)top_news.get(position).getTitle());
        zujian.time.setText((String)top_news.get(position).getDate());
        zujian.author.setText((String)top_news.get(position).getAuthor_name());
        new GetPic(zujian.picture,top_news.get(position).getThumbnail_pic_s02()).getPic();
        return convertView;
    }
}
