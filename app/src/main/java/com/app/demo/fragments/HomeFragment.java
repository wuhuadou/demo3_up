package com.app.demo.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.demo.R;
import com.app.demo.activitys.LoginActivity;
import com.app.demo.activitys.SearchActivity;
import com.app.demo.adapters.HomeListAdapter;
import com.app.demo.beans.HomeBean;
import com.app.shop.mylibrary.MyWebActivity;
import com.app.shop.mylibrary.base.BaseFragment;
import com.app.shop.mylibrary.beans.EventMessage;
import com.app.shop.mylibrary.utils.StringUtil;
import com.app.shop.mylibrary.utils.UserManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


/**
 * A simple {@link Fragment} subclass.
 */
public class  HomeFragment extends BaseFragment {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recy)
    RecyclerView recy;

    private List<HomeBean> list = new ArrayList<>();
    private HomeListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        registerEventBus();
        initBanner();
        initList();
        return view;
    }

    //列表
    private void initList() {
        //判断是否已登录
        if (!UserManager.isLogin(getActivity())) {
            skipActivity(getActivity(), LoginActivity.class);
            getActivity().finish();
        }

        recy.setFocusable(false);
        recy.setFocusableInTouchMode(false);
        list.clear();
        list = DataSupport.findAll(HomeBean.class);
        if (list == null || list.size() == 0) {
            initData();
        }
        adapter = new HomeListAdapter(R.layout.item_list, list);
        recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyWebActivity.start(getActivity(), list.get(position).getUrl(), "新闻");
            }
        });
    }


    public void initData() {
        String[] titles = new String[]{
                "《突围》主演丁勇岱：沉浸式演技，妻子让我自豪，儿子让我骄傲",
                "熬夜与不熬夜，过的是不一样的人生",
                "黄晓明的倔强，自己选的老婆，含泪也要走下去",
                "邓超一家下地挖红薯，孙俪扛锄头动作熟练，穿两百元上衣太接地气",

                "许多人不清楚，国家为什么突然鼓励家庭储备物资？到底是咋回事",
                "重磅！辉瑞新冠口服药“疗效惊人”，高危患者住院、死亡风险直降89%！拜登点赞，市值一天暴增1700亿",
                "一天近万人取关，鸿星尔克走红100天后，果然“凉凉了”？",
                "今年300家房企破产，房价能下降多少？孙宏斌给出了底线",
        };
        String[] urls = new String[]{
                "https://www.toutiao.com/a7026897628088648204/?log_from=95f3e2cbe0d98_1636189108763",
                "https://www.toutiao.com/a7027056724708278798/?log_from=bce18eaa69b9b_1636189142781",
                "https://www.toutiao.com/a6993969816956699139/?log_from=444273ecab4b6_1636189187035",
                "https://www.toutiao.com/a7027293781615460872/?log_from=0716f9437a096_1636189229899",

                "https://www.toutiao.com/a7027226338880717349/?log_from=54924fe01bde4_1636189276892",
                "https://www.toutiao.com/a7027242593326989861/?log_from=8eecb7ca8593a8_1636189330955",
                "https://www.toutiao.com/a7026971534719713804/?log_from=fb22c234c0b3f_1636189354437",
                "https://www.toutiao.com/a7027240080917922342/?log_from=afa44bea2d3ba8_1636189437596",
        };
        int[] pics = new int[]{
                R.mipmap.pic_1,
                R.mipmap.pic_2,
                R.mipmap.pic_3,
                R.mipmap.pic_4,

                R.mipmap.pic_5,
                R.mipmap.pic_6,
                R.mipmap.pic_7,
                R.mipmap.pic_8
        };


        for (int i = 0; i < titles.length; i++) {
            HomeBean bean = new HomeBean();
            bean.setId(i);
            bean.setPic(pics[i]);
            bean.setName(titles[i]);
            bean.setUrl(urls[i]);
            bean.save();
            list.add(bean);
        }
    }

    /**
     * 轮播图
     */
    private void initBanner() {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new FresscoImageLoader());
        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置允许手势滑动
        banner.setViewPagerIsScroll(true);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        List list_banner = new ArrayList();

        list_banner.add(R.mipmap.b0);
        list_banner.add(R.mipmap.b1);

        banner.setImages(list_banner);

        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
    }


    public class FresscoImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            int url = (int) path;
            imageView.setImageResource(url);
        }

        //提供createImageView 方法，方便fresco自定义ImageView
        @Override
        public ImageView createImageView(Context context) {

            ImageView simpleDraweeView = (ImageView) getLayoutInflater().inflate(R.layout.layout_banner_imageview, null);
//            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
            return simpleDraweeView;
        }
    }




    @Override
    public void onEvent(EventMessage msg) {
        super.onEvent(msg);

        if (msg.getMessageType() == 111) {
            list.clear();
            List<HomeBean> temp = DataSupport.findAll(HomeBean.class);
            list.addAll(temp);
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.tv_search)
    public void onViewClicked() {

        skipActivity(getActivity(), SearchActivity.class);
    }
}
