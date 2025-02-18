package com.app.demo.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.demo.R;
import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.beans.UserBean;
import com.app.shop.mylibrary.utils.StringUtil;
import com.app.shop.mylibrary.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class     RegisterActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edtMobile)
    EditText edtMobile;
    @BindView(R.id.edtpwd)
    EditText edtpwd;
    @BindView(R.id.edtpwdrepeate)
    EditText edtpwdrepeate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        tvTitle.setText("注册");
    }

    @OnClick({R.id.imgv_return, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgv_return:
                onBackPressed();
                break;

            case R.id.tv_register:
                String name = edtMobile.getText().toString();
                String ps = edtpwd.getText().toString();
                String ps2 = edtpwdrepeate.getText().toString();

                if (StringUtil.isEmpty(name) || StringUtil.isEmpty(ps) || StringUtil.isEmpty(ps2)) {
                    ToastUtil.showToast(this, "请输入");
                    return;
                }

                if (!ps.equals(ps2)) {
                    ToastUtil.showToast(this, "两次密码不一致");
                    return;
                }


                UserBean userBean = new UserBean();
                userBean.setUser_id(name);
                userBean.setName(name);
                userBean.setPassword(ps);
                userBean.setPic(R.mipmap.u65);
                userBean.save();
                ToastUtil.showToast(this, "注册成功");

                onBackPressed();

                break;
        }
    }

}
