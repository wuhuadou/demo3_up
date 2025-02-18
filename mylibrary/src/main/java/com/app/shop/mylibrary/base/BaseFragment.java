package com.app.shop.mylibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.shop.mylibrary.MyApplication;
import com.app.shop.mylibrary.R;
import com.app.shop.mylibrary.beans.EventMessage;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * @anthor : 大海
 * 每天进步一点点
 * @time :   2019/12/26
 * @description :
 */


public class BaseFragment extends Fragment {

    public BaseActivity mActivity;

    public BaseFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = (BaseActivity) this.getActivity();
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    /**
     * fragment处理back事件的方法
     * return  true表示要处理，false表示不处理
     */
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


        //移除所有的粘性事件
        EventBus.getDefault().removeAllStickyEvents();
        //取消注册
        EventBus.getDefault().unregister(this);

    }

    /**
     * fragment intent跳转
     */
    public void skipActivity(Activity aty, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(aty, cls);
        aty.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.start_in, R.anim.start_out);
    }



    public void skipActivity(Activity aty, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        aty.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.start_in, R.anim.start_out);
    }

    /**
     * 在要接收消息的页面,注册EventBus
     */
    public void registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 在要接收消息页面的onDestroy方法中,反注册EventBus
     */
    public void unRegisterEventBus() {
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEvent(EventMessage msg) {

    }

    @Subscribe(threadMode = ThreadMode.MainThread, sticky = true)
    public void onEventSticky(EventMessage msg) {

    }

}
