package com.app.shop.mylibrary.beans;

/**
 * Created by seven on 2016/8/5.
 */

public class MainConfig {

    private String message;
    private String result;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }




    public static class DataBean {

        public String queryCustomTemplet;           //定制列表模板

    }
}