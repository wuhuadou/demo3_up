package com.app.demo.beans;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 *   首页新闻表
 */


public class    HomeBean extends DataSupport  implements Serializable {

    private int id;
    private int pic;
    private String name;
    private String url;



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
