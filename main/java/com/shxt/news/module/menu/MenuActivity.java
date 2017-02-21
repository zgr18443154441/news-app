package com.shxt.news.module.menu;

import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shxt.news.R;
import com.shxt.news.module.class_news.ClassNewsFragment;
import com.shxt.news.module.top_news.TopNewsFragment;
import com.shxt.news.module.topic.TopicFragment;
import com.shxt.news.module.user.UserFragment;
import com.shxt.news.module.wechat.WechatFragment;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Fragment> list = new ArrayList<>();
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        /**
         *  一个APP如果在主线程中请求网络操作，将会抛出此异常。Android这个设计是为了防止网络请求时间过长而导致界面假死的情况发生。

            解决方案有两个，一个是使用StrictMode，二是使用线程来操作网络请求。
         */
        /*if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }*/

        TopNewsFragment topNewsFragment = new TopNewsFragment();
        ClassNewsFragment classNewsFragment = new ClassNewsFragment();
        TopicFragment topicFragment = new TopicFragment();
        WechatFragment wechatFragment = new WechatFragment();
        UserFragment userFragment = new UserFragment();
        list.add(topNewsFragment);
        list.add(classNewsFragment);
        list.add(topicFragment);
        list.add(wechatFragment);
        list.add(userFragment);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.menu, topNewsFragment);
        fragmentTransaction.commit();

        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ((ImageView)findViewById(R.id.btn_img_1)).setImageResource(R.drawable.news_off);
        ((ImageView)findViewById(R.id.btn_img_2)).setImageResource(R.drawable.class_off);
        ((ImageView)findViewById(R.id.btn_img_3)).setImageResource(R.drawable.topic_off);
        ((ImageView)findViewById(R.id.btn_img_4)).setImageResource(R.drawable.wechat_off);
        ((ImageView)findViewById(R.id.btn_img_5)).setImageResource(R.drawable.user_offline);
        switch (v.getId()){
            case R.id.btn_1:
                ((ImageView)findViewById(R.id.btn_img_1)).setImageResource(R.drawable.news_on);
                fragmentTransaction.replace(R.id.menu, list.get(0));
                break;
            case R.id.btn_2:
                ((ImageView)findViewById(R.id.btn_img_2)).setImageResource(R.drawable.class_on);
                fragmentTransaction.replace(R.id.menu, list.get(1));
                break;
            case R.id.btn_3:
                ((ImageView)findViewById(R.id.btn_img_3)).setImageResource(R.drawable.topic_on);
                fragmentTransaction.replace(R.id.menu, list.get(2));
                break;
            case R.id.btn_4:
                ((ImageView)findViewById(R.id.btn_img_4)).setImageResource(R.drawable.wechat_on);
                fragmentTransaction.replace(R.id.menu, list.get(3));
                break;
            case R.id.btn_5:
                ((ImageView)findViewById(R.id.btn_img_5)).setImageResource(R.drawable.user_onselect);
                fragmentTransaction.replace(R.id.menu, list.get(4));
                break;
        }
        fragmentTransaction.commit();
    }
}
