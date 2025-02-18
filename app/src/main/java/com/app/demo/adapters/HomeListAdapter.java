package com.app.demo.adapters;

import android.content.ContentValues;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.app.demo.R;
import com.app.demo.activitys.TalkActivity;
import com.app.demo.beans.HomeBean;
import com.app.shop.mylibrary.beans.EventMessage;
import com.app.shop.mylibrary.utils.StringUtil;
import com.app.shop.mylibrary.utils.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.litepal.crud.DataSupport;

import java.util.List;

import de.greenrobot.event.EventBus;

public class   HomeListAdapter extends BaseQuickAdapter<HomeBean, HomeListAdapter.ViewHolder> {

    public HomeListAdapter(int layoutResId, @Nullable List<HomeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, HomeBean item) {
        helper.imgv.setImageResource(item.getPic());
        helper.title.setText(StringUtil.getContent(item.getName()));



        helper.imgv_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(mContext,"点赞成功");
//                EventBus.getDefault().post(new EventMessage(111));
            }
        });

        helper.imgv_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TalkActivity.class);
                intent.putExtra("bena",item);
                mContext.startActivity(intent);
            }
        });

    }

    public class ViewHolder extends BaseViewHolder {

        ImageView imgv, imgv_zan,imgv_pl;
        TextView title;

        public ViewHolder(View view) {
            super(view);
            imgv = view.findViewById(R.id.imgv_list);
            title = view.findViewById(R.id.tv_title_item);
            imgv_zan = view.findViewById(R.id.imgv_zan);
            imgv_pl= view.findViewById(R.id.imgv_pl);
        }
    }
}