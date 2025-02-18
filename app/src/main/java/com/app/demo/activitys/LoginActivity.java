package com.app.demo.activitys;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.app.demo.MainActivity;
import com.app.demo.R;
import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.beans.EventMessage;
import com.app.shop.mylibrary.beans.UserBean;
import com.app.shop.mylibrary.utils.SharedPreferencesUtil;
import com.app.shop.mylibrary.utils.StringUtil;
import com.app.shop.mylibrary.utils.ToastUtil;
import com.app.shop.mylibrary.utils.UserManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class    LoginActivity  extends BaseActivity{

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.inputName)
    EditText inputName;
    @BindView(R.id.inputpwd)
    EditText inputpwd;
    @BindView(R.id.imgv_return)
    ImageView imgvReturn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        imgvReturn.setVisibility(View.GONE);
        tvTitle.setText("登录");
    }

    @Override
    public void onEvent(EventMessage msg) {
        super.onEvent(msg);
        if (msg.getMessageType() == EventMessage.LOGIN) {
            finish();
        }
    }

    @OnClick({R.id.toLogin, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toLogin:

                if (StringUtil.isEmpty(inputName.getText().toString()) || StringUtil.isEmpty(inputpwd.getText().toString())) {
                    ToastUtil.showToast(this, "请输入");
                    return;
                }

                boolean isHaveUser = UserManager.isHaveUser(inputName.getText().toString());
                if (isHaveUser) {//有该用户
                    if (UserManager.isOk(inputName.getText().toString(), inputpwd.getText().toString())) { //密码正确
                        UserBean userBean = UserManager.getUser(inputName.getText().toString());
                        SharedPreferencesUtil.saveDataBean(this, userBean, "user");
                        EventBus.getDefault().post(new EventMessage(EventMessage.LOGIN));
                        showActivity(this, MainActivity.class);
                        finish();
                    } else {
                        ToastUtil.showToast(this, "密码不匹配");
                    }
                } else {
                    ToastUtil.showToast(this, "无此用户，请先注册");
                }
                break;


            case R.id.tv_register:
                showActivity(this, RegisterActivity.class);
                break;
        }
    }
}
