package com.app.shop.mylibrary.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by apple on 16/4/6.
 * 封装的ViewHolder
 */
public class ViewHolder {
    private SparseArray<View> views;
    public int mPositon;
    private View mConvirtView;
    private ViewHolder(Context context, ViewGroup parent, View convertView, int positon, int layoutId){
        this.mPositon = positon;
        this.mConvirtView = convertView;
        this.views = new SparseArray<View>();
        mConvirtView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvirtView.setTag(this);
    }
    public static ViewHolder getViewHolder(Context context, ViewGroup parent, View convertView, int position, int layoutId){
        if (convertView ==null){
            return new ViewHolder(context,parent,convertView,position,layoutId);
        }else{
           ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPositon = position;
            return holder;
        }
    }
    /*获取VeiwHolder中封装的控件*/
    public <T extends View> T getView(int viewId){
        View view = views.get(viewId);
        if (view ==null){
            view = mConvirtView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T)view;
    }
    public View getConvirtView() {
        return mConvirtView;
    }
}
