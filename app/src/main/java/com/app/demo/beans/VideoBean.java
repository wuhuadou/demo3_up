package com.app.demo.beans;

import org.litepal.crud.DataSupport;

/**
   视频表
 */


 public class  VideoBean extends DataSupport {

    private int id;
    private int user_photo;
    private String user_name;
    private String video_url;
    private int video_pic;
    private String title;
    private int plNum;

    public int getPlNum() {
        return plNum;
    }

    public void setPlNum(int plNum) {
        this.plNum = plNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(int user_photo) {
        this.user_photo = user_photo;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getVideo_pic() {
        return video_pic;
    }

    public void setVideo_pic(int video_pic) {
        this.video_pic = video_pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
