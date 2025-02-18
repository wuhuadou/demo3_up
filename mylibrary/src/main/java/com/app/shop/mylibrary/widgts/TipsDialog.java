package com.app.shop.mylibrary.widgts;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shop.mylibrary.R;
import com.app.shop.mylibrary.utils.StringUtil;


/**
 * Created by XiaoHai on 2016/6/22.
 */
public class TipsDialog extends Dialog {
    public TipsDialog(Context context) {
        super(context);
        this.setCancelable(false);
    }

    public TipsDialog(Context context, int theme) {
        super(context, theme);
        this.setCancelable(false);
    }

    public static class Builder {
        private int buttonNum = 0;
        private Context context;
        private String title;
        private String message;
        private View contentView;
        private OnClickListener onClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder(Context context, int buttonNum) {
            this.buttonNum = buttonNum;
            this.context = context;
        }

        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }


        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        /*
         * Dialog的初始化
         * */
        @SuppressLint("ResourceAsColor")
        public TipsDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final TipsDialog dialog = new TipsDialog(context, R.style.Dialog);
            View layout = null;
            layout = isHaveTitle(inflater, dialog);
            dialog.setContentView(layout);

            return dialog;
        }


        private View isHaveTitle(LayoutInflater inflater, TipsDialog dialog) {
            View layout;
            layout = inflater.inflate(R.layout.dialog_tips, null);
            TextView tv_confirm=layout.findViewById(R.id.bt_comfirm);

            dialog.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

            if (contentView != null) {
                ((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content)).addView(contentView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            }

            if (title != null) {
                ((TextView) layout.findViewById(R.id.tv_title)).setText(title);
            }

            if (message != null) {
                ((TextView) layout.findViewById(R.id.tv_message)).setText(message);
            }

            tv_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onClickListener.onClick(dialog,DialogInterface.BUTTON_POSITIVE);
                }
            });
            return layout;
        }
    }

}