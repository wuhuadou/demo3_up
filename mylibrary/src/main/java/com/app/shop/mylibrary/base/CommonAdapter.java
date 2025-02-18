package com.app.shop.mylibrary.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by apple on 16/4/6.
 * 封装的适配器的类
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    public Context context;
    public ArrayList<T> datas;
    protected LayoutInflater inflater;
    protected int layoutId;

    public CommonAdapter(Context context, ArrayList<T> datas, int layoutId) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
    }

    public ArrayList<T> getArrayData() {
        return datas;
    }

    public Context getAdapterContext() {
        return context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.getViewHolder(context, parent, convertView, position, layoutId);
        setView(holder, (T) getItem(position),position);
        return holder.getConvirtView();
    }

    public abstract void setView(ViewHolder holder, T t,int position);
}
