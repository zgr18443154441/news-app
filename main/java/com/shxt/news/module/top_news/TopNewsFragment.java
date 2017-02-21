package com.shxt.news.module.top_news;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shxt.news.R;
import com.shxt.news.bean.News;
import com.shxt.news.utils.IOUtils;
import com.shxt.news.utils.IpAddressTool;
import com.shxt.news.utils.JsonTool;
import com.shxt.news.utils.PageTool;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张国荣 on 2016/12/25.
 */

public class TopNewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,AbsListView.OnScrollListener,AdapterView.OnItemClickListener{
    private ListView listView;
    List<News> top_news = new ArrayList<>();
    private PageTool page = new PageTool();
    //下拉刷新控件
    private SwipeRefreshLayout top_refresh;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){//首次进入/下拉刷新
                String strList = (String)msg.obj;
                //首次进入清空数据
                top_news.clear();
                //获取并封装新闻对象
                Map<String,Object> map = new Gson().fromJson(strList,new TypeToken<Map<String,Object>>(){}.getType());
                List<Map<String,String>> list = (ArrayList<Map<String,String>>)map.get("news");
                for(Map<String,String> m : list){
                    News news = new News();
                    news.setId(m.get("id"));
                    news.setAuthor_name(m.get("author_name"));
                    news.setDate(m.get("date"));
                    news.setIs_top(m.get("is_top"));
                    news.setThumbnail_pic_s(m.get("thumbnail_pic_s"));
                    news.setThumbnail_pic_s02(m.get("thumbnail_pic_s02"));
                    news.setThumbnail_pic_s03(m.get("thumbnail_pic_s03"));
                    news.setTitle(m.get("title"));
                    news.setType(m.get("type"));
                    news.setUrl(m.get("url"));
                    top_news.add(news);
                }
                //获取并封装分页对象
                Map<String,Object> map2 = (Map<String,Object>)map.get("page");
                page.setIndex((int)Double.parseDouble(map2.get("index").toString()));
                page.setSize((int)Double.parseDouble(map2.get("size").toString()));
                page.setCount((int)Double.parseDouble(map2.get("count").toString()));
                page.setAll((int)Double.parseDouble(map2.get("all").toString()));
                Toast.makeText(getActivity(), "更新完成", Toast.LENGTH_SHORT).show();
                top_refresh.setRefreshing(false);

            }else if (msg.what==2){//继续加载下一页数据
                String strList = (String)msg.obj;
                Map<String,Object> map = new Gson().fromJson(strList,new TypeToken<Map<String,Object>>(){}.getType());
                List<Map<String,String>> list = (ArrayList<Map<String,String>>)map.get("news");
                for(Map<String,String> m : list){
                    News news = new News();
                    news.setId(m.get("id"));
                    news.setAuthor_name(m.get("author_name"));
                    news.setDate(m.get("date"));
                    news.setIs_top(m.get("is_top"));
                    news.setThumbnail_pic_s(m.get("thumbnail_pic_s"));
                    news.setThumbnail_pic_s02(m.get("thumbnail_pic_s02"));
                    news.setThumbnail_pic_s03(m.get("thumbnail_pic_s03"));
                    news.setTitle(m.get("title"));
                    news.setType(m.get("type"));
                    news.setUrl(m.get("url"));
                    top_news.add(news);
                }
                Map<String,Object> map2 = (Map<String,Object>)map.get("page");
                page.setIndex((int)Double.parseDouble(map2.get("index").toString()));
                page.setSize((int)Double.parseDouble(map2.get("size").toString()));
                page.setCount((int)Double.parseDouble(map2.get("count").toString()));
                page.setAll((int)Double.parseDouble(map2.get("all").toString()));
            }

            BaseAdapter baseAdapter = (BaseAdapter)listView.getAdapter();
            baseAdapter.notifyDataSetChanged();

        }
    };



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.top_news_fragment , container, false);
        top_refresh = (SwipeRefreshLayout)view.findViewById(R.id.top_news_refresh);
        listView = (ListView)view.findViewById(R.id.top_news);
        //首次加载数据(what=1)
        page.setIndex(1);
        this.getData(1, JsonTool.getJsonString(page));
        //设置头条新闻listview适配器
        listView.setAdapter(new TopNewsAdapter(getActivity(), top_news));
        listView.setOnItemClickListener(this);
        //设置滑动监听
        listView.setOnScrollListener(this);

        //设置下拉刷新变色
        top_refresh.setColorSchemeColors(Color.BLUE,Color.GRAY,Color.RED);
        //设置下拉刷新监听
        top_refresh.setOnRefreshListener(this);

        return view;
    }


    //获取数据
    public void getData(final int what,final String page){
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    String path = "http://" + IpAddressTool.getIpAddress() + ":8080/news_back/news/top_news?page="+page;
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
                        message.what = what;
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



    //下拉刷新方法
    @Override
    public void onRefresh() {
        page.setIndex(1);
        this.getData(1, JsonTool.getJsonString(page));
    }


    //滑动状态改变方法
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //判断listview是否到底
        if (scrollState == SCROLL_STATE_IDLE){
            if (view.getLastVisiblePosition()==view.getCount()-1){
                //数据到底需要翻页
                page.setIndex(page.getIndex()+1);
                this.getData(2, JsonTool.getJsonString(page));
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    //listview每个item的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),NewsViewActivity.class);
        //传值
        intent.putExtra("id",top_news.get(position).getId());
        intent.putExtra("url",top_news.get(position).getUrl());
        intent.putExtra("author_name",top_news.get(position).getAuthor_name());
        startActivity(intent);
    }
}
