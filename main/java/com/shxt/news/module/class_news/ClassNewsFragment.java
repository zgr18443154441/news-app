package com.shxt.news.module.class_news;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shxt.news.R;
import com.shxt.news.module.class_news.caijing.CaijingFragment;
import com.shxt.news.module.class_news.guoji.GuoJiFragment;
import com.shxt.news.module.class_news.guonei.GuoNeiFragment;
import com.shxt.news.module.class_news.junshi.JunShiFragment;
import com.shxt.news.module.class_news.keji.KeJiFragment;
import com.shxt.news.module.class_news.shehui.SheHuiFragment;
import com.shxt.news.module.class_news.shishang.ShiShangFragment;
import com.shxt.news.module.class_news.tiyu.TiYuFragment;
import com.shxt.news.module.class_news.yule.YuLeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张国荣 on 2017/1/4.
 */

public class ClassNewsFragment extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener{
    private List<Fragment> list = new ArrayList<>();
    private ViewPager viewPager;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.class_news_fragment , container, false);
        //初始化各版块
        CaijingFragment caijingFragment = new CaijingFragment();
        GuoJiFragment guoJiFragment = new GuoJiFragment();
        GuoNeiFragment guoNeiFragment = new GuoNeiFragment();
        JunShiFragment junShiFragment = new JunShiFragment();
        KeJiFragment keJiFragment = new KeJiFragment();
        SheHuiFragment sheHuiFragment = new SheHuiFragment();
        ShiShangFragment shiShangFragment = new ShiShangFragment();
        TiYuFragment tiYuFragment = new TiYuFragment();
        YuLeFragment yuLeFragment = new YuLeFragment();
        list.add(sheHuiFragment);
        list.add(guoNeiFragment);
        list.add(guoJiFragment);
        list.add(yuLeFragment);
        list.add(tiYuFragment);
        list.add(junShiFragment);
        list.add(keJiFragment);
        list.add(caijingFragment);
        list.add(shiShangFragment);

        viewPager = (ViewPager)view.findViewById(R.id.class_news_viewpager);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        //设置viewpager适配器
        viewPager.setAdapter(new ClassNewsAdapter(fragmentManager));
        //设置翻页监听
        viewPager.addOnPageChangeListener(this);
        //首次进入效果
        ((TextView)view.findViewById(R.id.btn_shehui)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
        viewPager.setCurrentItem(0);
        //设置按键监听
        view.findViewById(R.id.btn_shehui).setOnClickListener(this);
        view.findViewById(R.id.btn_guonei).setOnClickListener(this);
        view.findViewById(R.id.btn_guoji).setOnClickListener(this);
        view.findViewById(R.id.btn_yule).setOnClickListener(this);
        view.findViewById(R.id.btn_tiyu).setOnClickListener(this);
        view.findViewById(R.id.btn_junshi).setOnClickListener(this);
        view.findViewById(R.id.btn_keji).setOnClickListener(this);
        view.findViewById(R.id.btn_caijing).setOnClickListener(this);
        view.findViewById(R.id.btn_shishang).setOnClickListener(this);

        return view;
    }
    //点击监听
    @Override
    public void onClick(View v) {
        ((TextView)view.findViewById(R.id.btn_shehui)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_guonei)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_guoji)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_yule)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_tiyu)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_junshi)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_keji)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_caijing)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_shishang)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        switch (v.getId()){
            case R.id.btn_shehui:
                ((TextView)view.findViewById(R.id.btn_shehui)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                viewPager.setCurrentItem(0);
                break;
            case R.id.btn_guonei:
                ((TextView)view.findViewById(R.id.btn_guonei)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                viewPager.setCurrentItem(1);
                break;
            case R.id.btn_guoji:
                ((TextView)view.findViewById(R.id.btn_guoji)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                viewPager.setCurrentItem(2);
                break;
            case R.id.btn_yule:
                ((TextView)view.findViewById(R.id.btn_yule)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                viewPager.setCurrentItem(3);
                break;
            case R.id.btn_tiyu:
                ((TextView)view.findViewById(R.id.btn_tiyu)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                viewPager.setCurrentItem(4);
                break;
            case R.id.btn_junshi:
                ((TextView)view.findViewById(R.id.btn_junshi)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                viewPager.setCurrentItem(5);
                break;
            case R.id.btn_keji:
                ((TextView)view.findViewById(R.id.btn_keji)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                viewPager.setCurrentItem(6);
                break;
            case R.id.btn_caijing:
                ((TextView)view.findViewById(R.id.btn_caijing)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                viewPager.setCurrentItem(7);
                break;
            case R.id.btn_shishang:
                ((TextView)view.findViewById(R.id.btn_shishang)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                viewPager.setCurrentItem(8);
                break;
        }
    }
    //滑动翻页监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ((TextView)view.findViewById(R.id.btn_shehui)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_guonei)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_guoji)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_caijing)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_tiyu)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_junshi)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_keji)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_yule)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        ((TextView)view.findViewById(R.id.btn_shishang)).setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
        switch (position){
            case 0:
                ((TextView)view.findViewById(R.id.btn_shehui)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                break;
            case 1:
                ((TextView)view.findViewById(R.id.btn_guonei)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                break;
            case 2:
                ((TextView)view.findViewById(R.id.btn_guoji)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                break;
            case 3:
                ((TextView)view.findViewById(R.id.btn_yule)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                break;
            case 4:
                ((TextView)view.findViewById(R.id.btn_tiyu)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                break;
            case 5:
                ((TextView)view.findViewById(R.id.btn_junshi)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                break;
            case 6:
                ((TextView)view.findViewById(R.id.btn_keji)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                break;
            case 7:
                ((TextView)view.findViewById(R.id.btn_caijing)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                break;
            case 8:
                ((TextView)view.findViewById(R.id.btn_shishang)).setBackgroundColor(Color.argb(0xff,0xee,0xee,0xee));
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //滑动翻页适配器
    class ClassNewsAdapter extends FragmentPagerAdapter{

        public ClassNewsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
