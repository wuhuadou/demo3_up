package com.app.shop.mylibrary.widgts;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shop.mylibrary.R;
import com.app.shop.mylibrary.utils.StringUtil;


/**
 * @anthor : 大海
 * 每天进步一点点
 * @time :   2020/2/28
 * @description :
 */


public class LoadingDialog {

    static TextView tipTextView;

    public static Dialog createLoadingDialog(Context context, boolean isCancelable, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v
                .findViewById(R.id.dialog_loading_view);// 加载布局
        tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(isCancelable); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);

        return loadingDialog;
    }

    /**
     * 开启dialog
     *
     * @param mDialogUtils
     */
    public static void showDialog(Dialog mDialogUtils) {
        if (mDialogUtils != null && !mDialogUtils.isShowing()) {
            mDialogUtils.show();
        }
    }

    /**
     * 开启dialog
     *
     * @param mDialogUtils
     */
    public static void showDialog(Dialog mDialogUtils, String msg) {
        if (mDialogUtils != null && !mDialogUtils.isShowing()) {
            mDialogUtils.show();
        }

        if (tipTextView != null) {
            tipTextView.setText(StringUtil.getContent(msg));
        }
    }


    /**
     * 关闭dialog
     * <p>
     * http://blog.csdn.net/qq_21376985
     *
     * @param mDialogUtils
     */
    public static void closeDialog(Dialog mDialogUtils) {
        if (mDialogUtils != null && mDialogUtils.isShowing()) {
            mDialogUtils.dismiss();
        }
    }
}
