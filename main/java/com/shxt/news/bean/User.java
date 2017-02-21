package com.shxt.news.bean;

/**
 * Created by 张国荣 on 2016/12/25.
 */

public class User {
    private String id;
    private String account;
    private String password;
    private String nick_name;
    private String sex;
    private String photo;
    private int today_read_number;
    private int all_read_number;
    private int collect_number;
    private int comment_number;
    private int message_number;

    public int getCollect_number() {
        return collect_number;
    }

    public void setCollect_number(int collect_number) {
        this.collect_number = collect_number;
    }

    public int getComment_number() {
        return comment_number;
    }

    public void setComment_number(int comment_number) {
        this.comment_number = comment_number;
    }

    public int getMessage_number() {
        return message_number;
    }

    public void setMessage_number(int message_number) {
        this.message_number = message_number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getToday_read_number() {
        return today_read_number;
    }

    public void setToday_read_number(int today_read_number) {
        this.today_read_number = today_read_number;
    }

    public int getAll_read_number() {
        return all_read_number;
    }

    public void setAll_read_number(int all_read_number) {
        this.all_read_number = all_read_number;
    }
}
