package com.shxt.news.module.user;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shxt.news.R;
import com.shxt.news.bean.User;
import com.shxt.news.utils.IOUtils;
import com.shxt.news.utils.IpAddressTool;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by 张国荣 on 2017/1/4.
 */

public class UserFragment extends Fragment{
    private View view;
    private User user = new User();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String userStr = (String) msg.obj;
            Map<String,Object> map = new Gson().fromJson(userStr,new TypeToken<Map<String,Object>>(){}.getType());
            //将取到的数据进行封装
            user.setId((String) map.get("id"));
            user.setAccount((String) map.get("account"));
            user.setPassword((String) map.get("password"));
            user.setNick_name((String) map.get("nick_name"));
            user.setSex((String) map.get("sex"));
            user.setToday_read_number((int)Double.parseDouble(map.get("today_read_number").toString()));
            user.setAll_read_number((int)Double.parseDouble(map.get("all_read_number").toString()));
            user.setCollect_number((int)Double.parseDouble(map.get("collect_number").toString()));
            user.setComment_number((int)Double.parseDouble(map.get("comment_number").toString()));
            user.setMessage_number((int)Double.parseDouble(map.get("message_number").toString()));
            //数据加载至界面
            ((TextView)view.findViewById(R.id.user_name)).setText(user.getNick_name());
            ((TextView)view.findViewById(R.id.collect_number)).setText(user.getCollect_number());
            ((TextView)view.findViewById(R.id.comment_number)).setText(user.getComment_number());
            ((TextView)view.findViewById(R.id.message_number)).setText(user.getMessage_number());
            ((TextView)view.findViewById(R.id.today_read_number)).setText(user.getToday_read_number());
            ((TextView)view.findViewById(R.id.all_read_number)).setText(user.getAll_read_number());
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LoginHelper loginHelper = new LoginHelper(getActivity(),"news",null,1);
        //可读写的数据库权限
        SQLiteDatabase sqLiteDatabase = loginHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("user",new String[]{"id","nick_name","account","password","sex","today_read_number","all_read_number","collect_number","comment_number","message_number"},null,null,null,null,"id");
        while (cursor.moveToFirst()){
            user.setId(cursor.getString(0));
            user.setNick_name(cursor.getString(1));
            user.setAccount(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            user.setSex(cursor.getString(4));
            user.setToday_read_number(cursor.getInt(5));
            user.setAll_read_number(cursor.getInt(6));
            user.setCollect_number(cursor.getInt(7));
            user.setComment_number(cursor.getInt(8));
            user.setMessage_number(cursor.getInt(9));
        }
        if("".equals(user.getId())){//存储状态为空（未登录）
            view= inflater.inflate(R.layout.user_offline , container, false);
            //登录跳转
            ((Button)view.findViewById(R.id.btn_login)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }
            });
            //注册跳转
            ((Button)view.findViewById(R.id.btn_register)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),RegisterActivity.class);
                    startActivity(intent);
                }
            });
            return view;
        }else{//存储状态不为空（已登录）
            view= inflater.inflate(R.layout.user_online , container, false);
            //更新数据
            this.getData(user.getId());
            return view;
        }

    }


    //获取数据
    public void getData(final String id){
        final String path = "http://" + IpAddressTool.getIpAddress() + ":8080/news_back/user/one?id="+id;
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
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
