package com.app.demo.activitys;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.demo.R;
import com.app.demo.adapters.HomeListAdapter;
import com.app.demo.beans.HomeBean;
import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.utils.StringUtil;
import com.app.shop.mylibrary.utils.ToastUtil;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class    SearchActivity extends BaseActivity {

    @BindView(R.id.imgv_return)
    ImageView imgvReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edt_search)
    EditText edtSearch;
    @BindView(R.id.bt_search)
    Button btSearch;
    @BindView(R.id.listview_search)
    RecyclerView listviewSearch;

    private List<HomeBean> list = new ArrayList<>();
    private HomeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        tvTitle.setText("搜索");
        initAdapter();


    }

    private void initAdapter() {
        adapter = new HomeListAdapter(R.layout.item_list, list);
        listviewSearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

        listviewSearch.setAdapter(adapter);
    }


    private void onSearch(String content) {
        list.clear();
        List<HomeBean> list_all = DataSupport.findAll(HomeBean.class);
        for (int i = 0; i < list_all.size(); i++) {
            if (list_all.get(i).getName().contains(content)) {
                list.add(list_all.get(i));
            }
        }

        if (list.size() == 0) {
            ToastUtil.showToast(this, "没有结果");
        }

        adapter.notifyDataSetChanged();
    }


    @OnClick({R.id.imgv_return, R.id.bt_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgv_return:
                onBackPressed();
                break;
            case R.id.bt_search:
                if (StringUtil.isEmpty(edtSearch.getText().toString())) {
                    ToastUtil.showToast(this, "请输入搜索内容");
                    return;
                }
                onSearch(edtSearch.getText().toString());
                break;
        }
    }
}
