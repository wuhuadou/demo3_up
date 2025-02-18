package com.app.demo.activitys;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.demo.MainActivity;
import com.app.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class      WelcomeActivity extends AppCompatActivity {
    MyCountdownTimer countdowntimer;
    @BindView(R.id.txt_btn)
    TextView txtBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        if (!isTaskRoot()) {
            finish();
            return;
        }

        setFlashPage();  //闪屏广告页
    }

    private void setFlashPage() {

        RelativeLayout mImageView = (RelativeLayout) findViewById(R.id.relay_top);
        ObjectAnimator objectAnimator;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            objectAnimator = ObjectAnimator.ofFloat(mImageView, "alpha", 0.5f, 1);
            objectAnimator.setDuration(1600);
            objectAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    startLoading();
                }

                @Override
                public void onAnimationEnd(Animator animator) {

                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            objectAnimator.start();
        }

    }

    private void startLoading() {
        countdowntimer = new MyCountdownTimer(4000, 1000);
        countdowntimer.start();
    }

    protected class MyCountdownTimer extends CountDownTimer {

        public MyCountdownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            Long s = millisUntilFinished / 1000;
            if (s == 0) {
                onFinish();
            }
            txtBtn.setText("跳过" + s + "s");
        }

        @Override
        public void onFinish() {
            //开启强制登录时，去登录页
            countdowntimer.cancel();
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }
    }

    @OnClick(R.id.txt_btn)
    public void onViewClicked() {
        countdowntimer.onFinish();
        countdowntimer.cancel();

    }
}
