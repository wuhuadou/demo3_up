package com.app.shop.mylibrary.widgts;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.app.shop.mylibrary.R;
import com.app.shop.mylibrary.utils.DisplayUtil;


/**
 * Created by liuhaitao
 */

public class MyItemView extends LinearLayout {
    private TextView tvRight;
    private TextView tvLeft;
    private ImageView imgv_left;
    private ImageView imgv_right;
    private View view_redpoint;
    private View tv_line_bottom;

    public MyItemView(Context context) {
        this(context, null, 0);
    }

    public MyItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_my_item, this);
        RelativeLayout rlViewitem = (RelativeLayout) view.findViewById(R.id.rl_viewitem);//条目布局
        imgv_left = view.findViewById(R.id.imgv_left);//图片
        tvLeft = view.findViewById(R.id.tv_viewitem_left);//左文字
        tvRight = view.findViewById(R.id.tv_viewitem_right);//右文字
        tv_line_bottom = view.findViewById(R.id.view_line_bottom);//横线
        view_redpoint = view.findViewById(R.id.view_red_tips);//红点
        imgv_right = view.findViewById(R.id.imgv_viewitem_right);//右侧图标


        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyItemView);

        //左侧属性


        int left_pic = ta.getResourceId(R.styleable.MyItemView_left_pic, 0);
        String left_text = ta.getString(R.styleable.MyItemView_left_text);
        float left_textSizePX = ta.getDimensionPixelSize(R.styleable.MyItemView_left_textSize, 0);
        int left_textColor = ta.getColor(R.styleable.MyItemView_left_textColor, 0);
        boolean show_left_pic = ta.getBoolean(R.styleable.MyItemView_show_left_pic, true);

        String right_text = ta.getString(R.styleable.MyItemView_right_text);
        float right_textSizePX = ta.getDimensionPixelSize(R.styleable.MyItemView_right_textSize, 0);
        int right_textColor = ta.getColor(R.styleable.MyItemView_right_textColor, 0);
        int right_pic = ta.getResourceId(R.styleable.MyItemView_right_pic, 0);
        boolean show_right_pic = ta.getBoolean(R.styleable.MyItemView_show_right_pic, true);


        int itemHeightPX = ta.getDimensionPixelSize(R.styleable.MyItemView_item_height, 0);
        int item_background_color = ta.getColor(R.styleable.MyItemView_item_backgroundcolor, 0);
        int item_background = ta.getResourceId(R.styleable.MyItemView_item_background, 0);
        boolean show_topline = ta.getBoolean(R.styleable.MyItemView_show_topline, false);
        boolean show_bottomline = ta.getBoolean(R.styleable.MyItemView_show_bottomline, false);

        ta.recycle();

        if (!TextUtils.isEmpty(left_text)) {
            tvLeft.setText(left_text);
        }
        if (!TextUtils.isEmpty(right_text)) {
            tvRight.setText(right_text);
        }

        if (show_left_pic) {
            imgv_left.setVisibility(VISIBLE);
        } else {
            imgv_left.setVisibility(GONE);
        }

        if (left_textSizePX != 0) {
            tvLeft.setTextSize(DisplayUtil.px2dip(context, left_textSizePX));
        }
        if (right_textSizePX != 0) {
            tvRight.setTextSize(DisplayUtil.px2dip(context, right_textSizePX));
        }

        if (left_textColor != 0) {
            tvLeft.setTextColor(left_textColor);
        }

        if (left_pic != 0) {
            imgv_left.setImageResource(left_pic);
        }

        if (right_textColor != 0) {
            tvRight.setTextColor(right_textColor);
        }

        if (show_right_pic) {
            imgv_right.setVisibility(VISIBLE);
        } else {
            imgv_right.setVisibility(GONE);
        }

        if (right_pic != 0) {
            imgv_right.setImageResource(right_pic);
        }

        if (itemHeightPX != 0) {
            LayoutParams layoutParams = (LayoutParams) rlViewitem.getLayoutParams();
            layoutParams.height = itemHeightPX;
            rlViewitem.setLayoutParams(layoutParams);
        }
        if (item_background_color != 0) {
            rlViewitem.setBackgroundColor(item_background_color);
        }
        if (item_background != 0) {
            rlViewitem.setBackgroundResource(item_background);
        }

        if (show_bottomline) {
            tv_line_bottom.setVisibility(VISIBLE);
        } else {
            tv_line_bottom.setVisibility(GONE);
        }


    }

    public void setRightImageListener(OnClickListener onClickListener) {
        imgv_right.setOnClickListener(onClickListener);
    }

    public void setRightText(String rightStr) {
        tvRight.setText(rightStr);
    }

    public TextView getTvLeft() {
        return tvLeft;
    }

    public void setLeftText(String rightStr) {
        tvLeft.setText(rightStr);
    }

    public void setRightCorlor(int color) {
        tvRight.setTextColor(color);
    }

    public void setRightTextSize(float textSize) {
        tvRight.setTextSize(textSize);
    }

    public String getRightText() {
        return tvRight.getText().toString();
    }

    public String getLeftText() {
        return tvLeft.getText().toString();
    }

    //红点儿提示
    public void ShowPoint(boolean isShow) {
        if (view_redpoint != null) {
            if (isShow) {
                view_redpoint.setVisibility(VISIBLE);
            } else {
                view_redpoint.setVisibility(GONE);
            }
        }
    }
}
