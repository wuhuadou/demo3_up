package com.app.demo.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.app.demo.R;
import com.app.demo.activitys.TalkActivity;
import com.app.demo.activitys.VideoActivity;
import com.app.demo.beans.VideoBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


public class   VideoListAdapter extends BaseQuickAdapter<VideoBean, VideoListAdapter.ViewHolder> {

    public VideoListAdapter(int layoutResId, @Nullable List<VideoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, VideoBean item) {

        helper.imgv_face.setImageResource(item.getUser_photo());

        helper.tv_name.setText(item.getUser_name());
        helper.imgv_video.setImageResource(item.getVideo_pic());
        helper.tv_video_title.setText(item.getTitle());
        helper.tv_num.setText("评论:"+item.getPlNum());

        helper.imgv_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoActivity.class);
                intent.putExtra("url", item.getVideo_url());
                mContext.startActivity(intent);

            }
        });


    }

    public class ViewHolder extends BaseViewHolder {
        ImageView imgv_face;
        TextView tv_name;
        ImageView imgv_video;
        TextView tv_video_title,tv_num;


        public ViewHolder(View view) {
            super(view);
            imgv_face = view.findViewById(R.id.imgv_face);
            tv_name = view.findViewById(R.id.tv_name);
            imgv_video = view.findViewById(R.id.imgv_video);
            tv_video_title = view.findViewById(R.id.tv_video_title);
            tv_num= view.findViewById(R.id.tv_num);
        }
    }
}
