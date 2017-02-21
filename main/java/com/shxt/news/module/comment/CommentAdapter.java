package com.shxt.news.module.comment;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shxt.news.R;
import com.shxt.news.bean.Comment;
import com.shxt.news.bean.User;
import com.shxt.news.utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by 张国荣 on 2017/1/3.
 */

public class CommentAdapter extends BaseAdapter{
    private User user;
    final String getUserpath = "http://49.140.81.53:8080/news_back/news/one?id=";

    private List<Comment> comments;
    private LayoutInflater layoutInflater;
    private Context context;

    public CommentAdapter(List<Comment> comments, Context context) {
        this.comments = comments;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }

    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class Zujian{
        public TextView uesr_name;
        public TextView set_time;
        public TextView agree_number;
        public TextView comment_content;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            user = new User();
            Map<String,Object> map = new Gson().fromJson((String) msg.obj,new TypeToken<Map<String,Object>>(){}.getType());
            user.setAccount((String) map.get("account"));
            user.setAll_read_number((int)Double.parseDouble(map.get("all_read_number").toString()));
            user.setId((String)map.get("id"));
            user.setNick_name((String)map.get("nick_name"));
            user.setPassword((String)map.get("password"));
            user.setSex((String)map.get("sex"));
            user.setPhoto((String)map.get("photo"));
            user.setToday_read_number((int)Double.parseDouble(map.get("todey_read_number").toString()));
        }
    };

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentAdapter.Zujian zujian=null;
        if(convertView==null){
            zujian=new Zujian();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.top_news_item, null);
            zujian.uesr_name=(TextView)convertView.findViewById(R.id.uesr_name);
            zujian.set_time=(TextView)convertView.findViewById(R.id.set_time);
            zujian.agree_number=(TextView)convertView.findViewById(R.id.agree_number);
            zujian.comment_content=(TextView)convertView.findViewById(R.id.comment_content);
            convertView.setTag(zujian);
        }else{
            zujian=(CommentAdapter.Zujian)convertView.getTag();
        }
        //绑定数据
        this.getUser((String)comments.get(position).getUser_id());
        zujian.uesr_name.setText(user.getNick_name());
        zujian.set_time.setText((String)comments.get(position).getSet_time());
        zujian.comment_content.setText((String)comments.get(position).getContent());
        zujian.agree_number.setText((int)comments.get(position).getAgree_number());
        return convertView;
    }

    public void getUser(final String id){
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL(getUserpath+id);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    if(httpURLConnection.getResponseCode()==200){
                        InputStream inputStream = httpURLConnection.getInputStream();
                        String text = IOUtils.getTextFromStream(inputStream);
                        Message message = handler.obtainMessage();
                        message.obj = text;
                        handler.sendMessage(message);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };
        thread.start();
    }
}
