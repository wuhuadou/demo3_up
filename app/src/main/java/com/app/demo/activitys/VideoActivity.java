package com.app.demo.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.app.demo.R;
import com.app.shop.mylibrary.base.BaseActivity;
import com.app.shop.mylibrary.utils.StringUtil;
import com.app.shop.mylibrary.utils.ToastUtil;
import com.app.shop.mylibrary.widgts.CustomDialog;
import com.app.shop.mylibrary.widgts.LoadingDialog;
import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class    VideoActivity extends BaseActivity {

    @BindView(R.id.video_view)
    VideoView videoView;
    @BindView(R.id.imgv_play)
    ImageView imgv_play;
    @BindView(R.id.imgv_close)
    ImageView imgvClose;
    @BindView(R.id.tv_current_time)
    TextView tvCurrentTime;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.tv_total_time)
    TextView tvTotalTime;

    private boolean isPause = true;
    private boolean isClickPause = false;
    private boolean isOnpauseStatus = false;
    private int progress;
    private String play_url;

    private boolean isBeginThread = false;

    private boolean isFirst;

    private Dialog mLoadingDialog;

    MyHandler handler;

    private Runnable runnable = new Runnable() {
        public void run() {
            if (videoView.isPlaying()) {
                int current = videoView.getCurrentPosition();
                seekBar.setProgress(current);
                tvCurrentTime.setText(getTime(videoView.getCurrentPosition()));
            }
            handler.postDelayed(runnable, 500);
        }
    };


    private static class MyHandler extends Handler {
        WeakReference<VideoActivity> wrf = null;

        public MyHandler(VideoActivity activity) {
            wrf = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            VideoActivity activity = wrf.get();
            if (activity == null) {
                return;
             }
            activity.doMessgae(msg);
        }
    }

    private void doMessgae(Message msg) {
        switch (msg.what) {
            case 1:
                LoadingDialog.closeDialog(mLoadingDialog);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        initData();

        initListener(); //seekbar监听
    }

    private void initData() {

        if (handler == null) {
            handler = new MyHandler(this);
        }
        isFirst = true;
        play_url = getIntent().getStringExtra( "url");
        initLoadingDialog(); //等待的loading框
        initVideoView(play_url); //播放器

    }

    //等待的loading框
    private void initLoadingDialog() {
        mLoadingDialog = LoadingDialog.createLoadingDialog(this, true, "请稍后，视频加载中...");
    }

    //seekbar监听
    private void initListener() {
        // 为进度条添加进度更改事件
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            // 当进度条停止修改的时候触发
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 取得当前进度条的刻度
                progress = seekBar.getProgress();
                if (videoView.isPlaying()) {
                    // 设置当前播放的位置
                    videoView.seekTo(progress);
                }
            }
        });
    }


    //播放器设置
    private void initVideoView(String play_url) {

//        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.video));
        videoView.setVideoURI(Uri.parse(play_url));
        videoView.requestFocus();

        //准备
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //准备好了  设置数据
                handler.sendEmptyMessage(1); // 关闭loading
                if (isFirst) {
                    isFirst = false;
                    tvCurrentTime.setText("00:00");
                    tvTotalTime.setText(getTime(videoView.getDuration()));
                    seekBar.setMax(videoView.getDuration());
                    videoView.start();
                    // 开始线程，更新进度条的刻度
                    OpenOrCloseThread();
                }

                if (isOnpauseStatus && !isClickPause) {
                    videoView.seekTo(progress);
                    videoView.start();
                    OpenOrCloseThread();
                }
            }
        });

        //错误
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                ToastUtil.showToast(VideoActivity.this, "播放出错");
                return false;
            }
        });

        //播放完成
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                progress = 0;
                seekBar.setProgress(0);
                tvCurrentTime.setText("00:00");
                OpenOrCloseThread();
            }
        });
    }


    protected void play() {
        // 开始线程，更新进度条的刻度
        OpenOrCloseThread();
        if (!videoView.isPlaying()) {
            videoView.seekTo(progress);
            videoView.start();
        }
    }

    protected void stop() {
        if (videoView.isPlaying()) {
            videoView.stopPlayback();
            OpenOrCloseThread();
        }
    }

    public void pause() {
        if (videoView.isPlaying()) {
            progress = videoView.getCurrentPosition();
            videoView.pause();
            OpenOrCloseThread();
        }
    }

    @OnClick({R.id.imgv_play, R.id.imgv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgv_play:  //播放
                if (isPause) {
                    isClickPause = false;
                    play();
                } else {
                    isClickPause = true;
                    pause();
                }
                break;
            case R.id.imgv_close: //关闭
                stop();
                onBackPressed();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isOnpauseStatus = true;
        isFirst = false;
        pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }


    @Override
    protected void onResume() {
        super.onResume();
        LoadingDialog.showDialog(mLoadingDialog);
    }

    //开启或关闭线程
    public void OpenOrCloseThread() {
        if (isBeginThread) {
            isPause = true;
            handler.removeCallbacks(runnable);
            isBeginThread = false;
            imgv_play.setImageResource(R.mipmap.icon_video_play);
        } else {
            isPause = false;
            handler.postDelayed(runnable, 0);
            isBeginThread = true;
            imgv_play.setImageResource(R.mipmap.icon_video_pause);
        }
    }

    //时间格式转换
    protected String getTime(long millionSeconds) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millionSeconds);
        return simpleDateFormat.format(c.getTime());
    }

}
