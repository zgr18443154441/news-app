package com.shxt.news.module.top_news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;

import com.shxt.news.R;
import com.shxt.news.bean.Comment;
import com.shxt.news.module.comment.CommentAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsViewActivity extends AppCompatActivity {
    private List<Comment> hot_comments = new ArrayList<>();
    private List<Comment> all_comments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);
        //获取数据
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String url = intent.getStringExtra("url");
        String author_name = intent.getStringExtra("author_name");
        //修改标题
        TextView textView = (TextView)findViewById(R.id.author_name);
        textView.setText(author_name);
        //加载网页
        WebView webView = (WebView)findViewById(R.id.news_id);
        webView.loadUrl(url);

        ListView hot_comment = (ListView)findViewById(R.id.hot_comment);
        hot_comment.setAdapter(new CommentAdapter(hot_comments,this));
        ListView all_comment = (ListView)findViewById(R.id.all_comment);
        all_comment.setAdapter(new CommentAdapter(all_comments,this));
    }
}
