package com.app.shop.mylibrary.widgts;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shop.mylibrary.R;


public class MyAlertDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title;

    private LinearLayout dialog_Group;
    private TextView btn_neg;
    private TextView btn_pos;
    private Display display;
    private boolean showLayout = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;

    public MyAlertDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public MyAlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.toast_view_alertdialog, null);

        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(display.getWidth());

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        dialog_Group = (LinearLayout) view.findViewById(R.id.dialog_Group);
        dialog_Group.setVisibility(View.GONE);
        btn_neg = (TextView) view.findViewById(R.id.btn_neg);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (TextView) view.findViewById(R.id.btn_pos);
        btn_pos.setVisibility(View.GONE);
        txt_title = (TextView) view.findViewById(R.id.tv_title_name);


        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);

        // 调整dialog背景大小
        /*lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
				.getWidth() * 0.85), LayoutParams.WRAP_CONTENT));*/

        return this;
    }

    public MyAlertDialog setTitle(String title) {
        if ("".equals(title)) {
            txt_title.setVisibility(View.GONE);
        } else {
            txt_title.setText(title);
        }
        return this;
    }


    public MyAlertDialog setView(View view) {
        showLayout = true;
        if (view == null) {
            showLayout = false;
        } else
            dialog_Group.addView(view,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
        return this;
    }

    public MyAlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public MyAlertDialog setPositiveButton(String text, final OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(text)) {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    public MyAlertDialog setNegativeButton(String text, final OnClickListener listener) {
        if ("".equals(text)) {
            showNegBtn = false;
            btn_neg.setVisibility(View.GONE);
        } else {
            showNegBtn = true;
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    private void setLayout() {
        if (showLayout) {
            dialog_Group.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn) {
            btn_pos.setText("确定");
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);

            btn_neg.setVisibility(View.VISIBLE);

        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);

        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);

        }
    }

    public void show() {
        setLayout();
        dialog.show();
    }
}
