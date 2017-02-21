package com.shxt.news.module.guide;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 张国荣 on 2016/12/25.
 */

public class GuidePagerAdapter extends PagerAdapter {
    private List<View> views;
    private Context context;

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView(views.get(position));
    }

    @Override
    //返回指定位置position上的列表
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(views.get(position));
        return views.get(position);
    }

    public GuidePagerAdapter(List<View> views, Context context){
        this.views = views;
        this.context = context;
    }

    @Override
    //返回的是数据源对象的个数，即列表项数
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
