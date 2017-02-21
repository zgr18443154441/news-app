package com.shxt.news.bean;

/**
 * Created by 张国荣 on 2017/1/3.
 */

public class Comment {
    private String id;
    private String news_id;
    private String user_id;
    private String content;
    private int agree_number;
    private String set_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAgree_number() {
        return agree_number;
    }

    public void setAgree_number(int agree_number) {
        this.agree_number = agree_number;
    }

    public String getSet_time() {
        return set_time;
    }

    public void setSet_time(String set_time) {
        this.set_time = set_time;
    }
}
