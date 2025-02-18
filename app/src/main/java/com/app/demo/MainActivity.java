package com.app.demo;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.demo.fragments.HomeFragment;
import com.app.demo.fragments.VideoFragment;
import com.app.demo.fragments.MineFragment;
import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class     MainActivity extends BaseActivity {


    Fragment mFragment;
    BaseFragment fragment_home, fragment_video,  fragment_plan, fragment_mine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSwipeEnabled(false);
        initFragment();
    }

    private void initFragment() {
        mFragment = new Fragment();
        if (fragment_home == null) {
            fragment_home = new HomeFragment();
        }
        switchContent(mFragment, fragment_home);
    }

    /**
     * 更换fragment
     *
     * @param from
     * @param to
     */
    public void switchContent(Fragment from, Fragment to) {
        if (mFragment != to) {
            mFragment = to;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.fragment_container, to).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    @OnClick({R.id.ll_1, R.id.ll_2,   R.id.ll_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_1:
                checkPosition(1);
                break;

            case R.id.ll_2:
                checkPosition(2);
                break;



            case R.id.ll_4:
                checkPosition(4);
                break;
        }
    }


    private void checkPosition(int position) {
        if (position == 1) {
            if (fragment_home == null) {
                fragment_home = new HomeFragment();
            }
            switchContent(mFragment, fragment_home);
        } else if (position == 2) {
            if (fragment_video == null) {
                fragment_video = new VideoFragment();
            }
            switchContent(mFragment, fragment_video);
        }  else if (position == 4) {
            if (fragment_mine == null) {
                fragment_mine = new MineFragment();
            }
            switchContent(mFragment, fragment_mine);
        }

    }

}
