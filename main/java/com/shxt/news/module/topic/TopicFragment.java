package com.shxt.news.module.topic;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shxt.news.R;

/**
 * Created by 张国荣 on 2017/1/4.
 */

public class TopicFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.topic_fragment , container, false);
        return view;
    }
}
