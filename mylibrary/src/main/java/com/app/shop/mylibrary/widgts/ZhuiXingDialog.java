package com.app.shop.mylibrary.widgts;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
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
public class ZhuiXingDialog extends Dialog {
    public ZhuiXingDialog(Context context) {
        super(context);
        this.setCancelable(false);
    }

    public ZhuiXingDialog(Context context, int theme) {
        super(context, theme);
        this.setCancelable(false);
    }

    public static class Builder {
        private int buttonNum = 0;
        private Context context;
        private String title;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private GetContentListener negativeButtonClickListener;


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

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, GetContentListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        /*
         * Dialog的初始化
         * */
        @SuppressLint("ResourceAsColor")
        public ZhuiXingDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final ZhuiXingDialog dialog = new ZhuiXingDialog(context, R.style.Dialog);
            View layout = null;
            if (buttonNum == 2) {
                if (!StringUtil.isEmpty(title)) {
                    layout = isHaveTitle(inflater, dialog);
                }
            }
            dialog.setContentView(layout);

            return dialog;
        }


        private View isHaveTitle(LayoutInflater inflater, ZhuiXingDialog dialog) {
            View layout;
            layout = inflater.inflate(R.layout.dialog_zhuixing, null);
            EditText edt_name = layout.findViewById(R.id.edt_name);
            EditText edt_address = layout.findViewById(R.id.edt_address);
            EditText edt_money = layout.findViewById(R.id.edt_money);
            dialog.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.bt_left)).setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    layout.findViewById(R.id.bt_left)
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                layout.findViewById(R.id.bt_left).setVisibility(View.GONE);
            }

            if (negativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.bt_right))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    layout.findViewById(R.id.bt_right)
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.getContent(dialog, edt_name.getText().toString(), edt_address.getText().toString(), edt_money.getText().toString());
                                }
                            });
                }
            } else {
                layout.findViewById(R.id.bt_right).setVisibility(View.GONE);
            }

            if (contentView != null) {
                ((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content)).addView(contentView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            }

            if (title != null) {
                ((TextView) layout.findViewById(R.id.tv_title)).setText(title);

            }
            return layout;
        }
    }

    public interface GetContentListener {
        void getContent(ZhuiXingDialog dialog, String name, String address, String money);
    }
}