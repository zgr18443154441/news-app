package com.shxt.news.module.user;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 张国荣 on 2017/1/4.
 */

public class LoginHelper extends SQLiteOpenHelper{
    public LoginHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(id Text primary key autoincrement,nick_name Text,account Text,password Text,sex Text,today_read_number Integer,all_read_number Integer,collect_number Integer,comment_number Integer,message_number Integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
