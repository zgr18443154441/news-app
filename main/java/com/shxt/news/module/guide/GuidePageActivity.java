package com.shxt.news.module.guide;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.shxt.news.MainActivity;
import com.shxt.news.R;

import java.util.ArrayList;
import java.util.List;

public class GuidePageActivity extends AppCompatActivity implements OnPageChangeListener{

    private ViewPager viewPager;
    private GuidePagerAdapter guidePagerAdapter;
    private List<View> views;
    private Button btnStart;
    private ImageView dots[];
    private int ids[] = {R.id.iv1,R.id.iv2,R.id.iv3,R.id.iv4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);
        initView();
        initDots();
    }
    /**
    * 初始化View
    */
    private void initView(){
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        //添加四个view
        views.add(inflater.inflate(R.layout.guide_one, null));
        views.add(inflater.inflate(R.layout.guide_two, null));
        views.add(inflater.inflate(R.layout.guide_three, null));
        views.add(inflater.inflate(R.layout.guide_four, null));

        guidePagerAdapter = new GuidePagerAdapter(views, this);

        btnStart = (Button)views.get(3).findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuidePageActivity.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        viewPager = (ViewPager)findViewById(R.id.guide_pager);
        viewPager.setAdapter(guidePagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    /**
    * 初始化小圆点控件
    */
    private void initDots(){
        dots = new ImageView[views.size()];
        for(int i = 0 ; i < ids.length ; i++){
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    /**
    * 当页面滑动的时候调用
    */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
    * 当页面被选择的时候调用
    */
    @Override
    public void onPageSelected(int position) {
        for (int i = 0 ; i<ids.length ; i++){
            //当此页面被选择的时候，将小圆点设置成亮点（white_dot）
            //其他小圆点设置成黑点
            if (i==position){
                dots[i].setImageResource(R.drawable.white_dot);
            }else{
                dots[i].setImageResource(R.drawable.dark_dot);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
