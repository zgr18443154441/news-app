package com.shxt.news.module.guide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shxt.news.MainActivity;
import com.shxt.news.R;
import com.shxt.news.module.menu.MenuActivity;

public class WelcomeActivity extends AppCompatActivity {

    private static final int TIME = 2000;//设置跳转的延迟时间
    private static final int GO_HOME = 1000;//设置跳转到主页面的识别代码
    private static final int GO_GUIDE = 1001;//设置跳转到引导页面的识别代码
    private boolean isFirstIn = false;//判断用户是否是第一次进入
    private Handler mHandler  = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what){
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    private void init(){
        SharedPreferences preferences = getSharedPreferences("oy", MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("flag", true);
        if(!isFirstIn){
            mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
        }else{
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
            SharedPreferences.Editor editor = preferences.edit();//给予修改的权限
            editor.putBoolean("flag", false);//修改isFirstIn的值
            editor.commit();//修改完之后进行提交
        }
    }
    /**
     * 执行跳转到主页面
     */
     private void goHome(){
        Intent intent = new Intent(WelcomeActivity.this , MenuActivity.class);
        startActivity(intent);
        finish();
     }
     /**
     * 执行跳转到引导页面
     */
     private void goGuide(){
        Intent intent = new Intent(WelcomeActivity.this , GuidePageActivity.class);
        startActivity(intent);
        finish();
     }
}
