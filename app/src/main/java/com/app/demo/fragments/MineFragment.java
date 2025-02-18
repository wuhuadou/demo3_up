package com.app.demo.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.app.demo.R;
import com.app.demo.activitys.LoginActivity;
import com.app.demo.activitys.PassWordResetActivity;
import com.app.shop.mylibrary.base.BaseFragment;
import com.app.shop.mylibrary.beans.EventMessage;
import com.app.shop.mylibrary.utils.DialogUtil;
import com.app.shop.mylibrary.utils.SharedPreferencesUtil;
import com.app.shop.mylibrary.utils.ToastUtil;
import com.app.shop.mylibrary.utils.UserManager;
import com.app.shop.mylibrary.widgts.CustomDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class   MineFragment extends BaseFragment {

    @BindView(R.id.imgv_face)
    ImageView imgvFace;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;

    private String dialog_title = "退出登录";
    private String dialog_content = "是否确定退出登录？";
    private CustomDialog customDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);

        setSelfView();
        return view;
    }

    @Override
    public void onEvent(EventMessage msg) {
        super.onEvent(msg);

        if (msg.getMessageType() == EventMessage.Refresh) {
            setSelfView();
        }
    }


    private void setSelfView() {
        if (UserManager.isLogin(getActivity())) {
            imgvFace.setVisibility(View.VISIBLE);
            tvUserName.setText(UserManager.getUserName(getActivity()));
        } else {
            imgvFace.setVisibility(View.GONE);
            tvUserName.setText("点击登录");
        }
    }

    @OnClick({R.id.rela_modify, R.id.rela_logout, R.id.tv_user_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_user_name:
                if (!UserManager.isLogin(getActivity())) {
                    skipActivity(getActivity(), LoginActivity.class);
                }
                break;
            case R.id.rela_modify:
                if (!UserManager.isLogin(getActivity())) {
                    ToastUtil.showToast(getActivity(), "您还没有登录");
                    return;
                }
                skipActivity(getActivity(), PassWordResetActivity.class);
                break;
            case R.id.rela_logout:
                if (!UserManager.isLogin(getActivity())) {
                    ToastUtil.showToast(getActivity(), "您还没有登录");
                    return;
                }
                Logout();
                break;
        }
    }


    private void Logout() {
        customDialog = DialogUtil.showDialog(getActivity(), customDialog, 2, dialog_title, dialog_content, "取消", "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                SharedPreferencesUtil.removeAll(getContext(), "user");
                setSelfView();


                skipActivity(getActivity(), LoginActivity.class);
                getActivity().finish();
            }
        });

        if (customDialog != null && !customDialog.isShowing()) {
            customDialog.show();
        }

        customDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                customDialog = null;
            }
        });
    }


}
