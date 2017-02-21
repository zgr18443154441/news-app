package com.shxt.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shxt.news.plugins.Titanic;
import com.shxt.news.custom.TitanicTextView;
import com.shxt.news.utils.Typefaces;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.loading();
    }

    protected void loading(){
        TitanicTextView tv = (TitanicTextView) findViewById(R.id.my_text_view);
        // set fancy typeface
        tv.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));
        // start animation
        new Titanic().start(tv);
    }
}
