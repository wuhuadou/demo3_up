package com.app.demo.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.demo.R;
import com.app.demo.activitys.VideoActivity;
import com.app.demo.adapters.VideoListAdapter;
import com.app.demo.beans.VideoBean;
import com.app.shop.mylibrary.base.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class   VideoFragment extends BaseFragment {

    @BindView(R.id.recy)
    RecyclerView recy;


    VideoListAdapter adapter;
    List<VideoBean> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, view);
        registerEventBus();
        initData();
        return view;
    }


    private void initData() {

        list.clear();
        list = DataSupport.findAll(VideoBean.class);
        if (list == null || list.size() == 0) {


            int[] pics = new int[]{
                    R.mipmap.v1,
                    R.mipmap.v2,
                    R.mipmap.v3,

            };

            String[] titles = new String[]{
                    "驯龙高手",
                    "赏金律条",
                    "紧急救援",


            };

            String[] play_url = new String[]{
                    "https://hb2345234.oss-cn-beijing.aliyuncs.com/WeChat_20240519235025.mp4",
                    "https://hb2345234.oss-cn-beijing.aliyuncs.com/%E6%8A%96%E9%9F%B3202457-683456.mp4",
                    "https://hb2345234.oss-cn-beijing.aliyuncs.com/WeChat_20240519235014.mp4",



            };

            String[] authors = new String[]{
                    "张梓琳",
                    "刘志强",
                    "糖渍",

            };

            for (int i = 0; i < play_url.length; i++) {
                VideoBean bean = new VideoBean();
                bean.setVideo_url(play_url[i]);
                bean.setTitle(titles[i]);
                bean.setUser_name(authors[i]);
                bean.setUser_photo(R.mipmap.u65);
                bean.setVideo_pic(pics[i]);
                //随机生成1000-10000的任意数
                int s = new Random().nextInt(10000) % (10000 - 1000 + 1) + 1000;
                bean.setPlNum(s);
                bean.save();
                list.add(bean);
            }
        }
        adapter = new VideoListAdapter(R.layout.item_video, list);
        recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy.setAdapter(adapter);

    }
}
