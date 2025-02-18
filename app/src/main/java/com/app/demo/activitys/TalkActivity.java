package com.app.demo.activitys;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.app.demo.R;
import com.app.demo.beans.HomeBean;
import com.app.demo.beans.TalkBean;
import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.base.CommonAdapter;
import com.app.shop.mylibrary.base.ViewHolder;
import com.app.shop.mylibrary.beans.EventMessage;
import com.app.shop.mylibrary.utils.StringUtil;
import com.app.shop.mylibrary.utils.TimeUtil;
import com.app.shop.mylibrary.utils.ToastUtil;
import com.app.shop.mylibrary.utils.UserManager;
import com.facebook.drawee.view.SimpleDraweeView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class   TalkActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.rela_empty)
    RelativeLayout rela_empty;
    @BindView(R.id.edt)
    EditText edt;
    @BindView(R.id.tv_bt)
    TextView tvBt;

    @BindView(R.id.imgv_list)
    SimpleDraweeView imgv_list;
    @BindView(R.id.tv_list_item)
    TextView tv_list_item;


    HomeBean quesBean;

    TalkAdapter adapter;
    List<TalkBean> list = new ArrayList<>();
    List<TalkBean> list_all = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        ButterKnife.bind(this);
        initData();
        tvTitle.setText("评论详情");
    }

    private void initData() {

        Bundle bundle = getIntent().getExtras();
        quesBean = (HomeBean) bundle.getSerializable("bena");
        tv_list_item.setText(quesBean.getName());
        imgv_list.setActualImageResource(quesBean.getPic());

        list_all = DataSupport.findAll(TalkBean.class);
        if (list_all.size() > 0) {
            for (int i = 0; i < list_all.size(); i++) {
                if ( list_all.get(i).getQues_id().equals(quesBean.getId()+"")) {
                    list.add(list_all.get(i));
                }
            }
        }

        Collections.reverse(list); // 倒序排列

        adapter = new TalkAdapter(this, (ArrayList) list, R.layout.item_list_talk);
        listview.setAdapter(adapter);

        showOrHideList();
    }


    @Override
    public void onEvent(EventMessage msg) {
        super.onEvent(msg);
        if (msg.getMessageType() == 1000) {
//            int position = (int) msg.getmObject();
//            Bundle bundle = new Bundle();
//            bundle.putString("user_name", list.get(position).getUser());
//            bundle.putString("user_id", list.get(position).getUser_id());
//            showActivity(this, AddMessageActivity.class, bundle);
        }
    }

    @OnClick({R.id.imgv_return, R.id.tv_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgv_return:
                onBackPressed();
                break;

            case R.id.tv_bt:

                if (StringUtil.isEmpty(edt.getText().toString())) {
                    ToastUtil.showToast(this, "请输入内容");
                    return;
                }

                TalkBean bean = new TalkBean();
                bean.setCreate_time(TimeUtil.getTodayData("yyyy-MM-dd HH-mm-ss"));
                bean.setTalk_id(System.currentTimeMillis() + "");
                bean.setUser(UserManager.getUserName(this));
                bean.setUser_id(UserManager.getUserId(this));
                bean.setContent(edt.getText().toString());
                bean.setQues_id(quesBean.getId()+"");
                bean.save();

                list.add(0, bean);
                adapter.notifyDataSetChanged();

                showOrHideList();

                edt.setText("");

                break;
        }
    }

    private void showOrHideList() {
        if (list.size() > 0) {
            listview.setVisibility(View.VISIBLE);
            rela_empty.setVisibility(View.GONE);
        } else {
            listview.setVisibility(View.GONE);
            rela_empty.setVisibility(View.VISIBLE);
        }
    }


    class TalkAdapter extends CommonAdapter {

        public TalkAdapter(Context context, ArrayList datas, int layoutId) {
            super(context, datas, layoutId);
        }

        @Override
        public void setView(ViewHolder holder, Object o, int position) {
            TalkBean bean = (TalkBean) o;
            TextView tv_name = holder.getView(R.id.tv_username);
            TextView tv_time = holder.getView(R.id.tv_time);
            TextView tv_content = holder.getView(R.id.tv_content);

            tv_name.setText(bean.getUser());
            tv_time.setText(bean.getCreate_time());
            tv_content.setText(bean.getContent());


        }
    }

}
