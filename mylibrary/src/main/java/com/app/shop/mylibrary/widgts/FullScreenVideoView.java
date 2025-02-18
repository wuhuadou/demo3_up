package com.app.shop.mylibrary.widgts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * @anthor : 大海
 * 每天进步一点点
 * @time :   2019/12/24
 * @description :
 */


public class FullScreenVideoView extends VideoView {

    //主要用于这个直接new出来的对象
    public FullScreenVideoView(Context context) {
        super(context);
    }
    //主要用于xml文件中，支持自定义属性
    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //也是主要用于xml文件中，支持自定义属性，同时支持style样式
    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //widthMeasureSpec 包含两个主要的内容 1、 测量模式，2、 测量大小
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width,height);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
