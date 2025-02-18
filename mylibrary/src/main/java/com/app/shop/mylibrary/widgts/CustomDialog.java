package com.app.shop.mylibrary.widgts;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shop.mylibrary.R;
import com.app.shop.mylibrary.utils.StringUtil;


/**
 * Created by XiaoHai on 2016/6/22.
 */
public class CustomDialog extends Dialog {
    public CustomDialog(Context context) {
        super(context);
        this.setCancelable(false);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
        this.setCancelable(false);
    }

    public static class Builder {
        private int buttonNum = 0;
        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private String messageColor;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        private OnClickListener closeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder(Context context, int buttonNum) {
            this.buttonNum = buttonNum;
            this.context = context;
        }


        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessageColor(String color) {
            this.messageColor = color;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
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

        public Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public void setCloseButtonClickListener(OnClickListener closeButtonClickListener) {
            this.closeButtonClickListener = closeButtonClickListener;
        }

        /*
         * Dialog的初始化
         * */
        @SuppressLint("ResourceAsColor")
        public CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CustomDialog dialog = new CustomDialog(context, R.style.Dialog);
            View layout = null;
            if (buttonNum == 2) {
                if (!StringUtil.isEmpty(title)) {
                    layout = isHaveTitle(inflater, dialog);
                } else {
                    layout = isNoTitle(inflater, dialog);
                }

            }
            dialog.setContentView(layout);

            return dialog;
        }

        private View isNoTitle(LayoutInflater inflater, CustomDialog dialog) {
            View layout;
            layout = inflater.inflate(R.layout.dialog_no_title, null);
            dialog.addContentView(layout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.bt_left)).setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    layout.findViewById(R.id.bt_left).setOnClickListener(new View.OnClickListener() {
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
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                layout.findViewById(R.id.bt_right).setVisibility(View.GONE);
            }

            if (message != null) {
                ((TextView) layout.findViewById(R.id.tv_message)).setText(message);
            } else if (contentView != null) {
                ((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content)).addView(contentView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            }

            return layout;

        }

        private View isHaveTitle(LayoutInflater inflater, CustomDialog dialog) {
            View layout;
            layout = inflater.inflate(R.layout.dialog_normal_2, null);
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
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                layout.findViewById(R.id.bt_right).setVisibility(View.GONE);
            }

            if (message != null) {
                ((TextView) layout.findViewById(R.id.tv_message)).setText(message);
                if (!StringUtil.isEmpty(messageColor)) {
                    ((TextView) layout.findViewById(R.id.tv_message)).setTextColor(Color.parseColor(messageColor));
                }

            } else if (contentView != null) {
                ((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content)).addView(contentView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            }

            if (title != null) {
                ((TextView) layout.findViewById(R.id.tv_title)).setText(title);

            }
            return layout;
        }
    }


}